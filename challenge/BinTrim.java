// 방법 ④ 비트 연산 + 앞쪽의 의미 없는 0 빼고 4자리씩 출력
//   1 이 처음 나온 뒤부터 찍고, ③ 과 같은 자리에서 빈칸을 넣는다
public class BinTrim {

    static void printTrim(int n) {
        boolean start = false;                 // 1 을 만난 적이 있는가

        for (int i = 31; i >= 0; i--) {
            int b = (n >> i) & 1;

            if (b == 1) start = true;          // 여기서부터 의미 있는 자리

            if (start) {
                System.out.print(b);

                if (i % 4 == 0 && i > 0) System.out.print(" ");   // 4자리마다 한 칸
            }
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
        System.out.println("빈칸 자리는 ③ 과 똑같다 → 오른쪽 묶음은 늘 4자리, 맨 앞만 짧다");
        System.out.println("음수는 31번 비트가 1 이라 잘라 낼 0 이 없다 (32자리 그대로)");
    }
}
