// 방법 ③ 비트 연산 + 4자리씩 끊어 출력
//   ② 와 같지만, 니블(4비트) 경계마다 빈칸을 하나 넣는다
public class BinGroup4 {

    static void print4(int n) {
        for (int i = 31; i >= 0; i--) {
            System.out.print((n >> i) & 1);

            if (i % 4 == 0 && i > 0) System.out.print(" ");   // 4개마다 한 칸 띄우기
        }

        System.out.println();
    }

    public static void main(String[] args) {
        int[] test = {0, 5, 26, 2026, -7};

        for (int i = 0; i < test.length; i++) {
            System.out.print(test[i] + "\t");
            print4(test[i]);
        }

        System.out.println();
        System.out.println("4자리씩 끊으면 16진수와 한 자리씩 맞아떨어진다");
    }
}
