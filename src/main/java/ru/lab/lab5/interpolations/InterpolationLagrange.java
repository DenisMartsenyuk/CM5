package ru.lab.lab5.interpolations;

import ru.lab.lab5.entities.Points;

import java.util.List;

public class InterpolationLagrange implements Interpolation {
    @Override
    public Double calcValue(Double x, Points points) {
        List<Double> pointsX = points.getPointsX();
        List<Double> pointsY = points.getPointsY();
        Double value = 0.0;
        for (int i = 0; i < pointsX.size(); ++i) {
            Double l = 1.0;
            for (int j = 0; j < pointsX.size(); ++j) {
                if (i != j) {
                    l *= ((x - pointsX.get(j)) / (pointsX.get(i) - pointsX.get(j)));
                }
            }
            l *= pointsY.get(i);
            value += l;
        }
        return value;
    }

    @Override
    public String getName() {
        return "Лагранж";
    }

    @Override
    public String getSymbol() {
        return "L";
    }
}
