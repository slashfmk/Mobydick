import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class WordProcessor {

    private  String[] allWords;
    private String fileContent;
    private String originalText;
    private Scanner fileScanner;

    public WordProcessor(String filePath) {

        try {
            fileScanner = new Scanner(new File(filePath));
          //  originalText = fileScanner.
            fileContent = fileScanner.useDelimiter("//A").next();
            this.processWord();
            fileScanner.close();
        } catch (IOException e) {
            System.out.println("An error has occurred: " + e.getMessage());
        }
    }

    /**
     * @Description
     * iterate through the entire array of words
     * and displays on the screen
     * @Param none
     * @Return void
     *
     */
    public void displayArray() {
        for(var word: this.allWords) {
            System.out.println(word);
        }
    }

    public void displayOriginaltext() {
        System.out.println(this.fileContent);
    }

    /**
     * @Description
     * Get rid of all necessary punctuations
     * @Param none
     * @Return void
     */
    private void stripPonctuation () {
        this.fileContent = this.fileContent.replaceAll("[,:/()”“’;!]", " ");
        this.fileContent = this.fileContent.replaceAll("\\.\\s+", " ");
        this.fileContent = this.fileContent.replaceAll("\\s+", " ");
    }

    /**
     * @Description
     * Convert the file content to an array of words
     * @Param none
     * @Return void
     */
    private void convertToArray() {
        this.allWords = this.fileContent.split(" ");
    }

    /**
     * @Description
     * process what requires
     * get rid of all punctuations
     * and convert to array
     * @Param none
     * @Return void
     */
    public void processWord () {
        stripPonctuation();
        convertToArray();
    }
}
