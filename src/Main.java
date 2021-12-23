import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel{

    private static final int heightOfField = 1000;

    private static final int widthOfField = 1500;

    private static final int SIZE = 1;

    private static Complex1[][] baseField = new Complex1[heightOfField][widthOfField];

    private static BufferedImage canvas;

    private static final Mandelbrot Mandelbrot = new Mandelbrot();

    public static void main(String[] args) {
        /*
        FixedPoint num = new FixedPoint(1d/3d);
        num = num.setScale(10,1);
        System.out.println(1);
        */


        int width = widthOfField * SIZE;
        int height = heightOfField * SIZE;
        canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        JFrame frame = new JFrame("Fractals");

        DirectDraw panel = new DirectDraw(width, height);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        baseField = Mandelbrot.setup(baseField);

        process(2);
        panel.paintField(baseField, SIZE, Mandelbrot);
        //TimeUnit.MILLISECONDS.sleep(1);
    }

    private static void process(int iterations) {
        FixedPoint decHeight = new FixedPoint(heightOfField);
        FixedPoint decWidth = new FixedPoint(widthOfField);
        FixedPoint zoomX = new FixedPoint("0.5");//0.5    //30
        FixedPoint offsetX = new FixedPoint("0.5");//0.5  //0.7
        FixedPoint zoomY = new FixedPoint("0.5");//0.5    //30
        FixedPoint offsetY = new FixedPoint("0.0");//0.0  //0.3
//        FixedPoint two = new FixedPoint("2.0");
//        decWidth.multiply(zoomX);
//        two.divide(decWidth);
        FixedPoint zoomXByWidth = new FixedPoint("0.00075");
//        two = new FixedPoint("2.0");
//        decHeight.multiply(zoomY);
//        two.divide(decHeight);
        FixedPoint zoomYByHeight = new FixedPoint("0.001");
        int startI = -widthOfField / 2;
        int startJ = -heightOfField / 2;
        for (int i = startI; i < startI + widthOfField; i++) {
            FixedPoint x = new FixedPoint(i);
            x.multiply(zoomXByWidth);
            x.subtract(offsetX);
            for (int j = startJ; j < startJ + heightOfField; j++) {
                FixedPoint y = new FixedPoint(j);
                y.multiply(zoomYByHeight);
                y.subtract(offsetY);
                Complex1 xy = new Complex1(x, y);
                for (int num = 0; num < iterations; num++) {
                    Complex1 current = baseField[j - startJ][i - startI];
                    current.square();
                    current.plus(xy);
                    FixedPoint r = new FixedPoint(current.real().getNumber(), current.real().getScale());
                    FixedPoint im = new FixedPoint(current.imag().getNumber(), current.imag().getScale());;
                    im.square();
                    r.square();
                    r.add(im);
                    if (Math.abs(r.getProperNumber()) > 4) {
                        break;
                    }
                }
            }
        }
    }
}