// Name: Gathrean Dela Cruz
// ID: A01167248
// Set: 3G

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * HashSimulator that will process an input list three times.
 * One for each of three different hash functions.
 */
public class HashSimulator {

    public static final String FILENAME1 = "37names.txt";
    public static final String FILENAME2 = "641names.txt";
    public static final String FILENAME3 = "5575names.txt";
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * HashSimulator Class Constructor
     */
    public HashSimulator() {
        HashMap<Character, Integer> alphabetMap = new HashMap<>();
        char[] alphabet = ALPHABET.toCharArray();
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

        int[] result;
        int h1Collision, h1Probes, h2Collision, h2Probes, h3Collision, h3Probes;

        // Simulating H1
        result = runH1(hashKeys, tableSize);
        h1Collision = result[0];
        h1Probes = result[1];

        // Simulating H2
        result = runH2(hashKeys, tableSize);
        h2Collision = result[0];
        h2Probes = result[1];

        // Simulating H3
        result = runH3(hashKeys, tableSize);
        h3Collision = result[0];
        h3Probes = result[1];

        return new int[]{h1Collision, h1Probes, h2Collision, h2Probes, h3Collision, h3Probes};
    }

    private ArrayList<Character> createAlphabetArrayList() {
        ArrayList<Character> alphabet = new ArrayList<>();
        char[] alphabetArray = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        for (char c : alphabetArray) {
            alphabet.add(c);
        }
        return alphabet;
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
        ArrayList<Character> alphabet = createAlphabetArrayList();

        long sum = 0;

        for (int i = 0; i < name.length(); i++) {
            sum += alphabet.indexOf(name.charAt(i)) + 1;
        }

        return (int) (sum % htSize);
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
        ArrayList<Character> alphabet = createAlphabetArrayList();

        long sum = 0;

        for (int i = 0; i < name.length(); i++) {
            sum += (long) ((alphabet.indexOf(name.charAt(i)) + 1) * Math.pow(26, i));
        }

        return (int) (sum % htSize);
    }

    /**
     * Hash function 3
     *
     * <p>
     * Custom hash function:
     * Sum the ASCII values of characters in the input string,
     * take the length of the string, and multiply them.
     * </p>
     *
     * @param name   - the key to hash
     * @param htSize - the size of the hash table
     * @return The hash value of the key
     */
    public int H3(String name, int htSize) {
        int sum = 0;

        // Calculate sum of ASCII values of characters in the input string
        for (char c : name.toCharArray()) {
            sum += c;
        }

        // Multiply the sum by the length of the input string
        int hashValue = sum * name.length();

        // Take the result mod HT size
        return hashValue % htSize;
    }

    /**
     * Runs the H1 hash simulation
     *
     * @param name   - the keys to hash
     * @param htSize - the size of the hash table
     * @return - An array of 2 ints: collision and probes with H1()
     */
    private int[] runH1(String[] name, int htSize) {
        int h1Collision = 0, h1Probes = 0;

        String[] hashSimulator = new String[htSize];

        int hashedKey;

        for (String s : name) {
            hashedKey = H1(s, htSize);
            if (hashSimulator[hashedKey] != null) ++h1Collision;
            while (hashSimulator[hashedKey] != null) {
                ++hashedKey;
                ++h1Probes;
                if (hashedKey >= htSize) hashedKey = 0;
            }
            hashSimulator[hashedKey] = s;
        }

        return new int[]{h1Collision, h1Probes};
    }

    /**
     * Runs the H2 hash simulation
     *
     * @param name   - the keys to hash
     * @param htSize - the size of the hash table
     * @return - An array of 2 ints: collision and probes with H2()
     */
    private int[] runH2(String[] name, int htSize) {
        int h2Collision = 0, h2Probes = 0;

        String[] hashSimulator = new String[htSize];

        int hashedKey;

        for (String s : name) {
            hashedKey = H2(s, htSize);
            if (hashSimulator[hashedKey] != null) ++h2Collision;
            while (hashSimulator[hashedKey] != null) {
                ++hashedKey;
                ++h2Probes;
                if (hashedKey >= htSize) hashedKey = 0;
            }
            hashSimulator[hashedKey] = s;
        }

        return new int[]{h2Collision, h2Probes};
    }

    /**
     * Runs the H3 hash simulation
     *
     * @param name   - the keys to hash
     * @param htSize - the size of the hash table
     * @return - An array of 2 ints: collision and probes with H3()
     */
    private int[] runH3(String[] name, int htSize) {
        int h3Collision = 0, h3Probes = 0;

        String[] hashSimulator = new String[htSize];

        int hashedKey;

        for (String s : name) {
            hashedKey = H3(s, htSize);
            if (hashSimulator[hashedKey] != null) ++h3Collision;
            while (hashSimulator[hashedKey] != null) {
                ++hashedKey;
                ++h3Probes;
                if (hashedKey >= htSize) hashedKey = 0;
            }
            hashSimulator[hashedKey] = s;
        }

        return new int[]{h3Collision, h3Probes};
    }

    /**
     * HELPER FUNCTION: Reads the strings from a file
     *
     * @param fileName - the name of the file to read
     * @return - an array of strings
     * @throws IOException - if the file is not found
     */
    public static String[] readStrings(String fileName) throws IOException {
        List<String> listOfStrings;

        // read the strings as an ArrayList
        listOfStrings = Files.readAllLines(Paths.get(fileName));

        // convert the ArrayList to a plain Array of Strings
        return listOfStrings.toArray(new String[0]);
    }

    /**
     * Main method
     *
     * @param args - command line arguments
     */
    public static void main(String[] args) throws IOException {
        HashSimulator hashTable = new HashSimulator();
        String[] data;

        data = readStrings(FILENAME1);
        int[] result = hashTable.runHashSimulation(data, data.length);

        System.out.println(FILENAME1 + ": [" + result[0] + ", " + result[1] + ", " + result[2] + ", " + result[3] + ", " + result[4] + ", " + result[5] + "]");

        data = readStrings(FILENAME2);
        result = hashTable.runHashSimulation(data, data.length);

        System.out.println(FILENAME2 + ": [" + result[0] + ", " + result[1] + ", " + result[2] + ", " + result[3] + ", " + result[4] + ", " + result[5] + "]");

        data = readStrings(FILENAME3);
        result = hashTable.runHashSimulation(data, data.length);

        System.out.println(FILENAME3 + ": [" + result[0] + ", " + result[1] + ", " + result[2] + ", " + result[3] + ", " + result[4] + ", " + result[5] + "]");
    }
}