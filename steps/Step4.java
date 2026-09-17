public class Step4 {

    static int[] cnvtDec2Bin(int num) {
        int[] out = new int[4];
        for (int i = 0; i < 4; i++) {
            out[i] = num % 2;
            num = num / 2;
        }
        return out;
    }

    static int[] ttl7446(int[] bin) {
        int[][] table = {
            {1,1,1,1,1,1,0}, {0,1,1,0,0,0,0}, {1,1,0,1,1,0,1},
            {1,1,1,1,0,0,1}, {0,1,1,0,0,1,1}, {1,0,1,1,0,1,1},
            {1,0,1,1,1,1,1}, {1,1,1,0,0,1,0}, {1,1,1,1,1,1,1},
            {1,1,1,1,0,1,1}
        };
        int num = bin[3] * 8 + bin[2] * 4 + bin[1] * 2 + bin[0];
        if (num > 9) return new int[7];
        return table[num];
    }

    static char[][] makeFnd(int[] seg) {
        char[][] fnd = new char[5][5];
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                fnd[i][j] = ' ';

        if (seg[0] == 1) for (int i = 0; i < 5; i++) fnd[0][i]   = '#';
        if (seg[1] == 1) for (int i = 0; i < 3; i++) fnd[i][4]   = '#';
        if (seg[2] == 1) for (int i = 0; i < 3; i++) fnd[i+2][4] = '#';
        if (seg[3] == 1) for (int i = 0; i < 5; i++) fnd[4][i]   = '#';
        if (seg[4] == 1) for (int i = 0; i < 3; i++) fnd[i+2][0] = '#';
        if (seg[5] == 1) for (int i = 0; i < 3; i++) fnd[i][0]   = '#';
        if (seg[6] == 1) for (int i = 0; i < 5; i++) fnd[2][i]   = '#';
        return fnd;
    }

    static void printFnd(char[][] fnd) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(fnd[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    // [Step 4] 1 ~ 3단계 메서드를 연결
    //   10진수 -> cnvtDec2Bin -> ttl7446 -> makeFnd -> printFnd
    static void dispNum(int n) {
        int[] bin    = cnvtDec2Bin(n);   // Step 1
        int[] seg    = ttl7446(bin);     // Step 2
        char[][] fnd = makeFnd(seg);     // Step 3
        printFnd(fnd);                   // Step 3
    }

    public static void main(String[] args) {
        for (int n = 0; n < 10; n++) {
            dispNum(n);
        }
    }
}
