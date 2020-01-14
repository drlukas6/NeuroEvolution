package com.lukassestic.main.neuralNetwork.activations;

public interface Activation {
    double calculate(double[] inputs, double[] weights, double[] scalingFactor);
}
