package ru.lab.lab5.utils;

import ru.lab.lab5.entities.InputData;
import ru.lab.lab5.entities.Points;
import ru.lab.lab5.interpolations.Interpolation;
import ru.lab.lab5.interpolations.InterpolationLagrange;
import ru.lab.lab5.interpolations.InterpolationNewton;

import java.util.ArrayList;
import java.util.List;

public class Solver {

    private final Painter painter;
    private final List<Interpolation> interpolations;

    private final double STEP = 0.1;
    private double minX;
    private double maxX;

    public Solver() {
        painter = new Painter();
        interpolations = new ArrayList<>();
        interpolations.add(new InterpolationLagrange());
        interpolations.add(new InterpolationNewton());
    }

    public void solve(InputData inputData) {
        minX = inputData.getMinX();
        maxX = inputData.getMaxX();
        painter.addPrimitive(inputData.getTablePoints(), "Исходные точки");

        Points tablePoints = inputData.getTablePoints();
        List<Double> findPoints = inputData.getFindPoints();
        for (Interpolation interpolation : interpolations) {
            System.out.println("Ответы для полинома " + interpolation.getName() + "а:");
            for (Double x : findPoints) {
                Double value = interpolation.calcValue(x, tablePoints);
                System.out.printf("\tx = %.3f ", x);
                System.out.print(interpolation.getSymbol() + "(x) = ");
                System.out.printf("%.3f\n", value);
            }
            painter.addPrimitive(getPointsForCurve(interpolation, tablePoints), interpolation.getName());
        }

        painter.openGraph();
    }

    public Points getPointsForCurve(Interpolation interpolation, Points tablePoints) {
        Points points = new Points();
        for (double i = minX; i <= maxX; i += STEP) {
            Double y = interpolation.calcValue(i, tablePoints);
            if (y.isNaN() || y.isInfinite()) {
                continue;
            }
            points.addPoint(i, y);
        }
        return points;
    }
}
