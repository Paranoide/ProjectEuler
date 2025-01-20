package de.michel.projecteuler.problem0099;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static java.util.Comparator.comparingDouble;
import static java.util.function.BinaryOperator.maxBy;

/**
 * @author micmeyer
 */
public class Problem0099
{
    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();

        List<Exp> exps = readFile("base_exp.txt");

        Exp greatest = exps.stream()
                .reduce(maxBy(comparingDouble(Exp::getLn)))
                .orElseThrow();

        System.out.println();
        System.out.println("Result: " + greatest.getLineNumber());
        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }

    private static List<Exp> readFile(String fileName)
    {
        URL resource = Problem0099.class.getResource(fileName);
        if (resource == null)
            throw new IllegalArgumentException(fileName + " was not found.");

        File file = new File(resource.getFile());

        List<Exp> exps = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            String line;
            int lineNumber = 1;
            while ((line = br.readLine()) != null)
            {
                String[] split = line.split(",");

                int base = Integer.parseInt(split[0]);
                int exponent = Integer.parseInt(split[1]);

                exps.add(new Exp(lineNumber++, base, exponent));
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        return exps;
    }

    private static class Exp
    {
        private final int lineNumber;
        private final int base;
        private final int exponent;

        private final double ln;

        public Exp(int lineNumber, int base, int exponent)
        {
            this.lineNumber = lineNumber;
            this.base = base;
            this.exponent = exponent;
            this.ln = Math.log(base) * exponent;
        }

        public int getLineNumber()
        {
            return this.lineNumber;
        }

        /**
         * Gets the value of the natural logarithm of the represented exponential value.
         * <p>
         * I.e.: {@code ln_value = ln(base^exponent) = ln(base) * exponent}
         */
        public double getLn()
        {
            return this.ln;
        }

        @Override
        public String toString()
        {
            return "Exp{" +
                    "lineNumber=" + lineNumber +
                    ", base=" + base +
                    ", exponent=" + exponent +
                    ", ln=" + ln +
                    '}';
        }
    }
}
