package com.lukassestic.main;

import com.lukassestic.main.dataset.DatasetUtility;
import com.lukassestic.main.neuralNetwork.activations.impl.SigmoidActivation;
import com.lukassestic.main.neuralNetwork.layer.impl.Layer;

public class Main {
    public static void main(String[] args) {
        DatasetUtility d = new DatasetUtility("dataset.txt");
    }
}
