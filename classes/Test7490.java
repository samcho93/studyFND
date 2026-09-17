public class Test7490 {

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
                System.out.print("i=" + i + "  a=" + a.getNum() + " (");
                printBin(a.getOutput());
                System.out.println(")   b=" + b.getNum());
            }
        }

        b.reset(0, 1, 0);                    // b 만 1로 초기화
        System.out.println("b.reset(0,1,0)  ->  a=" + a.getNum() + ", b=" + b.getNum());
    }
}
