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
        Assertions.assertEquals(0, first.getScaledInteger());
    }

    private static Stream<Arguments> equalPositive() {
        return Stream.of(
                arguments(new FPNumber1("2.345"), new FPNumber1("2.345")),
                arguments(new FPNumber2("2.345"), new FPNumber2("2.345"))
        );
    }

    @Test
    public void subtractionTestSmallBigPositive() {
        FPNumber1 num1 = new FPNumber1("1.111");
        FPNumber1 num2 = new FPNumber1("2.345");
        num1.subtract(num2).multiply(1000);
        Assertions.assertEquals(-1234, num1.getScaledInteger());
    }

    @Test
    public void subtractionTestBigSmallPositive() {
        FPNumber1 num1 = new FPNumber1("2.345");
        FPNumber1 num2 = new FPNumber1("1.111");
        num1.subtract(num2).multiply(1000);
        Assertions.assertEquals(1234, num1.getScaledInteger());
    }

    @Test
    public void subtractionTestScalePositive1() {
        FPNumber1 num1 = new FPNumber1("2.34");
        FPNumber1 num2 = new FPNumber1("1.111");
        num1.subtract(num2).multiply(1000);
        Assertions.assertEquals(1229, num1.getScaledInteger());
    }

    @Test
    public void subtractionTestScalePositive2() {
        FPNumber1 num1 = new FPNumber1("2.345");
        FPNumber1 num2 = new FPNumber1("1.11");
        num1.subtract(num2).multiply(1000);
        Assertions.assertEquals(1235, num1.getScaledInteger());
    }

    @Test
    public void subtractionTestEqualNegativePositive() {
        FPNumber1 num1 = new FPNumber1("-2.345");
        FPNumber1 num2 = new FPNumber1("2.345");
        num1.subtract(num2).multiply(1000);
        Assertions.assertEquals(-4690, num1.getScaledInteger());
    }

    @Test
    public void subtractionTestSmallNegativePositive() {
        FPNumber1 num1 = new FPNumber1("-1.111");
        FPNumber1 num2 = new FPNumber1("2.345");
        num1.subtract(num2).multiply(1000);
        Assertions.assertEquals(-3456, num1.getScaledInteger());
    }

    @Test
    public void subtractionTestNegativeSmallNegative() {
        FPNumber1 num1 = new FPNumber1("-2.345");
        FPNumber1 num2 = new FPNumber1("-1.111");
        num1.subtract(num2).multiply(1000);
        Assertions.assertEquals(-1234, num1.getScaledInteger());
    }

    @Test
    public void subtractionTestScalePositiveNegative1() {
        FPNumber1 num1 = new FPNumber1("-2.34");
        FPNumber1 num2 = new FPNumber1("1.111");
        num1.subtract(num2).multiply(1000);
        Assertions.assertEquals(-3451, num1.getScaledInteger());
    }

    @Test
    public void subtractionTestScalePositiveNegative2() {
        FPNumber1 num1 = new FPNumber1("-2.345");
        FPNumber1 num2 = new FPNumber1("1.11");
        num1.subtract(num2).multiply(1000);
        Assertions.assertEquals(-3455, num1.getScaledInteger());
    }

    @Test
    public void subtractionTestScalePositiveNegative3() {
        FPNumber1 num1 = new FPNumber1("2.34");
        FPNumber1 num2 = new FPNumber1("-1.111");
        num1.subtract(num2).multiply(1000);
        Assertions.assertEquals(3451, num1.getScaledInteger());
    }

    @Test
    public void subtractionTestScalePositiveNegative4() {
        FPNumber1 num1 = new FPNumber1("2.345");
        FPNumber1 num2 = new FPNumber1("-1.11");
        num1.subtract(num2).multiply(1000);
        Assertions.assertEquals(3455, num1.getScaledInteger());
    }

    @Test
    public void subtractionTestScaleNegative1() {
        FPNumber1 num1 = new FPNumber1("-2.34");
        FPNumber1 num2 = new FPNumber1("-1.111");
        num1.subtract(num2).multiply(1000);
        Assertions.assertEquals(-1229, num1.getScaledInteger());
    }

    @Test
    public void subtractionTestScaleNegative2() {
        FPNumber1 num1 = new FPNumber1("-2.345");
        FPNumber1 num2 = new FPNumber1("-1.11");
        num1.subtract(num2).multiply(1000);
        Assertions.assertEquals(-1235, num1.getScaledInteger());
    }
}
