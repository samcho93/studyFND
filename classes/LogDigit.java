// LogDigit : 세는 일은 그대로 두고 '무슨 일이 있었는지'만 적어 두는 자리  (과제 F)
//   부모 Digit 도, 칩 세 개도 건드리지 않는다. 상속으로 기능을 얹는 예.
class LogDigit extends Digit {

    private int clocks;                    // setClock 을 받은 횟수
    private int changes;                   // 그중 값이 실제로 바뀐 횟수
    private int laps;                      // 9 -> 0 처럼 한 바퀴 돈 횟수 (= 자리올림)
    private String trail = "";             // 지나간 값을 순서대로
    private int[] seen = new int[10];      // 숫자별로 몇 번 머물렀나

    LogDigit() {
        this('#', 0);
    }

    LogDigit(int n) {
        this('#', n);
    }

    LogDigit(char c, int n) {
        super(c, n);
        trail = "" + getNum();             // 시작값도 지나간 값이다
        seen[getNum()]++;
    }

    // ---- 부모 일을 시키고, 앞뒤 값을 비교해 기록만 남긴다 ----
    @Override
    public void setClock(int clk) {
        int before = getNum();

        super.setClock(clk);               // 세는 일은 부모(=7490) 몫

        int after = getNum();
        clocks++;

        if (after != before) {
            changes++;
            if (after < before) laps++;    // 값이 작아졌다 = 한 바퀴 돌았다
            trail += after;
            seen[after]++;
        }
    }

    // ---- 꺼내 보기 ----
    public int getClocks()  { return clocks; }
    public int getChanges() { return changes; }
    public int getLaps()    { return laps; }
    public String getTrail(){ return trail; }
    public int getSeen(int n){ return seen[n]; }

    // 한 줄 요약 : Object 의 toString 을 물려받아 고친 것
    @Override
    public String toString() {
        return "지금 " + getNum() + " · 클럭 " + clocks + "번 · 바뀜 " + changes + "번 · 한 바퀴 " + laps + "번";
    }

    // 숫자별 막대그래프
    public void report() {
        System.out.println(this);
        System.out.println("지나간 값 : " + trail);

        for (int n = 0; n < 10; n++) {
            System.out.print(" " + n + " |");

            for (int k = 0; k < seen[n]; k++) System.out.print("#");

            System.out.println(" " + seen[n]);
        }
    }
}
