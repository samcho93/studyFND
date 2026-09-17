public class Step2 {

    static int[] cnvtDec2Bin(int num) {
        int[] out = new int[4];
        for (int i = 0; i < 4; i++) {
            out[i] = num % 2;
            num = num / 2;
        }
        return out;
    }

    // [Step 2] 4비트 배열 -> FND 7비트 배열 {a, b, c, d, e, f, g}
    // 1 = 세그먼트 켜짐
    static int[] ttl7446(int[] bin) {
        int[][] table = {
            //a b c d e f g
            {1,1,1,1,1,1,0},  // 0
            {0,1,1,0,0,0,0},  // 1
            {1,1,0,1,1,0,1},  // 2
            {1,1,1,1,0,0,1},  // 3
            {0,1,1,0,0,1,1},  // 4
            {1,0,1,1,0,1,1},  // 5
            {1,0,1,1,1,1,1},  // 6
            {1,1,1,0,0,1,0},  // 7
            {1,1,1,1,1,1,1},  // 8
            {1,1,1,1,0,1,1}   // 9
        };

        // 4비트 -> 10진수 (가중치 8 4 2 1)
        int num = bin[3] * 8 + bin[2] * 4 + bin[1] * 2 + bin[0];

        // 10 ~ 15는 표에 없으므로 모두 끈다
        if (num > 9) return new int[7];

        return table[num];
    }

    static void printArray(int[] arr) {
        System.out.print("{");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(",");
        }
        System.out.print("}");
    }

    public static void main(String[] args) {
        for (int n = 0; n < 10; n++) {
            int[] bin = cnvtDec2Bin(n);
            int[] seg = ttl7446(bin);

            System.out.print(n + " : bin=");
            printArray(bin);
            System.out.print("  seg=");
            printArray(seg);
            System.out.println();
        }
    }
}
