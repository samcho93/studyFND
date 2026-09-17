public class Step6 {

    static int cnt = 0;      // 현재 카운트 값 (0 ~ 9)
    static int oldclk = 0;   // 직전 clk 값 (엣지 검출용)

    static int[] cnvtDec2Bin(int num) {
        int[] out = new int[4];
        for (int i = 0; i < 4; i++) {
            out[i] = num % 2;
            num = num / 2;
        }
        return out;
    }

    // [Step 6] 10진 카운터 TTL 7490
    //   R0 = 1 -> 0으로 초기화
    //   R1 = 1 -> 1로 초기화
    //   R2 = 1 -> 2로 초기화
    //   clk 가 1 -> 0 으로 바뀔 때(하강 엣지) 카운트 업
    static int[] ttl7490(int clk, int R0, int R1, int R2) {
        if (R0 == 1) {
            oldclk = 0;
            cnt = 0;
        }
        else if (R1 == 1) {
            oldclk = 0;
            cnt = 1;
        }
        else if (R2 == 1) {
            oldclk = 0;
            cnt = 2;
        }
        else {
            if (clk == 0 && oldclk == 1) {   // 하강 엣지
                if (++cnt == 10) cnt = 0;    // 9 다음은 0
            }
            oldclk = clk;                    // 현재 값을 기억
        }

        return cnvtDec2Bin(cnt);
    }

    static void printBin(int[] bin) {
        for (int i = 3; i >= 0; i--) System.out.print(bin[i]);
    }

    // 입력을 넣고 결과를 한 줄로 출력하는 테스트 도우미
    static void test(int clk, int R0, int R1, int R2) {
        int before = oldclk;
        int[] q = ttl7490(clk, R0, R1, R2);

        System.out.print("clk=" + clk + " R0=" + R0 + " R1=" + R1 + " R2=" + R2);
        System.out.print("  (oldclk=" + before + ")  -> cnt=" + cnt + "  Q=");
        printBin(q);
        System.out.println();
    }

    public static void main(String[] args) {
        test(0, 1, 0, 0);   // R0 : 0으로 초기화
        test(1, 0, 0, 0);   // 0 -> 1 : 상승 엣지, 변화 없음
        test(0, 0, 0, 0);   // 1 -> 0 : 하강 엣지, 카운트 업
        test(0, 0, 0, 0);   // 0 -> 0 : 변화 없음
        test(1, 0, 0, 0);
        test(0, 0, 0, 0);   // 하강 엣지, 카운트 업
        test(0, 0, 1, 0);   // R1 : 1로 초기화
        test(0, 0, 0, 1);   // R2 : 2로 초기화
        test(1, 0, 0, 0);
        test(0, 0, 0, 0);   // 하강 엣지, 카운트 업
    }
}
