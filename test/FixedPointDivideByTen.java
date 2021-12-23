import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FixedPointDivideByTen {
    @Test
    public void divideByTenTest1() {
        long[] input = { 1, Long.MAX_VALUE };
        long[] result = FixedPoint.divideByTen(input);
        System.out.println(result[0]);
        System.out.println(result[1]);

        Assertions.assertEquals(2767011611056432742L, result[1]);
        Assertions.assertEquals(0L, result[0]);
    }

    @Test
    public void divideByTenTest2() {
        long[] input = { Long.MAX_VALUE, Long.MIN_VALUE };
//        int counter = 0;
//        while (input[0] > 0) {
//            long[] result = FixedPoint.divideByTen(input);
//            input = result;
//            counter++;
//        }
//        input = FixedPoint.divideByTen(input);
//        System.out.println(input[1]);
//        System.out.println(counter);
        //17014118346046923155
        //170141183460469231722463931679029329920
        //17014118346046923171324055964217455411
        //1701411834604692317040171876053197783
        //17014118346046923168557044353161023

        long[] result = FixedPoint.divideByTen(input);
        System.out.println(result[0]);
        System.out.println(result[1]);

        //110011001100110011001100110011001100110011001100110011001100
        //1011001100110011001100110011001100110011001100110011001100110011
        long[] input2 = {922337203685477580L, -5534023222112865485L};
        long[] result2 = FixedPoint.divideByTen(input2);
        System.out.println(result2[0]);
        System.out.println(result2[1]);
        //92233720368547758
        //1199038364791120855
        //101000111101011100001010001111010111000010100011110101110
        //0001000010100011110101110000101000111101011100001010001111010111
        long[] input3 = {92233720368547758L, 1199038364791120855L};
        long[] result3 = FixedPoint.divideByTen(input3);
        System.out.println(result3[0]);
        System.out.println(result3[1]);
        //9223372036854775
        //-3578668350299653013
        //100000110001001001101110100101111000110101001111110111
        //1100111001010110000001000001100010010011011101001011110001101011
        long[] input4 = {9223372036854775L, -3578668350299653013L};
        long[] result4 = FixedPoint.divideByTen(input4);
        System.out.println(result4[0]);
        System.out.println(result4[1]);
        //11010001101101110001011101011000111000100001100101
        //1001010010011110111011001011111110110001010110110101011100111111
    }
}