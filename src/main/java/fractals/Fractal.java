package fractals;

import fractals.fpnumbers.ComplexNumber;
import fractals.fpnumbers.FPNumber;

public interface Fractal<T extends FPNumber<T>> {

    ComplexNumber<T> step(ComplexNumber<T> z, ComplexNumber<T> c);

    void setup();

    void process(int steps, T xBeg, T yBeg, T xRan, T yRan, int cores) throws InterruptedException;

    void subProcess(int numberOfCores, long width, long height, int iterations, T realStep, T imaginaryStep, T xBeginning, T yBeginning, int coreIndex);

    ComplexNumber<T>[][] getField();

    int[] getColorField(int i, int j);
}
