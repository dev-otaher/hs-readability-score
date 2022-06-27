package readability;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static String parseData(String filename) {
        File file = new File(filename);
        try (Scanner scanner = new Scanner(file)) {
            String text = "";
            while (scanner.hasNext()) {
                text += scanner.nextLine();
            }
            return text;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getAgeByScore(double score) {
        double minAge = 5 + Math.ceil(score);
        double maxAge = minAge + 1;
        if (Math.ceil(score) == 13) {
            maxAge = 24;
        }
        return String.format("%.0f-%.0f", minAge, maxAge);
    }

    public static void main(String[] args) {
        String text = parseData(args[0]);
        System.out.println("The text is:\n" + text);
        double words = text.split("\\s").length;
        System.out.printf("Words: %.0f\n", words);
        double sentences = text.split("[.!?]").length;
        System.out.printf("Sentences: %.0f\n", sentences);
        double characters = text.replaceAll("\\s", "").split("").length;
        System.out.printf("Characters: %.0f\n", characters);
        double score = (4.71 * (characters / words)) + (0.5 * (words / sentences)) - 21.43;
        System.out.printf("The score is: %.2f\n", score);
        System.out.printf("This text should be understood by %s-year-olds.", getAgeByScore(score));
    }
}
