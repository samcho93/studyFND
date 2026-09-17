// 디지털 시계 : 칩 객체들을 필드로 가진 클래스 (12시간제, AM/PM)
class DigitalClock {

    // ---- 카운터 칩 6개 ----
    private TTL7490 h10 = new TTL7490(0, 1, 0);   // 1  -> 12:00:00 에서 시작
    private TTL7490 h1  = new TTL7490(0, 0, 1);   // 2
    private TTL7490 m10 = new TTL7490();
    private TTL7490 m1  = new TTL7490();
    private TTL7490 s10 = new TTL7490();
    private TTL7490 s1  = new TTL7490();

    // ---- 디코더와 표시기 6개씩 (순서 : 시10, 시1, 분10, 분1, 초10, 초1) ----
    private TTL7446[] dec = new TTL7446[6];
    private FND[]     fnd = new FND[6];

    private boolean pm = false;      // false = AM, true = PM
    private boolean was11 = false;   // 11시를 지나왔는지 (AM/PM 전환용)

    DigitalClock() {
        for (int k = 0; k < 6; k++) {
            dec[k] = new TTL7446();
            fnd[k] = new FND();
        }
        update();
    }

    // 시간 맞추기 (객체를 만든 직후, 클럭을 넣기 전에 호출)
    public void setTime(int h, int m, int s, boolean isPm) {
        h10.setNum(h / 10);  h1.setNum(h % 10);
        m10.setNum(m / 10);  m1.setNum(m % 10);
        s10.setNum(s / 10);  s1.setNum(s % 10);
        pm = isPm;
        update();
    }

    // 1초 클럭 입력 : clk 가 1 -> 0 이 될 때 1초 증가
    public void setClock(int clk) {
        // 초 : 60진
        s1.setClock(clk);
        s10.setClock(s1.getOutput()[3]);
        s10.reset(s10.getOutput()[2] & s10.getOutput()[1], 0, 0);

        // 분 : 60진, 클럭 = 초 10의 자리 b2
        m1.setClock(s10.getOutput()[2]);
        m10.setClock(m1.getOutput()[3]);
        m10.reset(m10.getOutput()[2] & m10.getOutput()[1], 0, 0);

        // 시 : 1 ~ 12, 클럭 = 분 10의 자리 b2
        h1.setClock(m10.getOutput()[2]);
        h10.setClock(h1.getOutput()[3]);
        if ((h10.getOutput()[0] & h1.getOutput()[1] & h1.getOutput()[0]) == 1) {   // 13
            h10.reset(1, 0, 0);          // 10의 자리 -> 0
            h1.reset(0, 1, 0);           // 1의 자리  -> 1   => 01시
        }

        checkAmPm();
        update();
    }

    // 11시 -> 12시로 넘어가는 순간 AM/PM 전환
    private void checkAmPm() {
        int[] qh10 = h10.getOutput();
        int[] qh1  = h1.getOutput();

        if (qh10[0] == 1 && qh1[0] == 1) {                 // 11시
            was11 = true;
        }
        else if (qh10[0] == 1 && qh1[1] == 1 && was11) {   // 12시 (11시 다음)
            was11 = false;
            pm = !pm;
        }
    }

    // 모든 칩 연결 : 7490 -> 7446 -> FND
    private void update() {
        TTL7490[] cnt = { h10, h1, m10, m1, s10, s1 };
        for (int k = 0; k < 6; k++) {
            dec[k].setInput(cnt[k].getOutput());
            fnd[k].setInput(dec[k].getOutput());
        }
    }

    // 콘솔에 FND 6자리 출력
    public void display() {
        for (int line = 0; line < 5; line++) {
            // AM 이면 위쪽(1번 줄), PM 이면 아래쪽(3번 줄)에 @@ 표시
            if      (line == 1 && !pm) System.out.print("@@ ");
            else if (line == 3 &&  pm) System.out.print("@@ ");
            else                       System.out.print("   ");

            for (int k = 0; k < 6; k++) {
                fnd[k].dispFnd(line);
                if (k == 1 || k == 3) {                      // 시:분, 분:초 사이
                    System.out.print((line == 1 || line == 3) ? "@ " : "  ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    // "11:59:58 AM" 형태의 문자열
    @Override
    public String toString() {
        return "" + h10.getNum() + h1.getNum() + ":" + m10.getNum() + m1.getNum()
                + ":" + s10.getNum() + s1.getNum() + (pm ? " PM" : " AM");
    }
}
