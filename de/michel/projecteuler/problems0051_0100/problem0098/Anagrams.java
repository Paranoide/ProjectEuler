package de.michel.projecteuler.problems0051_0100.problem0098;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Anagrams
{
    public static List<Anagram> findAllAnagrams(List<String> words)
    {
        List<Anagram> allAnagrams = new ArrayList<>();

        for (int i = 0; i < words.size() - 1; i++)
        {
            String word = words.get(i);
            List<String> remainingWords = words.subList(i + 1, words.size());

            List<Anagram> anagrams = Anagrams.findAllAnagrams(word, remainingWords);

            allAnagrams.addAll(anagrams);
        }

        return allAnagrams;
    }

    public static List<Anagram> findAllAnagrams(String word, List<String> allWords)
    {
        List<Anagram> allAnagrams = new LinkedList<>();

        for (String otherWord : allWords)
        {
            Anagram anagram = getAnagram(word, otherWord);
            if (anagram != null)
                allAnagrams.add(anagram);
        }

        return allAnagrams;
    }

    private static Anagram getAnagram(String word1, String word2)
    {
        if (word1.length() != word2.length())
            return null;

        char[] chars1 = word1.toCharArray();
        char[] chars2 = word2.toCharArray();

        int[] permutation = new int[chars1.length];

        for (int i = 0; i < chars1.length; i++)
        {
            char c = chars1[i];
            int index = removeCharAndReturnIndex(chars2, c);
            if (index < 0)
                return null;

            permutation[i] = index;
        }

        return new Anagram(word1, word2, permutation);
    }

    private static int removeCharAndReturnIndex(char[] chars, char toRemove)
    {
        for (int i = 0; i < chars.length; i++)
        {
            if (chars[i] == toRemove)
            {
                chars[i] = 0;
                return i;
            }
        }

        return -1;
    }


    public static class Anagram
    {
        private final String baseWord;
        private final String anagram;
        private final int[] permutation;

        public Anagram(String baseWord, String anagram, int[] permutation)
        {
            this.baseWord = baseWord;
            this.anagram = anagram;
            this.permutation = permutation;
        }

        public String getBaseWord()
        {
            return this.baseWord;
        }

        public String getAnagram()
        {
            return this.anagram;
        }

        public int[] getPermutation()
        {
            return this.permutation;
        }

        @Override
        public String toString()
        {
            return "Anagram{" +
                    "baseWord='" + baseWord + '\'' +
                    ", anagram='" + anagram + '\'' +
                    ", permutation=" + Arrays.toString(permutation) +
                    '}';
        }
    }
}
