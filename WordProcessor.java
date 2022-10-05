import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WordProcessor {

    private String[] allWords;
    private String fileContent;
    private HashMap<String, Integer> uniqueWords;

    public WordProcessor(String filePath) {

        try {
            Scanner fileScanner = new Scanner(new File(filePath));
            uniqueWords = new HashMap<String, Integer>();
            fileContent = fileScanner.useDelimiter("//A").next();
            this.processWord();
            fileScanner.close();
        } catch (IOException e) {
            System.out.println("An error has occurred: " + e.getMessage());
        }
    }

    /**
     * @Description iterate through the entire array of words
     * and displays on the screen
     * @Param none
     * @Return void
     */
    public void displayArray() {
        for (var word : this.allWords) {
            System.out.println(word);
        }
    }

    public void displayOriginaltext() {
        System.out.println(this.fileContent);
    }

    /**
     * @Description Get rid of all necessary punctuations
     * @Param none
     * @Return void
     */
    private void stripPonctuation() {
        // —
        this.fileContent = this.fileContent.replaceAll("[,:/()”“‘’;!?—]", " ");
        this.fileContent = this.fileContent.replaceAll("\\.\\s+", " ");
        this.fileContent = this.fileContent.replaceAll("\\s+", " ");
    }

    /**
     * @Description Convert the file content to an array of words
     * @Param none
     * @Return void
     */
    private void convertToArray() {
        this.allWords = this.fileContent.split(" ");
    }

    private void wordUniqueness() {
        for (var word : this.allWords) {
            if (this.uniqueWords.containsKey(word)) {
                this.uniqueWords.put(word, uniqueWords.get(word) + 1);
            } else {
                this.uniqueWords.put(word, 1);
            }
        }
    }


    /**
     *
     * @param w:HasMap<String, Integer>
     */
    private void displayUniqueWordCount(HashMap<String, Integer> w) {
        for (var o : w.entrySet()) {
            System.out.println(o.getKey() + " | " + o.getValue());
        }
    }

    /**
     * @description
     * Displays the first 100 most occurent words
     * @Params none
     * @return none
     */
    public void displayFirst100() {
       // displayUniqueWordCount(this.selectFirst100());
    }


    public void displayUniquenessResult() {
        this.displayUniqueWordCount(this.uniqueWords);
    }



//    public HashMap<String, Integer> selectFirst100() {
//        return this.uniqueWords.entrySet().stream().map(Map.Entry::getValue).sorted().limit(100);
//    }


    /**
     * @Description process what requires
     * get rid of all punctuations
     * and convert to array
     * @Param none
     * @Return void
     */
    public void processWord() {
        stripPonctuation();
        convertToArray();
        wordUniqueness();
    }
}
