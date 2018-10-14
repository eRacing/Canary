package com.polymtl.eracing.cananalyzer.functional.tuple;

import com.polymtl.eracing.cananalyzer.functional.either.EitherIntFloat;

/**
 * This class represents a 2-tuple of numbers that can either be integers or floats.
 */
public class TupleNumber extends Tuple<EitherIntFloat, EitherIntFloat> {
    /**
     * Constructor.
     *
     * @param number1 The first number.
     * @param number2 The second number.
     */
    public TupleNumber(EitherIntFloat number1, EitherIntFloat number2) {
        super(number1, number2);
    }
}
