// Name: Gathrean Dela Cruz
// ID: A01167248
// Set: 3G

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * HashSimulator that will process an input list three times.
 * One for each of three different hash functions.
 */
public class HashSimulator {

    // The name of the input file to read from
    public static final String FILENAME = "37names.txt";

    // Alphabet hash map
    private final HashMap<Character, Integer> alphabetMap;

    // Alphabet hash map constructor
    public HashSimulator() {
        alphabetMap = new HashMap<>();
        char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        for (int i = 0; i < alphabet.length; i++) {
            alphabetMap.put(alphabet[i], i + 1);
        }
    }

    /**
     * Runs the Hash Simulation
     *
     * @param hashKeys  - the keys to hash
     * @param tableSize - the size of the hash table
     * @return - An array of 6 ints: collision and probes with H1(), H2(), and H3()
     */
    public int[] runHashSimulation(String[] hashKeys, int tableSize) {
        int h1CollisionCount = 0;
        int h1ProbeCount = 0;
        int h2CollisionCount = 0;
        int h2ProbeCount = 0;
        int h3CollisionCount = 0;
        int h3ProbeCount = 0;

        for (String key : hashKeys) {
            H1(key, tableSize);
        }

        // Return counts for further analysis if needed
        return new int[]{h1CollisionCount, h1ProbeCount, h2CollisionCount, h2ProbeCount, h3CollisionCount, h3ProbeCount};
    }

    /**
     * Hash function 1
     *
     * <p>
     * Let A=1, B=2, C=3. Then the hash function H1 is the
     * sum of the values of the letters in the string, mod HT size.
     * </p>
     *
     * <p>
     * For example if the string is BENNY,
     * the sum of letters is 2+5+14+14+25.
     * </p>
     *
     * @param name   - the key to hash
     * @param htSize - the size of the hash table
     * @return The hash value of 60 mod HT size
     */
    public int H1(String name, int htSize) {

        // Create a new, empty hash table of the given size
        //      (hash table is also an array of Strings)
        HashMap<Integer, String> h1HashTable = new HashMap<>(htSize);

        int collisionCount = 0; // Set collision count to 0
        int probeCount = 0; // Set probe count to 0

        // Calculate hash value using H1 function and take modulo 60
        for (char c : name.toUpperCase().toCharArray()) {
            int charValue = alphabetMap.get(c);
            int hashValue = (charValue + probeCount) % 60;

            // Calculate the hash value modulo tableSize (HT size)
            hashValue = hashValue % htSize;

            // Handle collisions with linear probing
            while (h1HashTable.containsKey(hashValue)) {
                collisionCount++;
                probeCount++;
                hashValue = (hashValue + 1) % htSize;
            }

            // Store key in hash table
            h1HashTable.put(hashValue, String.valueOf(c));
            probeCount++;
        }

        System.out.println("Result for " + name + " (H1):");
        System.out.println("Collision Count: " + collisionCount + "\t Probe Count: " + probeCount + "\n");

        return htSize;
    }

    /**
     * Hash function 2
     *
     * <p>
     * For the ith letter in the string (counting from 0),
     * multiply the character value (A=1, B=2, C=3) times 26^i.
     * Add up these values, and take the result mod HT size.
     * </p>
     *
     * <p>
     * For example, BENNY the intermediate result would be
     * 2*1 + 5*26 + 14*676 + 14*17576 + 25*456976 = 1188138,
     * and return value will be 11680060 mod HT size.
     * </p>
     *
     * @param name   - the key to hash
     * @param htSize - the size of the hash table
     * @return The hash value of the key
     */
    public int H2(String name, int htSize) {

        return htSize;
    }

    /**
     * Hash function 3
     *
     * <p>
     * Invent your own hash function!
     * </p>
     *
     * @param name   - the key to hash
     * @param htSize - the size of the hash table
     * @return The hash value of the key
     */
    public int H3(String name, int htSize) {

        return htSize;
    }

    /**
     * Main method
     *
     * @param args - command line arguments
     */
    public static void main(String[] args) {
        // Testing purposes, won't be marked.
        HashSimulator hashTable = new HashSimulator();
        int tableSize = 10;

        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String input = line.trim();
                String[] inputs = {input}; // Wrap the input in an array for runHashSimulation method
                hashTable.runHashSimulation(inputs, tableSize);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
