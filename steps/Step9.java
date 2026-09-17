public class Step9 {

    static final int ONE = 0;   // 1의 자리 카운터 번호
    static final int TEN = 1;   // 10의 자리 카운터 번호

    static int[] cnt    = new int[2];   // 카운터마다 따로 기억
    static int[] oldclk = new int[2];

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

    static void printFnds(char[][][] fnds) {
        for (int row = 0; row < 5; row++) {
            for (int k = 0; k < fnds.length; k++) {
                for (int col = 0; col < 5; col++) {
                    System.out.print(fnds[k][row][col]);
                }
                System.out.print("  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // [Step 8-1] 카운터 번호(id)를 함께 받는 ttl7490
    static int[] ttl7490(int id, int clk, int R0, int R1, int R2) {
        if (R0 == 1)      { oldclk[id] = 0; cnt[id] = 0; }
        else if (R1 == 1) { oldclk[id] = 0; cnt[id] = 1; }
        else if (R2 == 1) { oldclk[id] = 0; cnt[id] = 2; }
        else {
            if (clk == 0 && oldclk[id] == 1) {
                if (++cnt[id] == 10) cnt[id] = 0;
            }
            oldclk[id] = clk;
        }
        return cnvtDec2Bin(cnt[id]);
    }

    // [Step 9] 60진 카운터 (00 ~ 59)
    //   10의 자리가 6(0110)이 되면 b2 & b1 = 1 -> R0 = 1 -> 즉시 0
    //   반환: {10의 자리 Q, 1의 자리 Q}
    static int[][] counter60(int clk) {
        int[] q1  = ttl7490(ONE, clk,   0, 0, 0);    // 1의 자리
        int[] q10 = ttl7490(TEN, q1[3], 0, 0, 0);    // 10의 자리 clk = 1의 자리 b3

        int r0 = q10[2] & q10[1];                    // 6 = 0110 -> b2, b1 이 모두 1
        q10 = ttl7490(TEN, q1[3], r0, 0, 0);         // 출력을 R0로 되돌려 연결

        return new int[][] { q10, q1 };
    }

    // [Step 8-3] 여러 자리의 Q를 FND로 가로 출력
    static void dispCounter(int[][] q) {
        char[][][] fnds = new char[q.length][][];

        for (int k = 0; k < q.length; k++) {
            fnds[k] = makeFnd(ttl7446(q[k]));
        }

        printFnds(fnds);
    }

    public static void main(String[] args) {
        ttl7490(ONE, 0, 1, 0, 0);          // 두 카운터 모두 0으로 초기화
        ttl7490(TEN, 0, 1, 0, 0);

        for (int i = 0; i < 122; i++) {
            int clk = i % 2;
            int[][] q = counter60(clk);

            if (clk == 0) {
                System.out.println("i=" + i + "  count=" + cnt[TEN] + cnt[ONE]);
                dispCounter(q);
            }
        }
    }
}
