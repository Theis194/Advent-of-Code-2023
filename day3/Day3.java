package day3;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Day3 { //////////////////////////////////////////// part 2 is half done almost, it
                    //////////////////////////////////////////// sometimes gets -1 as a result from
                    //////////////////////////////////////////// finding first number
    public static Character[][] manual = new Character[140][140];

    public static void main(String[] args) {
        loadFile();
        part1();
        part2();
    }

    public static void loadFile() {
        try {
            File myObj = new File("D:\\Programming\\adventOfCode\\2023\\day3\\data.txt");
            Scanner myReader = new Scanner(myObj);

            // Fill out the 2d array
            int x = 0;
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                for (int i = 0; i < line.length(); i++) {
                    int y = i;
                    manual[x][y] = line.charAt(i);
                }
                x++;
            }
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void part1() {
        Integer sumOfParts = 0;
        Integer numberBuilder = 0;
        Boolean isPart = false;
        for (int i = 0; i < 140; i++) {
            for (int j = 0; j < 140; j++) {
                if (Character.isDigit(manual[i][j])) {
                    if (numberBuilder == 0) {
                        numberBuilder += Character.getNumericValue(manual[i][j]);
                        if (isPart == false) {
                            isPart = checksurrounding(i, j);
                        }
                    } else {
                        numberBuilder = ((numberBuilder * 10) + Character.getNumericValue(manual[i][j]));
                        if (isPart == false) {
                            isPart = checksurrounding(i, j);
                        }
                    }
                }
                if ((i >= 0 && j - 1 >= 0)
                        && (Character.isDigit(manual[i][j - 1]) && (!(Character.isDigit(manual[i][j]))))) {
                    if (isPart) {
                        sumOfParts += numberBuilder;
                    }
                    numberBuilder = 0;
                    isPart = false;
                }
            }
            if (isPart) {
                sumOfParts += numberBuilder;
            }
            numberBuilder = 0;
            isPart = false;
        }

        System.out.println("Sum of Parts: " + sumOfParts);
    }

    public static void part2() {
        Integer sumOfGearRatios = 0;
        // Find all "*"
        for (int i = 0; i < 140; i++) {
            for (int j = 0; j < 140; j++) {
                if (manual[i][j].equals('*')) {
                    Integer first = 0;
                    Integer second = 0;

                    int startX = Math.max(0, i - 1);
                    int endX = Math.min(139, i + 1);
                    int startY = Math.max(0, j - 1);
                    int endY = Math.min(139, j + 1);

                    for (int k = startX; k <= endX; k++) {
                        for (int l = startY; l <= endY; l++) {
                            // Exclude the current position (x, y) from the check
                            if (k != i || l != j) {
                                if (Character.isDigit(manual[k][l])) {
                                    if (first == 0) {
                                        first = getNumber(k, findNumStart(k, l));
                                    } else if (getNumber(k, findNumStart(k, l)) != first) {
                                        second = getNumber(k, findNumStart(k, l));
                                    }
                                }
                            }
                        }
                    }
                    
                    if (first != 0 && second != 0) {
                        sumOfGearRatios += (first * second);
                    }
                    if (first == second) {
                        System.out.println("first: " + first + " second: " + second);
                        System.out.println("i: " + i + " j: " + j + " " + manual[i][j]);
                    }
                }
            }
        }

        System.out.println("Sum of Gearratios: " + sumOfGearRatios);
    }

    // returns a found number, takes index of left most number in number
    public static Integer getNumber(int x, int startY) {
        Integer result = 0;
        if (startY <= 139 && startY >= 2) {
            while (startY < 139 && Character.isDigit(manual[x][startY])) {
                if (result == 0) {
                    result += Character.getNumericValue(manual[x][startY]);
                } else {
                    result = result * 10 + Character.getNumericValue(manual[x][startY]);
                }
                startY++;
            }
        }

        return result;
    }

    // returns the index of the start of a number
    public static Integer findNumStart(int x, int y) {
        if (y > 2) {
            while (Character.isDigit(manual[x][y]) && y >= 0) {
                y--;
            }
        }
        return y + 1;
    }

    public static Boolean checksurrounding(int x, int y) {
        int startX = Math.max(0, x - 1);
        int endX = Math.min(139, x + 1);
        int startY = Math.max(0, y - 1);
        int endY = Math.min(139, y + 1);

        for (int i = startX; i <= endX; i++) {
            for (int j = startY; j <= endY; j++) {
                // Exclude the current position (x, y) from the check
                if (i != x || j != y) {
                    if (!Character.isDigit(manual[i][j]) && manual[i][j] != '.') {
                        return true; // Found adjacent symbol
                    }
                }
            }
        }

        return false;
    }
}
