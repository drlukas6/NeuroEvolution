package com.lukassestic.main.neuralNetwork;

import com.lukassestic.main.dataset.DatasetUtility;
import com.lukassestic.main.neuralNetwork.activations.Activation;
import com.lukassestic.main.neuralNetwork.layer.impl.Layer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NeuralNetwork {
    private DatasetUtility datasetUtility;

    private double fitness = 0;

    private List<Layer> layers = new ArrayList<>();

    public NeuralNetwork(DatasetUtility datasetUtility) {
        this.datasetUtility = datasetUtility;
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

    private NeuralNetwork(DatasetUtility datasetUtility, double fitness, List<Layer> layers) {
        this.datasetUtility = datasetUtility;
        this.fitness = fitness;
        this.layers = layers;
    }

    public NeuralNetwork copy() {
        List<Layer> layersCopy = layers.stream().map(Layer::copy).collect(Collectors.toList());
        return new NeuralNetwork(datasetUtility, 0, layersCopy);
    }

    public void evaluate() {
        this.fitness = 1.0 / getMSE();
    }

    private double getMSE() {
        double sum = 0;

        for (int i = 0; i < datasetUtility.getIns().size(); i++) {
            double[] prediction = predict(datasetUtility.getInputAt(i));
            double[] expected = datasetUtility.getOutputAt(i);
            for (int j = 0; j < prediction.length; j++) {
                sum += Math.pow(expected[j] - prediction[j], 2);
            }
        }

        return sum / datasetUtility.getIns().size();
    }

    public double[] predict(double[] inputs) {
        layers.get(0).setOutputs(inputs);
        for (int i = 1; i < layers.size(); i++) {
            layers.get(i).forwardPassFrom(layers.get(i - 1));
        }

        Layer last = layers.get(layers.size() - 1);
        return last.getOutputs();
    }

    public double getFitness() {
        return fitness;
    }

    public Layer getLayerAt(int index) {
        return layers.get(index);
    }

    public int getNumberOfLayers() {
        return layers.size();
    }

    public NeuralNetwork prototypeCopy() {
        NeuralNetwork copy = new NeuralNetwork(datasetUtility);

        for (Layer layer : layers) {
            copy.addLayer(layer.getNeurons(), layer.getActivation());
        }

        copy.evaluate();

        return copy;
    }
}
