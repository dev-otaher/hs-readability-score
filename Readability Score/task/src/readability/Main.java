package readability;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
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

    public static double calculateFK(double words, double sentences, double syllables) {
        return 0.39 * (words / sentences) + 11.8 * (syllables / words) - 15.59;
    }

    public static double calculateSMOG(double polysyllables, double sentences) {
        return 1.043 * Math.sqrt(polysyllables * (30 / sentences)) + 3.1291;
    }

    public static double calculateCL(double words, double sentences, double syllables) {
        return 0.39 * (words / sentences) + 11.8 * (syllables / words) - 15.59;
    }

    public static double calculateARI(double words, double sentences, double characters) {
        return 4.71 * (characters / words) + 0.5 * (words / sentences) - 21.43;
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
//        String text = parseData(args[0]);
        String text = parseData("in.txt");
        System.out.println("The text is:\n" + text);
        double words = text.split("\\s").length;
        System.out.printf("Words: %.0f\n", words);
        double sentences = text.split("[.!?]").length;
        System.out.printf("Sentences: %.0f\n", sentences);
        double characters = text.replaceAll("\\s", "").split("").length;
        System.out.printf("Characters: %.0f\n", characters);

        String syllables = text.replaceAll("e\\b", "")
                .replaceAll("[.!?]", "")
                .replaceAll("au", "a")
                .replaceAll("oy", "a")
                .replaceAll("oo", "a")
                .replaceAll("iou", "a")
                .replaceAll("\\B[^aeiou]le|\\B[^aeiou]les", "a");
        System.out.println(Arrays.toString(syllables.split("[aieuo]")));
        System.out.println("Syllables Count = " + syllables.split("[aieou]").length);
        double score = calculateARI(words, sentences, characters);
        System.out.printf("The score is: %.2f\n", score);
        System.out.printf("This text should be understood by %s-year-olds.", getAgeByScore(score));
    }
}
