package fractals.fpnumbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class FixedPointSubtractionTest {

    @ParameterizedTest
    @MethodSource("equalPositive")
    public void subtractionTestEqualPositive(FPNumber first, FPNumber second) {
        first.subtract(second);
        Assertions.assertEquals(0, first.getLong());
    }

    private static Stream<Arguments> equalPositive() {
        return Stream.of(
                arguments(new FPDecimal1("2.345"), new FPDecimal1("2.345")),
                arguments(new FPDecimal2("2.345"), new FPDecimal2("2.345"))
        );
    }

    @Test
    public void subtractionTestSmallBigPositive() {
        FPDecimal1 num1 = new FPDecimal1("1.111");
        FPDecimal1 num2 = new FPDecimal1("2.345");
        num1.subtract(num2).multiply(1000);
        Assertions.assertEquals(-1234, num1.getLong());
    }

    @Test
    public void subtractionTestBigSmallPositive() {
        FPDecimal1 num1 = new FPDecimal1("2.345");
        FPDecimal1 num2 = new FPDecimal1("1.111");
        num1.subtract(num2).multiply(1000);
        Assertions.assertEquals(1234, num1.getLong());
    }

    @Test
    public void subtractionTestScalePositive1() {
        FPDecimal1 num1 = new FPDecimal1("2.34");
        FPDecimal1 num2 = new FPDecimal1("1.111");
        num1.subtract(num2).multiply(1000);
        Assertions.assertEquals(1229, num1.getLong());
    }

    @Test
    public void subtractionTestScalePositive2() {
        FPDecimal1 num1 = new FPDecimal1("2.345");
        FPDecimal1 num2 = new FPDecimal1("1.11");
        num1.subtract(num2).multiply(1000);
        Assertions.assertEquals(1235, num1.getLong());
    }

    @Test
    public void subtractionTestEqualNegativePositive() {
        FPDecimal1 num1 = new FPDecimal1("-2.345");
        FPDecimal1 num2 = new FPDecimal1("2.345");
        num1.subtract(num2).multiply(1000);
        Assertions.assertEquals(-4690, num1.getLong());
    }

    @Test
    public void subtractionTestSmallNegativePositive() {
        FPDecimal1 num1 = new FPDecimal1("-1.111");
        FPDecimal1 num2 = new FPDecimal1("2.345");
        num1.subtract(num2).multiply(1000);
        Assertions.assertEquals(-3456, num1.getLong());
    }

    @Test
    public void subtractionTestNegativeSmallNegative() {
        FPDecimal1 num1 = new FPDecimal1("-2.345");
        FPDecimal1 num2 = new FPDecimal1("-1.111");
        num1.subtract(num2).multiply(1000);
        Assertions.assertEquals(-1234, num1.getLong());
    }

    @Test
    public void subtractionTestScalePositiveNegative1() {
        FPDecimal1 num1 = new FPDecimal1("-2.34");
        FPDecimal1 num2 = new FPDecimal1("1.111");
        num1.subtract(num2).multiply(1000);
        Assertions.assertEquals(-3451, num1.getLong());
    }

    @Test
    public void subtractionTestScalePositiveNegative2() {
        FPDecimal1 num1 = new FPDecimal1("-2.345");
        FPDecimal1 num2 = new FPDecimal1("1.11");
        num1.subtract(num2).multiply(1000);
        Assertions.assertEquals(-3455, num1.getLong());
    }

    @Test
    public void subtractionTestScalePositiveNegative3() {
        FPDecimal1 num1 = new FPDecimal1("2.34");
        FPDecimal1 num2 = new FPDecimal1("-1.111");
        num1.subtract(num2).multiply(1000);
        Assertions.assertEquals(3451, num1.getLong());
    }

    @Test
    public void subtractionTestScalePositiveNegative4() {
        FPDecimal1 num1 = new FPDecimal1("2.345");
        FPDecimal1 num2 = new FPDecimal1("-1.11");
        num1.subtract(num2).multiply(1000);
        Assertions.assertEquals(3455, num1.getLong());
    }

    @Test
    public void subtractionTestScaleNegative1() {
        FPDecimal1 num1 = new FPDecimal1("-2.34");
        FPDecimal1 num2 = new FPDecimal1("-1.111");
        num1.subtract(num2).multiply(1000);
        Assertions.assertEquals(-1229, num1.getLong());
    }

    @Test
    public void subtractionTestScaleNegative2() {
        FPDecimal1 num1 = new FPDecimal1("-2.345");
        FPDecimal1 num2 = new FPDecimal1("-1.11");
        num1.subtract(num2).multiply(1000);
        Assertions.assertEquals(-1235, num1.getLong());
    }
}
