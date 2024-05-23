import org.junit.Test;

/**
 * @Author Lord_Bao
 * @Date 2024/5/23 17:24
 * @Version 1.0
 */
public class FlikTest {
    @Test
    public void testIsSameNumber() {
        for (int i = 0; i < 500; i++) {
            System.out.println(Flik.isSameNumber(i, i));
        }
    }
}
