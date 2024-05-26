/**
 * @Author Lord_Bao
 * @Date 2024/5/26 10:57
 * @Version 1.0
 */
public class OffByN implements CharacterComparator {
    private final int offset;

    public OffByN(int offset) {
        this.offset = offset;
    }

    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == offset;
    }
}
