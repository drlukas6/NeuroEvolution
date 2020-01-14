package com.lukassestic.main.neuralNetwork.layer.impl;


import com.lukassestic.main.neuralNetwork.activations.Activation;
import com.lukassestic.main.neuralNetwork.activations.impl.SigmoidActivation;
import com.lukassestic.main.utilities.RandomUtility;

import java.util.Arrays;

public class Layer {
    private double[] outputs; // Y
    private double[][] weights; // I x O
    private double[][] scalingFactors; // I x O

    private int inputSize;
    private int neurons;

    private Activation activation = new SigmoidActivation();

    public Layer(int inputSize, int neurons, Activation activation) {
        this.inputSize = inputSize;
        this.neurons = neurons;

        this.activation = activation;

        outputs = new double[neurons];

        weights = new double[neurons][inputSize];
        scalingFactors = new double[neurons][inputSize];

        createRandomWeights();
    }

    private Layer(double[] outputs, double[][] weights, double[][] scalingFactors, int inputSize, int neurons, Activation activation) {
        this.outputs = outputs;
        this.weights = weights;
        this.scalingFactors = scalingFactors;
        this.inputSize = inputSize;
        this.neurons = neurons;
        this.activation = activation;
    }

    public Layer copy() {
        double[] outputsCopy = Arrays.copyOf(outputs, outputs.length);
        double[][] weightsCopy = new double[weights.length][weights[0].length];
        double[][] scalingFactorsCopy = new double[scalingFactors.length][scalingFactors[0].length];

        for (int i = 0; i < weightsCopy.length; i++) {
            weightsCopy[i] = Arrays.copyOf(weights[i], weights[i].length);
            scalingFactorsCopy[i] = Arrays.copyOf(scalingFactorsCopy[i], scalingFactorsCopy[i].length);
        }

        return new Layer(outputsCopy, weightsCopy, scalingFactorsCopy, inputSize, neurons, activation);
    }

    private void createRandomWeights() {
        for (int i = 0; i < neurons; i++) {
            for(int j = 0; j < inputSize; j++) {
                weights[i][j] = RandomUtility.doubleInRange(-1, 1);
                scalingFactors[i][j] = RandomUtility.doubleInRange(-1, 1);
            }
        }
    }

    public void forwardPassFrom(Layer layer) {
        for (int i = 0; i < neurons; i++) {
            outputs[i] = activation.calculate(layer.getOutputs(), weights[i], scalingFactors[i]);
        }
    }

    public double[] getOutputs() {
        return outputs;
    }

    public void setOutputs(double[] outputs) {
        this.outputs = outputs;
    }

    public int getNeurons() {
        return neurons;
    }

    public double[][] getWeights() {
        return weights;
    }

    public void setWeights(double[][] weights) {
        this.weights = weights;
    }

    public double[][] getScalingFactors() {
        return scalingFactors;
    }

    public void setScalingFactors(double[][] scalingFactors) {
        this.scalingFactors = scalingFactors;
    }

    public Activation getActivation() {
        return activation;
    }
}
