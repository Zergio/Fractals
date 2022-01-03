package fractals.fpnumbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class FixedPointMultiplicationTest {

    @ParameterizedTest
    @MethodSource("ones")
    public void multiplicationTestEqualPositive1(FPNumber first, FPNumber second) {
        first.multiply(second);
        Assertions.assertEquals(1, first.getLong());
    }

    private static Stream<Arguments> ones() {
        return Stream.of(
                arguments(new FPDecimal1("1.0"), new FPDecimal1("1.0")),
                arguments(new FPDecimal2("1.0"), new FPDecimal2("1.0")),
                arguments(new FPBigDecimal("1.0"), new FPBigDecimal("1.0")));
    }

    @ParameterizedTest
    @MethodSource("twos")
    public void multiplicationTestEqualPositive2(FPNumber first, FPNumber second) {
        first.multiply(second);
        Assertions.assertEquals(4, first.getLong());
    }

    private static Stream<Arguments> twos() {
        return Stream.of(
                arguments(new FPDecimal1("2.0"), new FPDecimal1("2.0")),
                arguments(new FPDecimal2("2.0"), new FPDecimal2("2.0")),
                arguments(new FPBigDecimal("2.0"), new FPBigDecimal("2.0")));
    }

    @ParameterizedTest
    @MethodSource("ones")
    public void multiplicationTestEqualNegative(FPNumber first, FPNumber second) {
        first.multiply(second);
        Assertions.assertEquals(1, first.getLong());
    }

    @ParameterizedTest
    @MethodSource("minusTwos")
    public void multiplicationTestEqualNegative2(FPNumber first, FPNumber second) {
        first.multiply(second);
        Assertions.assertEquals(4, first.getLong());
    }

    private static Stream<Arguments> minusTwos() {
        return Stream.of(
                arguments(new FPDecimal1("-2.0"), new FPDecimal1("-2.0")),
                arguments(new FPDecimal2("-2.0"), new FPDecimal2("-2.0")),
                arguments(new FPBigDecimal("-2.0"), new FPBigDecimal("-2.0")));
    }

    @ParameterizedTest
    @MethodSource("minusTwoOne")
    public void multiplicationTestPositiveNegative(FPNumber first, FPNumber second) {
        first.multiply(second);
        Assertions.assertEquals(-2, first.getLong());
    }

    private static Stream<Arguments> minusTwoOne() {
        return Stream.of(
                arguments(new FPDecimal1("-2.0"), new FPDecimal1("1.0")),
                arguments(new FPDecimal2("-2.0"), new FPDecimal2("1.0")));
    }

    @ParameterizedTest
    @MethodSource("twoMinusOne")
    public void multiplicationTestNegativePositive(FPNumber first, FPNumber second) {
        first.multiply(second);
        Assertions.assertEquals(-2, first.getLong());
    }

    private static Stream<Arguments> twoMinusOne() {
        return Stream.of(
                arguments(new FPDecimal1("2.0"), new FPDecimal1("-1.0")),
                arguments(new FPDecimal2("2.0"), new FPDecimal2("-1.0")));
    }

    @ParameterizedTest
    @MethodSource("oneThousandthOne")
    public void multiplicationScale1(FPNumber first, FPNumber second) {
        first.multiply(second).multiply(1000);
        Assertions.assertEquals(1, first.getLong());
    }

    private static Stream<Arguments> oneThousandthOne() {
        return Stream.of(
                arguments(new FPDecimal1("0.001"), new FPDecimal1("1.0")),
                arguments(new FPDecimal2("0.001"), new FPDecimal2("1.0")));
    }

    @ParameterizedTest
    @MethodSource("oneOneThousandth")
    public void multiplicationScale2(FPNumber first, FPNumber second) {
        first.multiply(second).multiply(1000);
        Assertions.assertEquals(1, first.getLong());
    }

    private static Stream<Arguments> oneOneThousandth() {
        return Stream.of(
                arguments(new FPDecimal1("1"), new FPDecimal1("0.001")),
                arguments(new FPDecimal2("1"), new FPDecimal2("0.001")));
    }

    @ParameterizedTest
    @MethodSource("bigBig")
    public void multiplicationScale3(FPNumber first, FPNumber second) {
        first.multiply(second);
        System.out.println(first.getLong());
        System.out.println(new BigDecimal((1L << 48) + 1).multiply(new BigDecimal((1L << 48) + 3)));
        Assertions.assertEquals(7922816251426546349L, first.getLong());
    }

    private static Stream<Arguments> bigBig() {
        return Stream.of(
                arguments(new FPDecimal1((1L << 48) + 1), new FPDecimal1((1L << 48) + 3)),
                arguments(new FPDecimal2((1L << 48) + 1), new FPDecimal2((1L << 48) + 3)),
                arguments(new FPBigDecimal((1L << 48) + 1), new FPBigDecimal((1L << 48) + 3)));
    }

    //@Test
    public void multiplicationScalePerf() {
        long startTime = System.nanoTime();
        for (int i = 0; i < 100000000; i++) {
            FPDecimal1 num1 = new FPDecimal1((1L << 48) + 1);
            FPDecimal1 num2 = new FPDecimal1((1L << 48) + 3);
            num1.multiply(num2);
        }
        long estimatedTime = System.nanoTime() - startTime;
        System.out.println(estimatedTime);
    }

    //@Test
    public void multiplicationScalePerf2() {
        long startTime = System.nanoTime();
        for (int i = 0; i < 100000000; i++) {
            FPDecimal1 num1 = new FPDecimal1((1L << 48) + 1);
            FPDecimal1 num2 = new FPDecimal1((1L << 48) + 3);
            num1.multiply(num2);
        }
        long estimatedTime = System.nanoTime() - startTime;
        System.out.println(estimatedTime);
    }

    //@Test
    public void multiplicationScalePerfContinuous() {
        long startTime = System.nanoTime();
        FPDecimal1 num1 = new FPDecimal1((1L << 48) + 1);
        FPDecimal1 num2 = new FPDecimal1((1L << 48) + 3);
        for (int i = 0; i < 2; i++) {
            num1.multiply(num2);
        }
        long estimatedTime = System.nanoTime() - startTime;
        System.out.println(num1.getLong());
        System.out.println(estimatedTime);
    }
}
