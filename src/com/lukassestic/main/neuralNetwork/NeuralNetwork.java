package com.lukassestic.main.neuralNetwork;

import com.lukassestic.main.neuralNetwork.activations.Activation;
import com.lukassestic.main.neuralNetwork.layer.impl.Layer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NeuralNetwork {
    private int maxIteration;
    private double learningRate;
    private List<double[]> inputs;
    private List<double[]> outputs;

    private List<Integer> indexes;

    private List<Layer> layers = new ArrayList<>();

    public NeuralNetwork(int maxIteration, double learningRate, List<double[]> inputs, List<double[]> outputs) {
        this.maxIteration = maxIteration;
        this.learningRate = learningRate;
        this.inputs = inputs;
        this.outputs = outputs;

        indexes = new ArrayList<>(inputs.size());

        for (int i = 0; i < inputs.size(); i++) {
            indexes.add(i);
        }
    }

    public NeuralNetwork addLayer(int neurons, Activation activation) {
        if (layers.size() == 0) {
            layers.add(new Layer(neurons, neurons, activation)); // This would be the input layer
            return this;
        }

        int lastInputSize = layers.get(layers.size() - 1).getNeurons();
        layers.add(new Layer(lastInputSize, neurons, activation));

        return this;
    }

    public void fit(int batchSize) {
        for (int i = 0; i < maxIteration; i++) {
            if (i % 1000 == 0) {
                System.out.println(i + ". Error: " + getMSE());
            }

            iterateThrough(batchSize);

            Collections.shuffle(indexes);
        }
        System.out.println("Final error: " + getMSE());
    }

    private void iterateThrough(int batchSize) {
        int lastLayer = layers.size() - 1;

        for (int i = 0; i < batchSize; i++) {
            double[] input = inputs.get(indexes.get(i));
            predict(input);
            double[] expected = outputs.get(indexes.get(i));

            Layer lastLayerObj = layers.get(lastLayer);

//            double[] tempDelta = new double[lastLayerObj.getDelta().length];
//            double[] lastOutputs = lastLayerObj.getOutputs();
//
//            for (int j = 0; j < tempDelta.length; j++) {
//                tempDelta[j] = lastOutputs[j] * (1 - lastOutputs[j]) * (expected[j] - lastOutputs[j]);
//            }
//            lastLayerObj.setDelta(tempDelta);
//            for (int j = lastLayer - 1; j >= 1; j--) {
//                layers.get(j).updateDelta(layers.get(j + 1));
//            }
//
//            for (int j = lastLayer; j >= 1; j--) {
//                layers.get(j).updateDeltaWeights(layers.get(j - 1));
//            }
        }

//        for(Layer layer: layers) {
//            layer.updateWeights(learningRate);
//        }
    }

    private double getMSE() {
        double sum = 0;

        for (int i = 0; i < inputs.size(); i++) {
            double[] prediction = predict(inputs.get(i));
            double[] expected = outputs.get(i);
            for (int j = 0; j < prediction.length; j++) {
                sum += Math.pow(expected[j] - prediction[j], 2);
            }
        }

        return sum / inputs.size();
    }

    public double[] predict(double[] inputs) {
        layers.get(0).setOutputs(inputs);
        for (int i = 1; i < layers.size(); i++) {
            layers.get(i).forwardPassFrom(layers.get(i - 1));
        }

        Layer last = layers.get(layers.size() - 1);
        return last.getOutputs();
    }
}
