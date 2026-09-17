// TTL 7490 : 10진 카운터 칩
class TTL7490 {

    // ---- 필드 : 칩이 기억하는 상태 ----
    private int num;                     // 현재 카운트 값 (0 ~ 9)
    private int oldclk;                  // 직전 clk 값 (엣지 검출용)
    private int[] output = new int[4];   // 출력 핀 b0 ~ b3

    // ---- 생성자 : 전원을 켰을 때의 상태 ----
    TTL7490() {
        this(1, 0, 0);                   // 기본은 0으로 초기화
    }

    TTL7490(int R0, int R1, int R2) {
        oldclk = 0;
        reset(R0, R1, R2);
    }

    // num -> 4비트 출력 (1부 Step 1 의 cnvtDec2Bin)
    private void cnvt() {
        int n = num;
        for (int i = 0; i < 4; i++) {
            output[i] = n % 2;
            n = n / 2;
        }
    }

    // clk 입력 핀 : 1 -> 0 으로 바뀔 때 카운트 업
    public void setClock(int clk) {
        if (clk == 0 && oldclk == 1) {
            if (++num == 10) num = 0;
            cnvt();
        }
        oldclk = clk;
    }

    // 리셋 입력 핀 : R0 -> 0, R1 -> 1, R2 -> 2
    public void reset(int R0, int R1, int R2) {
        if (R0 == 1)      num = 0;
        else if (R1 == 1) num = 1;
        else if (R2 == 1) num = 2;
        cnvt();
    }

    // 출력 핀 b0 ~ b3
    public int[] getOutput() {
        return output;
    }

    // 원하는 값으로 맞추기 (시계의 시간 설정용)
    public void setNum(int n) {
        num = n % 10;
        cnvt();
    }

    public int getNum() {
        return num;
    }
}
