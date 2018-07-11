package com.muk.draw.canvas;

import com.muk.draw.exception.DrawingFailureException;
import com.muk.draw.shape.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

@RunWith(Parameterized.class)
public class TextCanvasDrawingFailuresTest {
    private static final int CANVAS_HEIGHT = 5;
    private static final int CANVAS_WIDTH = 20;

    private Scenario scenario;
    private final TextCanvas canvasUnderTest;

    @Parameterized.Parameters(name = "{index}: {0}")
    public static Iterable<Scenario> data() {
        int START_INDEX = 1;
        return Arrays.asList(
                new Scenario('L', new Coordinate(START_INDEX, START_INDEX - 1), new Coordinate(CANVAS_WIDTH, CANVAS_HEIGHT)),
                new Scenario('L', new Coordinate(START_INDEX - 1, START_INDEX), new Coordinate(CANVAS_WIDTH, CANVAS_HEIGHT)),
                new Scenario('L', new Coordinate(START_INDEX, START_INDEX), new Coordinate(CANVAS_WIDTH, START_INDEX - 1)),
                new Scenario('L', new Coordinate(START_INDEX, START_INDEX), new Coordinate(START_INDEX - 1, CANVAS_HEIGHT)),

                new Scenario('L', new Coordinate(START_INDEX, START_INDEX), new Coordinate(CANVAS_WIDTH + 1, CANVAS_HEIGHT)),
                new Scenario('L', new Coordinate(START_INDEX, START_INDEX), new Coordinate(CANVAS_WIDTH, CANVAS_HEIGHT + 1)),
                new Scenario('L', new Coordinate(CANVAS_WIDTH + 1, CANVAS_HEIGHT), new Coordinate(CANVAS_WIDTH, CANVAS_HEIGHT)),
                new Scenario('L', new Coordinate(CANVAS_WIDTH, CANVAS_HEIGHT + 1), new Coordinate(CANVAS_WIDTH, CANVAS_HEIGHT)),

                new Scenario('R', new Coordinate(START_INDEX, START_INDEX - 1), new Coordinate(CANVAS_WIDTH, CANVAS_HEIGHT)),
                new Scenario('R', new Coordinate(START_INDEX - 1, START_INDEX), new Coordinate(CANVAS_WIDTH, CANVAS_HEIGHT)),
                new Scenario('R', new Coordinate(START_INDEX, START_INDEX), new Coordinate(CANVAS_WIDTH, START_INDEX - 1)),
                new Scenario('R', new Coordinate(START_INDEX, START_INDEX), new Coordinate(START_INDEX - 1, CANVAS_HEIGHT)),

                new Scenario('R', new Coordinate(1, 1), new Coordinate(CANVAS_WIDTH + 1, CANVAS_HEIGHT)),
                new Scenario('R', new Coordinate(1, 1), new Coordinate(CANVAS_WIDTH, CANVAS_HEIGHT + 1)),
                new Scenario('R', new Coordinate(CANVAS_WIDTH + 1, CANVAS_HEIGHT), new Coordinate(CANVAS_WIDTH, CANVAS_HEIGHT)),
                new Scenario('R', new Coordinate(CANVAS_WIDTH, CANVAS_HEIGHT + 1), new Coordinate(CANVAS_WIDTH, CANVAS_HEIGHT))
        );
    }

    public TextCanvasDrawingFailuresTest(Scenario scenario) {
        this.scenario = scenario;
        canvasUnderTest = new TextCanvas(CANVAS_WIDTH, CANVAS_HEIGHT);
    }

    @Test(expected = DrawingFailureException.class)
    public void runScenario() {
        switch (scenario.shapeType) {
            case 'L':
                canvasUnderTest.draw((Line) scenario.shape);
                break;
            case 'R':
                canvasUnderTest.draw((Rectangle) scenario.shape);
                break;
        }
    }

    static class Scenario {
        public IShape shape;
        char shapeType;

        public Scenario(char shapeType, Coordinate... coordinates) {
            this.shape = ShapeFactory.getInstance(shapeType, coordinates).get();
            this.shapeType = shapeType;
        }
    }
}