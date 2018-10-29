import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Implementation of Data Structures
 * Created by Ketki Mahajan on ${Date}
 **/
public class MDSTest {
    MDS m1 = new MDS();

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

    }

}