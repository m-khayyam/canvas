package com.muk.draw.shape;

import com.muk.draw.canvas.ICanvas;

public class Line extends Shape {

    public Line(Coordinate... coordinates) {
        super(coordinates);
    }

    public boolean isValid() {
        return coordinates.length == 2 && (coordinates[0].getX() == coordinates[1].getX()
                || coordinates[0].getY() == coordinates[1].getY());
    }

    @Override
    public void drawOn(ICanvas canvas) {
        canvas.drawAndDisplay(this);
    }

}
