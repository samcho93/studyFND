// Digit : TTL7490 + TTL7446 + FND 를 한 클래스로 합친 '시계 한 자리'
//   세는 일(7490) · 세그먼트로 바꾸는 일(7446) · 화면(FND) 을 모두 안에서 처리한다
class Digit {

    // 0 ~ 9 의 세그먼트 표 {a,b,c,d,e,f,g}
    //   자리마다 같은 표이므로 클래스에 하나만 둔다 (static), 바꿔 끼울 일도 없다 (final)
    private static final int[][] TABLE = {
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

    private int num;                        // 지금 숫자 0 ~ 9   (7490 의 num)
    private int oldclk;                     // 직전 클럭          (7490 의 oldclk)
    private char ch;                        // 켜진 칸에 찍을 문자 (FND 의 ch)
    private char[][] seg = new char[5][5];  // 화면               (FND 의 seg)

    // ---- 생성자 ----
    Digit() {
        this('#', 0);
    }

    Digit(char c) {
        this(c, 0);
    }

    Digit(char c, int n) {
        ch = c;
        oldclk = 0;
        setNum(n);
    }

    // ---- 입력 핀 ----
    // 클럭이 1 -> 0 으로 떨어질 때만 하나 센다
    public void setClock(int clk) {
        if (clk == 0 && oldclk == 1) {
            if (++num == 10) num = 0;
            draw();
        }
        oldclk = clk;
    }

    // R0 -> 0, R1 -> 1, R2 -> 2
    public void reset(int R0, int R1, int R2) {
        if (R0 == 1)      num = 0;
        else if (R1 == 1) num = 1;
        else if (R2 == 1) num = 2;
        draw();
    }

    public void setNum(int n) {
        num = n % 10;
        draw();
    }

    // ---- 출력 핀 ----
    // 7490 의 출력 4비트는 결국 num 의 2진수였다. 배열을 들고 있을 필요 없이 한 자리만 꺼낸다.
    //   bit(3) : 자리올림,  bit(2) & bit(1) : 6 인지 검사
    public int bit(int i) {
        return (num >> i) & 1;
    }

    public int getNum() {
        return num;
    }

    // ---- 화면 ----
    // 숫자가 바뀔 때마다 표를 보고 5x5 를 다시 그린다 (7446 -> FND 가 여기서 한 번에 끝난다)
    private void draw() {
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                seg[i][j] = ' ';

        int[] s = TABLE[num];

        if (s[0] == 1) for (int i = 0; i < 5; i++) seg[0][i]   = ch;  // a
        if (s[1] == 1) for (int i = 0; i < 3; i++) seg[i][4]   = ch;  // b
        if (s[2] == 1) for (int i = 0; i < 3; i++) seg[i+2][4] = ch;  // c
        if (s[3] == 1) for (int i = 0; i < 5; i++) seg[4][i]   = ch;  // d
        if (s[4] == 1) for (int i = 0; i < 3; i++) seg[i+2][0] = ch;  // e
        if (s[5] == 1) for (int i = 0; i < 3; i++) seg[i][0]   = ch;  // f
        if (s[6] == 1) for (int i = 0; i < 5; i++) seg[2][i]   = ch;  // g
    }

    // 한 자리 전체 출력 (세로 5줄)
    public void dispFnd() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(seg[i][j]);
            }
            System.out.println("");
        }

        System.out.println("");
    }

    // line 번째 줄만 출력 (여러 자리를 가로로 이어 붙일 때)
    public void dispFnd(int line) {
        for (int j = 0; j < 5; j++) {
            System.out.print(seg[line][j]);
        }
        System.out.print(" ");
    }
}
