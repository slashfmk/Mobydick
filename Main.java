public class Main {

    public static void main(String[] args) {

        // loading both files
        var mobydick = new WordProcessor("mobydick.txt");
        var stopWord = new WordProcessor("stop-words.txt");

        // Display most frequently occurring words with the count of occurrences
        // with the stop-words removed
        mobydick.getTop100Words(stopWord);
    }
}
