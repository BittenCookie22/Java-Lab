package DF;

import DF.Exceptions.DifferentAmountOfColumns;
import DF.Exceptions.IncoherentTypeException;
import DF.Exceptions.ZeroLengthException;
import DF.Values.Value;

import java.util.*;
import java.util.concurrent.*;

public class ThreadDataFrame extends DataFrame {

    // ExecutorService automatically provides a pool of threads and API for assigning tasks to it.
    private final ExecutorService executorService;

    public ThreadDataFrame(ExecutorService executorService, DataFrame df) {
        this(executorService, df.zwroc_nazwy(), df.zwroc_typy());

        for (int i = 0; i < df.size(); i++) {
            try {
                dodajElement(df.zwrocWiersz(i));
            } catch (DifferentAmountOfColumns | IncoherentTypeException e) {
                e.printStackTrace();
            }
        }

    }

    public ThreadDataFrame(ExecutorService executorService, String[] nazwyKolumn, Class<? extends Value>[] typyKolumn) {
        super(nazwyKolumn, typyKolumn);
        this.executorService = executorService;
    }


    //Uruchamia kolekcje callable zwracających kolumny i tworzy z nich DF
//    The invokeAll() method invokes all of the Callable objects you pass to it in the collection passed as parameter.
//    The invokeAll() returns a list of Future objects via which you can obtain the results of the executions of each Callable.
    public static DataFrame convertCallableToDataFrame(List<Callable<Kolumna>> callableKolumny, ExecutorService executorService) {
        try {

//            the Future class represents a future result of an asynchronous computation –
//            a result that will eventually appear in the Future after the processing is complete.
           // This enables us to execute some other process while we are waiting for the task encapsulated in Future to complete.
            List<Future<Kolumna>> isComplete = executorService.invokeAll(callableKolumny);

            //kopiuje kolumny do arraya
            Kolumna[] targetColumns = new Kolumna[isComplete.size()];
            for (int i = 0; i < isComplete.size(); i++) {
                targetColumns[i] = isComplete.get(i).get();
            }

            return new DataFrame(targetColumns);

        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

// get'a nie opłaca się robić równolegle dla parametrów przekazywanych przez referencje
    // nie opłaca się też dla pojedynczej kolumny
@Override
public DataFrame get(String[] cols, boolean copy) {
    if (!copy || cols.length < 2)
        return super.get(cols, copy);


    List<Callable<Kolumna>> callableKolumny = new ArrayList<>(iloscKolumn());
    for (int i = 0; i < cols.length; i++) {
        int Fi = i;
        callableKolumny.add(new Callable<Kolumna>() {
            @Override
            public Kolumna call() {
                return get(cols[Fi]).copy();
            }
        });
    }

    return convertCallableToDataFrame(callableKolumny, executorService);
}


    @Override
    public DataFrame iloc(int from, int to) {
        if (from - to < 500) return super.iloc(from, to);


        //zrównoleglone kopiowanie żądanych kolumn
        List<Callable<Kolumna>> callables = new ArrayList<>(iloscKolumn());
        for (int i = 0; i < iloscKolumn(); i++) {
            int Fi = i;

            callables.add(new Callable<Kolumna>() {
                @Override
                public Kolumna call() throws IncoherentTypeException {

                    Kolumna c = new Kolumna(kolumny[Fi].getNazwa(), kolumny[Fi].getType());
                    for (int j = from; j < to; j++) {
                        c.dodaj(kolumny[Fi].zwrocObiekt(j));
                    }

                    return c;
                }
            });
        }

        return convertCallableToDataFrame(callables, executorService);
    }

    @Override
    public GroupBy groupBy(String... colname) {

        Map<ValueGroup, DataFrame> storage = new ConcurrentHashMap<>();
        DataFrame keys = null;

        keys = get(colname, false);

        DataFrame finalKeys = keys;

        //pogrupowanie wierszy do grup - od begin -end -
        // jeśli jest to za dużo wierszy dieli te  pracę na 2 podzadania od begin do mid i od mid do end
        class GroupMaker implements Callable<Object> {
            final int start;
            final int end;

            GroupMaker(int begin, int end) {
                this.start = begin;
                this.end = end;
            }

            @Override
            public Object call() throws InterruptedException, DifferentAmountOfColumns, IncoherentTypeException {
                if (start - end > 25_000) {
                    int mid = (start + end) / 2;
                    executorService.invokeAll(Arrays.asList(new GroupMaker(start, mid), new GroupMaker(mid, end)));
                    return null;
                } else {
                    for (int i = start; i < end; i++) {

                        ValueGroup key =  new ValueGroup(finalKeys.zwrocWiersz(i));
                        Value[] row = zwrocWiersz(i);

//                        The computeIfAbsent(Key, Function) method of HashMap class
//                        is used to compute value for a given key using the given mapping function,
//                        if key is not already associated with a value (or is mapped to null)
//                        and enter that computed value in Hashmap else null.
                        DataFrame group = storage.computeIfAbsent(key, k -> new SparseDataFrame(zwroc_nazwy(), zwroc_typy(), row));

                        synchronized (group) {
                            group.dodajElement(row);
                        }

                    }
                    return null;
                }
            }
        }

        try {
            int size = size();
            int wokrSize = 25_000;

            List<GroupMaker> workers = new ArrayList<>(size / wokrSize);
            for (int i = 0; i < size; i += wokrSize)
                workers.add(new GroupMaker(i, Math.min(i + wokrSize, size)));
            executorService.invokeAll(workers);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new GrupatorThreaded(storage, colname, keys.zwroc_typy());

    }

    class GrupatorThreaded implements GroupBy {

        Map<ValueGroup, DataFrame> groups;
        String[] datanames;

        String[] keynames;
        Class<? extends Value>[] keyTypes;

        GrupatorThreaded(Map<ValueGroup, DataFrame> groups, String[] keynames, Class<? extends Value>[] keyTypes) {
            this.groups = groups;
            this.keynames = keynames;
            this.keyTypes = keyTypes;

            Set<String> datanames = new HashSet<String>(Arrays.asList(zwroc_nazwy()));
            datanames.removeAll(Arrays.asList(keynames));

            this.datanames =datanames.toArray(new String[0]);


        }



        @Override
        public DataFrame apply(Applyable apply) {
            List<Callable<DataFrame>> groupCalculator = new ArrayList<>(groups.size());
            for (ValueGroup key : groups.keySet()) {
                groupCalculator.add(new Callable<DataFrame>() {

                    @Override
                    public DataFrame call() throws IncoherentTypeException, ZeroLengthException {

                        DataFrame group = groups.get(key);
                        DataFrame cutDown = group.get(datanames, false);

                        return apply.apply(cutDown);


                    }
                });
            }


            List<Future<DataFrame>> calculated = null;
            try {
                calculated = executorService.invokeAll(groupCalculator);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            SortedSet<ValueGroup> k = new TreeSet<>(groups.keySet());
            Iterator<ValueGroup> keys = k.iterator();
            DataFrame output = null;

            for (Future<DataFrame> f : calculated) {
                ValueGroup key = keys.next();
                DataFrame group = null;
                try {
                    group = f.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                if (output == null)
                    output = GroupBy.getOutputDataFrame(keyTypes, keynames, group.zwroc_typy(), group.zwroc_nazwy());

                try {
                    GroupBy.addGroup(output, key.getId(), group);
                } catch (DifferentAmountOfColumns differentAmountOfColumns) {
                    differentAmountOfColumns.printStackTrace();
                } catch (IncoherentTypeException e) {
                    e.printStackTrace();
                }
            }
            return output;


        }

    }

}