package com.boha.monitor.utilx;

/**
 * Solution for Trello job application task: Code to find a 5 letter string of
 * characters that contains only letters from acdegilmnoprstuw such that the
 * hash(the_string) is 491454843, given the hashing pseudo code.
 *
 * @author aubreyM
 */
public class Trello {

    static final String 
            CHARACTERS_ALLOWED = "acdegilmnoprstuw",
            CHECK_STRING = "leepadg";
    static final long SOLUTION_HASH = 491454843;
    static final int 
            HASH_CONSTANT_A = 7,
            HASH_CONSTANT_B = 37;

    /**
     * Confirm that implementation of given pseudo code is accurate. Get
     * required string from given solution hash. Print on console as appropriate
     *
     * @param args - not used
     */
    public static void main(String[] args) {

        System.out.println("##### Hash from " + CHECK_STRING + " is: "
                + getHash(CHECK_STRING)
                + " - has to match 680131659347");

        System.out.println("##### The solution string: " + getStringFromHash());
    }
    /**
     * Compute solution string from SOLUTION_HASH (491454843). In effect, get
     * and store each character in reverse
     *
     * @return reversed string
     */
     static String getStringFromHash() {
        StringBuilder sb = new StringBuilder();
        long hash = SOLUTION_HASH;
        while (hash > HASH_CONSTANT_A) {
            for (int index = 0; index < CHARACTERS_ALLOWED.length(); index++) {
                long rem = (hash - index) % HASH_CONSTANT_B;
                if (rem == 0) {
                    sb.append(CHARACTERS_ALLOWED.charAt(index));
                    hash = (hash - index) / HASH_CONSTANT_B;
                    break;
                }              
            }
        }

        return sb.reverse().toString();
    }
     
    /**
     * Check that hash of "leepadg" returns proper result
     * using an implementation of given pseudo code
     * @param leepadg
     * @return hash
     */
     static long getHash(String leepadg) {
        long hash = HASH_CONSTANT_A;
        for (int index = 0; index < leepadg.length(); index++) {
            hash = (hash * HASH_CONSTANT_B + CHARACTERS_ALLOWED.indexOf(leepadg.charAt(index)));
        }

        return hash;
    }


}
