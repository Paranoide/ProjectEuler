package de.michel.projecteuler.problem0062;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * The cube, 41063625 (345<sup>3</sup>), can be permuted to produce two other
 * cubes:
 * 56623104 (384<sup>3</sup>) and 66430125 (405<sup>3</sup>). In fact, 41063625
 * is the smallest cube
 * which has exactly three permutations of its digits which are also cube.<br/>
 * <br/>
 * Find the smallest cube for which exactly five permutations of its digits are
 * cube.
 *
 * @author micmeyer
 */
public class Problem0062
{
    
    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();
        
//        BigInteger i1 = new BigInteger("13");
//        BigInteger i2 = new BigInteger("987");
//        BigInteger i3 = new BigInteger("5");
//        
//        DigitInfo info1 = new DigitInfo(i1, 5);
//        DigitInfo info2 = new DigitInfo(i2, 5);
//        DigitInfo info3 = new DigitInfo(i3, 5);
//        
//        System.out.println(Arrays.toString(info1.digitCounts));
//        System.out.println(Arrays.toString(info2.digitCounts));
//        System.out.println(Arrays.toString(info3.digitCounts));
//        
//        System.out.println(info1.equals(info2));
//        System.out.println(info2.equals(info1));
//        System.out.println(info1.equals(info3));
//        System.out.println(info3.equals(info1));
//        System.out.println(info2.equals(info3));
//        System.out.println(info3.equals(info2));
//        
//        System.exit(1);
        
        BigInteger answer = null;
        int n = 3;
        while (answer == null)
        {
            answer = checkWithNDigits(n);
            n++;
        }
        
        System.out.println(answer);
        
        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }
    
    private static BigInteger checkWithNDigits(int nDigits)
    {
        List<DigitInfo> infos = new ArrayList<>();
        
        // Calculate the first n so that n^3 is the first cube
        // that has nDigits digits.
        int n = (int)(Math.pow(10, (nDigits-1)/3.0)+0.5);
        
        BigInteger cube = BigInteger.valueOf(n).pow(3);
        DigitInfo info;
        while (cube.toString().length() == nDigits)
        {
            info = new DigitInfo(cube);
            infos.add(info);
            n++;
            cube = BigInteger.valueOf(n).pow(3);
        }
        
        return find5(infos);
    }
    
    private static BigInteger find5(List<DigitInfo> infos)
    {
        Map<DigitInfo, List<DigitInfo>> samePermuts = new HashMap<>();
        for (int t = 0; t < infos.size(); t++)
        {
            DigitInfo info = infos.get(t);
            List<DigitInfo> permuts = samePermuts.get(info);
            if (permuts == null)
            {
                permuts = new ArrayList<>();
                samePermuts.put(info, permuts);
            }
            permuts.add(info);
        }
        
        BigInteger smallest = null;
        for (DigitInfo key: samePermuts.keySet())
        {
            List<DigitInfo> permuts = samePermuts.get(key);
            if (permuts.size() == 5)
            {
                for (DigitInfo info : permuts)
                {
                    if (smallest == null || smallest.compareTo(info.i) > 0)
                    {
                        smallest = info.i;
                    }
                }
            }
        }
        
        return smallest;
    }

    
    private static class DigitInfo
    {
        private final BigInteger i;
        private final int[] digitCounts;
        
        public DigitInfo(BigInteger i)
        {
            this.i = i;
            this.digitCounts = new int[10];
            while (i.compareTo(BigInteger.ZERO) > 0)
            {
                this.digitCounts[i.mod(BigInteger.TEN).intValue()]++;
                i = i.divide(BigInteger.TEN);
            }
        }
        
        @Override
        public boolean equals(Object other)
        {
            if (other instanceof DigitInfo)
            {
                DigitInfo info = (DigitInfo)other;
                if (this.hashCode() != info.hashCode())
                {
                    return false;
                }
                for (int t = 0; t < 10; t++)
                {
                    if (this.digitCounts[t] != info.digitCounts[t])
                    {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }
        
        @Override
        public int hashCode()
        {
            return Arrays.hashCode(this.digitCounts);
        }
        
        @Override
        public String toString()
        {
            return this.i.toString();
        }
        
    }
}
