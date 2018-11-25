package Testy;

import DF.DataFrame;
import DF.Kolumna;
import DF.Values.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class TestDF {

    private DataFrame df;
    private String[] names;
    private Class<? extends Value>[] types;
    private StringValue[] str;
    private IntegerValue[] ints;
    private FloatValue[]  floats;

    @BeforeEach
    void setUp() {
        System.out.println("Test Setup");
        String[] names = {"A","B","C"};
        Class<? extends Value>[] types = new Class[]{StringValue.class, IntegerValue.class, FloatValue.class};

        StringValue[] str = {new StringValue("A"),new StringValue("B"),new StringValue("C"),new StringValue("D")};
        IntegerValue[] ints = {new IntegerValue(15),new IntegerValue(5),new IntegerValue(4),new IntegerValue(5)};
        FloatValue[] floats = {new FloatValue(17.0f),new FloatValue(1.0f),new FloatValue(7.0f),new FloatValue(7.5f)};
        this.names=names;
        this.types=types;

        this.str=str;
        this.ints=ints;
        this.floats=floats;

        df = create(names,types,str,ints,floats);
    }



    @Test
    void testGetKolumn() {
        Kolumna kol = df.get(names[0]);
        for(int i =0;i<kol.size();i++){
            assertEquals(str[i],kol.zwrocObiekt(i));
        }
        kol = df.get(names[1]);
        for(int i =0;i<kol.size();i++){
            assertEquals(ints[i],kol.zwrocObiekt(i));
        }
        kol =df.get(names[2]);
        for(int i =0;i<kol.size();i++){
            assertEquals(floats[i],kol.zwrocObiekt(i));
        }
    }

    @Test
    void testIloc() {
        for(int i=0;i<str.length;i++) {
            DataFrame row1 = df.iloc(i);
            assertEquals(str[i],row1.get(names[0]).zwrocObiekt(0));
            assertEquals(ints[i],row1.get(names[1]).zwrocObiekt(0));
            assertEquals(floats[i],row1.get(names[2]).zwrocObiekt(0));
        }

        for(int x=0;x<str.length;x++)
            for(
                    int y=x;y<str.length;y++){
                DataFrame rows23 = df.iloc(x,y);
                for(int i=x, j=0;i<y;i++,j++){
                    assertEquals(str[i],rows23.get(names[0]).zwrocObiekt(j));
                    assertEquals(ints[i],rows23.get(names[1]).zwrocObiekt(j));
                    assertEquals(floats[i],rows23.get(names[2]).zwrocObiekt(j));
                }
            }
    }

    @Test
    void testToString() {
        System.out.println("To String");
        assertEquals("|A:StringValue|B:IntegerValue|C:FloatValue|\n" +
                "|A|15|17.0|\n" +
                "|B|5|1.0|\n" +
                "|C|4|7.0|\n" +
                "|D|5|7.5|\n",
                df.toString(),"ToString()");

        assertArrayEquals(names,df.zwroc_nazwy(),"ToString(1)");
        assertArrayEquals(types,df.zwroc_typy(),"ToString(2)");

    }

    @Test
    void testShallowCopy() {
        DataFrame a1 = df.get(new String[]{names[0],names[1]},false);
        DataFrame b1 = df.get(new String[]{names[0],names[1]},false);
        for(int i=0;i<str.length;i++)
        {
            assertSame(a1.get(names[0]).zwrocObiekt(i),b1.get(names[0]).zwrocObiekt(i));
            assertSame(a1.get(names[1]).zwrocObiekt(i),b1.get(names[1]).zwrocObiekt(i));
        }
    }



    @Test
    void testReadingFromFile() throws IOException{
        String[] cols={"id","do","str"};
        Class<? extends Value>[] types = new Class[]{IntegerValue.class, DoubleValue.class, StringValue.class};
        IntegerValue[] colI = {new IntegerValue(0),new IntegerValue(1),new IntegerValue(2),new IntegerValue(3),new IntegerValue(4),new IntegerValue(5)};
        DoubleValue[] colII = {new DoubleValue(0.5),new DoubleValue(0.4),new DoubleValue(0.3),new DoubleValue(0.2),new DoubleValue(0.1),new DoubleValue(0.0)};
        StringValue[] colIII = {new StringValue("A"),new StringValue("B"),new StringValue("C"),new StringValue("D"),new StringValue("E"),new StringValue("F")};

        DataFrame dfF = new DataFrame("test.csv",types);

        for(int i=0;i<cols.length;i++){
            assertEquals(colI[i],dfF.get(cols[0]).zwrocObiekt(i));
            assertEquals(colII[i],dfF.get(cols[1]).zwrocObiekt(i));
            assertEquals(colIII[i],dfF.get(cols[2]).zwrocObiekt(i));
        }


        DataFrame dfH = new DataFrame("test-noH.csv",types,cols);

        for(int i=0;i<cols.length;i++){
            assertEquals(colI[i],dfH.get(cols[0]).zwrocObiekt(i));
            assertEquals(colII[i],dfH.get(cols[1]).zwrocObiekt(i));
            assertEquals(colIII[i],dfH.get(cols[2]).zwrocObiekt(i));
        }



    }


    private DataFrame create(String[] names,  Class<? extends Value>[] types, Value[]... arrays){

        DataFrame df = new DataFrame(names,types);
        Value[] v =new Value[types.length];
        for(int i=0;i<arrays[0].length;i++) {
            for (int j = 0; j < types.length; j++)
                v[j] = arrays[j][i];
            df.dodajElement(v);
        }
        return df;
    }
}
