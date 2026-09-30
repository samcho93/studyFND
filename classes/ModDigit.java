// ModDigit : N 이 되면 스스로 0 으로 돌아가는 자리  (과제 A)
//   Digit 은 한 줄도 고치지 않는다. 물려받아 setClock 뒤에 규칙 한 줄을 덧붙일 뿐이다.
//   시계에서 s10.reset(s10.bit(2) & s10.bit(1), 0, 0) 하던 일을 자리 스스로 하게 만든다.
class ModDigit extends Digit {

    private int mod;                       // 되돌아갈 수 (6 이면 0 1 2 3 4 5 를 센다)

    // ---- 생성자 : super 가 먼저 끝나야 내 필드를 채울 수 있다 ----
    ModDigit(int mod) {
        this(mod, 0);
    }

    ModDigit(int mod, int n) {
        this('#', mod, n);
    }

    ModDigit(char c, int mod, int n) {
        super(c, n);                       // 부모(칩 3개) 부터 만든다
        this.mod = mod;                    // 그 다음에 내 것
        check();                           // 시작값이 이미 범위를 넘었으면 0 으로
    }

    // ---- 내 규칙 한 줄 ----
    private void check() {
        if (getNum() >= mod) reset(1, 0, 0);     // R0 핀을 스스로 누른다
    }

    // ---- 값이 바뀌는 길목마다 규칙을 지킨다 ----
    //   부모가 하던 일을 먼저 시키고(super), 그 뒤에 내 것을 더한다
    @Override
    public void setClock(int clk) {
        super.setClock(clk);
        check();
    }

    @Override
    public void setNum(int n) {
        super.setNum(n);
        check();
    }

    public int getMod() {
        return mod;
    }
}
