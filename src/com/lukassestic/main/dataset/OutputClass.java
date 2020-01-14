package com.lukassestic.main.dataset;

import java.util.ArrayList;
import java.util.List;

public class OutputClass {
    private int id;
    private String label;
    private List<List<Integer>> xPointsSet = new ArrayList<>();
    private List<List<Integer>> yPointsSet = new ArrayList<>();

    public OutputClass(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getExampleCount() {
        return xPointsSet.size();
    }

    public int getId() {
        return id;
    }

    public String getIdOutput(int totalCount) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < totalCount; i++) {
            int val = i == id ? 1 : 0;
            s.append(val).append(" ");
        }

        return s.toString();
    }

    public String getLabel() {
        return label;
    }

    public void addXPoints(List<Integer> xPoints) {
        xPointsSet.add(xPoints);
    }

    public void addYPoints(List<Integer> yPoints) {
        yPointsSet.add(yPoints);
    }

    public List<Integer> getXsAt(int index) {
        return xPointsSet.get(index);
    }

    public List<Integer> getYsAt(int index) {
        return yPointsSet.get(index);
    }
}
