package com.lukassestic.main.genetics.algorithm;

import com.lukassestic.main.genetics.Context;
import com.lukassestic.main.neuralNetwork.NeuralNetwork;

import java.util.ArrayList;
import java.util.List;

public abstract class GeneticAlgorithm {
    protected List<NeuralNetwork> population = new ArrayList<>();
    protected Context context;

    public GeneticAlgorithm(Context context) {
        this.context = context;

        for (int i = 0; i < context.getPopulationSize(); i++) {
            population.add(context.getPrototype().prototypeCopy());
        }
    }

    protected abstract List<NeuralNetwork> select();
    public abstract NeuralNetwork fit();
}
