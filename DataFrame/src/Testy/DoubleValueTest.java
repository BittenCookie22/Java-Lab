package Testy;

import DF.DoubleValue;
import DF.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DoubleValueTest {
    private Value[] values;

    private String[] double_w_stringach;

    private Double[] double_values;

    @BeforeEach  // wykona przed każdym testem
    void ustaw() {
        int count = 20000;

        values = new Value[count];
        double_values = new Double[count];
        double_w_stringach = new String[count];


        for (int i = 0; i < count; i++) {
            double_values[i] =  (Math.random() * count - count / 7);
            values[i] = new DoubleValue(double_values[i]);
            double_w_stringach[i] = Double.toString(double_values[i]);
        }
    }


    @Test
     void getValue() {
        for (int i = 0; i < values.length; i++) {
            assertEquals(double_values[i], values[i].getValue());
        }
    }

    @Test
     void Test_toString() {
        for (int i = 0; i < values.length; i++) {
            assertEquals("DoubleValue{" + "value=" + this.double_values[i] + '}', values[i].toString()) ;
        }
    }

    @Test
     void add() {
        for (int j = 0; j < values.length; j++)
            for (int i = 0; i < values.length; i++) {
                assertEquals(new DoubleValue(((double_values[i]) + (double_values[j]))), values[i].add(values[j]));
            }
    }

    @Test
     void sub() {
        for (int j = 0; j < values.length; j++)
            for (int i = 0; i < values.length; i++) {
                assertEquals(new DoubleValue(((double_values[i]) - (double_values[j]))), values[i].sub(values[j]));
            }
    }

    @Test
     void eq() {
        for (int j = 0; j < values.length; j++)
            for (int i = 0; i < values.length; i++) {
                assertEquals((double_values[i]).equals(double_values[j]), values[i].eq(values[j]));
            }
            assertEquals(Double.NaN==Double.NaN,new DoubleValue(Double.NaN).eq(new DoubleValue(Double.NaN)));
    }

    @Test
     void mul() {
        for (int j = 0; j < values.length; j++)
            for (int i = 0; i < values.length; i++) {
                assertEquals(new DoubleValue(((double_values[i]) * (double_values[j]))), values[i].mul(values[j]));
            }

    }

    @Test
     void div() {
        for (int j = 0; j < values.length; j++)
            for (int i = 0; i < values.length; i++) {
                if (double_values[j] != 0)
                    assertEquals(new DoubleValue(((double_values[i]) / (double_values[j]))), values[i].div(values[j]));
            }
    }

    @Test
     void pow() {

        for (int j = 0; j < values.length; j++)
            for (int i = 0; i < values.length; i++) {
                double d = Math.pow((double_values[i]), (double_values[j]));
                if (!Double.isNaN(d)) {
                    assertEquals(new DoubleValue(d), values[i].pow(values[j]));
                }
            }

    }

    @Test
     void lte() {
        for (int j = 0; j < values.length; j++)
            for (int i = 0; i < values.length; i++) {
                assertEquals((double_values[i]) <= (double_values[j]), values[i].lte(values[j]));
            }
    }

    @Test
     void gte() {
        for (int j = 0; j < values.length; j++)
            for (int i = 0; i < values.length; i++) {
                assertEquals((double_values[i]) >= (double_values[j]), values[i].gte(values[j]));
            }
    }

    @Test
     void neq() {
        for (int j = 0; j < values.length; j++)
            for (int i = 0; i < values.length; i++) {
                assertEquals(!(double_values[i]).equals(double_values[j]), values[i].neq(values[j]));
            }
    }

    @Test
     void equals() {
        for (int j = 0; j < values.length; j++)
            for (int i = 0; i < values.length; i++) {
                assertEquals((double_values[i]).equals(double_values[j]), values[i].equals(values[j]));
            }
    }

    @Test
    void Test_hashCode() {
        for (int i = 0; i < values.length; i++) {
            assertEquals(7*Double.hashCode(double_values[i]), values[i].hashCode());
        }
    }


    @Test
     void create() {
        for (int i = 0; i < values.length; i++) {
            assertEquals(values[i], values[i].create(double_w_stringach[i]));
        }
    }
}
