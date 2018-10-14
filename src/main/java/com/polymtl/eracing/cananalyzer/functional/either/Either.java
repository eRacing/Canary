package com.polymtl.eracing.cananalyzer.functional.either;

import java.util.Optional;

/**
 * This class represents a value that can either be of type L (left) or of type R (right).
 *
 * @param <L> The type of the left value.
 * @param <R> The type of the right value.
 */
public class Either<L, R> {
    /**
     * This optional value on the left.
     */
    private final Optional<L> fLeft;

    /**
     * This optional value on the right.
     */
    private final Optional<R> fRight;

    /**
     * Constructor.
     *
     * @param left  The optional value on the left.
     * @param right The optional value on the right.
     */
    protected Either(Optional<L> left, Optional<R> right) {
        if(left.isPresent() == right.isPresent()) {
            throw new IllegalArgumentException();
        }
        fLeft = left;
        fRight = right;
    }

    /**
     * This method returns an Either type with the left value populated.
     *
     * @param left The left value.
     * @param <L> The type of the left value.
     * @param <R> The type of the right value.
     * @return The left either object.
     */
    public static <L, R> Either<L, R> left(L left) {
        return new Either<>(Optional.of(left), Optional.empty());
    }

    /**
     * This method returns an Either type with the right value populated.
     *
     * @param right The right value.
     * @param <L> The type of the left value.
     * @param <R> The type of the right value.
     * @return The left either object.
     */
    public static <L, R> Either<L, R> right(R right) {
        return new Either<>(Optional.empty(), Optional.of(right));
    }

    /**
     * This method returns the left value.
     *
     * @return The left value.
     */
    public Optional<L> getLeft() {
        return fLeft;
    }

    /**
     * This method returns the right value.
     *
     * @return The right value.
     */
    public Optional<R> getRight() {
        return fRight;
    }
}
