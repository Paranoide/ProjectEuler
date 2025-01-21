package de.michel.projecteuler.problems0051_0100.problem0059;

import de.michel.projecteuler.util.FileReading;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Each character on a computer is assigned a unique code and the preferred
 * standard is ASCII (American Standard Code for Information Interchange). For
 * example, uppercase A = 65, asterisk (*) = 42, and lowercase k = 107.
 * <p>
 * A modern encryption method is to take a text file, convert the bytes to
 * ASCII, then XOR each byte with a given value, taken from a secret key. The
 * advantage with the XOR function is that using the same encryption key on the
 * cipher text, restores the plain text; for example, 65 XOR 42 = 107, then 107
 * XOR 42 = 65.<br/>
 * <br/>
 * For unbreakable encryption, the key is the same length as the plain text
 * message, and the key is made up of random bytes. The user would keep the
 * encrypted message and the encryption key in different locations, and without
 * both "halves", it is impossible to decrypt the message.<br/>
 * <br/>
 * Unfortunately, this method is impractical for most users, so the modified
 * method is to use a password as a key. If the password is shorter than the
 * message, which is likely, the key is repeated cyclically throughout the
 * message. The balance for this method is using a sufficiently long password
 * key for security, but short enough to be memorable.<br/>
 * <br/>
 * Your task has been made easy, as the encryption key consists of three lower
 * case characters. Using cipher.txt (right click and 'Save Link/Target As...'),
 * a file containing the encrypted ASCII codes, and the knowledge that the plain
 * text must contain common English words, decrypt the message and find the sum
 * of the ASCII values in the original text.
 *
 * @author micmeyer
 */
public class Problem0059
{
    private static final String FILE_NAME = "cipher.txt";

    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();

        char[] cipher = readCipher();
        char[] plain;
        char[] key = new char[3];

        for (char a = 'a'; a <= 'z'; a++)
        {
            for (char b = 'a'; b <= 'z'; b++)
            {
                for (char c = 'a'; c <= 'z'; c++)
                {
                    key[0] = a;
                    key[1] = b;
                    key[2] = c;
                    plain = decryptCipherWithKey(cipher, key);

                    if (validate(plain))
                    {
                        int sum = 0;
                        for (int t = 0; t < plain.length; t++)
                        {
                            sum += plain[t];
                        }
                        System.out.println("Sum: " + sum);
                        a = b = c = 'z';
                    }
                }
            }
        }

        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }

    private static boolean validate(char[] plain)
    {
        for (int t = 0; t < plain.length; t++)
        {
            if (plain[t] > 'z')
            {
                return false;
            }
        }

        String text = String.copyValueOf(plain);
        return !(!text.contains("and") || !text.contains("the"));
    }

    private static char[] decryptCipherWithKey(char[] cipher, char[] key)
    {
        int keyLen = key.length;

        char[] plain = new char[cipher.length];

        for (int t = 0; t < cipher.length; t++)
        {
            plain[t] = (char) (cipher[t] ^ key[t % keyLen]);
        }

        return plain;
    }

    private static char[] readCipher()
    {
        File file = FileReading.getFile(Problem0059.class, FILE_NAME);

        char[] cipher = null;

        try (BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            String line = br.readLine();
            String[] numbers = line.split(",");
            cipher = new char[numbers.length];
            for (int t = 0; t < numbers.length; t++)
            {
                cipher[t] = (char) Integer.parseInt(numbers[t]);
            }
        }
        catch (IOException ioe)
        {
            System.err.println(ioe.getMessage());
        }

        return cipher;
    }
}
