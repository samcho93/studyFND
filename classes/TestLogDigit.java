// LogDigit 확인 : 자리올림 회수가 맞는지 윗자리와 비교해 본다
public class TestLogDigit {

    public static void main(String[] args) {
        LogDigit one = new LogDigit(0);          // 1의 자리 (기록하는 자리)
        Digit    ten = new Digit('#', 0);        // 10의 자리 (보통 자리)

        for (int i = 0; i < 50; i++) {           // 25 틱
            one.setClock(i % 2);
            ten.setClock(one.bit(3));            // 자리올림은 늘 하던 대로
        }

        one.report();
        System.out.println();
        System.out.println("10의 자리 = " + ten.getNum() + "  /  기록한 한 바퀴 = " + one.getLaps());
        System.out.println(ten.getNum() == one.getLaps() ? "두 값이 같다 -> 기록이 맞다" : "어긋난다");

        System.out.println();
        LogDigit six = new LogDigit(0);          // 세는 자리를 바꿔 끼워도 기록은 그대로
        for (int i = 0; i < 16; i++) six.setClock(i % 2);
        System.out.println(six);
    }
}
