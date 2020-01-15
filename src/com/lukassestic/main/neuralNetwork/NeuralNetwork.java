package com.lukassestic.main.neuralNetwork;

import com.lukassestic.main.dataset.DatasetUtility;
import com.lukassestic.main.neuralNetwork.activations.Activation;
import com.lukassestic.main.neuralNetwork.layer.impl.Layer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class NeuralNetwork implements Comparable<NeuralNetwork> {
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
        return new NeuralNetwork(datasetUtility, fitness, layersCopy);
    }

    public void evaluate() {
        this.fitness = 1.0 / getMSE();
    }

    private double getMSE() {
        double sum = 0;

        for (int i = 0; i < datasetUtility.getIns().size(); i++) {
            double[] prediction = predict(datasetUtility.getInputAt(i), false);
            double[] expected = datasetUtility.getOutputAt(i);
            for (int j = 0; j < prediction.length; j++) {
                sum += Math.pow(expected[j] - prediction[j], 2);
            }
        }

        return sum / datasetUtility.getIns().size();
    }

    private double[] predict(double[] inputs, boolean round) {
        layers.get(0).setOutputs(inputs);
        for (int i = 1; i < layers.size(); i++) {
            layers.get(i).forwardPassFrom(layers.get(i - 1));
        }

        Layer last = layers.get(layers.size() - 1);

        double[] outputs = last.getOutputs();

        int maxIndex = 0;
        for (int i = 0; i < outputs.length; i++) {
            if (outputs[i] > outputs[maxIndex]) {
                maxIndex = i;
            }
        }

        if (round) {
            for (int i = 0; i < outputs.length; i++) {
                outputs[i] = i == maxIndex ? 1 : 0;
            }
        }

        return outputs;
    }

    public double[] predict(double[] inputs) {
        return predict(inputs, true);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NeuralNetwork that = (NeuralNetwork) o;
        return Double.compare(that.fitness, fitness) == 0 &&
                layers.equals(that.layers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fitness, layers);
    }


    @Override
    public int compareTo(NeuralNetwork o) {
        return Double.compare(fitness, o.fitness);
    }
}
