import java.awt.image.BufferedImage;
import java.math.BigDecimal;
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
        BigDecimal num = new BigDecimal(1d/3d);
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

        process(30);
        panel.paintField(baseField, SIZE, Mandelbrot);
        //TimeUnit.MILLISECONDS.sleep(1);


    }

    private static void process(int iterations) {
        BigDecimal decHeight = new BigDecimal(heightOfField, Utils.CONTEXT);
        BigDecimal decWidth = new BigDecimal(widthOfField, Utils.CONTEXT);
        BigDecimal zoomX = new BigDecimal("30", Utils.CONTEXT);//0.5
        BigDecimal offsetX = new BigDecimal("0.7", Utils.CONTEXT);//0.5
        BigDecimal zoomY = new BigDecimal("30", Utils.CONTEXT);//0.5
        BigDecimal offsetY = new BigDecimal("0.3", Utils.CONTEXT);//0.0
        BigDecimal zoomXByWidth = Utils.TWO.divide(decWidth.multiply(zoomX, Utils.CONTEXT), Utils.CONTEXT);
        BigDecimal zoomYByHeight = Utils.TWO.divide(decHeight.multiply(zoomY, Utils.CONTEXT), Utils.CONTEXT);
        int startI = -widthOfField / 2;
        int startJ = -heightOfField / 2;
        for (int i = startI; i < startI + widthOfField; i++) {
            BigDecimal x = new BigDecimal(i, Utils.CONTEXT)
                    .multiply(zoomXByWidth, Utils.CONTEXT)
                    .subtract(offsetX, Utils.CONTEXT);
            for (int j = startJ; j < startJ + heightOfField; j++) {
                BigDecimal y = new BigDecimal(j, Utils.CONTEXT)
                        .multiply(zoomYByHeight, Utils.CONTEXT)
                        .subtract(offsetY, Utils.CONTEXT);
                Complex1 xy = new Complex1(x, y);
                for (int num = 0; num < iterations; num++) {
                    Complex1 current = baseField[j - startJ][i - startI];
                    current.square();
                    current.plus(xy);
                    if (Math.abs(current.real()
                            .pow(2, Utils.CONTEXT)
                            .add(current.imag()
                                    .pow(2, Utils.CONTEXT),
                                    Utils.CONTEXT).intValue()) > 4)
                        break;
                }
            }
        }
    }
}