package com.muk.draw.command;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Command {
    char command;
    List<Integer> params = new ArrayList<>();

    public Command(char command) {
        this.command = command;
    }

    public void addParam(int p) {
        params.add(p);
    }

    public int getParam(int i) {
        if (params.size() <= i) {

            throw new NoSuchElementException();
        }
        return params.get(i);
    }

    public char getCommand() {
        return command;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Command command1 = (Command) o;

        if (command != command1.command) return false;
        return params != null ? params.equals(command1.params) : command1.params == null;
    }

    @Override
    public int hashCode() {
        int result = (int) command;
        result = 31 * result + (params != null ? params.hashCode() : 0);
        return result;
    }
}
