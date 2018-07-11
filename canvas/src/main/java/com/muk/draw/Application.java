package com.muk.draw;

import com.muk.draw.command.CommandExecutor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.in;

public class Application {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            new CommandExecutor().execute(br);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}