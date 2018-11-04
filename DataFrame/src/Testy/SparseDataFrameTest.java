package Testy;

import DF.*;
import DF.Values.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertSame;

class TestSDF {

    private static SparseDataFrame df;
    private static String[] names;
    private static Class<? extends Value>[] types;
    private static StringValue[] str;
    private static IntegerValue[] ints;
    private static FloatValue[]  floats;
    private static Value[]  hidden;


    @BeforeEach
    void setUp() {
        System.out.println("Test Setup");
        String[] names1 = {"A","B","C"};
        Class<? extends Value>[] types1 = new Class[]{StringValue.class, IntegerValue.class, FloatValue.class};

        StringValue[] str1 =   {new StringValue("A"),   new StringValue("B"),  new StringValue("C"),  new StringValue("D"),  new StringValue("A"),  new StringValue("A"),  new StringValue("A"),  new StringValue("A"),  new StringValue("A"),  new StringValue("A"),  new StringValue("A"),  new StringValue("A"),  new StringValue("A"),  new StringValue("A"),  new StringValue("D")};
        IntegerValue[] ints1 = {new IntegerValue(15),new IntegerValue(5),new IntegerValue(4),new IntegerValue(5),new IntegerValue(0),new IntegerValue(0),new IntegerValue(0),new IntegerValue(0),new IntegerValue(0),new IntegerValue(0),new IntegerValue(0),new IntegerValue(0),new IntegerValue(0),new IntegerValue(0),new IntegerValue(0)};
        FloatValue[] floats1= {new FloatValue(17.0f),new FloatValue(1.0f),new FloatValue(7.0f),new FloatValue(7.5f),new FloatValue(1.0f),new FloatValue(17.0f),new FloatValue(1.0f),new FloatValue(7.0f),new FloatValue(7.5f),new FloatValue(1.0f),new FloatValue(17.0f),new FloatValue(1.0f),new FloatValue(7.0f),new FloatValue(7.5f),new FloatValue(1.0f)};
        Value[] hidden1={new StringValue("A"),new IntegerValue(0),new FloatValue(0.0f)};
        names=names1;
        types=types1;

        str=str1;
        ints=ints1;
        floats=floats1;
        hidden=hidden1;
        df = create(names,types,hidden,str,ints,floats);
    }



    @Test
    void testGetKolumn() {
        SparseDataFrame.SparseKolumna kol = df.get(names[0]);
        for(int i =0;i<kol.size();i++){
            Assertions.assertEquals(str[i],kol.zwrocObiekt(i));
        }
        kol = df.get(names[1]);
        for(int i =0;i<kol.size();i++){
            Assertions.assertEquals(ints[i],kol.zwrocObiekt(i));
        }
        kol =df.get(names[2]);
        for(int i =0;i<kol.size();i++){
            Assertions.assertEquals(floats[i],kol.zwrocObiekt(i));
        }
    }

    @SuppressWarnings("SuspiciousNameCombination")
    @Test
    void testIloc() {
        for(int i=0;i<str.length;i++) {
            SparseDataFrame row1 = df.iloc(i);
            Assertions.assertEquals(str[i],row1.get(names[0]).zwrocObiekt(0));
            Assertions.assertEquals(ints[i],row1.get(names[1]).zwrocObiekt(0));
            Assertions.assertEquals(floats[i],row1.get(names[2]).zwrocObiekt(0));
        }

        for(int x=0;x<str.length;x++)
            for(//noinspection SuspiciousNameCombination
                    int y=x;y<str.length;y++){
                SparseDataFrame rows23 = df.iloc(x,y);
                for(int i=x, j=0;i<y;i++,j++){
                    Assertions.assertEquals(str[i],rows23.get(names[0]).zwrocObiekt(j));
                    Assertions.assertEquals(ints[i],rows23.get(names[1]).zwrocObiekt(j));
                    Assertions.assertEquals(floats[i],rows23.get(names[2]).zwrocObiekt(j));
                }
            }
    }

    @Test
    void testShallowCopy() {
        SparseDataFrame a1 = df.get(new String[]{names[0],names[1]},false);
        SparseDataFrame b1 = df.get(new String[]{names[0],names[1]},false);
        for(int i=0;i<str.length;i++)
        {
            assertSame(a1.get(names[0]).zwrocObiekt(i),b1.get(names[0]).zwrocObiekt(i));
            assertSame(a1.get(names[1]).zwrocObiekt(i),b1.get(names[1]).zwrocObiekt(i));
        }
    }

//    @Test
//    void testDeepCopy(){
//        SparseDataFrame a2 = df.get(new String[]{names[0],names[1]},true);
//        SparseDataFrame b2 = df.get(new String[]{names[0],names[1]},true);
//        assertNotSame(a2,b2);
//        for(int i=0;i<str.length;i++)
//        {
//            System.out.println(names[0]+i);
//            System.out.println(names[1]+i);
//            if(!a2.get(names[0]).zwrocObiekt(i).equals(hidden[0]))
//                assertNotSame(a2.get(names[0]).zwrocObiekt(i),b2.get(names[0]).zwrocObiekt(i),names[0]+i );
//            if(!a2.get(names[1]).zwrocObiekt(i).equals(hidden[1]))
//                assertNotSame(a2.get(names[1]).zwrocObiekt(i),b2.get(names[1]).zwrocObiekt(i),names[1]+i );
//        }
//
//    }

    @Test
    void testLoad() throws IOException{
        Value[] hid={new IntegerValue(0),new DoubleValue(0.5),new StringValue("A")};
        String[] cols={"id","do","str"};
        Class<? extends Value>[] types = new Class[]{IntegerValue.class, DoubleValue.class, StringValue.class};
        IntegerValue[] colI = {new IntegerValue(0),new IntegerValue(1),new IntegerValue(2),new IntegerValue(3),new IntegerValue(4),new IntegerValue(5)};
        DoubleValue[] colII = {new DoubleValue(0.5),new DoubleValue(0.4),new DoubleValue(0.3),new DoubleValue(0.2),new DoubleValue(0.1),new DoubleValue(0.0)};
        StringValue[] colIII = {new StringValue("A"),new StringValue("B"),new StringValue("C"),new StringValue("D"),new StringValue("E"),new StringValue("F")};

        SparseDataFrame dfF = new SparseDataFrame("test.csv",types,hid);

        for(int i=0;i<cols.length;i++){
            Assertions.assertEquals(colI[i],dfF.get(cols[0]).zwrocObiekt(i));
            Assertions.assertEquals(colII[i],dfF.get(cols[1]).zwrocObiekt(i));
            Assertions.assertEquals(colIII[i],dfF.get(cols[2]).zwrocObiekt(i));
        }


        SparseDataFrame dfH = new SparseDataFrame("test-noH.csv",types,cols,hid);

        for(int i=0;i<cols.length;i++){
            Assertions.assertEquals(colI[i],dfH.get(cols[0]).zwrocObiekt(i));
            Assertions.assertEquals(colII[i],dfH.get(cols[1]).zwrocObiekt(i));
            Assertions.assertEquals(colIII[i],dfH.get(cols[2]).zwrocObiekt(i));
        }



    }


    private static SparseDataFrame create(String[] names, Class<? extends Value>[] types, Value[] hidden, Value[]... arrays){

        SparseDataFrame df = new SparseDataFrame(names,types,hidden);
        Value[] v =new Value[types.length];
        for(int i=0;i<arrays[0].length;i++) {
            for (int j = 0; j < types.length; j++)
                v[j] = arrays[j][i];
            df.addRecord(v);
        }
        return df;
    }
}