package fractals.fpnumbers;

public final class FractalSettings<T extends FPNumber<T>, F extends FPNumberFactory<T>> {
    final int SIZE;

    T zoom;

    final int heightOfField;

    final int widthOfField;

    final int width;

    final int height;

    final int cores;

    final Fractal<T> fractal;

    final F numberFactory;

    final FractalPanel<T> panel;

    final T xBeginning;

    final T yBeginning;

    final T xRange;

    final T yRange;

    public FractalSettings(Fractal<T> fractal, F numberFactory, int heightOfField, int widthOfField,
                           String xBeginning, String yBeginning, String xRange, String yRange, int size,
                           int cores, double zoom) {
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
    }
}
