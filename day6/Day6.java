package day6;

import java.util.List;
import java.util.ArrayList;

public class Day6 {
    public static void main(String[] args) {
        long time = 48876981L;
        long dist = 255128811171623L;

        Long least = 0l;
        Long most = 0l;

        for (long j = 0; j < time; j++) {
            long distance = j * (time - j);
            if (distance > dist) {
                if (least == 0) {
                    least = j;
                } else {
                    most = j;
                }
            }
        }
        

        System.out.println("total: " + (most - least + 1));
    }
}
