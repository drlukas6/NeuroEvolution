package com.lukassestic.main.genetics.crossover.Impl;

import com.lukassestic.main.genetics.crossover.Crossover;
import com.lukassestic.main.neuralNetwork.NeuralNetwork;

import java.util.Random;

public class RandomCrossover implements Crossover {
    private Crossover[] crossovers = new Crossover[]{new AverageCrossover(), new BlendCrossover(), new HeuristicNetworkCrossover()};

    @Override
    public NeuralNetwork cross(NeuralNetwork n1, NeuralNetwork n2) {
        Random r = new Random();
        double randomCoef = r.nextDouble();
        int index = randomCoef <= 1.0/6.0 ? 2 : randomCoef <= 1.0/3.0 ? 1 : 0;


        return crossovers[index].cross(n1, n2);
    }
}
