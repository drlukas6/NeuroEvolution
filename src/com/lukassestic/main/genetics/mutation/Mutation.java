package com.lukassestic.main.genetics.mutation;

import com.lukassestic.main.neuralNetwork.NeuralNetwork;

public interface Mutation {
    void mutate(NeuralNetwork neuralNetwork, double propability);
}
