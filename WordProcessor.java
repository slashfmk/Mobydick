import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

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
     * @param w: HasMap<String, Integer>
     */
    private void displayUniqueWordCount(HashMap<String, Integer> w) {
        for (var o : w.entrySet()) {
            System.out.println(o.getKey() + " | " + o.getValue());
        }
    }


    public void displayUniquenessResult() {
        this.displayUniqueWordCount(this.uniqueWords);
    }

    public void display100SortedDesc() {
        this.displayUniqueWordCount(this.get100SortedWordDesc());
    }

    public HashMap<String, Integer> getSortedWordAscend() {

        LinkedHashMap<String, Integer> sortedWords = new LinkedHashMap<>();
        ArrayList<Integer> list = new ArrayList<>();

        for (var w : this.uniqueWords.entrySet()) {
            list.add(w.getValue());
        }

        Collections.sort(list);

        for (int num : list) {
            for (var entry : this.uniqueWords.entrySet()) {
                if (entry.getValue().equals(num)) {
                    sortedWords.put(entry.getKey(), num);
                }
            }
        }

        return sortedWords;
    }

    /**
     * @Description
     *  Create a descending sorted HashMap of words
     * @Params none
     * @return HashMap
     */
    public HashMap<String, Integer> get100SortedWordDesc() {

        return this.uniqueWords.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(100)
                .collect(Collectors.toMap(
                        e -> e.getKey(),
                        e -> e.getValue(),
                        (e1, e2) -> null,
                        () -> new LinkedHashMap<String, Integer>()
                ));
    }

    /**
     * @Description process what requires
     * get rid of all punctuations
     * and convert to array
     * @Param none
     * @Return void
     */
    private void processWord() {
        stripPonctuation();
        convertToArray();
        wordUniqueness();
    }
}
