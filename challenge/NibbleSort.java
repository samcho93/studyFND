// [실습] int 변수 하나에 들어 있는 니블 8개를 버블 정렬로 오름차순 정렬하기
//
//   규칙 1 : 배열을 만들지 않는다. 정렬은 n 안에서 일어난다
//   규칙 2 : 값을 잠시 담아 두는 int 임시 변수는 써도 된다
//   규칙 3 : 니블을 읽고 쓰는 일은 비트 연산으로만 한다
//
//   ★ 표시된 빈칸을 채워 완성하세요. 아래에서 위로 한 칸씩 채우면
//      ① 숫자가 보이고  ②③ 교환이 되고  ④ 정렬이 됩니다.
public class NibbleSort {

    static final int MASK = 0xF;                  // 니블 하나를 덮는 마스크 (…0000 1111)

    // k 번 니블이 시작하는 비트 위치.  k = 0 이 맨 왼쪽(가장 높은 자리)
    //   k : 0  1  2  3  4  5  6  7
    //   s : 28 24 20 16 12  8  4  0
    static int shift(int k) {
        return (7 - k) * 4;
    }

    // ★ 빈칸 ① ─ k 번 니블 값(0 ~ 15)을 꺼내 돌려준다
    //    힌트 : s 만큼 오른쪽으로 민 다음, 아래 4비트만 남긴다
    static int get(int n, int k) {
        int s = shift(k);

        return 0;                                 // <-- 이 줄을 고치세요
    }

    // ★ 빈칸 ② ─ k 번 니블에 값 d 를 넣은 결과를 돌려준다
    //    힌트 : 그 자리를 0으로 비우고(AND), d 를 제자리로 밀어 넣는다(OR)
    //           자리를 비울 때는 뒤집은 마스크 ~(MASK << s) 를 쓴다
    static int set(int n, int k, int d) {
        int s = shift(k);

        return n;                                 // <-- 이 줄을 고치세요
    }

    // ★ 빈칸 ③ ─ i 번 니블과 j 번 니블을 교환한 결과를 돌려준다
    //    힌트 : 한쪽 값을 임시 변수에 담아 두고 set 을 두 번 부른다
    static int swap(int n, int i, int j) {

        return n;                                 // <-- 이 줄을 고치세요
    }

    // ★ 빈칸 ④ ─ 버블 정렬 : 이웃한 두 니블을 비교해 왼쪽이 크면 교환
    //    힌트 : 안쪽 for 안에서 get 두 번, 조건이 맞으면 swap
    static int sort(int n) {
        for (int pass = 0; pass < 7; pass++) {
            for (int k = 0; k < 7 - pass; k++) {

                // <-- 여기를 채우세요

            }
        }

        return n;
    }

    /* ================= 아래는 완성되어 있습니다 ================= */

    // 0 ~ 9 를 니블 8칸에 무작위로 채운다 (씨앗값이 같으면 늘 같은 값이 나온다)
    static int makeRandom(int seed) {
        int n = 0;

        for (int k = 0; k < 8; k++) {
            seed = seed * 1103515245 + 12345;          // 다음 난수
            int d = ((seed >>> 16) & 0x7FFF) % 10;     // 0 ~ 9
            n = n | (d << shift(k));                   // k 번 자리에 끼워 넣기
        }

        return n;
    }

    static String hex8(int n) {
        String h = Integer.toHexString(n);
        while (h.length() < 8) h = "0" + h;
        return h;
    }

    // 니블 8개를 차례로 출력한다 (get 을 쓰므로 빈칸 ① 을 채워야 보인다)
    static void show(String title, int n) {
        System.out.print(title);

        for (int k = 0; k < 8; k++) {
            System.out.print(get(n, k) + " ");
        }

        System.out.println("   n = 0x" + hex8(n));
    }

    static String check(int n) {
        int sum = 0;
        for (int k = 0; k < 8; k++) sum = sum + get(n, k);

        if (sum == 0 && n != 0) return "니블이 모두 0으로 읽힌다 — 빈칸 ① 부터 채워 보세요";

        for (int k = 0; k < 7; k++) {
            if (get(n, k) > get(n, k + 1)) return "아직 정렬되지 않았다 (" + k + "번 > " + (k + 1) + "번)";
        }
        return "오름차순 정렬 완료";
    }

    public static void main(String[] args) {
        int n = makeRandom(20260929);

        show("처음       ", n);

        int t = swap(n, 0, 7);                 // ② ③ 확인용 : 양 끝을 바꿔 본다
        show("0 <-> 7    ", t);

        n = sort(n);                           // ④
        show("정렬 후    ", n);

        System.out.println();
        System.out.println("확인 : " + check(n));
    }
}
