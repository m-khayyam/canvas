package com.muk.draw.command;

import com.muk.draw.exception.DrawingFailureException;
import com.muk.draw.exception.InvalidCommandException;
import com.muk.draw.shape.Coordinate;
import com.muk.draw.canvas.ICanvas;
import com.muk.draw.canvas.TextCanvas;
import com.muk.draw.shape.IShape;
import com.muk.draw.shape.ShapeFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class CommandExecutor {
    private Optional<ICanvas> canvas = Optional.empty();

    public void execute(BufferedReader br) {
        String userInput = null;
        do {
            System.out.print("Enter Command: ");
            try {
                userInput = br.readLine();
                executeCommand(getCommand(userInput));
            } catch (DrawingFailureException | InvalidCommandException | IOException e) {
                System.err.println(e.getMessage());
            }
        } while (!userInput.equals("Q"));
    }

    public void executeCommand(Command command) {
        try {
            switch (command.getCommand()) {
                case 'Q':
                    return;
                case 'R':
                case 'L':
                    drawShape(command);
                    break;
                case 'C':
                    createCanvas(command.getParam(0), command.getParam(1));
                    break;
                default:
                    throw new InvalidCommandException("Un recognised command: " + command.getCommand());
            }
        } catch (IllegalStateException e) {
            System.out.println("Illegal state. Please create canvas before drawing shapes.");
        } catch (NoSuchElementException e) {
            throw new InvalidCommandException("Invalid Command. Not all required parameters are provided.", e.getCause());
        }

    }

    private void createCanvas(int width, int height) {
        if (width < 3 || height < 3) {
            throw new InvalidCommandException("Invalid Command: Canvas coordinates are not valid.");
        }
        canvas = Optional.ofNullable(new TextCanvas(width, height));
    }

    private void drawShape(Command command) {
        Optional<IShape> shape = ShapeFactory.getInstance(command.getCommand(),
                new Coordinate(command.getParam(0), command.getParam(1)),
                new Coordinate(command.getParam(2), command.getParam(3)));

        if (shape.isPresent()) {
            shape.get().drawOn(canvas.orElseThrow(IllegalStateException::new));
        }
    }


    private Command getCommand(String input) {

        try (Scanner scanner = new Scanner(input)) {

            Command command = new Command(scanner.next().charAt(0));

            while (scanner.hasNext()) {
                int paramValue = scanner.nextInt();
                command.addParam(paramValue);
            }
            return command;
        } catch (InputMismatchException | NumberFormatException e) {
            throw new InvalidCommandException("Invalid Command", e.getCause());
        } catch (NoSuchElementException n) {
            throw new InvalidCommandException("Invalid Command", n.getCause());
        }
    }


}