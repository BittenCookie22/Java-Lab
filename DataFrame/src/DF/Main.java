package DF;

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


//         TEST DLA JEDNEGO KLUCZA
//        DataFrame tmp2 = new DataFrame("groupby.csv", new Class[]{StringValue.class, DateTimeValue.class, DoubleValue.class, DoubleValue.class});
//        DataFrame tmp4 = new DataFrame("tg.csv", new Class[]{StringValue.class,DateTimeValue.class,DoubleValue.class,DoubleValue.class});
//
//        System.out.println(tmp2.toString());
//
//
//        DataFrame.Grupator group = tmp2.groupBy(new String[]{"id"});
//        System.out.println(group.sum());
//        System.out.println(group.max());
//         System.out.println(group.min());
//         System.out.println(group.mean());
//        System.out.println(group.var());
//         System.out.println(group.std());


        // TEST DLA DWÓCH KLUCZY

          //DataFrame tmp3 = new DataFrame("groubymulti.csv", new Class[]{StringValue.class,DateTimeValue.class,DoubleValue.class,DoubleValue.class});

         //DataFrame.Grupator group1 = tmp3.groupBy(new String []{"id","date"});
        // System.out.println(group1.min());
        //System.out.println(group1.max());
        // System.out.println(group1.sum());
        // System.out.println(group1.mean());
        // System.out.println(group1.var());
        // System.out.println(group1.std());



//        DataFrame my = new DataFrame(new String[]{"double","double2","double3"},new Class[]{DoubleValue.class,DoubleValue.class,DoubleValue.class});
//        my.addRecord(new DoubleValue(9.0),new DoubleValue(6.7),new DoubleValue(6.7));
//        my.addRecord(new DoubleValue(10.0),new DoubleValue(45.7),new DoubleValue(6.7));
//        my.addRecord(new DoubleValue(11.0),new DoubleValue(6.0),new DoubleValue(8.0));
//        my.addRecord(new DoubleValue(11.0),new DoubleValue(5.0),new DoubleValue(7.0));
//
//       // System.out.println(my.toString());
//
//        DataFrame.Grupator grupkaTest = my.groupBy(new String[]{"double"});
//        System.out.println(grupkaTest.std());

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
    }




}