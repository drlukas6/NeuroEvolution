package com.lukassestic.main.genetics.mutation.impl;

import com.lukassestic.main.genetics.mutation.Mutation;
import com.lukassestic.main.neuralNetwork.NeuralNetwork;
import com.lukassestic.main.neuralNetwork.layer.impl.Layer;
import com.lukassestic.main.utilities.RandomUtility;

public class NoiseMutation implements Mutation {

    @Override
    public void mutate(NeuralNetwork neuralNetwork, double propability) {

        for (int layerIndex = 1; layerIndex < neuralNetwork.getNumberOfLayers(); layerIndex++) {

            Layer layer = neuralNetwork.getLayerAt(layerIndex);

            double[][] weights = layer.getWeights();

            double[][] scalingFactors = layer.getScalingFactors();

            for (int row = 0; row < weights.length; row++) {

                for (int column = 0; column < weights[0].length; column++) {

                    if (RandomUtility.doubleInRange(0, 1) <= propability) {
                        weights[row][column] += RandomUtility.doubleInRange(-1, 1);
                    }

                    if (RandomUtility.doubleInRange(0, 1) <= propability) {
                        scalingFactors[row][column] += RandomUtility.doubleInRange(-1, 1);
                    }
                }
            }

            layer.setWeights(weights);
            layer.setScalingFactors(scalingFactors);
        }
    }
}
