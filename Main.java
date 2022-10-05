import java.io.BufferedReader;
import java.io.FileReader;

public class Main {

    public static void main(String[] args) {

        try {

            BufferedReader br = new BufferedReader(new FileReader("mobydick.txt"));
            String line = br.readLine();

            while (line != null) {
                System.out.println(line);
                line = br.readLine();
            }

        } catch (Exception e) {
            System.out.println("An error occured: " + e.getMessage());
        }

    }
}
