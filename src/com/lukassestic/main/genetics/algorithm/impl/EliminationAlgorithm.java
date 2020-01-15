package com.lukassestic.main.genetics.algorithm.impl;

import com.lukassestic.main.genetics.Context;
import com.lukassestic.main.genetics.algorithm.GeneticAlgorithm;
import com.lukassestic.main.neuralNetwork.NeuralNetwork;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EliminationAlgorithm extends GeneticAlgorithm {

    public EliminationAlgorithm(Context context) {
        super(context);
    }

    @Override
    protected List<NeuralNetwork> select() {
        List<NeuralNetwork> selection = new ArrayList<>(3);

        Random r = new Random();
        while (selection.size() < 3) {
            int randomIndex = r.nextInt(population.size());

            NeuralNetwork selected = population.get(randomIndex);

            if (!selection.contains(selected)) {
                selection.add(selected);
            }
        }

        selection.sort(NeuralNetwork::compareTo);

        return selection;
    }

    @Override
    public NeuralNetwork fit() {

        population.sort(NeuralNetwork::compareTo);

        for (int iteration = 0; iteration <= context.getMaxIterations(); iteration++) {
            double bestFitness = population.get(population.size() - 1).getFitness();

            if (iteration % 100 == 0) {
                System.out.println("I: " + iteration + " Min error: " + 1.0 / bestFitness + " Fitness: " + bestFitness);
            }

            if (1.0 / bestFitness < 1E-7) {
                break;
            }

            List<NeuralNetwork> selection = select();
            int childIndex = population.indexOf(selection.get(0));

            NeuralNetwork newNetwork = context.getCrossover().cross(selection.get(1), selection.get(2));
            context.getMutation().mutate(newNetwork, context.getMutationPropability());

            newNetwork.evaluate();

            population.set(childIndex, newNetwork);

            population.sort(NeuralNetwork::compareTo);

        }

        return population.get(population.size() - 1);
    }
}
