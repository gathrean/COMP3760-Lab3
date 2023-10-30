//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//
//public class Driver {
//    public static void main(String[] args) {
//        // Testing purposes, won't be marked.
//        HashSimulator hashTable = new HashSimulator();
//        int tableSize = 10;
//        int totalH1Collisions = 0;
//        int totalH1Probes = 0;
//
//        try (BufferedReader br = new BufferedReader(new FileReader(HashSimulator.FILENAME))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String input = line.trim();
//                String[] inputs = {input}; // Wrap the input in an array for runHashSimulation method
//                int[] results = hashTable.runHashSimulation(inputs, tableSize);
//                totalH1Collisions += results[0];
//                totalH1Probes += results[1];
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // Print total collisions and probes for H1 hash function
//        System.out.println("[" + totalH1Collisions + ", " + totalH1Probes + "]");
//    }
//
//
//}
