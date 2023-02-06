package de.michel.projecteuler.problem0089;

import java.util.List;

/**
 *
 * For a number written in Roman numerals to be considered valid there are basic rules which must be followed. Even though the rules allow some numbers to be
 * expressed in more than one way there is always a "best" way of writing a particular number.
 * <p>
 * For example, it would appear that there are at least six ways of writing the number sixteen:
 * <pre>
 * IIIIIIIIIIIIIIII
 * VIIIIIIIIIII
 * VVIIIIII
 * XIIIIII
 * VVVI
 * XVI
 * </pre>
 * However, according to the rules only XIIIIII and XVI are valid, and the last example is considered to be the most efficient, as it uses the least number of
 * numerals.
 * <p>
 * The 11K text file, roman.txt (right click and 'Save Link/Target As...'), contains one thousand numbers written in valid, but not necessarily minimal, Roman
 * numerals; see About... Roman Numerals for the definitive rules for this problem.
 * <p>
 * Find the number of characters saved by writing each of these in their minimal form.
 * <p>
 * Note: You can assume that all the Roman numerals in the file contain no more than four consecutive identical units.
 *
 * @author micmeyer
 */
public class Problem0089
{
    /**
     * Implemented a converter for [Roman -> Arabic] and [Arabic -> Roman].
     * The former is unambigiuous and the latter will always convert to the
     * minimal/shortest representation. So all we need to do is to convert
     * every roman numeral in the given list to its arabic representation
     * and then back to the roman numeral which will now be the shortened
     * version.
     * <p>
     * Finally just sum up the differences in character lengths and that's
     * the searched result.
     *
     * @param args
     */
    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();

        List<String> romanNumerals = RomanNumeralsFileReader.getRomanNumerals();

        RomanNumeralConverter converter = new RomanNumeralConverter();

        int result = 0;
        String shortened;
        for (String numeral : romanNumerals)
        {
            shortened = converter.convert(converter.convert(numeral));

            result += numeral.length() - shortened.length();
        }

        System.out.println("Result: " + result);

        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }
}
