// FND : 7세그먼트 표시기를 5x5 문자로 흉내
class FND {

    // ---- 필드 ----
    private int[] input = new int[7];        // 입력 핀 a ~ g
    private char[][] seg = new char[5][5];   // 화면
    private char ch;                         // 켜진 칸에 찍을 문자

    // ---- 생성자 (오버로딩) ----
    FND() {
        this('#');
    }

    FND(char c) {
        ch = c;
        cnvt();
    }

    FND(char c, int[] in) {
        ch = c;
        setInput(in);
    }

    private void clear() {
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                seg[i][j] = ' ';
    }

    // 입력 a ~ g -> 5x5 화면 (1부 Step 3 의 makeFnd)
    private void cnvt() {
        clear();

        if (input[0] == 1) for (int i = 0; i < 5; i++) seg[0][i]   = ch;  // a
        if (input[1] == 1) for (int i = 0; i < 3; i++) seg[i][4]   = ch;  // b
        if (input[2] == 1) for (int i = 0; i < 3; i++) seg[i+2][4] = ch;  // c
        if (input[3] == 1) for (int i = 0; i < 5; i++) seg[4][i]   = ch;  // d
        if (input[4] == 1) for (int i = 0; i < 3; i++) seg[i+2][0] = ch;  // e
        if (input[5] == 1) for (int i = 0; i < 3; i++) seg[i][0]   = ch;  // f
        if (input[6] == 1) for (int i = 0; i < 5; i++) seg[2][i]   = ch;  // g
    }

    public void setChar(char c) {
        ch = c;
        cnvt();                              // 문자가 바뀌면 화면도 다시 그림
    }

    public void setInput(int[] in) {
        input = in;
        cnvt();
    }

    // 한 자리 전체 출력 (세로 5줄)
    public void dispFnd() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(seg[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    // line 번째 줄만 출력 (여러 자리를 가로로 이어 붙일 때)
    public void dispFnd(int line) {
        for (int j = 0; j < 5; j++) {
            System.out.print(seg[line][j]);
        }
        System.out.print(" ");
    }
}
