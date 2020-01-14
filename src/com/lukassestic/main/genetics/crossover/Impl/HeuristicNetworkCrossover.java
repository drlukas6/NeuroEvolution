package com.lukassestic.main.genetics.crossover.Impl;

import com.lukassestic.main.genetics.crossover.Crossover;
import com.lukassestic.main.neuralNetwork.NeuralNetwork;
import com.lukassestic.main.neuralNetwork.layer.impl.Layer;
import com.lukassestic.main.utilities.RandomUtility;

public class HeuristicNetworkCrossover implements Crossover {

    @Override
    public NeuralNetwork cross(NeuralNetwork n1, NeuralNetwork n2) {
        NeuralNetwork better = n1.getFitness() >= n2.getFitness() ? n1 : n2;
        NeuralNetwork worse = n1.getFitness() >= n2.getFitness() ? n2 : n1;

        NeuralNetwork child = better.copy();

//        for (int i = 0; i < p1.getLength(); i++) {
//            double value = better.getValueAt(i) + RandomUtility.doubleInRange(0, 1) * (better.getValueAt(i) - worse.getValueAt(i));
//            child.setValueAt(value, i);
//        }

        for (int layerIndex = 1; layerIndex < better.getNumberOfLayers(); layerIndex++) {
            Layer betterLayer = better.getLayerAt(layerIndex);
            Layer worseLayer = better.getLayerAt(layerIndex);

            Layer childLayer = child.getLayerAt(layerIndex);

            double[][] betterWeights = betterLayer.getWeights();
            double[][] worseWeights = worseLayer.getWeights();

            double[][] childWeights = childLayer.getWeights();

            double[][] betterScalingFactors = betterLayer.getScalingFactors();
            double[][] worseScalingFactors = worseLayer.getScalingFactors();

            double[][] childScalingFactors = childLayer.getScalingFactors();

            for (int row = 0; row < betterWeights.length; row++) {
                for (int column = 0; column < betterWeights[0].length; column++) {
                    double wDifference = betterWeights[row][column] - worseWeights[row][column];
                    double sDifference = betterScalingFactors[row][column] - worseScalingFactors[row][column];

                    childWeights[row][column] = betterWeights[row][column] * RandomUtility.doubleInRange(0, 1) * (wDifference);
                    childScalingFactors[row][column] = betterScalingFactors[row][column] * RandomUtility.doubleInRange(0, 1) * (sDifference);
                }
            }

            childLayer.setWeights(childWeights);
            childLayer.setScalingFactors(childScalingFactors);
        }

        return child;
    }
}
