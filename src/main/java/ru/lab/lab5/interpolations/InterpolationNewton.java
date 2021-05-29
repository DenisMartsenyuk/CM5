package ru.lab.lab5.interpolations;

import ru.lab.lab5.entities.Points;

import java.util.List;

public class InterpolationNewton implements Interpolation {
    @Override
    public Double calcValue(Double x, Points points) {
        List<Double> pointsX = points.getPointsX();
        List<Double> pointsY = points.getPointsY();
        Double h = pointsX.get(1) - pointsX.get(0);
        Double t = (x - pointsX.get(0)) / h;
        Double multiplier = 1.0;
        Double value = 0.0;
        Double denominator = 1.0;
        for (int i = 0; i < pointsX.size(); ++i) {
            if (i != 0) {
                denominator *= i;
                multiplier *= (t - i + 1);
            }
            value += multiplier * getFiniteDifferenceByFirst(i, 0, pointsY) / denominator;
        }
        return value;
    }

     private Double getFiniteDifferenceByFirst (int k, int i, List<Double> pointsY) {
        if (k == 0) {
            return pointsY.get(i);
        }
        return getFiniteDifferenceByFirst(k - 1, i + 1, pointsY) - getFiniteDifferenceByFirst(k - 1, i, pointsY);
     }

    @Override
    public String getName() {
        return "Ньютон";
    }

    @Override
    public String getSymbol() {
        return "N";
    }
}
