package pl.wasat.smarthma.parser.Parser;

/**
 * Created by marcel paduch on 2015-08-07 00:09.
 * Part of the project  SmartHMA
 */
public class Pair<T, U> {
    public final T title;
    public final U content;

    public Pair(T t, U u) {
        this.content = u;
        this.title = t;
    }
}