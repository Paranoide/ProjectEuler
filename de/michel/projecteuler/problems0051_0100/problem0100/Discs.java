package de.michel.projecteuler.problems0051_0100.problem0100;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Discs
{
    private static final BigInteger THOUSAND = BigInteger.valueOf(1000);

    private final List<BigInteger> allNs;
    private final List<BigInteger> allBs;

    public Discs()
    {
        this.allNs = new ArrayList<>();
        this.allNs.addAll(Stream.of(21, 120, 697, 4060).map(BigInteger::valueOf).collect(toList()));

        this.allBs = new ArrayList<>();
        this.allBs.addAll(Stream.of(15, 85, 493, 2871).map(BigInteger::valueOf).collect(toList()));
    }

    private BigInteger getStartB()
    {
        BigInteger lastB = this.allBs.get(this.allBs.size() - 1);
        BigInteger secondToLastB = this.allBs.get(this.allBs.size() - 2);

        BigInteger startB = lastB.multiply(lastB).divide(secondToLastB);

        return startB.subtract(THOUSAND);
    }

    private long getStartN()
    {
        BigInteger lastN = this.allNs.get(this.allNs.size() - 1);
        BigInteger secondToLastN = this.allNs.get(this.allNs.size() - 2);

        BigInteger startN = lastN.multiply(lastN).divide(secondToLastN);

        return startN.subtract(THOUSAND).longValue();
    }

    public boolean calcNext()
    {
        BigInteger b = getStartB();

        BigInteger numerator;
        BigInteger denominator;

        long startN = this.getStartN();
        long endN = startN + 2000L;

        for (long n_ = startN; n_ < endN; n_++)
        {
            BigInteger n = BigInteger.valueOf(n_);

            numerator = b.subtract(BigInteger.ONE).multiply(b);
            denominator = n.subtract(BigInteger.ONE).multiply(n);

            while (numerator.multiply(BigInteger.TWO).compareTo(denominator) < 0)
            {
                b = b.add(BigInteger.ONE);
                numerator = b.subtract(BigInteger.ONE).multiply(b);
                denominator = n.subtract(BigInteger.ONE).multiply(n);
            }

            if (numerator.multiply(BigInteger.TWO).equals(denominator))
            {
                this.allNs.add(n);
                this.allBs.add(b);
                return true;
            }
        }

        return false;
    }

    public List<BigInteger> getAllNs()
    {
        return this.allNs;
    }

    public BigInteger getLastN()
    {
        if (this.allNs.isEmpty())
            return null;

        return this.allNs.get(this.allNs.size() - 1);
    }

    public List<BigInteger> getAllBs()
    {
        return this.allBs;
    }

    public BigInteger getLastB()
    {
        if (this.allBs.isEmpty())
            return null;

        return this.allBs.get(this.allBs.size() - 1);
    }
}
