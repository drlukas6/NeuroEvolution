package com.lukassestic.main.neuralNetwork;


import com.lukassestic.main.dataset.DatasetUtility;

public class NNTest {
    public static void main(String[] args) {
        double[][] inputs = new double[][]{
                new double[]{-1},
                new double[]{-0.8},
                new double[]{-0.6},
                new double[]{-0.4},
                new double[]{-0.2},
                new double[]{0},
                new double[]{0.2},
                new double[]{0.4},
                new double[]{0.6},
                new double[]{0.8},
                new double[]{1}
        };

        double[][] outputs = new double[][] {
                new double[]{1},
                new double[]{0.64},
                new double[]{0.36},
                new double[]{0.16},
                new double[]{0.04},
                new double[]{0},
                new double[]{0.04},
                new double[]{0.16},
                new double[]{0.36},
                new double[]{0.64},
                new double[]{1}
        };

        DatasetUtility d = new DatasetUtility("dataset1");
        d.read();

//        NeuralNetwork nn = new NeuralNetwork(100000, 0.2, d.getIns(), d.getOuts(), new int[]{40, 5, 5, 5});
//        NeuralNetwork nn = new NeuralNetwork(10000, 0.2, d.getIns(), d.getOuts());
//        nn.addLayer(40, )
//                .addLayer(5)
//                .addLayer(5)
//                .addLayer(5);
//        nn.fit(d.getnSamples());
    }
}
