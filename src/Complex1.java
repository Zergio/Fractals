import java.math.BigDecimal;
import java.math.MathContext;

public class Complex1 extends Object {
    private BigDecimal x,y;

    public Complex1(BigDecimal real,BigDecimal imaginary) {
        x = real;
        y = imaginary;
    }

    public Complex1(double real, double imaginary) {
        x = new BigDecimal(real, MathContext.DECIMAL64);
        y = new BigDecimal(imaginary, MathContext.DECIMAL64);
    }
/*
    public Complex1(int real,int imaginary) {
        x = new BigDecimal(real);
        y = new BigDecimal(imaginary);
    }


 */
    public BigDecimal real() {
        return x;
    }

    public BigDecimal imag() {
        return y;
    }

    public void plus(Complex1 number) {
        x = x.add(number.real(), MathContext.DECIMAL64);
        y = y.add(number.imag(), MathContext.DECIMAL64);
    }

    public void minus(Complex1 number) {
        x = x.subtract(number.real(), MathContext.DECIMAL64);
        y = y.subtract(number.imag(), MathContext.DECIMAL64);
    }

    /**
     Complex multiplication (doesn't change this Complex number).
     @param w is the number to multiply by.
     @return z*w where z is this Complex number.
     */
    public void times(Complex1 w) {
        //IMPLEMENT
    }

    public void divideBy(Complex w) {
        //IMPLEMENT
    }

    public void square() {
        BigDecimal temp = x;
        x = x.pow(2, MathContext.DECIMAL64)
                .subtract(
                        y.pow(2, MathContext.DECIMAL64),
                        MathContext.DECIMAL64);
        y = y.multiply(temp, MathContext.DECIMAL64)
                .multiply(Utils.TWO, MathContext.DECIMAL64);
    }

    public void sqrt() {
        //IMPLEMENT
    }
}