package day4;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class Day4 {
    public static void main(String[] args) {
        part1();
        part2();
    }

    public static void part1() {
        try {
            File myObj = new File("D:\\Programming\\adventOfCode\\2023\\day4\\data.txt");
            Scanner myReader = new Scanner(myObj);

            Integer totalPoints = 0;

            while (myReader.hasNextLine()) {
                Integer points = 0;

                String card = myReader.nextLine();
                String[] temp = card.split(":");
                String[] numbers = temp[1].split("\\|");
                String winning = numbers[0].trim();
                String playing = numbers[1].trim();
                String[] _winning = winning.split(" ");
                String[] _playing = playing.split(" ");
                ArrayList<Integer> wNums = new ArrayList<Integer>();
                ArrayList<Integer> pNums = new ArrayList<Integer>();

                for (String string : _winning) {
                    if (!string.equals("")) {
                        wNums.add(Integer.parseInt(string));
                    }
                }
                for (String string : _playing) {
                    if (!string.equals("")) {
                        pNums.add(Integer.parseInt(string));
                    }
                }

                for (int i = 0; i < pNums.size(); i++) {
                    for (int j = 0; j < wNums.size(); j++) {
                        if (pNums.get(i) == wNums.get(j)) {
                            if (points == 0) {
                                points = 1;
                            } else {
                                points *= 2;
                            }
                        }
                    }
                }
                totalPoints += points;
            }
            System.out.println("total points: " + totalPoints);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void part2() {
        List<Integer> cardMatchingNums = new ArrayList<Integer>();
        try {
            File myObj = new File("D:\\Programming\\adventOfCode\\2023\\day4\\data.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String card = myReader.nextLine();
                String temp = card.split(":")[1];
                String[] numbers = temp.split("\\|");
                String[] wNums = numbers[0].trim().split("\\s+");
                String[] pNums = numbers[1].trim().split("\\s+");

                Set<String> setW = new HashSet<>();
                Set<String> setP = new HashSet<>();

                for (String num : wNums) {
                    setW.add(num);
                }
        
                for (String num : pNums) {
                    setP.add(num);
                }

                Set<String> matchingNumbers = new HashSet<>(setW);
                matchingNumbers.retainAll(setP);

                cardMatchingNums.add(matchingNumbers.size());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        int[] scrathedCards = new int[205];
        Arrays.fill(scrathedCards, 1);

        for (int i = 0; i < cardMatchingNums.size(); i++) {
            int matches = cardMatchingNums.get(i);
            for (int j = 0; j < scrathedCards[i]; j++) {
                for (int k = i + 1; k < i + matches + 1; k++) {
                    scrathedCards[k] = scrathedCards[k] + 1;
                }
            }
        }

        int totalScratched = 0;
        for (int i : scrathedCards) {
            totalScratched += i;
        }

        System.out.println("Total scratched cards: " + totalScratched);
    } 
}
