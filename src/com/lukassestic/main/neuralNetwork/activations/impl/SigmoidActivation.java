package com.lukassestic.main.neuralNetwork.activations.impl;


import com.lukassestic.main.neuralNetwork.activations.Activation;

public class SigmoidActivation implements Activation {
    @Override
    public double calculate(double[] inputs, double[] weights, double[] scalingFactor) {
        double weightedSum = 0;

        for (int i = 0; i < inputs.length; i++) {
            weightedSum += inputs[i] * weights[i];
        }

        return 1.0 / (1.0 + Math.exp(-weightedSum));
    }
}
