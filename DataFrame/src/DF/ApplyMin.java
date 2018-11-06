package DF;

import DF.Values.Value;

import java.util.HashSet;

public class ApplyMin implements Applyable {


    @Override
    public DataFrame apply(DataFrame df) {

        DataFrame output = new DataFrame(df.lista_nazw, df.zwroc_typy());
        HashSet<Integer> bannedColumn = new HashSet<>(); // pojemnik na nieporównywalne dane np. daty
        int size = df.size();

        if (size > 0) {
            Value[] min = output.zwrocWiersz(0);
            for (int i = 0; i < size; i++) {
                Value[] row = output.zwrocWiersz(i);
                for (int kolumna = 0; kolumna < row.length; kolumna++) {


                    if (bannedColumn.contains(kolumna)) {continue;}

                    try {
                        if (min[kolumna].gte(row[kolumna])) {
                            min[kolumna] = row[kolumna];
                        }

                    } catch (UnsupportedOperationException ignored) { bannedColumn.add(kolumna); }
                }
            }
            output.dodajElement(min);
        }

        if (bannedColumn.size() == output.iloscKolumn()) {
            throw new RuntimeException("nope");
        }
        String[] output_colnames = output.lista_nazw;
        String[] colnames = new String[output.iloscKolumn() - bannedColumn.size()];

        int j = 0;
        for (int i = 0; i < output_colnames.length; i++) {
            if (!bannedColumn.contains(i)) {
                colnames[j++] = output_colnames[i];
            }
        }

        return output.get(colnames, false);
    }
}


