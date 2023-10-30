// Name: Gathrean Dela Cruz
// ID: A01167248
// Set: 3G

import java.io.IOException;

/**
 * Driver for the HashSimulator
 */
public class Driver {

    /**
     * MAIN METHOD: Runs the Hash Simulation
     *
     * @param args - the command line arguments
     */
    public static void main(String[] args) {
        HashSimulator hashTable = new HashSimulator();
        String[] data;

        try {
            // Run the simulation and print results for FILENAME1
            data = HashSimulator.readStrings(HashSimulator.FILENAME1);
            int[] result = hashTable.runHashSimulation(data, data.length);
            printResults(HashSimulator.FILENAME1, result);

            // Run the simulation and print results for FILENAME2
            data = HashSimulator.readStrings(HashSimulator.FILENAME2);
            result = hashTable.runHashSimulation(data, data.length);
            printResults(HashSimulator.FILENAME2, result);

            // Run the simulation and print results for FILENAME3
            data = HashSimulator.readStrings(HashSimulator.FILENAME3);
            result = hashTable.runHashSimulation(data, data.length);
            printResults(HashSimulator.FILENAME3, result);
        } catch (IOException e) {
            System.out.println("Error reading files: " + e.getMessage());
        }
    }

    /**
     * Prints the results of the simulation
     *
     * @param fileName - the name of the file
     * @param result   - the results of the simulation
     */
    private static void printResults(String fileName, int[] result) {
        System.out.println(fileName + ": [" + result[0] + ", " + result[1] + ", " +
                result[2] + ", " + result[3] + ", " + result[4] + ", " + result[5] + "]");
    }
}