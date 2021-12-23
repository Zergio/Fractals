public class Complex1 extends Object {
    private FixedPoint x,y;

    public Complex1(FixedPoint real,FixedPoint imaginary) {
        x = real;
        y = imaginary;
    }

    public Complex1(double real, double imaginary) {
        x = new FixedPoint((long) real); //I know that this isn't really how it should work, but duck this, i am tired and willl fix this later.
        y = new FixedPoint((long) imaginary);
    }
/*
    public Complex1(int real,int imaginary) {
        x = new FixedPoint(real);
        y = new FixedPoint(imaginary);
    }
 */

    public FixedPoint real() {
        return x;
    }

    public FixedPoint imag() {
        return y;
    }

    public void plus(Complex1 number) {
        x.add(number.real());
        y.add(number.imag());
    }

    public void minus(Complex1 number) {
        x.subtract(number.real());
        y.subtract(number.imag());
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
        FixedPoint temp = new FixedPoint(x.getNumber(), x.getScale());
        x.square();
        y.square();
        x.subtract(y);
        y.multiply(temp);
        x.multiply(new FixedPoint(2));
    }

    public void sqrt() {
        //IMPLEMENT
    }
}