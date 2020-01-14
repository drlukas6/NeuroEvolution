package com.lukassestic.main.genetics.algorithm.impl;

import com.lukassestic.main.genetics.Context;
import com.lukassestic.main.genetics.algorithm.GeneticAlgorithm;
import com.lukassestic.main.neuralNetwork.NeuralNetwork;
import com.lukassestic.main.utilities.RandomUtility;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EliminationAlgorithm extends GeneticAlgorithm {

    public EliminationAlgorithm(Context context) {
        super(context);
    }

    @Override
    protected List<NeuralNetwork> select() {
        List<NeuralNetwork> selection = new ArrayList<>(3);

        while (selection.size() < 3) {
            int randomIndex = RandomUtility.intInRange(0, context.getPopulationSize());

            NeuralNetwork selected = population.get(randomIndex);

            if (!selection.contains(selected)) {
                selection.add(selected);
            }
        }

        selection.sort((o1, o2) -> (int) (o1.getFitness() - o2.getFitness()));

        return selection;
    }

    @Override
    public NeuralNetwork fit() {

        population.sort((o1, o2) -> (int) (o1.getFitness() - o2.getFitness()));

        for (int iteration = 0; iteration < context.getMaxIterations(); iteration++) {

            if (iteration % 100 == 0) {
                System.out.println("Best fitness: " + population.get(population.size() - 1).getFitness());
            }

            List<NeuralNetwork> selection = select();
            int childIndex = population.indexOf(selection.get(0));

            NeuralNetwork newNetwork = context.getCrossover().cross(selection.get(1), selection.get(2));
            context.getMutation().mutate(newNetwork, context.getMutationPropability());

            population.set(childIndex, newNetwork);

            population.sort((o1, o2) -> (int) (o1.getFitness() - o2.getFitness()));
        }


        return population.get(population.size() - 1);
    }
}
