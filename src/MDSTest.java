import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Implementation of Data Structures
 * Created by Ketki Mahajan on ${Date}
 **/
public class MDSTest {
    MDS m1;

    @BeforeEach
    void makeObject() {
        m1 = new MDS();
    }

    @Test
    void find() {

    }


    @Test
    void insert() {

        int x = m1.insert(1, new MDS.Money("32.79"), Arrays.asList(123L, 4L, 5L));
        x += m1.insert(2, new MDS.Money("232.99"), Arrays.asList(4L, 789L, 25L));
        x += m1.insert(3, new MDS.Money("132.59"), Arrays.asList(123L, 789L, 254L));
        x += m1.insert(4, new MDS.Money("222.79"), Arrays.asList(1L, 4L, 15L));
        x += m1.insert(5, new MDS.Money("312.79"), Arrays.asList(173L, 180L, 195L));
        assertEquals(5, x);

        x += m1.insert(5, new MDS.Money("382.79"), Arrays.asList(1233456L, 678912L));
        x += m1.insert(3, new MDS.Money("532.79"), Arrays.asList(1233456L, 678912L));
        x += m1.insert(6, new MDS.Money(43, 23), Arrays.asList(200L, 4L));
        assertEquals(6, x);
        assertEquals("32.79", m1.find(1).toString());
        assertNotEquals(new MDS.Money("32.79"), m1.find(11));

        long sum = m1.delete(1);
        assertEquals(132, sum);

        m1.insert(1, new MDS.Money("32.79"), Arrays.asList(123L, 4L, 5L));
        long sum2 = m1.delete(1);
        assertEquals(132, sum2);

        long sum1 = m1.delete(10);
        assertEquals(0, sum1);
    }

    @Test
    void testMaxPrice() {
        MDS testMaxPriceObject = new MDS();
        testMaxPriceObject.insert(1, new MDS.Money("200"), Arrays.asList(100L, 205L));
        testMaxPriceObject.insert(2, new MDS.Money("350"), Arrays.asList(405L, 105L));
        testMaxPriceObject.insert(3, new MDS.Money("200"), Arrays.asList(105L, 205L));
        testMaxPriceObject.insert(4, new MDS.Money("500"), Arrays.asList(405L, 500L));

        assertEquals("350.0", testMaxPriceObject.findMaxPrice(105).toString());
        assertNotEquals("250.0", testMaxPriceObject.findMaxPrice(105).toString());
        assertEquals("500.0", testMaxPriceObject.findMaxPrice(500).toString());
        assertNotEquals("505.0", testMaxPriceObject.findMaxPrice(500).toString());
        assertEquals("500.0", testMaxPriceObject.findMaxPrice(405).toString());
        assertNotEquals("305.0", testMaxPriceObject.findMaxPrice(405).toString());
        assertEquals("200.0", testMaxPriceObject.findMaxPrice(100).toString());
        assertNotEquals("100.0", testMaxPriceObject.findMaxPrice(100).toString());
        assertEquals("200.0", testMaxPriceObject.findMaxPrice(205).toString());
        assertNotEquals("100.0", testMaxPriceObject.findMaxPrice(205).toString());
        assertNotEquals("100.0", testMaxPriceObject.findMaxPrice(150).toString());
        assertEquals("0.0", testMaxPriceObject.findMaxPrice(150).toString());
        testMaxPriceObject.insert(5, new MDS.Money("350"), Arrays.asList(1000L, 500L));
        assertEquals("500.0", testMaxPriceObject.findMaxPrice(500).toString());
        assertNotEquals("350.0", testMaxPriceObject.findMaxPrice(500).toString());


    }

}