import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LP3DriverTest {

    @Test
    void main401() {
        assertEquals(1448, LP3Driver.main(System.getProperty("user.dir") + "\\401.txt"));
    }

    @Test
    void main402() {
        assertEquals(4142, LP3Driver.main(System.getProperty("user.dir") + "\\402.txt"));
    }


    @Test
    void main403() {
        assertEquals(11132, LP3Driver.main(System.getProperty("user.dir") + "\\403.txt"));
    }

    @Test
    void main405() {
        assertEquals(16494, LP3Driver.main(System.getProperty("user.dir") + "\\405.txt"));
    }

    @Test
    void main406() {
        assertEquals(19005, LP3Driver.main(System.getProperty("user.dir") + "\\406.txt"));
    }




    @Test
    void main404() {
        assertEquals(52018, LP3Driver.main(System.getProperty("user.dir") + "\\404.txt"));
    }


    @Test
    void main407() {
        assertEquals(489174, LP3Driver.main(System.getProperty("user.dir") + "\\407.txt"));
    }

    @Test
    void main420() {
        assertEquals(1016105100, LP3Driver.main(System.getProperty("user.dir") + "\\420.txt"));
    }


@Test
    void main100k() {
    assertEquals(565883014879L, LP3Driver.main(System.getProperty("user.dir") + "\\lp3-big-100k.txt"));
    }


@Test
    void main200k() {
    assertEquals(12957774, LP3Driver.main(System.getProperty("user.dir") + "\\lp3-big-200k.txt"));
    }

    @Test
    void main300k() {
        assertEquals(29000824L, LP3Driver.main(System.getProperty("user.dir") + "\\lp3-big-300k.txt"));
    }

    @Test
    void main500k() {
        assertEquals(303607214L, LP3Driver.main(System.getProperty("user.dir") + "\\lp3-big-500k.txt"));
    }

}
