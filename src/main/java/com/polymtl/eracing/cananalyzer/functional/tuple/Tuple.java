package com.polymtl.eracing.cananalyzer.functional.tuple;

/**
 * This class represents a 2-tuple of types A and B.
 *
 * @param <A> The type of the first object.
 * @param <B> The type of the second object.
 */
public class Tuple<A,B> {
    /**
     * The first object in the tuple.
     */
    private A fA;

    /**
     * The second object in the tuple.
     */
    private B fB;

    /**
     * Constructor.
     *
     * @param a The first value in the tuple.
     * @param b The second value in the tuple.
     */
    public Tuple(A a, B b) {
        fA = a;
        fB = b;
    }

    /**
     * This method returns the first value in the tuple.
     *
     * @return The first value in the tuple.
     */
    public A getFirst() {
        return fA;
    }

    /**
     * This method returns the second value in the tuple.
     *
     * @return The second value in the tuple.
     */
    public B getSecond() {
        return fB;
    }
}
