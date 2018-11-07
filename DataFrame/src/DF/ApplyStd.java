package DF;

import DF.Values.DoubleValue;
import DF.Values.Value;

public class ApplyStd  implements Applyable{


        @Override
        public DataFrame apply(DataFrame df){


            Applyable a = new ApplyVar();
            DataFrame var = a.apply(df);

            Value[] wariancja = var.zwrocWiersz(0);

            DataFrame output = new DataFrame(var.zwroc_nazwy(), var.zwroc_typy());

            Value[] std = new Value[output.iloscKolumn()];
            int size = var.size();

            if(size>0){

                for (int i=0; i<var.iloscKolumn();i++){
                    std[i] = wariancja[i].pow(new DoubleValue(0.5));

                }
                output.dodajElement(std);

            }
            return output;
        }
    }

