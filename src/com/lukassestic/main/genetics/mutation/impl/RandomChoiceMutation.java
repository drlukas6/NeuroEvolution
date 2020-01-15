package com.lukassestic.main.genetics.mutation.impl;

import com.lukassestic.main.genetics.mutation.Mutation;
import com.lukassestic.main.neuralNetwork.NeuralNetwork;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class RandomChoiceMutation implements Mutation {
    Mutation[] mutations;
    double[] propabilities;
    Integer[] indexes = new Integer[]{0, 1};
    double propSum;

    public RandomChoiceMutation(double noiseProp, double noiseSigma, double randomProp, double randomSigma) {
        propSum = noiseProp + randomProp;
        propabilities = new double[]{noiseProp / propSum, randomProp / propSum};

        mutations = new Mutation[]{new NoiseMutation(noiseSigma), new RandomMutation(randomSigma)};

        Arrays.sort(propabilities);
        Arrays.sort(indexes, Comparator.comparingDouble(i -> propabilities[i]));
    }

    @Override
    public void mutate(NeuralNetwork neuralNetwork, double propability) {
        Random r = new Random();

        Mutation mutation = mutations[indexes[r.nextInt(2)]];

        mutation.mutate(neuralNetwork, propability);
    }
}
