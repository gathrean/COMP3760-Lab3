import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Driver {
    public static void main(String[] args) {
        // Testing purposes, won't be marked.
        HashSimulator hashTable = new HashSimulator();
        int tableSize = 10;

        try (BufferedReader br = new BufferedReader(new FileReader(HashSimulator.FILENAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String input = line.trim();
                String[] inputs = {input}; // Wrap the input in an array for runHashSimulation method
                int[] results = hashTable.runHashSimulation(inputs, tableSize);
                System.out.println(input);
                System.out.println("H1 | Collisions: " + results[0] + "\t| Probes: " + results[1]);
                System.out.println("H2 | Collisions: " + results[2] + "\t| Probes: " + results[3]);
                System.out.println("H3 | Collisions: " + results[4] + "\t| Probes: " + results[5] + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
