package DF;

import DF.Values.DoubleValue;
import DF.Values.Value;

public class ApplyStd  implements Applyable{


        @Override
        public DataFrame apply(DataFrame df){
            DataFrame output = new DataFrame(df.lista_nazw,df.zwroc_typy());

            int size = df.size();
            Value[] var = df.zwrocWiersz(0);
            Value[] std = df.zwrocWiersz(0);

            //-----wyliczenie średniej--------
            Value[] srednie = df.zwrocWiersz(0);

            if (size>0) {
                for (int i = 0; i < size; i++) {
                    Kolumna kol = df.get(df.lista_nazw[i]);
                    if (NumericValue.class.isAssignableFrom(kol.typ)) {
                        Value suma = srednie[i];
                        for (int j = 1; j < kol.size(); j++) {
                            suma = suma.add(kol.dane.get(j));
                        }
                        srednie[i] = suma.div(new DoubleValue(size));
                    }
                }
            }

            if(size>0){
                for (int i =0; i<srednie.length;i++){
                    Kolumna kol =  df.get(df.lista_nazw[i]);
                    if (NumericValue.class.isAssignableFrom(kol.typ)){
                        Value roznica;
                        Value suma; //???
                        for(int j=0;j<kol.size();j++){
                            Value roznica = kol.dane.get(j).sub(srednie[i]).mul(kol.dane.get(j).sub(srednie[i]));
                            suma = suma.add(roznica);
                        }
                        var[i] = suma.div(new DoubleValue((size)));
                    }
                }
                for (int k=0; k<var.length;k++){
                    std[k] = var[k].pow(new DoubleValue(0.5));
                    output.dodajElement(std);
                }


            }
            return output;
        }
    }

