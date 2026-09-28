public class Test7490 {

    // 4비트 출력을 10진수로 (8·4·2·1 가중치) — TTL7490 에는 getNum 이 없다
    static int val(int[] q) {
        return q[3] * 8 + q[2] * 4 + q[1] * 2 + q[0];
    }

    static void printBin(int[] q) {
        for (int i = 3; i >= 0; i--) System.out.print(q[i]);
    }

    public static void main(String[] args) {
        TTL7490 a = new TTL7490();           // 0 에서 시작
        TTL7490 b = new TTL7490(0, 0, 1);    // R2 -> 2 에서 시작

        for (int i = 0; i < 8; i++) {
            int clk = i % 2;
            a.setClock(clk);                 // a 에만 클럭을 넣는다

            if (clk == 0) {
                System.out.print("i=" + i + "  a=" + val(a.getOutput()) + " (");
                printBin(a.getOutput());
                System.out.println(")   b=" + val(b.getOutput()));
            }
        }

        b.reset(0, 1, 0);                    // b 만 1로 초기화
        System.out.println("b.reset(0,1,0)  ->  a=" + val(a.getOutput()) + ", b=" + val(b.getOutput()));
    }
}
