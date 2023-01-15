import org.junit.Test;
import praktikum.Bun;

import static org.junit.Assert.assertEquals;

public class BunTest {
    @Test
    public void getNameTest(){
        Bun bun = new Bun("black bun", 100);
        assertEquals("black bun",bun.getName());
    }

    @Test
    public void getPriceTest(){
        Bun bun = new Bun("black bun", 100);
        assertEquals(100 ,bun.getPrice(), 0.0);
    }
}
