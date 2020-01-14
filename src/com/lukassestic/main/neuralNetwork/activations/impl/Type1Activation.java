package com.lukassestic.main.neuralNetwork.activations.impl;

import com.lukassestic.main.neuralNetwork.activations.Activation;

public class Type1Activation implements Activation {

    @Override
    public double calculate(double[] inputs, double[] weights, double[] scalingFactor) {
        double sum = 0;

        for (int i = 0; i < inputs.length; i++) {
            double nominator = Math.abs(inputs[i] - weights[i]);
            double denominator = Math.abs(scalingFactor[i]);

            sum += nominator / denominator;
        }

        return 1.0 / (1.0 + sum);
    }
}
