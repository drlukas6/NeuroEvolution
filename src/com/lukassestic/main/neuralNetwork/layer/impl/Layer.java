package com.lukassestic.main.neuralNetwork.layer.impl;


import com.lukassestic.main.neuralNetwork.activations.Activation;
import com.lukassestic.main.neuralNetwork.activations.impl.SigmoidActivation;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

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
        Random r = new Random();
        for (int i = 0; i < neurons; i++) {
            for(int j = 0; j < inputSize; j++) {
                weights[i][j] = r.nextGaussian();
                scalingFactors[i][j] = r.nextGaussian();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Layer layer = (Layer) o;
        return inputSize == layer.inputSize &&
                neurons == layer.neurons &&
                Arrays.equals(outputs, layer.outputs) &&
                Arrays.equals(weights, layer.weights) &&
                Arrays.equals(scalingFactors, layer.scalingFactors) &&
                Objects.equals(activation, layer.activation);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(inputSize, neurons, activation);
        result = 31 * result + Arrays.hashCode(outputs);
        result = 31 * result + Arrays.hashCode(weights);
        result = 31 * result + Arrays.hashCode(scalingFactors);
        return result;
    }
}
