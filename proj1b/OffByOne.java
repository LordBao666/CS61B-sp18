/**
 * @Author Lord_Bao
 * @Date 2024/5/26 10:35
 * @Version 1.0
 */
public class OffByOne implements CharacterComparator {

    /**
     * return true when x and y are off by 1
     */
    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == 1;
    }
}
