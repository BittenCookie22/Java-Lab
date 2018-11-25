package Testy;

import DF.Values.IntegerValue;
import DF.Values.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IntegerValueTest {
    private Value[] values;

    private String[] inty_w_stringach;

    private Integer[] int_values;

    @BeforeEach  // wykona przed każdym testem
    void ustaw() {
        int count = 20000;

        values = new Value[count];
        int_values = new Integer[count];
        inty_w_stringach = new String[count];

        for (int i = 0; i < count; i++) {
            int_values[i] = (int) (Math.random() * count - count / 2);
            values[i] = new IntegerValue(int_values[i]);
            inty_w_stringach [i] = Integer.toString(int_values[i]);
        }
    }

    @Test
    void Test_toString() {
        for (int i = 0; i < values.length; i++) {
            assertEquals(this.int_values[i].toString(), values[i].toString());
        }

    }

    @Test
    void Test_equals() {
        for (int j = 0; j < values.length; j++)
            for (int i = 0; i < values.length; i++) {
                assertEquals((int_values[i]).equals(int_values[j]), values[i].equals(values[j]));
            }
    }

    @Test
    void Test_hashCode() {
        for (int i = 0; i < values.length; i++) {
            assertEquals(Integer.hashCode(int_values[i]), values[i].hashCode());
        }
    }

    @Test
    void Test_getValue() {
        for (int i = 0; i < values.length; i++) {
            assertEquals(int_values[i], values[i].getValue());
        }
    }



    @Test
    void Test_neq() {
        for (int j = 0; j < values.length; j++)
            for (int i = 0; i < values.length; i++) {
                assertEquals(!(int_values[i]).equals(int_values[j]), values[i].neq(values[j]));
            }
    }

    @Test
    void Test_eq() {
        for (int j = 0; j < values.length; j++)
            for (int i = 0; i < values.length; i++) {
                assertEquals((int_values[i]).equals(int_values[j]), values[i].eq(values[j]));
            }
    }

    @Test
    void Test_lte() {
        for (int j = 0; j < values.length; j++)
            for (int i = 0; i < values.length; i++) {
                assertEquals((int_values[i]) <= (int_values[j]), values[i].lte(values[j]));
            }
    }

    @Test
    void Test_gte() {
        for (int j = 0; j < values.length; j++)
            for (int i = 0; i < values.length; i++) {
                assertEquals((int_values[i]) >= (int_values[j]), values[i].gte(values[j]));
            }
    }

    @Test
    void Test_pow() {
        for (int j = 0; j < values.length; j++)
            for (int i = 0; i < values.length; i++) {
                assertEquals(new IntegerValue((int) Math.pow((int_values[i]), (int_values[j]))), values[i].pow(values[j]));
            }
    }


    @Test
    void Test_add() {
        for (int j = 0; j < values.length; j++)
            for (int i = 0; i < values.length; i++) {
                assertEquals(new IntegerValue(((int_values[i]) + (int_values[j]))), values[i].add(values[j]));
            }
    }

    @Test
    void Test_sub() {
        for (int j = 0; j < values.length; j++)
            for (int i = 0; i < values.length; i++) {
                assertEquals(new IntegerValue(((int_values[i]) - (int_values[j]))), values[i].sub(values[j]));
            }
    }

    @Test
    void Test_mul() {
        for (int j = 0; j < values.length; j++)
            for (int i = 0; i < values.length; i++) {
                assertEquals(new IntegerValue(((int_values[i]) * (int_values[j]))), values[i].mul(values[j]));
            }
    }

    @Test
    void Test_div() {
        for (int j = 0; j < values.length; j++)
            for (int i = 0; i < values.length; i++) {
                if (int_values[j] != 0)
                    assertEquals(new IntegerValue(((int_values[i]) / (int_values[j]))), values[i].div(values[j]));
            }
    }

    @Test
    void Test_create() {
        for (int i = 0; i < values.length; i++) {
                assertEquals(values[i], values[i].create(inty_w_stringach[i]));
            }

    }
}