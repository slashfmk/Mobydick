import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Array for capturing every word
        String[] allWords;

        try {
            // reading the file from disc
            Scanner fileScanner = new Scanner(new File("mobydick.txt"));
            // Getting all the file content
            String fileContent = fileScanner.useDelimiter("\\A").next();
            fileScanner.close();

            allWords = fileContent.split(" ");

            displayArray(allWords);
            // Checking file content
            //System.out.println(fileContent);

        } catch (IOException e) {
            System.out.println("An error has occurred: " + e.getMessage());
        }

    }

    public static void displayArray(String[] arr) {
        for(var word: arr) {
            System.out.println(word);
        }
    }
}
