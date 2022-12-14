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
     * @description
     * Get rid of all necessary punctuations
     */
    private void stripPonctuation() {
        // —
        this.fileContent = this.fileContent.replaceAll("[,:/()”“‘’;!?—]", " ");
        this.fileContent = this.fileContent.replaceAll("\\.\\s+", " ");
        this.fileContent = this.fileContent.replaceAll("\\s+", " ");
    }

    /**
     * @description
     * Convert the file content to an array of words
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
            if(!o.getKey().equals(" "))
                System.out.printf(String.format("%-9s %s \t %d%n", o.getKey(), "----------->",  o.getValue()));
        }
    }


    public void displayUniquenessResult() {
        this.displayUniqueWordCount(this.uniqueWords);
    }

    /**
     * @description
     * Displays the 100 descending sorted words
     * including the stop-words
     */
    public void display100SortedDesc() {
        this.displayUniqueWordCount(this.getSortedWordsDesc(this.uniqueWords, 100));
    }


    /**
     *
     * @return hashmap
     */
    private HashMap<String, Integer> getSortedWordAscend() {

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
     *
     * @description Create a descending sorted HashMap of words
     * @param limit
     * @param source
     * @return HashMap
     *
     */
    private HashMap<String, Integer> getSortedWordsDesc(HashMap<String, Integer> source, int limit) {

        return source.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(limit)
                .collect(Collectors.toMap(
                        e -> e.getKey(),
                        e -> e.getValue(),
                        (e1, e2) -> null,
                        () -> new LinkedHashMap<String, Integer>()
                ));
    }


    /**
     * @param stopWord
     * @return hashMap
     * @description Remove keys that match the stop word list
     */
    private HashMap<String, Integer> removeIfExists(WordProcessor stopWord) {

        var result = this.getSortedWordsDesc(this.uniqueWords, this.uniqueWords.size());

        for (var sw : stopWord.uniqueWords.entrySet()) {
           // System.out.println("Added: ------- " + sw.getKey().substring(0,1).toUpperCase()+sw.getKey().substring(1).toLowerCase());
            result.remove(sw.getKey());
            result.remove(sw.getKey().toLowerCase());
            result.remove(sw.getKey().substring(0,1).toUpperCase()+sw.getKey().substring(1).toLowerCase());
        }

        return result;
    }

    /**
     *
     * @param stopWord
     * @description
     * list of the top 100 words without
     * the stop words
     * @return
     * HashMap<String, Integer>
     */
    public HashMap <String, Integer> getTop100Words(WordProcessor stopWord) {
        var removedStopWords =  this.removeIfExists(stopWord);
        var finalResult = getSortedWordsDesc(removedStopWords, 100);
        this.displayUniqueWordCount(finalResult);
        return finalResult;
    }

    /**
     * @description
     * process what requires
     * get rid of all punctuations
     * and convert to array
     */
    private void processWord() {
        stripPonctuation();
        convertToArray();
        wordUniqueness();
    }
}
