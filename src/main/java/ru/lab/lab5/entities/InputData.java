package ru.lab.lab5.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InputData {
    private Points tablePoints;
    private List<Double> findPoints;

    public InputData() {
        tablePoints = new Points();
        findPoints = new ArrayList<>();
    }

    public void addTablePoint(Double x, Double y) {
        tablePoints.addPoint(x, y);
    }

    public void addFindPoint(Double point) {
        findPoints.add(point);
    }

    public Points getTablePoints() {
        return tablePoints;
    }

    public List<Double> getFindPoints() {
        return findPoints;
    }

    public Double getMinX() {
        return Math.min(Collections.min(findPoints), tablePoints.getMinX());
    }

    public Double getMaxX() {
        return Math.max(Collections.max(findPoints), tablePoints.getMaxX());
    }
}
