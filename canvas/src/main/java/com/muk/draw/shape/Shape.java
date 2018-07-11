package com.muk.draw.shape;

import java.util.Arrays;

public abstract class Shape implements IShape {

    Coordinate[] coordinates;

    public Shape(Coordinate... coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public Coordinate[] coordinates() {
        return coordinates;
    }

    @Override
    public Coordinate coordinate(int i) {
        return coordinates[i];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shape shape = (Shape) o;

        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(coordinates, shape.coordinates);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(coordinates);
    }
}
