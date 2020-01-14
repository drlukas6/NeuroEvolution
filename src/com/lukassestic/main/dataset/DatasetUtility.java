package com.lukassestic.main.dataset;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class DatasetUtility {
    private String filename;

    private List<double[]> ins;
    private List<double[]> outs;

    private List<Integer> indexes;

    public DatasetUtility(String filename) {
        this.filename = filename;
        read();
    }

    private void read() {
        ins = new ArrayList<>();
        outs = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));
            for(String line: lines) {
                List<Double> lineListSplit = Arrays.stream(line.split("\\s+")).map(Double::parseDouble).collect(Collectors.toList());
                double[] in = new double[]{lineListSplit.get(0), lineListSplit.get(1)};
                double[] out = new double[]{lineListSplit.get(2), lineListSplit.get(3), lineListSplit.get(4)};
                ins.add(in);
                outs.add(out);
            }
        } catch (Exception e) {
            System.err.println(e);
        }

        indexes = new ArrayList<>(ins.size());

        for (int i = 0; i < outs.size(); i++) {
            indexes.add(i);
        }
    }

    public List<double[]> getIns() {
        return ins;
    }

    public List<double[]> getOuts() {
        return outs;
    }

    public void shuffle() {
        Collections.shuffle(indexes);
    }

    public double[] getInputAt(int i) {
        return ins.get(indexes.get(i));
    }

    public double[] getOutputAt(int i) {
        return outs.get(indexes.get(i));
    }
}
