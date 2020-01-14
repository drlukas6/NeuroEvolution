package com.lukassestic.main.neuralNetwork.layer.impl;


import com.lukassestic.main.neuralNetwork.activations.Activation;
import com.lukassestic.main.neuralNetwork.activations.impl.SigmoidActivation;
import com.lukassestic.main.utilities.RandomUtility;

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
}
