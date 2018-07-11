package com.muk.draw.canvas;

import com.muk.draw.shape.IShape;
import com.muk.draw.shape.Line;
import com.muk.draw.shape.Rectangle;

public interface ICanvas {
    void draw(Rectangle rectangle);

    void draw(Line line);

    void drawAndDisplay(Line line);

    void drawAndDisplay(Rectangle rectangle);

    void display();

    void validateShapeCoordinates(IShape shape);
}
