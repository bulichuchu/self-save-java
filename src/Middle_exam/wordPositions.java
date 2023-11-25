package Middle_exam;

import java.util.Scanner;

public class wordPositions {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            String sentence = input.nextLine();
            String[] words = sentence.split(" ");
            for (String word : words) {
                System.out.println(word + ": " + sentence.indexOf(word) + ", " + word.length());
            }
        }
    }
}
