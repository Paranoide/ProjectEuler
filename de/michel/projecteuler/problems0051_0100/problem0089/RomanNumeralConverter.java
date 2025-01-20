package de.michel.projecteuler.problems0051_0100.problem0089;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Michel Meyer <micmeyer@uos.de>
 */
public class RomanNumeralConverter
{
    public RomanNumeralConverter()
    {
    }

    public int convert(String romanNumeral)
    {
        int arabic = 0;

        int literalCount = romanNumeral.length();

        RomanLiteral[] literals = new RomanLiteral[literalCount];
        for (int t = 0; t < literalCount; t++)
            literals[t] = new RomanLiteral(String.valueOf(romanNumeral.charAt(t)));

        /*
         * In this loop we look one character (literal) ahead and if the next
         * literal is "greater" (the arabic value of the roman numeral) than
         * the current it means that the current literal is a subtractive
         * literal for the next one (like the 'I' in 'IX' (=9) or the
         * 'C' in 'CM' (=900)).
         *
         * So, easy enough, we can just subtract its value instead of adding it.
         *
         * In all other cases the values will just be added up.
         */
        for (int t = 0; t < literalCount - 1; t++)
        {
            RomanLiteral literal = literals[t];
            RomanLiteral next = literals[t + 1];

            if (literal.compareTo(next) < 0)
                arabic -= literal.getArabicValue();
            else
                arabic += literal.getArabicValue();
        }

        /*
         * The last one will always be additive
         */
        arabic += literals[literalCount - 1].getArabicValue();

        return arabic;
    }

    public String convert(int arabicNumber)
    {
        return this.convertRec(arabicNumber, "");
    }

    private String convertRec(int arabicNumber, String romanNumeral)
    {
        if (arabicNumber >= 1000)
            return this.convertRec(arabicNumber - 1000, romanNumeral + "M");
        else if (arabicNumber >= 100)
        {
            int digit = arabicNumber / 100;
            if (digit >= 1 && digit <= 3)
                return this.convertRec(arabicNumber - 100, romanNumeral + "C");
            else if (digit == 4)
                return this.convertRec(arabicNumber - 400, romanNumeral + "CD");
            else if (digit >= 5 && digit <= 8)
                return this.convertRec(arabicNumber - 500, romanNumeral + "D");
            else if (digit == 9)
                return this.convertRec(arabicNumber - 900, romanNumeral + "CM");
        }
        else if (arabicNumber >= 10)
        {
            int digit = arabicNumber / 10;
            if (digit >= 1 && digit <= 3)
                return this.convertRec(arabicNumber - 10, romanNumeral + "X");
            else if (digit == 4)
                return this.convertRec(arabicNumber - 40, romanNumeral + "XL");
            else if (digit >= 5 && digit <= 8)
                return this.convertRec(arabicNumber - 50, romanNumeral + "L");
            else if (digit == 9)
                return this.convertRec(arabicNumber - 90, romanNumeral + "XC");
        }
        else if (arabicNumber > 0)
        {
            int digit = arabicNumber;
            if (digit >= 1 && digit <= 3)
                return this.convertRec(arabicNumber - 1, romanNumeral + "I");
            else if (digit == 4)
                return this.convertRec(arabicNumber - 4, romanNumeral + "IV");
            else if (digit >= 5 && digit <= 8)
                return this.convertRec(arabicNumber - 5, romanNumeral + "V");
            else if (digit == 9)
                return this.convertRec(arabicNumber - 9, romanNumeral + "IX");
        }

        return romanNumeral;
    }

    /**
     * We use this class to convert roman literals into arabic values and it
     * conveniently provides the possibility to compare two literals.
     */
    private static class RomanLiteral implements Comparable<RomanLiteral>
    {
        private static final Map<String, Integer> ROMAN_TO_ARABIC = new HashMap<>();

        static
        {
            ROMAN_TO_ARABIC.put("I", 1);
            ROMAN_TO_ARABIC.put("V", 5);
            ROMAN_TO_ARABIC.put("X", 10);
            ROMAN_TO_ARABIC.put("L", 50);
            ROMAN_TO_ARABIC.put("C", 100);
            ROMAN_TO_ARABIC.put("D", 500);
            ROMAN_TO_ARABIC.put("M", 1000);
        }

        private final String literal;
        private final int arabicValue;

        public RomanLiteral(String literal)
        {
            Integer value = ROMAN_TO_ARABIC.get(literal);
            if (value == null)
                throw new IllegalArgumentException(MessageFormat.format("Literal {0} is unknown.", literal));

            this.literal = literal;
            this.arabicValue = value;
        }

        public String getLiteral()
        {
            return literal;
        }

        public int getArabicValue()
        {
            return arabicValue;
        }

        @Override
        public int compareTo(RomanLiteral other)
        {
            return this.getArabicValue() - other.getArabicValue();
        }
    }
}
