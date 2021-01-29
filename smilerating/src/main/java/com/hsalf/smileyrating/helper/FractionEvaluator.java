package com.hsalf.smileyrating.helper;

public class FractionEvaluator {

    public float evaluate(float x, float start, float end) {
        if (start == end) {
            return 1;
        }
        return (x - start) / (end - start);
    }
}
