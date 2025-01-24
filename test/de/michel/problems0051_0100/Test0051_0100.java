package test.de.michel.problems0051_0100;

import de.michel.projecteuler.problems0051_0100.problem0100.Problem0100;


public class Test0051_0100
{
    public void executeAllTests()
    {
        this.test0100();
    }

    public void test0100()
    {
        String result = Problem0100.executeProblem();

        assert "756872327473".equals(result);
    }
}
