package com.lukassestic.main.genetics.crossover.Impl;

import com.lukassestic.main.genetics.crossover.Crossover;
import com.lukassestic.main.neuralNetwork.NeuralNetwork;
import com.lukassestic.main.neuralNetwork.layer.impl.Layer;

import java.util.Random;

public class BlendCrossover implements Crossover {
    @Override
    public NeuralNetwork cross(NeuralNetwork n1, NeuralNetwork n2) {
        Random r = new Random();

        NeuralNetwork child = n1.copy();

        for (int layerIndex = 1; layerIndex < n1.getNumberOfLayers(); layerIndex++) {
            Layer n1Layer = n1.getLayerAt(layerIndex);
            Layer n2Layer = n2.getLayerAt(layerIndex);

            Layer childLayer = child.getLayerAt(layerIndex);

            double[][] n1Weights = n2Layer.getWeights();
            double[][] n2Weights = n2Layer.getWeights();

            double[][] childWeights = childLayer.getWeights();

            double[][] n1ScalingFactors = n2Layer.getScalingFactors();
            double[][] n2ScalingFactors = n2Layer.getScalingFactors();

            double[][] childScalingFactors = childLayer.getScalingFactors();


            for (int row = 0; row < n1Weights.length; row++) {
                for (int column = 0; column < n1Weights[0].length; column++) {
                    double wW = 2 * r.nextDouble() - 0.5;
                    double wS = 2 * r.nextDouble() - 0.5;

                    childWeights[row][column] = (1 - wW) * n1Weights[row][column] + wW * n2Weights[row][column];
                    childScalingFactors[row][column] = (1 - wS) * n1ScalingFactors[row][column] + wS * n2ScalingFactors[row][column];
                }
            }

            childLayer.setWeights(childWeights);
            childLayer.setScalingFactors(childScalingFactors);
        }

        return child;
    }
}
