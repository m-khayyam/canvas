package com.muk.draw.command;

import com.muk.draw.canvas.TextCanvas;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(Parameterized.class)
public class CommandExecutorTest {


    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private static final int CANVAS_HEIGHT = 5;
    private static final int CANVAS_WIDTH = 20;

    private final CommandExecutor commandExecutorUnderTest;
    private final BufferedReader mockedBufferedReader = mock(BufferedReader.class);

    private Scenario scenario;


    @Before
    public void setUp() throws IOException {
        System.setOut(new PrintStream(outContent));
        when(mockedBufferedReader.readLine()).thenReturn(String.format("C %d %d", CANVAS_WIDTH, CANVAS_HEIGHT), "Q");
        commandExecutorUnderTest.execute(mockedBufferedReader);
        outContent.reset();
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Parameterized.Parameters(name = "{index}: {0}")
    public static Iterable<Scenario> data() {
        return Arrays.asList(
                new Scenario("L 1 3 7 3", "Enter Command: " +
                        "----------------------\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "|xxxxxxx             |\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "----------------------\n" +
                        "Enter Command: " +
                        ""),
                new Scenario("L 7 1 7 3", "Enter Command: " +
                        "----------------------\n" +
                        "|      x             |\n" +
                        "|      x             |\n" +
                        "|      x             |\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "----------------------\n" +
                        "Enter Command: " +
                        ""),
                new Scenario("R 15 2 20 5", "Enter Command: " +
                        "----------------------\n" +
                        "|                    |\n" +
                        "|              xxxxxx|\n" +
                        "|              x    x|\n" +
                        "|              x    x|\n" +
                        "|              xxxxxx|\n" +
                        "----------------------\n" +
                        "Enter Command: " +
                        ""),
                new Scenario("Q", "Enter Command: " +
                        "")
        );
    }

    public CommandExecutorTest(Scenario scenario) {
        this.scenario = scenario;
        commandExecutorUnderTest = new CommandExecutor();
    }

    @Test
    public void runScenario() throws IOException {
        //given
        when(mockedBufferedReader.readLine()).thenReturn(scenario.command, "Q");

        //when
        commandExecutorUnderTest.execute(mockedBufferedReader);

        //then
        assertEquals(scenario.expectedDrawing, outContent.toString());
    }

    static class Scenario {
        String command;
        String expectedDrawing;

        public Scenario(String command, String expectedDrawing) {
            this.command = command;
            this.expectedDrawing = expectedDrawing;
        }
    }
}
