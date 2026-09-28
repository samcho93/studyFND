public class TestFND {

    // 숫자 n 을 표시하는 FND 객체 만들기 (7490 -> 7446 -> FND)
    static FND makeDigit(int n, char c) {
        TTL7490 counter = new TTL7490();
        counter.setNum(n);

        TTL7446 decoder = new TTL7446(counter.getOutput());
        return new FND(c, decoder.getOutput());
    }

    public static void main(String[] args) {
        // 1) 한 자리 세로 출력
        FND seven = makeDigit(7, '#');
        seven.dispFnd();

        // 2) 문자 바꾸기 : setChar 는 화면을 다시 그리지 않는다 (cnvt 를 부르지 않음)
        seven.setChar('*');
        seven.dispFnd();                     // 아직 '#' 그대로

        seven.setInput(seven.input);         // 입력을 다시 넣으면 그때 '*' 로 그려진다
        seven.dispFnd();

        // 3) 여러 자리 가로 출력 : 줄(line) 단위로
        FND[] fnds = new FND[5];
        for (int k = 0; k < 5; k++) {
            fnds[k] = makeDigit(k * 2, '@');     // 0 2 4 6 8
        }

        for (int line = 0; line < 5; line++) {
            for (int k = 0; k < fnds.length; k++) {
                fnds[k].dispFnd(line);
            }
            System.out.println();
        }
    }
}
