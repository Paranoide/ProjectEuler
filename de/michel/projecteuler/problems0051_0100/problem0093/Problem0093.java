package de.michel.projecteuler.problems0051_0100.problem0093;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import de.michel.projecteuler.util.Sets;

/**
 *
 * By using each of the digits from the set, {1, 2, 3, 4}, exactly once, and making use of the four arithmetic operations (+, −, *, /) and brackets/parentheses,
 * it is possible to form different positive integer targets.
 * <p>
 * For example,
 * <pre>
 * 8 = (4 * (1 + 3)) / 2
 * 14 = 4 * (3 + 1 / 2)
 * 19 = 4 * (2 + 3) − 1
 * 36 = 3 * 4 * (2 + 1)
 * </pre>
 * Note that concatenations of the digits, like 12 + 34, are not allowed.
 * <p>
 * Using the set, {1, 2, 3, 4}, it is possible to obtain thirty-one different target numbers of which 36 is the maximum, and each of the numbers 1 to 28 can be
 * obtained before encountering the first non-expressible number.
 * <p>
 * Find the set of four distinct digits, a < b < c < d, for which the longest set of consecutive positive integers, 1 to n, can be obtained, giving your answer
 * as a string: abcd.
 *
 * @author micmeyer
 */
public class Problem0093
{
    private static final List<List<NumberOrOperator>> FUNCTIONS = new LinkedList<>();
    private static final List<Integer[]> VARIABLE_COMBINATIONS = new LinkedList<>();
    private static final List<Operator[]> OPERATOR_COMBINATIONS = new LinkedList<>();

    private static final FunctionOperator OP_1 = new FunctionOperator(Operator.PLUS);
    private static final FunctionOperator OP_2 = new FunctionOperator(Operator.PLUS);
    private static final FunctionOperator OP_3 = new FunctionOperator(Operator.PLUS);

    private static final FunctionNumber A = new FunctionNumber(0);
    private static final FunctionNumber B = new FunctionNumber(0);
    private static final FunctionNumber C = new FunctionNumber(0);
    private static final FunctionNumber D = new FunctionNumber(0);

    public static void main(String[] args)
    {
        long time = System.currentTimeMillis();

        initFunctions();
        initOperatorCombinations();
        initVariableCombinations();

        int max = 0;
        Integer[] best = null;

        for (Integer[] variables : VARIABLE_COMBINATIONS)
        {
            TreeSet<Integer> integers = eval(variables);

            int maxCons = maxConsecutive(integers);

            if (maxCons > max)
            {
                max = maxCons;
                best = variables;
            }
        }

        List<Integer> resultList = new ArrayList<>(Arrays.asList(best[0], best[1], best[2], best[3]));
        Collections.sort(resultList);
        Integer[] r = resultList.toArray(new Integer[0]);

        System.out.println(MessageFormat.format("Result: {0}{1}{2}{3}", r[0], r[1], r[2], r[3]));

        System.out.println();
        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }

    private static void initVariableCombinations()
    {
        List<Set<Integer>> allSubsets = Sets.findAllSubsetsOfLengthN(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), 4);
        for (Set<Integer> subset : allSubsets)
            VARIABLE_COMBINATIONS.add(subset.toArray(new Integer[0]));
    }

    private static void initOperatorCombinations()
    {
        for (Operator o1 : Operator.values())
            for (Operator o2 : Operator.values())
                for (Operator o3 : Operator.values())
                    OPERATOR_COMBINATIONS.add(new Operator[]
                    {
                        o1, o2, o3
                    });
    }

    private static List<Integer[]> createPermutations(Integer[] variables)
    {
        List<List<Integer>> permutations = Sets.createPermutations(Arrays.asList(variables));
        ArrayList<Integer[]> list = new ArrayList<>();
        for (List<Integer> perm : permutations)
            list.add(perm.toArray(new Integer[0]));
        return list;
    }

    private static TreeSet<Integer> eval(Integer[] variables)
    {
        Deque<Double> deque = new LinkedList<>();
        TreeSet<Integer> integers = new TreeSet<>();

        List<Integer[]> permutations = createPermutations(variables);

        for (Integer[] varPerm : permutations)
        {
            A.setValue(varPerm[0]);
            B.setValue(varPerm[1]);
            C.setValue(varPerm[2]);
            D.setValue(varPerm[3]);

            for (Operator[] operators : OPERATOR_COMBINATIONS)
            {
                OP_1.setOperator(operators[0]);
                OP_2.setOperator(operators[1]);
                OP_3.setOperator(operators[2]);

                for (List<NumberOrOperator> function : FUNCTIONS)
                {
                    deque.clear();

                    for (NumberOrOperator item : function)
                        item.evaluate(deque);

                    double value = deque.pop();

                    if (value >= 1.0 && isInteger(value))
                        integers.add((int) value);
                }
            }
        }

        return integers;
    }

    private static void initFunctions()
    {
        List<NumberOrOperator> function1 = new LinkedList<>(
                Arrays.asList(A, B, C, D, OP_1, OP_2, OP_3)
        );

        List<NumberOrOperator> function2 = new LinkedList<>(
                Arrays.asList(A, B, C, OP_1, D, OP_2, OP_3)
        );

        List<NumberOrOperator> function3 = new LinkedList<>(
                Arrays.asList(A, B, C, OP_1, OP_2, D, OP_3)
        );

        List<NumberOrOperator> function4 = new LinkedList<>(
                Arrays.asList(A, B, OP_1, C, D, OP_2, OP_3)
        );

        List<NumberOrOperator> function5 = new LinkedList<>(
                Arrays.asList(A, B, OP_1, C, OP_2, D, OP_3)
        );

        FUNCTIONS.addAll(Arrays.asList(function1, function2, function3, function4, function5));
    }

    private static boolean isInteger(double d)
    {
        return d == (int) d;
    }

    private static int maxConsecutive(TreeSet<Integer> integers)
    {
        int n = 1;
        for (Integer integer : integers)
            if (!integer.equals(n++))
                return n - 2;

        return 0;
    }

    private static interface NumberOrOperator
    {
        public void setValue(int number);

        public void setOperator(Operator operator);

        public void evaluate(Deque<Double> currentStack);
    }

    private static class FunctionNumber implements NumberOrOperator
    {
        private int value;

        public FunctionNumber(int number)
        {
            this.value = number;
        }

        @Override
        public void setValue(int number)
        {
            this.value = number;
        }

        @Override
        public void setOperator(Operator operator)
        {
            throw new UnsupportedOperationException("Not supported for this type.");
        }

        @Override
        public void evaluate(Deque<Double> currentStack)
        {
            currentStack.push((double) this.value);
        }
    }

    private static class FunctionOperator implements NumberOrOperator
    {
        private Operator operator;

        public FunctionOperator(Operator operator)
        {
            this.operator = operator;
        }

        @Override
        public void setValue(int number)
        {
            throw new UnsupportedOperationException("Not supported for this type.");
        }

        @Override
        public void setOperator(Operator operator)
        {
            this.operator = operator;
        }

        @Override
        public void evaluate(Deque<Double> currentStack)
        {
            double value2 = currentStack.pop();
            double value1 = currentStack.pop();

            switch (this.operator)
            {
                case PLUS:
                    currentStack.add(value1 + value2);
                    break;
                case MINUS:
                    currentStack.add(value1 - value2);
                    break;
                case MULT:
                    currentStack.add(value1 * value2);
                    break;
                case DIVIDE:
                    currentStack.add(value1 / value2);
                    break;
            }
        }
    }

    private static enum Operator
    {
        PLUS, MINUS, MULT, DIVIDE;
    }
}
