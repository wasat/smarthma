package pl.wasat.smarthma.parser.Parser;

/**
 * Created by marcel paduch on 2015-08-07.
 */
public class Pair<T, U> {
    public final T title;
    public final U content;

    public Pair(T t, U u) {
        this.content = u;
        this.title = t;
    }
}