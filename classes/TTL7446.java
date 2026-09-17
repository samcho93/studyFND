// TTL 7446 : BCD -> 7세그먼트 디코더 칩
class TTL7446 {

    // 진리표 {a,b,c,d,e,f,g} : 모든 7446 객체가 함께 쓰므로 static
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

    // ---- 필드 ----
    private int[] input;                 // 입력 핀 b0 ~ b3
    private int[] output = new int[7];   // 출력 핀 a ~ g
    private int num;                     // 입력을 10진수로 읽은 값

    // ---- 생성자 ----
    TTL7446() {
        this(new int[4]);                // 입력 0000
    }

    TTL7446(int[] in) {
        setInput(in);
    }

    // 입력 4비트 -> 7세그먼트 출력 (1부 Step 2 의 ttl7446)
    private void cnvt() {
        num = input[3] * 8 + input[2] * 4 + input[1] * 2 + input[0];

        if (num > 9) output = new int[7];       // 10 ~ 15 는 모두 끔
        else         output = TABLE[num].clone(); // 표 원본은 지킨다
    }

    // 입력 핀에 신호 연결
    public void setInput(int[] in) {
        input = in;
        cnvt();
    }

    // 출력 핀 a ~ g
    public int[] getOutput() {
        return output;
    }

    public int getNum() {
        return num;
    }
}
