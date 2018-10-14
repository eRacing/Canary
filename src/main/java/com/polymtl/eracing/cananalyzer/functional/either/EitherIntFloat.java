package com.polymtl.eracing.cananalyzer.functional.either;

import java.util.Optional;

/**
 * This class represents a value that can either be of type Integer or type Float.
 */
public class EitherIntFloat extends Either<Integer, Float> {
    /**
     * Constructor.
     *
     * @param left  The optional integer on the left.
     * @param right The optional float on the right.
     */
    protected EitherIntFloat(Optional<Integer> left, Optional<Float> right) {
        super(left, right);
    }

    /**
     * This method returns an instance of this class that contains a integer value.
     *
     * @param left The integer value.
     * @return The integer either object.
     */
    public static EitherIntFloat left(Integer left) {
        return new EitherIntFloat(Optional.of(left), Optional.empty());
    }

    /**
     * This method returns an instance of this class that contains a float value.
     *
     * @param right The float value.
     * @return The float either object.
     */
    public static EitherIntFloat right(Float right) {
        return new EitherIntFloat(Optional.empty(), Optional.of(right));
    }

    /**
     * This method returns a number that can either be a integer or a float from a string object. When used in a parser,
     * the input should be checked so that is is indeed a integer or a float.
     *
     * @param s The string object.
     * @return Either an integer or a float.
     */
    public static EitherIntFloat fromString(String s) throws NumberFormatException {
        /* try parsing the integer value */
        try {
            Integer number = Integer.parseInt(s);
            return EitherIntFloat.left(number);
        } catch (NumberFormatException e) {

        }

        /* try parsing the float value */
        try {
            Float number = Float.parseFloat(s);
            return EitherIntFloat.right(number);
        } catch (NumberFormatException e) {

        }

        /* shouldn't be happening */
        throw new NumberFormatException();
    }
}

