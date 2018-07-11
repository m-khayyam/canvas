package com.muk.draw.shape;

import java.util.Optional;

public class ShapeFactory {

    public static Optional<IShape> getInstance(char shape, Coordinate... coordinates) {

        switch (shape) {
            case 'L':
                return Optional.of(new Line(coordinates[0], coordinates[1]));
            case 'R':
                return Optional.of(new Rectangle(coordinates[0], coordinates[1]));
            default:
                return Optional.empty();
        }
    }
}
