// 방법 ② 비트 연산으로 32비트 전부 출력
//   맨 위(31번) 비트부터 0번까지 한 칸씩 내려오며 한 자리씩 찍는다
public class BinBits32 {

    static void print32(int n) {
        for (int i = 31; i >= 0; i--) {
            System.out.print((n >> i) & 1);    // i 번 비트만 꺼내 찍는다
        }

        System.out.println();
    }

    public static void main(String[] args) {
        int[] test = {0, 5, 26, 2026, -7};

        for (int i = 0; i < test.length; i++) {
            System.out.print(test[i] + "\t");
            print32(test[i]);
        }

        System.out.println();
        System.out.println("음수는 맨 앞(부호) 비트가 1 이라 앞쪽이 1 로 가득 찬다");
    }
}
