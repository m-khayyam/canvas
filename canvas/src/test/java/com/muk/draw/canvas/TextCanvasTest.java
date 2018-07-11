package com.muk.draw.canvas;

import com.muk.draw.shape.Coordinate;
import com.muk.draw.shape.Line;
import com.muk.draw.shape.Rectangle;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;


public class TextCanvasTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    TextCanvas canvasUnderTest;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void createsCanvas() throws Exception {
        //when
        canvasUnderTest = new TextCanvas(20, 5);

        //then
        assertEquals("----------------------\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "----------------------\n",
                outContent.toString());
    }

    @Test
    public void drawsLine() throws Exception {
        // given
        canvasUnderTest = new TextCanvas(20, 5);
        outContent.reset();

        //when
        canvasUnderTest.drawAndDisplay(new Line(new Coordinate(1, 3), new Coordinate(7, 3)));

        //then
        assertEquals("----------------------\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "|xxxxxxx             |\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "----------------------\n",
                outContent.toString());
    }

    @Test
    public void drawsVerticalLine() throws Exception {
        // given
        canvasUnderTest = new TextCanvas(20, 5);
        outContent.reset();

        //when
        canvasUnderTest.drawAndDisplay(new Line(new Coordinate(7, 1), new Coordinate(7, 3)));

        //then
        assertEquals("----------------------\n" +
                        "|      x             |\n" +
                        "|      x             |\n" +
                        "|      x             |\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "----------------------\n",
                outContent.toString());
    }

    @Test
    public void drawsRectangle() throws Exception {
        // given
        canvasUnderTest = new TextCanvas(20, 5);
        outContent.reset();

        //when
        canvasUnderTest.drawAndDisplay(new Rectangle(new Coordinate(15, 2), new Coordinate(20, 5)));

        //then
        assertEquals("----------------------\n" +
                        "|                    |\n" +
                        "|              xxxxxx|\n" +
                        "|              x    x|\n" +
                        "|              x    x|\n" +
                        "|              xxxxxx|\n" +
                        "----------------------\n",
                outContent.toString());
    }

    @Test
    public void allShapesAreDisplayedOnCanvasWhenNewShapeIsAdded() throws Exception {
        // given
        canvasUnderTest = new TextCanvas(20, 5);

        //when
        canvasUnderTest.drawAndDisplay(new Line(new Coordinate(1, 3), new Coordinate(7, 3)));
        canvasUnderTest.drawAndDisplay(new Line(new Coordinate(7, 1), new Coordinate(7, 3)));
        outContent.reset();
        canvasUnderTest.drawAndDisplay(new Rectangle(new Coordinate(15, 2), new Coordinate(20, 5)));

        //then
        assertEquals("----------------------\n" +
                        "|      x             |\n" +
                        "|      x       xxxxxx|\n" +
                        "|xxxxxxx       x    x|\n" +
                        "|              x    x|\n" +
                        "|              xxxxxx|\n" +
                        "----------------------\n",
                outContent.toString());
    }


}