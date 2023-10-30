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

    /**
     * Alphabet hash map
     */
    private HashMap<Character, Integer> alphabetMap;

    /**
     * Alphabet hash map constructor
     */
    public HashSimulator() {
        alphabetMap = new HashMap<>();
        char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        for (int i = 0; i < alphabet.length; i++) {
            alphabetMap.put(alphabet[i], i + 1);
        }
    }

    public int[] runHashSimulation(String[] hashKeys, int tableSize) {

        return new int[3];
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
     * @param hashKey   - the key to hash
     * @param tableSize - the size of the hash table
     * @return The hash value of 60 mod HT size
     */
    public int H1(String hashKey, int tableSize) {

        // Create a new, empty hash table of the given size
        //      (hash table is also an array of Strings)
        HashMap<Integer, String> h1HashTable = new HashMap<>(tableSize);

        int collisionCount = 0; // Set collision count to 0
        int probeCount = 0; // Set probe count to 0

        // Calculate hash value using H1 function and take modulo 60
        for (char c : hashKey.toUpperCase().toCharArray()) {
            int charValue = alphabetMap.get(c);
            int hashValue = (charValue + probeCount) % 60;

            // Calculate the hash value modulo tableSize (HT size)
            hashValue = hashValue % tableSize;

            // Handle collisions with linear probing
            while (h1HashTable.containsKey(hashValue)) {
                collisionCount++;
                probeCount++;
                hashValue = (hashValue + 1) % tableSize;
            }

            // Store key in hash table
            h1HashTable.put(hashValue, String.valueOf(c));
            probeCount++;
        }

        // Print collision and probe counts for testing purposes
        System.out.println("Collision Count: " + collisionCount);
        System.out.println("Probe Count: " + probeCount);

        return tableSize;
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
     * @param hashKey   - the key to hash
     * @param tableSize - the size of the hash table
     * @return The hash value of the key
     */
    public int H2(String hashKey, int tableSize) {

        return tableSize;
    }

    /**
     * Hash function 3
     *
     * <p>
     * Invent your own hash function!
     * </p>
     *
     * @param hashKey   - the key to hash
     * @param tableSize - the size of the hash table
     * @return The hash value of the key
     */
    public int H3(String hashKey, int tableSize) {

        return tableSize;
    }

    /**
     * Main method
     *
     * @param args - command line arguments
     */
    public static void main(String[] args) {
        // Testing purposes, won't be marked.
        HashSimulator hashTable = new HashSimulator();
        int tableSize = 10; // Set your desired table size
        String input = "BENNY"; // Input string
        int result = hashTable.H1(input, tableSize);
        System.out.println("Result: " + result);
    }
}
