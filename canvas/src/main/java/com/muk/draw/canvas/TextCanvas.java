package com.muk.draw.canvas;

import com.muk.draw.exception.DrawingFailureException;
import com.muk.draw.shape.IShape;
import com.muk.draw.shape.Line;
import com.muk.draw.shape.Rectangle;

import java.util.Arrays;
import java.util.stream.Stream;

public class TextCanvas extends Canvas {

    private char[][] drawing;
    private int width;
    private int height;


    public TextCanvas(int width, int height) {
        this.width = width;
        this.height = height;
        drawing = new char[height + 2][width + 2];
        initiateCanvas(width + 2, height + 2);
        display();
    }

    private void initiateCanvas(int width, int height) {
        Arrays.fill(drawing[0], 0, width, '-');
        Arrays.fill(drawing[height - 1], 0, width, '-');
        for (int i = 1; i < height - 1; i++) {
            Arrays.fill(drawing[i], 1, width, ' ');
            drawing[i][0] = '|';
            drawing[i][width - 1] = '|';
        }
    }

    public void draw(Rectangle rectangle) {

        validateShapeCoordinates(rectangle);

            Arrays.fill(drawing[rectangle.coordinate(0).getY()], rectangle.coordinate(0).getX(), rectangle.coordinate(1).getX() + 1, 'x');
        Arrays.fill(drawing[rectangle.coordinate(1).getY()], rectangle.coordinate(0).getX(), rectangle.coordinate(1).getX() + 1, 'x');

        for (int i = rectangle.coordinate(0).getY() + 1; i < rectangle.coordinate(1).getY(); i++) {
            drawing[i][rectangle.coordinate(0).getX()] = 'x';
            drawing[i][rectangle.coordinate(1).getX()] = 'x';
        }
    }

    public void draw(Line line) {
        validateShapeCoordinates(line);

        if (line.coordinate(0).getX() != line.coordinate(1).getX()) {
            Arrays.fill(drawing[line.coordinate(0).getY()], line.coordinate(0).getX(), line.coordinate(1).getX() + 1, 'x');
        } else if (line.coordinate(0).getY() != line.coordinate(1).getY()) {

            for (int i = line.coordinate(0).getY(); i <= line.coordinate(1).getY(); i++) {
                drawing[i][line.coordinate(0).getX()] = 'x';
                drawing[i][line.coordinate(1).getX()] = 'x';
            }
        } else {
            drawing[line.coordinate(0).getX()][line.coordinate(0).getY()] = 'x';
        }
    }

    public void validateShapeCoordinates(IShape shape) {
        if (Stream.of(shape.coordinates()).anyMatch(c -> c.getX() < 1 || c.getY() < 1 ||
                c.getX() > this.width || c.getY() > this.height)) {
            throw new DrawingFailureException("Invalid shape coordinates. Failed to draw on text canvas.");
        }
    }

    @Override
    public void display() {
        Arrays.stream(drawing)
                .forEach(array ->
                        System.out.println(array));
    }

}
