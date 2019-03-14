package Testy;

import DF.Values.StringValue;
import DF.Values.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringValueTest {



    int count = 1000;
    String [] words = new String[count];
    Value [] values = new Value[count];


    @BeforeEach
    void ustaw(){
        for (int i=0;i<count; i++){
            words[i] = "QWERTYUIOOPALSKJFHB"+Integer.toString(i)+("MNBVCGSJSK").charAt(i%3);
            values[i] = new StringValue(words[i]);
        }
    }

    @Test
    void TEST_toString() {
        for (int i = 0; i < count; i++) {
            assertEquals(words[i],values[i].toString());
        }
    }

    @Test
    void TEST_getValue() {
        for (int i = 0; i < count; i++){
            assertEquals(words[i],values[i].getValue());
        }
    }


    @Test
    void TEST_add() {
        assertThrows(UnsupportedOperationException.class, () -> {
            for (int i = 0; i < count; i++) {
                for (int j = 0; j < count; j++)
                    values[i].add(values[j]);}
        });
    }



    @Test
    void TEST_div(){
        assertThrows(UnsupportedOperationException.class, () -> {
            for (int i = 0; i < count; i++) {
                for (int j = 0; j < count; j++)
                    values[i].div(values[j]);}
        });
    }

    @Test
    void TEST_mul(){
        assertThrows(UnsupportedOperationException.class, () -> {
            for (int i = 0; i < count; i++) {
                for (int j = 0; j < count; j++)
                    values[i].mul(values[j]);}
        });
    }

    @Test
    void TEST_pow(){
        assertThrows(UnsupportedOperationException.class, () -> {
            for (int i = 0; i < count; i++) {
                for (int j = 0; j < count; j++)
                    values[i].pow(values[j]);}
        });
    }
    @Test
    void TEST_sub(){
        assertThrows(UnsupportedOperationException.class, () -> {
            for (int i = 0; i < count; i++) {
                for (int j = 0; j < count; j++)
                    values[i].sub(values[j]);}
        });
    }

    @Test
    void TEST_eq(){
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                assertEquals(words[i].equals(words[j]), values[i].eq(values[j]));
            }
        }
    }

    @Test
    void TEST_neq(){
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                assertNotEquals(words[i].equals(words[j]), values[i].neq(values[j]));
            }
        }
    }

    @Test
    void TEST_lte(){
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                assertEquals(words[i].compareTo(words[j])<=0, values[i].lte(values[j]));
            }
        }
    }


    @Test
    void TEST_gte(){
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                assertEquals(words[i].compareTo(words[j])>=0, values[i].gte(values[j]));
            }
        }
    }

    @Test
    void TEST_equals(){
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                assertEquals(words[i].equals(words[j]), values[i].equals(values[j]));
            }
        }
    }

    @Test
    void Test_hashCode() {
        for (int i = 0; i < values.length; i++) {
            assertEquals(37*(words[i].hashCode()), values[i].hashCode());
        }
    }

    @Test
    void create() {
        for (int i = 0; i < values.length; i++) {
            assertEquals(values[i], values[i].create(words[i]));
        }
    }

}
