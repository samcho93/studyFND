public class Counter60 {

    static int val(int[] q) {
        return q[3] * 8 + q[2] * 4 + q[1] * 2 + q[0];
    }

    static void wire(TTL7490 c, TTL7446 d, FND f) {
        d.setInput(c.getOutput());
        f.setInput(d.getOutput());
    }

    public static void main(String[] args) {
        TTL7490 s1  = new TTL7490();   TTL7446 d1  = new TTL7446();   FND f1  = new FND();
        TTL7490 s10 = new TTL7490();   TTL7446 d10 = new TTL7446();   FND f10 = new FND();

        s10.setNum(5);                                   // 50초부터 시작
        s1.setNum(0);

        for (int i = 0; i < 24; i++) {
            s1.setClock(i % 2);
            s10.setClock(s1.getOutput()[3]);             // 자리올림

            int[] q = s10.getOutput();
            s10.reset(q[2] & q[1], 0, 0);                // 6 이면 R0 = 1 -> 0

            if (i % 2 == 0) {
                wire(s10, d10, f10);
                wire(s1, d1, f1);

                System.out.println("[" + val(s10.getOutput()) + val(s1.getOutput()) + "초]");
                for (int line = 0; line < 5; line++) {
                    f10.dispFnd(line);
                    f1.dispFnd(line);
                    System.out.println();
                }
                System.out.println();
            }
        }
    }
}
