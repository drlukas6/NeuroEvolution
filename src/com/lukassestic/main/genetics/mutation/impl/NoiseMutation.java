package com.lukassestic.main.genetics.mutation.impl;

import com.lukassestic.main.genetics.mutation.Mutation;
import com.lukassestic.main.neuralNetwork.NeuralNetwork;
import com.lukassestic.main.neuralNetwork.layer.impl.Layer;
import com.lukassestic.main.utilities.RandomUtility;

import java.util.Random;

public class NoiseMutation implements Mutation {

    @Override
    public void mutate(NeuralNetwork neuralNetwork, double propability) {

        Random r = new Random();

        for (int layerIndex = 1; layerIndex < neuralNetwork.getNumberOfLayers(); layerIndex++) {

            Layer layer = neuralNetwork.getLayerAt(layerIndex);

            double[][] weights = layer.getWeights();

            double[][] scalingFactors = layer.getScalingFactors();

            for (int row = 0; row < weights.length; row++) {

                for (int column = 0; column < weights[0].length; column++) {

                    if (r.nextDouble() <= propability) {
                        weights[row][column] += r.nextGaussian();
                    }

                    if (r.nextDouble() <= propability) {
                        scalingFactors[row][column] += r.nextGaussian();
                    }
                }
            }

            layer.setWeights(weights);
            layer.setScalingFactors(scalingFactors);
        }
    }
}
