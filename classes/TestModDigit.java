// ModDigit 확인 : 같은 클럭을 주고 10진 · 6진 · 3진 자리를 나란히 본다
public class TestModDigit {

    public static void main(String[] args) {
        ModDigit d10 = new ModDigit(10);        // 보통 자리 (7490 그대로)
        ModDigit d6  = new ModDigit(6);         // 시계의 10초 자리
        ModDigit d3  = new ModDigit(3);

        System.out.print("10진 : ");
        for (int i = 0; i < 26; i++) { d10.setClock(i % 2); if (i % 2 == 1) System.out.print(d10.getNum()); }

        System.out.print("\n 6진 : ");
        for (int i = 0; i < 26; i++) { d6.setClock(i % 2);  if (i % 2 == 1) System.out.print(d6.getNum()); }

        System.out.print("\n 3진 : ");
        for (int i = 0; i < 26; i++) { d3.setClock(i % 2);  if (i % 2 == 1) System.out.print(d3.getNum()); }

        System.out.println("\n");
        System.out.println("시작값이 범위를 넘으면 : new ModDigit(6, 9) -> " + new ModDigit(6, 9).getNum());
        System.out.println("나중에 넣어도 막는다  : setNum(8) -> " + set8(new ModDigit(6, 2)));

        System.out.println();
        ModDigit show = new ModDigit(6, 4);      // 화면은 부모 것을 그대로 쓴다
        for (int i = 0; i < 4; i++) {                        // 4 -> 5 -> 0 -> 1
            for (int line = 0; line < 5; line++) { show.dispFnd(line); System.out.println(); }
            System.out.println();
            show.setClock(1); show.setClock(0);
        }
    }

    static int set8(ModDigit d) {
        d.setNum(8);
        return d.getNum();
    }
}
