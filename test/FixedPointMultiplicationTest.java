import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FixedPointMultiplicationTest {
    @Test
    public void multiplicationTestEqualPositive1() {
        FixedPoint num1 = new FixedPoint("1.0");
        FixedPoint num2 = new FixedPoint("1.0");
        num1.multiply(num2);
        Assertions.assertEquals(1.0, num1.getProperNumber());
    }

    @Test
    public void multiplicationTestEqualPositive2() {
        FixedPoint num1 = new FixedPoint("2.0");
        FixedPoint num2 = new FixedPoint("2.0");
        num1.multiply(num2);
        Assertions.assertEquals(4.0, num1.getProperNumber());
    }

    @Test
    public void multiplicationTestEqualNegative() {
        FixedPoint num1 = new FixedPoint("1.0");
        FixedPoint num2 = new FixedPoint("1.0");
        num1.multiply(num2);
        Assertions.assertEquals(1.0, num1.getProperNumber());
    }

    @Test
    public void multiplicationTestEqualNegative2() {
        FixedPoint num1 = new FixedPoint("-2.0");
        FixedPoint num2 = new FixedPoint("-2.0");
        num1.multiply(num2);
        Assertions.assertEquals(4.0, num1.getProperNumber());
    }
    @Test
    public void multiplicationTestPositiveNegative() {
        FixedPoint num1 = new FixedPoint("-2.0");
        FixedPoint num2 = new FixedPoint("1.0");
        num1.multiply(num2);
        Assertions.assertEquals(-2.0, num1.getProperNumber());
    }

    @Test
    public void multiplicationTestNegativePositive() {
        FixedPoint num1 = new FixedPoint("2.0");
        FixedPoint num2 = new FixedPoint("-1.0");
        num1.multiply(num2);
        Assertions.assertEquals(-2.0, num1.getProperNumber());
    }

    @Test
    public void multiplicationScale1() {
        FixedPoint num1 = new FixedPoint("0.001");
        FixedPoint num2 = new FixedPoint("1.0");
        num1.multiply(num2);
        Assertions.assertEquals(0.001, num1.getProperNumber());
    }

    @Test
    public void multiplicationScale2() {
        FixedPoint num1 = new FixedPoint("1.0");
        FixedPoint num2 = new FixedPoint("0.001");
        num1.multiply(num2);
        Assertions.assertEquals(0.001, num1.getProperNumber());
    }


    @Test
    public void multiplicationScale3() {
        FixedPoint num1 = new FixedPoint(1L << 48 + 1);
        FixedPoint num2 = new FixedPoint(1L << 48 + 3);
        FixedPoint temp = new FixedPoint(num1.getNumber(), num1.getScale());
        num1.multiply(num2);

        Assertions.assertEquals(7922816251426546349L, temp.getNumber());
    }

/*
        FixedPoint num1 = new FixedPoint("112.021234345");
        FixedPoint num2 = new FixedPoint("30.91");
        num1.multiply(num2);
        Assertions.assertEquals(3462.57635360395, num1.getProperNumber());

 */
}
