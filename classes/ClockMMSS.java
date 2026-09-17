public class ClockMMSS {

    static void wire(TTL7490 c, TTL7446 d, FND f) {
        d.setInput(c.getOutput());
        f.setInput(d.getOutput());
    }

    public static void main(String[] args) {
        // 분 10 | 분 1 | 초 10 | 초 1
        TTL7490 m10 = new TTL7490(), m1 = new TTL7490(), s10 = new TTL7490(), s1 = new TTL7490();
        TTL7446 dm10 = new TTL7446(), dm1 = new TTL7446(), ds10 = new TTL7446(), ds1 = new TTL7446();
        FND     fm10 = new FND(),     fm1 = new FND(),     fs10 = new FND(),     fs1 = new FND();

        m10.setNum(5);  m1.setNum(9);                    // 59:50 부터 시작
        s10.setNum(5);  s1.setNum(0);

        for (int i = 0; i < 24; i++) {
            // ---- 초 : 60진 ----
            s1.setClock(i % 2);
            s10.setClock(s1.getOutput()[3]);
            s10.reset(s10.getOutput()[2] & s10.getOutput()[1], 0, 0);

            // ---- 분 : 60진, 클럭 = 초 10의 자리 b2 ----
            m1.setClock(s10.getOutput()[2]);
            m10.setClock(m1.getOutput()[3]);
            m10.reset(m10.getOutput()[2] & m10.getOutput()[1], 0, 0);

            if (i % 2 == 0) {
                wire(m10, dm10, fm10);  wire(m1, dm1, fm1);
                wire(s10, ds10, fs10);  wire(s1, ds1, fs1);

                for (int line = 0; line < 5; line++) {
                    fm10.dispFnd(line);
                    fm1.dispFnd(line);
                    System.out.print((line == 1 || line == 3) ? "@ " : "  ");   // 콜론
                    fs10.dispFnd(line);
                    fs1.dispFnd(line);
                    System.out.println();
                }
                System.out.println();
            }
        }
    }
}
