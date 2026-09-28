// DigitClock : Digit 여섯 개로 만든 12시간제 디지털 시계
//   Main.java 와 하는 일도 출력도 같지만, 칩 18개를 연결하던 자리에 '자리' 6개만 남는다
public class DigitClock {

    // 여섯 자리를 줄(line) 단위로 가로 출력
    static void display(Digit[] d, boolean apm) {
        for (int line = 0; line < 5; line++) {
            if (line == 1 && apm == false)     System.out.print("@@ ");   // AM 표시
            else if (line == 3 && apm == true) System.out.print("@@ ");   // PM 표시
            else                               System.out.print("   ");

            for (int k = 0; k < 6; k++) {
                d[k].dispFnd(line);
                if (k == 1 || k == 3) {                                   // 시:분:초 사이의 콜론
                    if (line == 1 || line == 3) System.out.print("@ ");
                    else                        System.out.print("  ");
                }
            }
            System.out.println("");
        }

        System.out.println("\n");
    }

    public static void main(String[] args) {
        // 시10, 시1, 분10, 분1, 초10, 초1  ->  12:59:30 에서 출발
        Digit[] d = { new Digit('#', 1), new Digit('#', 2),
                      new Digit('#', 5), new Digit('#', 9),
                      new Digit('#', 3), new Digit('#', 0) };

        Digit h10 = d[0], h1 = d[1], m10 = d[2], m1 = d[3], s10 = d[4], s1 = d[5];

        boolean apm = false;   // false = AM, true = PM
        boolean chk = false;   // 11시를 지나왔는가

        System.out.println("------------------------------------");
        for (int i = 0; i < 102; i++) {
            s1.setClock(i % 2);                        // 초 1의 자리 : 바깥에서 주는 클럭
            s10.setClock(s1.bit(3));                   // 자리올림
            s10.reset(s10.bit(2) & s10.bit(1), 0, 0);  // 6 이면 0 으로 (60진)

            m1.setClock(s10.bit(2));                   // 분 1의 자리
            m10.setClock(m1.bit(3));
            m10.reset(m10.bit(2) & m10.bit(1), 0, 0);

            h1.setClock(m10.bit(2));                   // 시 1의 자리
            h10.setClock(h1.bit(3));
            if ((h10.bit(0) & h1.bit(1) & h1.bit(0)) == 1) {   // 13시 -> 01시
                h10.reset(1, 0, 0);
                h1.reset(0, 1, 0);
            }

            if (h10.bit(0) == 1 && h1.bit(0) == 1 && chk == false) {
                chk = true;                            // 11시를 지났다
            }
            else if (h10.bit(0) == 1 && h1.bit(1) == 1 && chk == true) {
                chk = false;
                apm = !apm;                            // 12시가 되는 순간 한 번만 뒤집는다
            }

            if (i % 2 == 0) display(d, apm);           // 클럭이 0 일 때만 출력
        }
    }
}
