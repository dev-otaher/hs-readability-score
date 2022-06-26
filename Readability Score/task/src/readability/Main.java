package readability;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        String[] sentences = text.split("[.!?]");
        int wordCount = 0;
        for (String sentence : sentences) {
            String[] tokens = sentence.split("\\s");
            wordCount += tokens.length;
        }
        int avg = wordCount / sentences.length;
        System.out.println(avg > 10 ? "HARD" : "EASY");
    }
}
