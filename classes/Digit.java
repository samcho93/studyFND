// Digit : TTL7490 · TTL7446 · FND 를 고치지 않고 그대로 가진 '시계 한 자리'
//   칩을 필드로 두고, 칩이 이미 가진 메서드를 불러 쓴다 (조합 · composition)
//   시계 코드에서 사라진 배선(setInput 연결)은 이 클래스 안의 update() 로 들어왔다
class Digit {

    private TTL7490 counter = new TTL7490();   // 세는 칩
    private TTL7446 decoder = new TTL7446();   // 숫자 -> 세그먼트
    private FND     fnd;                       // 화면 (문자를 정해야 하므로 생성자에서)

    // ---- 생성자 ----
    Digit() {
        this('#', 0);
    }

    Digit(char c) {
        this(c, 0);
    }

    Digit(char c, int n) {
        fnd = new FND(c);
        counter.setNum(n);
        update();
    }

    // ---- 배선 : 카운터 -> 디코더 -> 화면 ----
    //   7단계에서 main 에 늘어놓던 두 줄이 여기로 들어왔다
    private void update() {
        decoder.setInput(counter.getOutput());
        fnd.setInput(decoder.getOutput());
    }

    // ---- 입력 핀 : 칩에게 그대로 넘기고, 바뀐 값을 화면까지 흘려보낸다 ----
    public void setClock(int clk) {
        counter.setClock(clk);
        update();
    }

    public void reset(int R0, int R1, int R2) {
        counter.reset(R0, R1, R2);
        update();
    }

    public void setNum(int n) {
        counter.setNum(n);
        update();
    }

    // ---- 출력 핀 ----
    // 자리올림은 bit(3), 60진 리셋 검사는 bit(2) & bit(1)
    public int bit(int i) {
        return counter.getOutput()[i];
    }

    // TTL7490 에는 getNum() 이 없지만, 디코더가 입력을 10진수로 읽어 둔다
    public int getNum() {
        return decoder.getNum();
    }

    // ---- 화면 ----
    public void dispFnd() {
        fnd.dispFnd();
    }

    public void dispFnd(int line) {
        fnd.dispFnd(line);
    }
}
