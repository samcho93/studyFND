// 비트 연산 눈으로 보기 — 니블 정렬에 쓸 연산들을 먼저 익힌다
//   AND(&) 남기기 · OR(|) 합치기 · XOR(^) 뒤집기 · NOT(~) 반전 · 시프트(<< >>) 옮기기
public class BitOps {

    // 32비트를 2진수로 (4비트씩 띄어서)
    static String bin(int v) {
        String s = "";

        for (int i = 31; i >= 0; i--) {
            s = s + ((v >> i) & 1);
            if (i % 4 == 0 && i > 0) s = s + " ";
        }

        return s;
    }

    static void show(String name, int v) {
        System.out.println(name + bin(v));
    }

    public static void main(String[] args) {
        int x = 0x37;          // 0011 0111  (십진수 55)
        int y = 0xF;           // 0000 1111  (니블 하나를 덮는 마스크)

        show("x            ", x);
        show("y = 0xF      ", y);
        System.out.println();

        show("x & y   AND  ", x & y);      // 둘 다 1인 자리만 1 -> 필요한 비트만 남긴다
        show("x | y   OR   ", x | y);      // 하나라도 1이면 1   -> 비트를 합친다
        show("x ^ y   XOR  ", x ^ y);      // 다르면 1          -> 두 번 하면 제자리
        show("~y      NOT  ", ~y);         // 0과 1을 뒤집는다  -> 마스크 뒤집기
        System.out.println();

        show("x << 4  왼쪽 ", x << 4);     // 니블 한 칸 왼쪽으로 (16배)
        show("x >> 4  오른쪽", x >> 4);    // 니블 한 칸 오른쪽으로 (16으로 나눔)
        System.out.println();

        // 니블 하나만 꺼내는 방법 : 밀어서 맨 아래로 내린 뒤, 아래 4비트만 남긴다
        int n = 0x3715;                    // 니블 4개 : 3 7 1 5
        System.out.println("n = 0x" + Integer.toHexString(n));
        System.out.println("  (n >> 8) & 0xF = " + ((n >> 8) & 0xF) + "   <- 왼쪽에서 세 번째 니블");
        System.out.println("  (n >> 0) & 0xF = " + ((n >> 0) & 0xF) + "   <- 맨 오른쪽 니블");
        System.out.println();

        // XOR 을 두 번 하면 원래대로 돌아온다 (교환에 쓰이는 성질)
        System.out.println("x ^ y ^ y = " + (x ^ y ^ y) + "  (원래 x 와 같다)");
    }
}
