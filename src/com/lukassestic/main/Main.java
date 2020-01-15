package com.lukassestic.main;

import com.lukassestic.main.dataset.DatasetUtility;
import com.lukassestic.main.genetics.Context;
import com.lukassestic.main.genetics.algorithm.GeneticAlgorithm;
import com.lukassestic.main.genetics.algorithm.impl.EliminationAlgorithm;
import com.lukassestic.main.genetics.crossover.Crossover;
import com.lukassestic.main.genetics.crossover.Impl.RandomCrossover;
import com.lukassestic.main.genetics.mutation.Mutation;
import com.lukassestic.main.genetics.mutation.impl.RandomChoiceMutation;
import com.lukassestic.main.neuralNetwork.NeuralNetwork;
import com.lukassestic.main.neuralNetwork.activations.Activation;
import com.lukassestic.main.neuralNetwork.activations.impl.SigmoidActivation;
import com.lukassestic.main.neuralNetwork.activations.impl.Type1Activation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        DatasetUtility datasetUtility = new DatasetUtility("dataset.txt");

        NeuralNetwork neuralNetwork = new NeuralNetwork(datasetUtility);

        Activation sigmoid = new SigmoidActivation();
        Activation type1 = new Type1Activation();

        neuralNetwork.addLayer(2, sigmoid)
                     .addLayer(8, type1)
                     .addLayer(4, sigmoid)
                     .addLayer(3, sigmoid);

        Crossover crossover = new RandomCrossover();
        Mutation mutation = new RandomChoiceMutation(3, 1, 2, 3, 1);

        Context context = new Context(neuralNetwork, 30,
                0.02, mutation,
                crossover, datasetUtility,
                600000);

        GeneticAlgorithm geneticAlgorithm = new EliminationAlgorithm(context);

        NeuralNetwork best = geneticAlgorithm.fit();
        test(best, datasetUtility);
    }

    private static void test(NeuralNetwork neuralNetwork, DatasetUtility datasetUtility) {

        List<String> outputs = new ArrayList<>(datasetUtility.getDatasetSize());

        for (int i = 0; i < datasetUtility.getDatasetSize(); i++) {
            double[] expected = datasetUtility.getOutputAt(i);
            double[] predicted = neuralNetwork.predict(datasetUtility.getInputAt(i));

            String predictions = Arrays.stream(predicted).mapToObj(d -> (int) d).map(Object::toString).collect(Collectors.joining("\t"));
            String inputs = Arrays.stream(datasetUtility.getInputAt(i)).mapToObj(String::valueOf).collect(Collectors.joining("\t"));
            outputs.add(inputs + "\t" + predictions);

            String stringBuilder = "Real: " +
                    Arrays.toString(expected) +
                    "\tPredicted: " +
                    Arrays.toString(predicted);
            System.out.println(stringBuilder);

            try {
                Path path = Paths.get("2843_output.csv");

                String lines = String.join("\n", outputs);
                Files.write(path, lines.getBytes());
            } catch (IOException e) {
                System.err.println(e);
            }
        }


    }
}
