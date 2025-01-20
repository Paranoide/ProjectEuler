package de.michel.projecteuler.problem0098;

import de.michel.projecteuler.problem0098.Anagrams.Anagram;
import de.michel.projecteuler.util.Digits;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Stream;

import static java.util.Collections.binarySearch;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toCollection;

/**
 * @author micmeyer
 */
public class Problem0098
{
    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();

        List<String> words = loadWords("words.txt");

        List<Anagram> allAnagrams = Anagrams.findAllAnagrams(words);

        int maxWordLen = words.stream().mapToInt(String::length).max().orElseThrow();
        int maxSquareNumber = (int) Math.pow(10, maxWordLen);

        List<Integer> allSquareNumbers = findAllSquareNumbers(maxSquareNumber);

//        Map<List<Integer>, List<Anagram>> anagramsPerStructure = allAnagrams.stream()
//                .collect(groupingBy(a -> getWordStructure(a.getBaseWord())));
//
//        anagramsPerStructure.forEach((k, v) -> System.out.println(k + " => " + v));
//
//        for (Integer squareNumber : allSquareNumbers)
//        {
//            String squareNumberAsString = String.valueOf(squareNumber);
//            List<Integer> wordStructure = getWordStructure(squareNumberAsString);
//
//            if (anagramsPerStructure.containsKey(wordStructure))
//            {
//                List<Anagram> anagrams = anagramsPerStructure.get(wordStructure);
//                for (Anagram anagram : anagrams)
//                {
//                    int permNumber = applyPermutation(squareNumberAsString, anagram.getPermutation());
//                    int result = binarySearch(allSquareNumbers, permNumber, Collections.reverseOrder());
//                    if (result >= 0)
//                        System.out.println(squareNumber + " => " + permNumber + " => " + anagram);
//                }
//            }
//        }

        Map<List<Integer>, List<Anagram>> anagramsPerPattern = allAnagrams.stream()
                .collect(groupingBy(a -> getWordPattern(a.getBaseWord())));

        int result = 0;

        for (Integer squareNumber : allSquareNumbers)
        {
            String squareNumberAsString = String.valueOf(squareNumber);
            List<Integer> wordPattern = getWordPattern(squareNumberAsString);

            if (anagramsPerPattern.containsKey(wordPattern))
            {
                List<Anagram> anagrams = anagramsPerPattern.get(wordPattern);
                for (Anagram anagram : anagrams)
                {
                    int permNumber = applyPermutation(squareNumber, anagram.getPermutation());
                    if (haveSameAmountsOfDigits(squareNumber, permNumber))
                    {
                        int searchResult = binarySearch(allSquareNumbers, permNumber, Collections.reverseOrder());
                        if (searchResult >= 0)
                        {
                            int higherSquareNumber = Math.max(squareNumber, permNumber);
                            if (higherSquareNumber > result)
                                result = higherSquareNumber;
                        }
                    }
                }
            }
        }

        System.out.println();
        System.out.println("Result: " + result);
        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }

    private static boolean haveSameAmountsOfDigits(int n1, int n2)
    {
        return Digits.countDigits(n1) == Digits.countDigits(n2);
    }

    private static int applyPermutation(int number, int[] permutation)
    {
        int[] numberAsArray = Digits.getDigits(number);
        int[] result = new int[numberAsArray.length];

        for (int i = 0; i < permutation.length; i++)
            result[permutation[i]] = numberAsArray[i];

        return Digits.arrayToInt(result);
    }

//    private static List<Integer> getWordStructure(String word)
//    {
//        char[] chars = word.toCharArray();
//        Map<Character, Long> amountPerChar = IntStream.range(0, chars.length)
//                .mapToObj(i -> chars[i])
//                .collect(groupingBy(Function.identity(), Collectors.counting()));
//
//        return amountPerChar.values().stream()
//                .sorted()
//                .map(Long::intValue)
//                .collect(toList());
//    }

    private static List<Integer> getNumberPattern(int number)
    {
        int[] digits = Digits.getDigits(number);
        List<Integer> pattern = new ArrayList<>(digits.length);

        HashMap<Integer, Integer> idPerDigit = new HashMap<>();
        int id = 1;
        for (int d : digits)
        {
            if (!idPerDigit.containsKey(d))
            {
                idPerDigit.put(d, id);
                id++;
            }

            pattern.add(idPerDigit.get(d));
        }

        return pattern;
    }

    private static List<Integer> getWordPattern(String word)
    {
        char[] chars = word.toCharArray();
        List<Integer> pattern = new ArrayList<>(chars.length);

        HashMap<Character, Integer> idPerCharacter = new HashMap<>();
        int id = 1;
        for (char c : chars)
        {
            if (!idPerCharacter.containsKey(c))
            {
                idPerCharacter.put(c, id);
                id++;
            }

            pattern.add(idPerCharacter.get(c));
        }

        return pattern;
    }

    private static List<Integer> findAllSquareNumbers(int maxSquareNumber)
    {
        int maxN = (int) Math.sqrt(maxSquareNumber);
        List<Integer> allSquareNumbers = new ArrayList<>(maxN);

        for (int n = maxN; n > 0; n--)
            allSquareNumbers.add(n * n);

        return allSquareNumbers;
    }

    private static List<String> loadWords(String fileName)
    {
        URL resource = Problem0098.class.getResource(fileName);
        if (resource == null)
            throw new IllegalArgumentException("File " + fileName + " could not be found.");

        File wordsFile = new File(resource.getFile());

        try (BufferedReader br = new BufferedReader(new FileReader(wordsFile)))
        {
            String line = br.readLine();
            String[] wordsAsArray = line.split(",");

            return Stream.of(wordsAsArray)
                    .map(word -> word.substring(1, word.length() - 1))
                    .collect(toCollection(ArrayList::new));
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    private static void exampleCodeToUnderstandTheIdea()
    {
        List<String> words = loadWords("words.txt");

        List<Anagram> allAnagrams = Anagrams.findAllAnagrams(words);

        int maxWordLen = words.stream().mapToInt(String::length).max().orElseThrow();
        int maxSquareNumber = (int) Math.pow(10, maxWordLen);

        List<Integer> allSquareNumbers = findAllSquareNumbers(maxSquareNumber);

        Map<List<Integer>, List<Anagram>> anagramsPerPattern = allAnagrams.stream()
                .collect(groupingBy(a -> getWordPattern(a.getBaseWord())));

        int exampleNumber = 1296;

        List<Integer> pattern = getNumberPattern(exampleNumber);
        System.out.println(pattern);
        String matchingWord = anagramsPerPattern.get(pattern).stream().map(Anagram::getBaseWord).findFirst().orElseThrow();
        System.out.println(matchingWord);

        String correspondingAnagram = anagramsPerPattern.get(pattern).stream().map(Anagram::getAnagram).findFirst().orElseThrow();
        System.out.println(correspondingAnagram);

        int[] permutation = anagramsPerPattern.get(pattern).stream().map(Anagram::getPermutation).findFirst().orElseThrow();
        System.out.println(Arrays.toString(permutation));

        int permutatedExampleNumber = applyPermutation(exampleNumber, permutation);
        System.out.println(permutatedExampleNumber);

        boolean isAlsoSquareNumber = allSquareNumbers.contains(permutatedExampleNumber);
        System.out.printf("%s and %s is a square anagramic word pair: %b\n", matchingWord, correspondingAnagram, isAlsoSquareNumber);
    }
}
