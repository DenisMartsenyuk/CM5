package ru.lab.lab5.interpolations;

import ru.lab.lab5.entities.Points;

public interface Interpolation {
    Double calcValue(Double x, Points points);
    String getName();
    String getSymbol();
}