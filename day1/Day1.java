package day1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day1 {
    //////////////////////////////////////////////// Gets a too high value but only on my full set
    public static void main(String[] args) {
        Map<String, Integer> stringNumbers = new HashMap<String, Integer>();
        stringNumbers.put("one", 1);
        stringNumbers.put("two", 2);
        stringNumbers.put("three", 3);
        stringNumbers.put("four", 4);
        stringNumbers.put("five", 5);
        stringNumbers.put("six", 6);
        stringNumbers.put("seven", 7);
        stringNumbers.put("eight", 8);
        stringNumbers.put("nine", 9);
        
        ArrayList<Integer> numbers = findNumbers(stringNumbers);
        Integer result = 0;
        for (Integer integer : numbers) {
            result += integer;
        }
        System.out.println("result: " +  result);
    }

    private static ArrayList<Integer> findNumbers(Map<String, Integer> stringNumbers) {
        String[] numbers = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        ArrayList<Integer> temp = new ArrayList<Integer>();
        try {
            File myObj = new File("D:\\Programming\\adventOfCode\\2023\\day1\\data.txt");
            Scanner myReader = new Scanner(myObj);
            Integer first = -1;
            Integer indexOfFirst = -1;
            Integer last = -1;
            Integer indexOfLast = -1;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                for (int i = 0; i < data.length(); i++) {
                    if ((Character.isDigit(data.charAt(i)) && first == -1)) {
                        first = Character.getNumericValue(data.charAt(i));
                        indexOfFirst = i;
                    } else if (Character.isDigit(data.charAt(i)) && i > indexOfLast) {
                        last = Character.getNumericValue(data.charAt(i));
                        indexOfLast = i;
                    }
                }
                for (String s : numbers) {
                    if ((data.indexOf(s) != -1 && data.indexOf(s) < indexOfFirst) || (data.indexOf(s) != -1 && indexOfFirst == -1)) {
                        first = stringNumbers.get(s);
                        indexOfFirst = data.indexOf(s);
                    } else if (data.indexOf(s) != -1  && data.indexOf(s) > indexOfLast) {
                        last = stringNumbers.get(s);
                        indexOfLast = data.indexOf(s);
                    }
                }
                for (String s : numbers) {
                    if ((data.lastIndexOf(s) != -1 && data.lastIndexOf(s) < indexOfFirst) || (data.lastIndexOf(s) != -1 && indexOfFirst == -1)) {
                        first = stringNumbers.get(s);
                        indexOfFirst = data.lastIndexOf(s);
                    } else if (data.lastIndexOf(s) != -1  && data.lastIndexOf(s) > indexOfLast) {
                        last = stringNumbers.get(s);
                        indexOfLast = data.lastIndexOf(s);
                    }
                }
                for (int i = 0; i < data.length(); i++) {
                    if ((Character.isDigit(data.charAt(i)) && first == -1)) {
                        first = Character.getNumericValue(data.charAt(i));
                        indexOfFirst = i;
                    } else if (Character.isDigit(data.charAt(i)) && i > indexOfLast) {
                        last = Character.getNumericValue(data.charAt(i));
                        indexOfLast = i;
                    }
                }
                if (first == -1) {
                    temp.add(first);
                }

                if (first >= 0 && last == -1) {
                    last = first;
                    temp.add(first * 10 + last);
                } else {
                    temp.add(first * 10 + last);
                }
                first = -1;
                last = -1;
                indexOfFirst = -1;
                indexOfLast = -1;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return temp;
    }
}
