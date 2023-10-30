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

    public static final String FILENAME = "37names.txt";
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final HashMap<Character, Integer> alphabetMap;

    /**
     * HashSimulator Class Constructor
     */
    public HashSimulator() {
        alphabetMap = new HashMap<>();
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

        result = runH1(hashKeys, tableSize);
        h1Collision = result[0];
        h1Probes = result[1];

        // Return counts for further analysis if needed
        return new int[]{h1Collision, h1Probes, 0, 0, 0, 0};
    }

    private ArrayList<Character> createAlphabetArrayList() {
        ArrayList<Character> alphabet = new ArrayList<>();
        char[] alphabetArray = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        for (char c : alphabetArray) {
            alphabet.add(c);
        }
        return alphabet;
    }

    private int[] runH1(String[] name, int htSize) {
        int h1Collision = 0, h1Probes = 0;

        String[] hashSimulator = new String[htSize];

        int hashedKey = 0;

        for (int i = 0; i < name.length; i++) {
            hashedKey = H1(name[i], htSize);
            if (hashSimulator[hashedKey] != null) ++h1Collision;
            while (hashSimulator[hashedKey] != null) {
                ++hashedKey;
                ++h1Probes;
                if (hashedKey >= htSize) hashedKey = 0;
            }
            hashSimulator[hashedKey] = name[i];
        }

        return new int[]{h1Collision, h1Probes};
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
            sum += alphabet.get(name.charAt(i));
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
            sum += (long) (alphabet.get(name.charAt(i)) * Math.pow(26, i));
        }

        return (int) (sum % htSize);
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
        ArrayList<Character> alphabet = createAlphabetArrayList();

        return htSize;
    }

    /**
     * @param fileName: text file containing a list of names, one per line
     * @return
     * @throws IOException
     */
    public static String[] readStrings(String fileName) throws IOException {
        List<String> listOfStrings = new ArrayList<String>();

        // read the strings as an ArrayList
        listOfStrings = Files.readAllLines(Paths.get(fileName));

        // convert the ArrayList to a plain Array of Strings
        String[] array = listOfStrings.toArray(new String[0]);

        return array;

    }

    /**
     * Main method
     *
     * @param args - command line arguments
     */
    public static void main(String[] args) throws IOException {
        HashSimulator hashTable = new HashSimulator();
        int tableSize = 10;
//
//        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String input = line.trim();
//                String[] inputs = {input}; // Wrap the input in an array for runHashSimulation method
//                hashTable.runHashSimulation(inputs, tableSize);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        String[] data = readStrings(FILENAME);
//        int[] result = HashSimulator.runHashSimulation(data, data.length);
    }
}