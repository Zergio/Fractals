package fractals;

import fractals.Fractal;
import fractals.fpnumbers.FPNumber;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class ProcessingThread<T extends FPNumber<T>> extends Thread implements Callable<Integer> {

    private final int cores;
    private final int core;
    private final int width;
    private final int height;
    private final Fractal<T> fractal;

    private int iterations;
    private T realStep;
    private T imaginaryStep;
    private T xBeginning;
    private T yBeginning;

    private final CountDownLatch latch;

    public ProcessingThread(Fractal<T> fractal, int cores, int core, int width, int height, CountDownLatch latch) {
        this.cores = cores;
        this.core = core;
        this.width = width;
        this.height = height;
        this.fractal = fractal;
        this.latch = latch;
    }

    public void set(int iterations, T realStep, T imaginaryStep, T xBeginning, T yBeginning) {
        this.iterations = iterations;
        this.realStep = realStep;
        this.imaginaryStep = imaginaryStep;
        this.xBeginning = xBeginning;
        this.yBeginning = yBeginning;
    }

    @Override
    public Integer call() {
        fractal.subProcess(cores, width, height, iterations, realStep, imaginaryStep, xBeginning, yBeginning, core);
        latch.countDown();
        return core;
    }

    public void run() {
        call();
    }
}
