// Name: Gathrean Dela Cruz
// ID: A01167248

public class HashSimulator {


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
    }
}
