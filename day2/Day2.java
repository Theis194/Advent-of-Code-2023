package day2;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Day2 {
    private static Integer redCubes = 12;
    private static Integer greenCubes = 13;
    private static Integer blueCubes = 14;

    public static void main(String[] args) {
        ArrayList<Boolean> gameData = new ArrayList<Boolean>();
        ArrayList<Integer> gameIds = new ArrayList<Integer>();
        ArrayList<Integer> powers = new ArrayList<Integer>();
        Integer validGames = 0;
        Integer totalPower = 0;

        try {
            File myObj = new File("D:\\Programming\\adventOfCode\\2023\\day2\\data.txt");
            Scanner myReader = new Scanner(myObj);
            Integer count = 1;
            while (myReader.hasNextLine()) {
                // Split the string into substrings
                String[] data = myReader.nextLine().split(":");
                // Check if they are legal games and insert into gameData
                if (isGamePossible(data)) {
                    gameData.add(true);
                    gameIds.add(count);
                }

                powers.add(calcGamePower(data));
                count++;
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        for (Integer integer : gameIds) {
            validGames += integer;
        }

        for (Integer integer : powers) {
            totalPower += integer;
        }
        
        System.out.println("Sum of ids: " + validGames);
        System.out.println("Total Power: " + totalPower);
    }

    private static Boolean isGamePossible(String[] data) {
        Boolean isPossible = false;
        String[] game = data[1].split(";");

        for (String string : game) {
            String[] cubes = string.split(",");
            for (String cube : cubes) {
                String[] temp = cube.split(" ");
                switch (temp[2]) {
                    case "red":
                        isPossible = Integer.parseInt(temp[1]) <= redCubes;
                        break;
                    case "blue":
                        isPossible = Integer.parseInt(temp[1]) <= blueCubes;
                        break;
                    case "green":
                        isPossible = Integer.parseInt(temp[1]) <= greenCubes;
                        break;
                }
                if (isPossible == false) {
                    return false;
                }
            }
        }

        return isPossible;
    }

    private static Integer calcGamePower(String[] data) {
        String[] game = data[1].split(";");
        Integer minReds = 0;
        Integer minGreens = 0;
        Integer minBlues = 0;

        for (String string : game) {
            String[] cubes = string.split(",");
            for (String cube : cubes) {
                String[] temp = cube.split(" ");
                switch (temp[2]) {
                    case "red":
                        if (Integer.parseInt(temp[1]) > minReds) {
                            minReds = Integer.parseInt(temp[1]);
                        }
                        break;
                    case "blue":
                        if (Integer.parseInt(temp[1]) > minBlues) {
                            minBlues = Integer.parseInt(temp[1]);
                        }
                        break;
                    case "green":
                        if (Integer.parseInt(temp[1]) > minGreens) {
                            minGreens = Integer.parseInt(temp[1]);
                        }
                        break;
                }
            }
        }

        return minReds * minBlues * minGreens;
    }
}
