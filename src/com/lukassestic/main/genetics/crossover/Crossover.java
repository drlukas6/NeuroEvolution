package com.lukassestic.main.genetics.crossover;

import com.lukassestic.main.neuralNetwork.NeuralNetwork;

public interface Crossover {
    NeuralNetwork cross(NeuralNetwork n1, NeuralNetwork n2);
}
