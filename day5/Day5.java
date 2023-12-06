package day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.math.*;

public class Day5 {
    public static void main(String[] args) {
        part1();
        //part2();
    }

    public static void part1() {
        try {
            File myObj = new File("D:\\Programming\\adventOfCode\\2023\\day5\\data.txt");
            Scanner myReader = new Scanner(myObj);
            String[] s = myReader.nextLine().split(": ")[1].split(" ");
            BigInteger[] seeds = new BigInteger[s.length];
            for (int i = 0; i < s.length; i++) {
                seeds[i] = new BigInteger(s[i]);
            }

            String data = "";
            while (myReader.hasNextLine()) {
                data += myReader.nextLine() + "\n";
            }

            String[] blocks = data.split("\n\n");
            for (String string : blocks) {
                String[] st = string.split(":\n")[1].split("\n");
                List<BigInteger[]> lines = new ArrayList<BigInteger[]>();
                for (String string2 : st) {
                    String[] l = string2.replace("\n", " ").split(" ");
                    BigInteger[] line = new BigInteger[l.length];
                    for (int i = 0; i < l.length; i++) {
                        line[i] = new BigInteger(l[i]);
                    }
                    lines.add(line);
                }
                
                List<BigInteger> al = new ArrayList<BigInteger>();
                for (BigInteger seed : seeds) {
                    found: {
                        for (int i = 0; i < lines.size(); i++) {
                            BigInteger[] x = lines.get(i);
                            if (x[1].compareTo(seed) <= 0 && seed.compareTo(x[1].add(x[2])) == -1) {
                                al.add((seed.subtract(x[1].add(x[0]))));
                                break found;
                            }
                        }
                        al.add(seed);
                    }
                    seeds = al.toArray(new BigInteger[al.size()]);
                }
                System.out.println("yo");
            }

            BigInteger min = new BigInteger("0");
            for (BigInteger i : seeds) {
                if(i.compareTo(min) == -1) {
                    min = i;
                }
            }

            System.out.println("min value: " + min);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void part2() {
        try {
            File myObj = new File("D:\\Programming\\adventOfCode\\2023\\day5\\data.txt");
            Scanner myReader = new Scanner(myObj);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
