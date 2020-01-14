package com.lukassestic.main.genetics;

import com.lukassestic.main.dataset.DatasetUtility;
import com.lukassestic.main.genetics.crossover.Crossover;
import com.lukassestic.main.genetics.mutation.Mutation;
import com.lukassestic.main.neuralNetwork.NeuralNetwork;

public class Context {
    private NeuralNetwork prototype;
    private int populationSize;
    private double mutationPropability;
    private Mutation mutation;
    private Crossover crossover;
    private DatasetUtility datasetUtility;

    private int maxIterations;

    public Context(NeuralNetwork prototype, int populationSize,
                   double mutationPropability, Mutation mutation,
                   Crossover crossover, DatasetUtility datasetUtility,
                   int maxIterations) {
        this.prototype = prototype;
        this.populationSize = populationSize;
        this.mutationPropability = mutationPropability;
        this.mutation = mutation;
        this.crossover = crossover;
        this.datasetUtility = datasetUtility;

        this.maxIterations = maxIterations;
    }

    public NeuralNetwork getPrototype() {
        return prototype;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public double getMutationPropability() {
        return mutationPropability;
    }

    public Mutation getMutation() {
        return mutation;
    }

    public Crossover getCrossover() {
        return crossover;
    }

    public DatasetUtility getDatasetUtility() {
        return datasetUtility;
    }

    public int getMaxIterations() {
        return maxIterations;
    }
}
