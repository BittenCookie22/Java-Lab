package DF;

import DF.Exceptions.*;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main (String[] args) throws IOException, ValueParseException, ZeroLengthException, NoSenseInGroupingByAllColumnsException, IncoherentTypeException, DifferentAmountOfColumns, SQLException, DivideByZeroException, DifferentSizeOfColumnsExcepiton {
//      DataFrame tmp = new DataFrame(new String[]{"z","g","r"}, new Class[] {IntegerValue.class,IntegerValue.class,IntegerValue.class} );
//       tmp.dodajElement(new IntegerValue(9),new IntegerValue(23),new IntegerValue(33));
//        System.out.println(tmp.get("z").getType());
////        tmp.addRecord(new IntegerValue(11),new IntegerValue(44),new IntegerValue(55));
////
////       System.out.println(tmp.ilosc_wierszy);
////        System.out.println(tmp.get("g"));
////        System.out.println(tmp.toString());

//
//       //  TEST DLA JEDNEGO KLUCZA
//        try {
//            DataFrame tmp2 = new DataFrame("C:\\Users\\Joanna\\Desktop\\test.csv", new Class[]{ StringValue.class, IntegerValue.class, IntegerValue.class});
//           // System.out.println(tmp2.toString());
//            DataFrame.Grupator group = tmp2.groupBy(new String[]{"id"});
//            System.out.println(group.std());
//        } catch (IncoherentTypeException e) {
//            e.printStackTrace();
//        }

//      //  DataFrame tmp4 = new DataFrame("tg.csv", new Class[]{StringValue.class,DateTimeValue.class,DoubleValue.class,DoubleValue.class});
//
//       // System.out.println(tmp2.toString());
//
//
      //  DataFrame.Grupator group = tmp2.groupBy(new String[]{"id"});
//       System.out.println(group.sum());
//        System.out.println(group.max());
//         System.out.println(group.min());
//         System.out.println(group.mean());
//        System.out.println(group.var());
//         System.out.println(group.std());


        // TEST DLA DWÓCH KLUCZY

       // DataFrame tmp3 = new DataFrame("groubymulti.csv", new Class[]{StringValue.class,DateTimeValue.class,DoubleValue.class,DoubleValue.class});

      //   DataFrame.Grupator group1 = tmp3.groupBy(new String []{"id","date"});
        // System.out.println(group1.min());
        //System.out.println(group1.max());
        // System.out.println(group1.sum());
        // System.out.println(group1.mean());
         //System.out.println(group1.var());
        // System.out.println(group1.std());



//       DataFrame my = new DataFrame(new String[]{"double","double2","double3"},new Class[]{DoubleValue.class,DoubleValue.class,DoubleValue.class});
//        my.addRecord(new DoubleValue(9.0),new DoubleValue(6.7),new DoubleValue(6.7));
//        my.addRecord(new DoubleValue(10.0),new DoubleValue(45.7),new DoubleValue(6.7));
//        my.addRecord(new DoubleValue(11.0),new DoubleValue(6.0),new DoubleValue(8.0));
//        my.addRecord(new DoubleValue(11.0),new DoubleValue(5.0),new DoubleValue(7.0));
////
////System.out.println(my.toString());
//////
//       DataFrame.Grupator grupkaTest = my.groupBy(new String[]{"double"});
//       System.out.println(grupkaTest.max());

//        DataFrame my1 = new DataFrame(new String[]{"float","float2","float3"},new Class[]{FloatValue.class,FloatValue.class,FloatValue.class});
//        my1.addRecord(new FloatValue(9.0f),new FloatValue(67.0f),new FloatValue(67.0f));
//        my1.addRecord(new FloatValue(10.0f),new FloatValue(457.0f),new FloatValue(67.0f));
//        my1.addRecord(new FloatValue(11.0f),new FloatValue(70.0f),new FloatValue(2.0f));
//        my1.addRecord(new FloatValue(11.0f),new FloatValue(7.0f),new FloatValue(4.0f));
//
//         System.out.println(my1.toString());
//
//        DataFrame.Grupator grupkaTest = my1.groupBy(new String[]{"float"});
//        System.out.println(grupkaTest.var());

//        DataFrame my2 = new DataFrame(new String[]{"string","string2","string3"},new Class[]{StringValue.class,StringValue.class,StringValue.class});
//        my2.addRecord(new StringValue("a"),new StringValue("aa"),new StringValue("aaa"));
//        my2.addRecord(new StringValue("b"),new StringValue("bb"),new StringValue("bbb"));
//        my2.addRecord(new StringValue("c"),new StringValue("cc"),new StringValue("ccc"));
//        my2.addRecord(new StringValue("c"),new StringValue("dd"),new StringValue("ddd"));

        // System.out.println(my2.toString());

     //   DataFrame.Grupator grupkaTest = my2.groupBy(new String[]{"string"});
       //  System.out.println(grupkaTest.sum());
        //System.out.println(grupkaTest.min());
       // System.out.println(grupkaTest.var());



//        DataFrame my1 = new DataFrame(new String[]{"data","data2","data3"},new Class[]{DateTimeValue.class,DateTimeValue.class,DateTimeValue.class});
//        my1.addRecord(new DateTimeValue(LocalDateTime.parse("2007-12-03T10:15:30")),new DateTimeValue(LocalDateTime.parse("2007-12-04T10:15:30")),new DateTimeValue(LocalDateTime.parse("2007-12-03T10:15:31")));
//        my1.addRecord(new DateTimeValue(LocalDateTime.parse("2007-12-13T10:15:30")),new DateTimeValue(LocalDateTime.parse("2007-12-04T10:15:30")),new DateTimeValue(LocalDateTime.parse("2007-12-03T10:15:31")));
//        my1.addRecord(new DateTimeValue(LocalDateTime.parse("2007-12-13T10:15:30")),new DateTimeValue(LocalDateTime.parse("2007-12-04T10:15:40")),new DateTimeValue(LocalDateTime.parse("2007-11-03T10:15:31")));
//
//
//       // System.out.println(my1.toString());
//
//        DataFrame.Grupator grupkaTest = my1.groupBy(new String[]{"data"});
//        System.out.println(grupkaTest.std());


//        DataFrame my = new DataFrame(new String[]{"integer","integer2","integer3"},new Class[]{IntegerValue.class,IntegerValue.class,IntegerValue.class});
//        my.addRecord(new IntegerValue(9),new IntegerValue(6),new IntegerValue(6));
//        my.addRecord(new IntegerValue(10),new IntegerValue(4),new IntegerValue(26));
//        my.addRecord(new IntegerValue(19),new IntegerValue(6),new IntegerValue(6));
//        my.addRecord(new IntegerValue(19),new IntegerValue(2),new IntegerValue(8));
//
//
//        // System.out.println(my.toString());
//
//        DataFrame.Grupator grupkaTest = my.groupBy(new String[]{"integer"});
//        System.out.println(grupkaTest.std());



////

//        Kolumna test1 = new Kolumna ("nazwa",StringValue.class);
//
//
//        try {
//            test1.dodaj(new StringValue("1"));
//            test1.dodaj(new StringValue("2"));
//            test1.dodaj(new StringValue("3"));
//            test1.dodaj(new StringValue("4"));
//        } catch (IncoherentTypeException e) {
//            System.out.println("problemik");
//        }
//
//
//        Kolumna test3 = new Kolumna ("nazwaa",IntegerValue.class);
//
//        test3.dodaj(new IntegerValue(1));
//        test3.dodaj(new IntegerValue(2));
//        test3.dodaj(new IntegerValue(3));
//        test3.dodaj(new IntegerValue(4));
//
//        Kolumna test2 = new Kolumna ("nazwaaaaa",IntegerValue.class);
//
//        test2.dodaj(new IntegerValue(1));
//        test2.dodaj(new IntegerValue(2));
//        test2.dodaj(new IntegerValue(3));
//        test2.dodaj(new IntegerValue(4));
//        Kolumna test22= test2.doMathOperationWithOtherColumn(test3,"mul");
//        System.out.println(test22.toString());
////
//////
////       // Kolumna test11= test1.doMathByUser(new StringValue("s"));
////
////       Kolumna testA = test1.addKol(test2);
//     //   System.out.println(testA.toString());
//       // Kolumna test11= test1.sub(new IntegerValue(10));
//        //Kolumna test11= test1.mul(new IntegerValue(10));
//        //Kolumna test11= test1.div(new IntegerValue(10));
//       // Kolumna test11= test1.pow(new IntegerValue(3));
//
//        //System.out.println(test11.toString());
//        Kolumna test2 = new Kolumna ("id",FloatValue.class);
//
//        try {
//            test2.dodaj(new FloatValue(1.0f));
//            test2.dodaj(new FloatValue(2.0f));
//            test2.dodaj(new FloatValue(3.0f));
//            test2.dodaj(new FloatValue(4.0f));
//        } catch (IncoherentTypeException e) {
//            e.printStackTrace();
//        }
//
//
//        Kolumna output = null;
//        try {
//            output = test1.powKol(test2);
//        } catch (IncoherentTypeException | DifferentSizeOfColumnsExcepiton e) {
//            System.out.println("problemik");
//        }
//        System.out.println(output.toString());

        //---------- testy dla Data Frame DB------------

     //   DataFrameDB tmp = new  DataFrameDB.DataFrameBDBuilder("jdbc:mysql://mysql.agh.edu.pl/joannabi",
     //           "joannabi","87cz4zfRV047S5Wr","TABLE3").build();

//        DataFrameDB tmp = new  DataFrameDB.DataFrameBDBuilder("jdbc:mysql://mysql.agh.edu.pl/joannabi",
//                "joannabi","87cz4zfRV047S5Wr","books").build();
//
//        System.out.println(Arrays.toString(tmp.zwrocWiersz(1)));
//        System.out.println(tmp.size());
//
//
//        System.out.println(tmp.get("title"));
//        DataFrame test = tmp.fromDataFrameDFtoDataFrame();
//        System.out.println(test.toString());
      //  GroupBy grp=tmp.groupBy("id");
       // System.out.println(grp.apply(new ApplyMax()));
       // tmp.dodajElement(new StringValue("0101010101"),new StringValue("test tytul"),new StringValue("test autor"),new IntegerValue(2000));
     //   System.out.println(tmp.toString());


      // -----porównanie czasów dla max -------

//        System.out.println("DataFrame DB:");
//        long start = System.currentTimeMillis();
//        grp.apply(new ApplyMax());
//        long stop = System.currentTimeMillis();
//        System.out.println(stop-start);
//
//        DataFrame tmp2 = new DataFrame("TABLE3.csv", new Class[]{ StringValue.class,DateTimeValue.class,DoubleValue.class,DoubleValue.class});
//        GroupBy group = tmp2.groupBy(new String[]{"id"});
//        long start2 = System.currentTimeMillis();
//        group.max();
//        long stop2 = System.currentTimeMillis();
//
//        System.out.println("DataFrame :");
//        System.out.println(stop2-start2);

        


    }






}