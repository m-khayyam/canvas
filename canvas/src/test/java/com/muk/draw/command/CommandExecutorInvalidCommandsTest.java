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
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(Parameterized.class)
public class CommandExecutorInvalidCommandsTest {
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalErr = System.err;

    private static final int CANVAS_HEIGHT = 5;
    private static final int CANVAS_WIDTH = 20;

    private final CommandExecutor commandExecutorUnderTest;
    private final BufferedReader mockedBufferedReader = mock(BufferedReader.class);

    private Scenario scenario;

    @Before
    public void setUpStreams() {

        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {

        System.setErr(originalErr);
    }

    @Parameterized.Parameters(name = "{index}: {0}")
    public static Iterable<Scenario> data() {
        return Arrays.asList(
                new Scenario("C", "Invalid Command. Not all required parameters are provided."),
                new Scenario("C 10 -5", "Invalid Command: Canvas coordinates are not valid."),
                new Scenario("C -5 10", "Invalid Command: Canvas coordinates are not valid."),
                new Scenario("C 0 5", "Invalid Command: Canvas coordinates are not valid."),
                new Scenario("C 5 0", "Invalid Command: Canvas coordinates are not valid."),

                new Scenario("L", "Invalid Command. Not all required parameters are provided."),
                new Scenario("L 7 3", "Invalid Command. Not all required parameters are provided."),
                new Scenario("L 3 7 3", "Invalid Command. Not all required parameters are provided."),

                new Scenario("R", "Invalid Command. Not all required parameters are provided."),
                new Scenario("R 20 5", "Invalid Command. Not all required parameters are provided."),
                new Scenario("R 2 20 5", "Invalid Command. Not all required parameters are provided."),

                new Scenario("W", "Un recognised command: W"),

                new Scenario("c 5 5", "Un recognised command: c"),
                new Scenario("q", "Un recognised command: q"),
                new Scenario("l 2 2 2 8", "Un recognised command: l"),
                new Scenario("r 2 2 4 4", "Un recognised command: r")
        );
    }

    public CommandExecutorInvalidCommandsTest(Scenario scenario) throws IOException {
        this.scenario = scenario;
        commandExecutorUnderTest = new CommandExecutor();
        when(mockedBufferedReader.readLine()).thenReturn(String.format("C %d %d", CANVAS_WIDTH, CANVAS_HEIGHT), "Q");
        commandExecutorUnderTest.execute(mockedBufferedReader);
    }
    @Test
    public void runScenario() throws IOException {
        //given
        when(mockedBufferedReader.readLine()).thenReturn(scenario.command, "Q");

        //when
        commandExecutorUnderTest.execute(mockedBufferedReader);

        //then
        assertTrue(errContent.toString().contains(scenario.expectedErrorLog));
    }

    static class Scenario {
        String command;
        String expectedErrorLog;

        public Scenario(String command, String expectedDrawing) {
            this.command = command;
            this.expectedErrorLog = expectedDrawing;
        }
    }
}
