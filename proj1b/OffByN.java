public class OffByN implements CharacterComparator {
    public int diff;

    public OffByN(int N) {
        diff = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int result = x - y;
        return (result == diff);
    }
}
