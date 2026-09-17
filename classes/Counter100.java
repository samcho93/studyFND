public class Counter100 {

    // 7490 -> 7446 -> FND 전선 연결 (값이 바뀐 뒤 호출)
    static void wire(TTL7490 c, TTL7446 d, FND f) {
        d.setInput(c.getOutput());
        f.setInput(d.getOutput());
    }

    public static void main(String[] args) {
        TTL7490 c1  = new TTL7490();     // 1의 자리 칩 세트
        TTL7446 d1  = new TTL7446();
        FND     f1  = new FND();

        TTL7490 c10 = new TTL7490();     // 10의 자리 칩 세트
        TTL7446 d10 = new TTL7446();
        FND     f10 = new FND();

        for (int i = 0; i < 202; i++) {
            c1.setClock(i % 2);                  // 1의 자리 : 외부 클럭
            c10.setClock(c1.getOutput()[3]);     // 10의 자리 : 1의 자리 b3

            if (i % 2 == 0) {
                wire(c10, d10, f10);
                wire(c1, d1, f1);

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
