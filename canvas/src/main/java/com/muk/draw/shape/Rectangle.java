package com.muk.draw.shape;

import com.muk.draw.canvas.ICanvas;

public class Rectangle extends Shape {

    public Rectangle(Coordinate... coordinates) {
        super(coordinates);
    }

    public boolean isValid() {
        return coordinates.length == 2;
    }

    @Override
    public void drawOn(ICanvas canvas) {
        canvas.drawAndDisplay(this);
    }

}
