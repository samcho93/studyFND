public class Step5 {

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

    // [Step 5-1] 한 자리(digit) = Step 1 ~ 3 을 묶은 메서드
    static char[][] digit(int n) {
        return makeFnd(ttl7446(cnvtDec2Bin(n)));
    }

    // [Step 5-2] 0 ~ 9999 -> 자릿수 4개 {천, 백, 십, 일}
    static int[] splitDigits(int num) {
        int[] d = new int[4];
        d[0] = num / 1000 % 10;   // 천의 자리
        d[1] = num / 100  % 10;   // 백의 자리
        d[2] = num / 10   % 10;   // 십의 자리
        d[3] = num        % 10;   // 일의 자리
        return d;
    }

    // [Step 5-3] 여러 개의 FND를 가로로 나란히 출력
    //   바깥 for = 줄(row), 가운데 for = 자리(k), 안쪽 for = 칸(col)
    static void printFnds(char[][][] fnds) {
        for (int row = 0; row < 5; row++) {
            for (int k = 0; k < fnds.length; k++) {
                for (int col = 0; col < 5; col++) {
                    System.out.print(fnds[k][row][col]);
                }
                System.out.print("  ");   // 자리 사이 간격
            }
            System.out.println();
        }
        System.out.println();
    }

    // [Step 5-4] 0 ~ 9999 를 4자리 FND로 표시
    static void dispNum4(int num) {
        int[] d = splitDigits(num);
        char[][][] fnds = new char[4][][];

        for (int k = 0; k < 4; k++) {
            fnds[k] = digit(d[k]);
        }

        printFnds(fnds);
    }

    public static void main(String[] args) {
        for (int n = 0; n <= 9999; n++) {
            dispNum4(n);
        }
    }
}
