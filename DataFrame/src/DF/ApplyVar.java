package DF;

import DF.Values.DoubleValue;
import DF.Values.Value;

public class ApplyVar  implements Applyable{

    @Override
    public DataFrame apply(DataFrame df){
//        //logika odpowiedzialna za pracowanie w funkcji var tylko na tych kolumnach na których można wykonać daną operacje
//        ArrayList<String> colnames = new ArrayList();
//        ArrayList<Class<? extends Value>> types = new ArrayList<>();
//
//        Class<? extends Value> []df_types = df.zwroc_typy();
//        String[] df_names = df.lista_nazw;
//
//        for (int i = 0; i < df_types.length ; i++) {
//            if (NumericValue.class.isAssignableFrom(df_types[i])){
//                types.add(df_types[i]);
//                colnames.add(df_names[i]);
//            }
//        }

        Applyable a = new ApplyMean();
        DataFrame mean = a.apply(df);

        Value[] srednie = mean.zwrocWiersz(0);

        DataFrame output = new DataFrame(mean.zwroc_nazwy(), mean.zwroc_typy());

        Value[] var = new Value[output.iloscKolumn()];
        int size = mean.size();

        //-----wyliczenie średniej--------


//        if (size>0) {
//            for (int i = 0; i < size; i++) {
//                Kolumna kol = df.get(df.lista_nazw[i]);
//                if (NumericValue.class.isAssignableFrom(kol.typ)) {
//                    Value suma = srednie[i];
//                    for (int j = 1; j < kol.size(); j++) {
//                        suma = suma.add(kol.dane.get(j));
//                    }
//                    srednie[i] = suma.div(new DoubleValue(size));
//                }
//            }
//        }

        if(size>0){
            String[] lista_nazw = mean.zwroc_nazwy();
            for (int i =0; i<mean.iloscKolumn();i++){
                Kolumna kol =  df.get(lista_nazw[i]);
                    Value roznica;
                    Value suma= new DoubleValue(0.0); //???
                    for(int j=0;j<kol.size();j++){
                        roznica = kol.zwrocObiekt(j).sub(srednie[i]).mul(kol.zwrocObiekt(j).sub(srednie[i]));
                        suma = suma.add(roznica);
                    }
                    var[i] = suma.div(new DoubleValue((df.size())));

            }

            output.dodajElement(var);
        }
        return output;
    }


}
