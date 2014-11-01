package utility;

/**
 * A Pair of Objects
 *
 * @param <T>
 * @param <U>
 */
public class Pair<T, U> {
    private final T first;
    private final U second;

    public Pair(T aFirst, U aSecond) {
        first = aFirst;
        second = aSecond;
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Pair other = (Pair) o;

        return
                this.first.equals(other.getFirst()) && this.second.equals(other.getSecond())
                ||
                this.first.equals(other.getSecond()) && this.second.equals(other.getFirst());
    }
}
