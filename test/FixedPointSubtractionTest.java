import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FixedPointSubtractionTest {
    @Test
    public void subtractionTestEqualPositive() {
        FixedPoint num1 = new FixedPoint("2.345");
        FixedPoint num2 = new FixedPoint("2.345");
        num1.subtract(num2);
        Assertions.assertEquals(0.0, num1.getProperNumber());
    }

    @Test
    public void subtractionTestSmallBigPositive() {
        FixedPoint num1 = new FixedPoint("1.111");
        FixedPoint num2 = new FixedPoint("2.345");
        num1.subtract(num2);
        Assertions.assertEquals(-1.234, num1.getProperNumber());
    }

    @Test
    public void subtractionTestBigSmallPositive() {
        FixedPoint num1 = new FixedPoint("2.345");
        FixedPoint num2 = new FixedPoint("1.111");
        num1.subtract(num2);
        Assertions.assertEquals(1.234, num1.getProperNumber());
    }

    @Test
    public void subtractionTestScalePositive1() {
        FixedPoint num1 = new FixedPoint("2.34");
        FixedPoint num2 = new FixedPoint("1.111");
        num1.subtract(num2);
        Assertions.assertEquals(1.229, num1.getProperNumber());
    }

    @Test
    public void subtractionTestScalePositive2() {
        FixedPoint num1 = new FixedPoint("2.345");
        FixedPoint num2 = new FixedPoint("1.11");
        num1.subtract(num2);
        Assertions.assertEquals(1.235, num1.getProperNumber());
    }

    @Test
    public void subtractionTestEqualNegativePositive() {
        FixedPoint num1 = new FixedPoint("-2.345");
        FixedPoint num2 = new FixedPoint("2.345");
        num1.subtract(num2);
        Assertions.assertEquals(-4.69, num1.getProperNumber());
    }

    @Test
    public void subtractionTestSmallNegativePositive() {
        FixedPoint num1 = new FixedPoint("-1.111");
        FixedPoint num2 = new FixedPoint("2.345");
        num1.subtract(num2);
        Assertions.assertEquals(-3.456, num1.getProperNumber());
    }

    @Test
    public void subtractionTestNegativeSmallNegative() {
        FixedPoint num1 = new FixedPoint("-2.345");
        FixedPoint num2 = new FixedPoint("-1.111");
        num1.subtract(num2);
        Assertions.assertEquals(-1.234, num1.getProperNumber());
    }

    @Test
    public void subtractionTestScalePositiveNegative1() {
        FixedPoint num1 = new FixedPoint("-2.34");
        FixedPoint num2 = new FixedPoint("1.111");
        num1.subtract(num2);
        Assertions.assertEquals(-3.451, num1.getProperNumber());
    }

    @Test
    public void subtractionTestScalePositiveNegative2() {
        FixedPoint num1 = new FixedPoint("-2.345");
        FixedPoint num2 = new FixedPoint("1.11");
        num1.subtract(num2);
        Assertions.assertEquals(-3.455, num1.getProperNumber());
    }

    @Test
    public void subtractionTestScalePositiveNegative3() {
        FixedPoint num1 = new FixedPoint("2.34");
        FixedPoint num2 = new FixedPoint("-1.111");
        num1.subtract(num2);
        Assertions.assertEquals(3.451, num1.getProperNumber());
    }

    @Test
    public void subtractionTestScalePositiveNegative4() {
        FixedPoint num1 = new FixedPoint("2.345");
        FixedPoint num2 = new FixedPoint("-1.11");
        num1.subtract(num2);
        Assertions.assertEquals(3.455, num1.getProperNumber());
    }


    @Test
    public void subtractionTestScaleNegative1() {
        FixedPoint num1 = new FixedPoint("-2.34");
        FixedPoint num2 = new FixedPoint("-1.111");
        num1.subtract(num2);
        Assertions.assertEquals(-1.229, num1.getProperNumber());
    }

    @Test
    public void subtractionTestScaleNegative2() {
        FixedPoint num1 = new FixedPoint("-2.345");
        FixedPoint num2 = new FixedPoint("-1.11");
        num1.subtract(num2);
        Assertions.assertEquals(-1.235, num1.getProperNumber());
    }
}
