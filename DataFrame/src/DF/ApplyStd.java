package DF;

import DF.Values.DoubleValue;
import DF.Values.Value;

public class ApplyStd  implements Applyable{


        @Override
        public DataFrame apply(DataFrame df){
//            //logika odpowiedzialna za pracowanie w funkcji std tylko na tych kolumnach na których można wykonać daną operacje
//            ArrayList<String> colnames = new ArrayList();
//            ArrayList<Class<? extends Value>> types = new ArrayList<>();
//
//            Class<? extends Value> []df_types = df.zwroc_typy();
//            String[] df_names = df.lista_nazw;
//
//            for (int i = 0; i < df_types.length ; i++) {
//                if (NumericValue.class.isAssignableFrom(df_types[i])){
//                    types.add(df_types[i]);
//                    colnames.add(df_names[i]);
//                }
//            }
            //---------------------------------------------------------------

            Applyable a = new ApplyVar();
            DataFrame var = a.apply(df);

            Value[] wariancja = var.zwrocWiersz(0);

            DataFrame output = new DataFrame(var.zwroc_nazwy(), var.zwroc_typy());

            Value[] std = new Value[output.iloscKolumn()];
            int size = var.size();
//            if (size>0) {
//                for (int i = 0; i < size; i++) {
//                    Kolumna kol = df.get(df.lista_nazw[i]);
//                    if (NumericValue.class.isAssignableFrom(kol.typ)) {
//                        Value suma = srednie[i];
//                        for (int j = 1; j < kol.size(); j++) {
//                            suma = suma.add(kol.dane.get(j));
//                        }
//                        srednie[i] = suma.div(new DoubleValue(size));
//                    }
//                }
//            }

            if(size>0){

                for (int i=0; i<var.iloscKolumn();i++){
                    std[i] = wariancja[i].pow(new DoubleValue(0.5));

                }
                output.dodajElement(std);

            }
            return output;
        }
    }

