public class Step7 {

    static int cnt = 0;
    static int oldclk = 0;

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

    static int[] ttl7490(int clk, int R0, int R1, int R2) {
        if (R0 == 1)      { oldclk = 0; cnt = 0; }
        else if (R1 == 1) { oldclk = 0; cnt = 1; }
        else if (R2 == 1) { oldclk = 0; cnt = 2; }
        else {
            if (clk == 0 && oldclk == 1) {
                if (++cnt == 10) cnt = 0;
            }
            oldclk = clk;
        }
        return cnvtDec2Bin(cnt);
    }

    public static void main(String[] args) {
        ttl7490(0, 1, 0, 0);                // 시작 전에 0으로 초기화

        for (int i = 0; i < 20; i++) {
            int clk = i % 2;                // 0,1,0,1,... 클럭 생성
            int[] q = ttl7490(clk, 0, 0, 0);

            if (clk == 0) {                 // clk가 0일 때만 표시
                System.out.println("i=" + i + "  count=" + cnt);
                printFnd(makeFnd(ttl7446(q)));   // 7490 -> 7446 -> FND
            }
        }
    }
}
