package career.datastructure.arrays.easy;

public class ReadingNCharacterGivenRead4 {
    public static void main(String[] args) {

    }

    public int read(char[] buf, int n) {
        // Temporary buffer to hold chunks of read characters
        char[] tempBuffer = new char[4];

        // Index for the destination buffer 'buf'
        int bufIndex = 0;

        // Variable to hold the count of characters actually read in each read4 call
        int charReadCount = 0;

        // Continue reading until there are fewer than 4 characters returned, which signifies end of file or buffer
        do {
            // Read up to 4 characters into tempBuffer
//            charReadCount = read4(tempBuffer);

            // Copy characters from tempBuffer to buf, up to the number of characters requested 'n'
            for (int j = 0; j < charReadCount; ++j) {
                buf[bufIndex] = tempBuffer[j];
                bufIndex++;

                // If 'bufIndex' reaches 'n', we've read the required number of characters
                if (bufIndex == n) {
                    return n; // The requested number of characters have been read
                }
            }
        } while (charReadCount == 4); // Continue if we read 4 characters, meaning there could be more to read

        // Return the number of characters actually stored in 'buf'
        return bufIndex;
    }
}
