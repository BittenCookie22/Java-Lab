package DF;

import DF.Values.DateTimeValue;
import DF.Values.DoubleValue;
import DF.Values.StringValue;

import java.io.IOException;

public class Main {
    public static void main (String[] args) throws IOException {
//        DataFrame tmp = new DataFrame(new String[]{"z","g","r"}, new Class[] {IntegerValue.class,IntegerValue.class,IntegerValue.class} );
//        tmp.addRecord(new IntegerValue(9),new IntegerValue(23),new IntegerValue(33));
//        tmp.addRecord(new IntegerValue(11),new IntegerValue(44),new IntegerValue(55));
//
//       System.out.println(tmp.ilosc_wierszy);
//        System.out.println(tmp.get("g"));
//        System.out.println(tmp.toString());


        // TEST DLA JEDNEGO KLUCZA

     //   DataFrame tmp2 = new DataFrame("groupby.csv", new Class[]{StringValue.class,DateTimeValue.class,DoubleValue.class,DoubleValue.class});
      //  DataFrame tmp4 = new DataFrame("tg.csv", new Class[]{StringValue.class,DateTimeValue.class,DoubleValue.class,DoubleValue.class});

//        System.out.println(tmp2.toString());

      //  DataFrame.Grupator group = tmp2.groupBy(new String []{"id"});
      // System.out.println(group.sum());
//      System.out.println(group.max());
//       System.out.println(group.min());
       // System.out.println(group.mean());
//       System.out.println(group.var());
//        System.out.println(group.std());



        // TEST DLA DWÓCH KLUCZY

        DataFrame tmp3 = new DataFrame("groubymulti.csv", new Class[]{StringValue.class,DateTimeValue.class,DoubleValue.class,DoubleValue.class});

        DataFrame.Grupator group1 = tmp3.groupBy(new String []{"id","date"});
       // System.out.println(group1.min());
        //System.out.println(group1.max());
        // System.out.println(group1.sum());
        // System.out.println(group1.mean());
       // System.out.println(group1.var());
        // System.out.println(group1.std());

    }



}