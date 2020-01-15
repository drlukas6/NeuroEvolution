package com.lukassestic.main;

import com.lukassestic.main.dataset.DatasetUtility;
import com.lukassestic.main.genetics.Context;
import com.lukassestic.main.genetics.algorithm.GeneticAlgorithm;
import com.lukassestic.main.genetics.algorithm.impl.EliminationAlgorithm;
import com.lukassestic.main.genetics.crossover.Crossover;
import com.lukassestic.main.genetics.crossover.Impl.RandomCrossover;
import com.lukassestic.main.genetics.mutation.Mutation;
import com.lukassestic.main.genetics.mutation.impl.NoiseMutation;
import com.lukassestic.main.genetics.mutation.impl.RandomChoiceMutation;
import com.lukassestic.main.genetics.mutation.impl.RandomMutation;
import com.lukassestic.main.neuralNetwork.NeuralNetwork;
import com.lukassestic.main.neuralNetwork.activations.Activation;
import com.lukassestic.main.neuralNetwork.activations.impl.SigmoidActivation;
import com.lukassestic.main.neuralNetwork.activations.impl.Type1Activation;

public class Main {
    public static void main(String[] args) {
        DatasetUtility datasetUtility = new DatasetUtility("dataset.txt");

        NeuralNetwork neuralNetwork = new NeuralNetwork(datasetUtility);

        Activation sigmoid = new SigmoidActivation();
        Activation type1 = new Type1Activation();

        neuralNetwork.addLayer(2, sigmoid)
                     .addLayer(5, type1)
                     .addLayer(5, type1)
                     .addLayer(3, sigmoid);

        Crossover crossover = new RandomCrossover();
        Mutation mutation = new RandomChoiceMutation(2, 1, 1, 3);

        Context context = new Context(neuralNetwork, 30,
                0.01, mutation,
                crossover, datasetUtility,
                600000);

        GeneticAlgorithm geneticAlgorithm = new EliminationAlgorithm(context);

        NeuralNetwork best = geneticAlgorithm.fit();
    }
}
