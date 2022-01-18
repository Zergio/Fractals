package fractals;

import fractals.fpnumbers.FPNumber;
import fractals.fpnumbers.FPNumberFactory;
import fractals.ui.FractalPanel;

public final class FractalSettings<T extends FPNumber<T>, F extends FPNumberFactory<T>> {
    public final int SIZE;

    public T zoom;

    public int zoomSteps;

    public int steps;

    public final int heightOfField;

    public final int widthOfField;

    public final int width;

    public final int height;

    public final int cores;

    public final Fractal<T> fractal;

    public final F numberFactory;

    public final FractalPanel<T> panel;

    public final T xBeginning;

    public final T yBeginning;

    public final T xRange;

    public final T yRange;

    public FractalSettings(Fractal<T> fractal, F numberFactory, int heightOfField, int widthOfField,
                           String xBeginning, String yBeginning, String xRange, String yRange, int size,
                           int cores, double zoom, int zoomSteps, int steps) {
        this.SIZE = size;
        this.fractal = fractal;
        this.numberFactory = numberFactory;
        this.heightOfField = heightOfField;
        this.widthOfField = widthOfField;
        this.width = widthOfField * SIZE;
        this.height = heightOfField * SIZE;
        this.panel = new FractalPanel<>(fractal, width, height);
        this.xBeginning = numberFactory.createFPNumber(xBeginning);
        this.yBeginning = numberFactory.createFPNumber(yBeginning);
        this.xRange = numberFactory.createFPNumber(xRange);
        this.yRange = numberFactory.createFPNumber(yRange);
        this.cores = cores;
        this.zoom = numberFactory.createFPNumber(String.valueOf(zoom));
        this.zoomSteps = zoomSteps;
        this.steps = steps;
    }
}
