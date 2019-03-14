package Testy;

import DF.Values.DateTimeValue;
import DF.Values.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Random;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DateTimeValueTest {


    int count = 1000;
    LocalDateTime[] daty = new LocalDateTime[count];
    Value[] values = new Value[count];

    @BeforeEach
    void ustaw() throws Exception{
        Random rand = new Random();
        for (int i = 0; i < count; i++) {
            daty[i] = LocalDateTime.parse(String.format("%04d-%02d-%02dT%02d:%02d:%02d", (rand.nextInt(2000) + 1000), (rand.nextInt(12) + 1), (rand.nextInt(26) + 1), (rand.nextInt(24)), (rand.nextInt(60)), (rand.nextInt(60))));
            values[i] = new DateTimeValue(daty[i]);
        }
    }

    @Test
    void TEST_getValue() throws Exception{
        for (int i = 0; i < count; i++) {
            assertEquals(daty[i], values[i].getValue());
        }
    }

    @Test
    void TEST_toString() throws Exception{
        for (int i = 0; i < count; i++) {
            assertEquals(daty[i].toString(), values[i].toString());
        }
    }

    @Test
    void TEST_add() throws Exception{
        assertThrows(UnsupportedOperationException.class, () -> {
            for (int i = 0; i < count; i++) {
                for (int j = 0; j < count; j++)
                    values[i].add(values[j]);
            }
        });
    }

    @Test
    void TEST_sub() throws Exception{
        assertThrows(UnsupportedOperationException.class, () -> {
            for (int i = 0; i < count; i++) {
                for (int j = 0; j < count; j++)
                    values[i].sub(values[j]);
            }
        });
    }

    @Test
    void TEST_mul() throws Exception{
        assertThrows(UnsupportedOperationException.class, () -> {
            for (int i = 0; i < count; i++) {
                for (int j = 0; j < count; j++)
                    values[i].mul(values[j]);
            }
        });
    }

    @Test
    void TEST_pow() throws Exception{
        assertThrows(UnsupportedOperationException.class, () -> {
            for (int i = 0; i < count; i++) {
                for (int j = 0; j < count; j++)
                    values[i].pow(values[j]);
            }
        });
    }

    @Test
    void TEST_div() throws Exception{
        assertThrows(UnsupportedOperationException.class, () -> {
            for (int i = 0; i < count; i++) {
                for (int j = 0; j < count; j++)
                    values[i].div(values[j]);
            }
        });
    }


    @Test
    void TEST_eq() throws Exception{
        for (int j = 0; j < values.length; j++)
            for (int i = 0; i < values.length; i++) {
                assertEquals((daty[i]).equals(daty[j]), values[i].eq(values[j]));
            }

    }

    @Test
    void TEST_equals() throws Exception{
        for (int j = 0; j < values.length; j++)
            for (int i = 0; i < values.length; i++) {
                assertEquals((daty[i]).equals(daty[j]), values[i].equals(values[j]));
            }

    }

    @Test
    void TEST_neq() throws Exception{
        for (int j = 0; j < values.length; j++)
            for (int i = 0; i < values.length; i++) {
                assertNotEquals((daty[i]).equals(daty[j]), values[i].neq(values[j]));
            }

    }


    @Test
    void TEST_lte() throws Exception{
        for (int j = 0; j < values.length; j++)
            for (int i = 0; i < values.length; i++) {
                assertEquals((daty[i]).isBefore(daty[j]), values[i].lte(values[j]));
            }

    }

    @Test
    void TEST_gte() throws Exception{
        for (int j = 0; j < values.length; j++)
            for (int i = 0; i < values.length; i++) {
                assertEquals((daty[i]).isAfter(daty[j]), values[i].gte(values[j]));
            }

    }

    @Test
    void TEST_hash() throws Exception{
        for (int j = 0; j < values.length; j++)
            assertEquals((daty[j]).hashCode() * 31, values[j].hashCode());
    }



    @Test
    void TEST_create() throws Exception{
        for (int j = 0; j < values.length; j++)
            assertEquals((values[j]),values[j].create(daty[j].toString()));
    }
}






