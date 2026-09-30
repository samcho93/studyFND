// 방법 ④ 비트 연산 + 앞쪽의 의미 없는 0 빼고 출력
//   1 이 처음 나온 뒤부터 찍기 시작한다
public class BinTrim {

    static void printTrim(int n) {
        boolean start = false;                 // 1 을 만난 적이 있는가

        for (int i = 31; i >= 0; i--) {
            int b = (n >> i) & 1;

            if (b == 1) start = true;          // 여기서부터 의미 있는 자리
            if (start) System.out.print(b);
        }

        if (start == false) System.out.print("0");   // n 이 0 이면 한 번도 못 찍는다

        System.out.println();
    }

    public static void main(String[] args) {
        int[] test = {0, 5, 26, 2026, -7};

        for (int i = 0; i < test.length; i++) {
            System.out.print(test[i] + "\t");
            printTrim(test[i]);
        }

        System.out.println();
        System.out.println("음수는 31번 비트가 1 이라 잘라 낼 0 이 없다 (32자리 그대로)");
    }
}
