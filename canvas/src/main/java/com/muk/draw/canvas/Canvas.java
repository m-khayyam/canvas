package com.muk.draw.canvas;

import com.muk.draw.exception.DrawingFailureException;
import com.muk.draw.shape.IShape;
import com.muk.draw.shape.Line;
import com.muk.draw.shape.Rectangle;

public abstract class Canvas implements ICanvas {

    public final void drawAndDisplay(Line line) {
        validateShap(line);
        draw(line);
        display();
    }

    public final void drawAndDisplay(Rectangle rectangle) throws DrawingFailureException {
        validateShap(rectangle);
        draw(rectangle);
        display();
    }

    public final void validateShap(IShape shape) {
        shape.isValid();
        validateShapeCoordinates(shape);
    }

}
