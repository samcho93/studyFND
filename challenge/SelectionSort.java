// 선택 정렬 : 남은 칸 중에서 가장 작은 값을 찾아 맨 앞자리와 바꾼다
//   한 바퀴 돌 때마다 앞에서부터 한 자리씩 확정된다
public class SelectionSort {

    // 자리 맞춰 출력하기 (8 -> "  8", 42 -> " 42")
    static String pad(int v) {
        if (v < 10)  return "  " + v;
        if (v < 100) return " " + v;
        return "" + v;
    }

    // fixed : 앞쪽으로 확정된 칸 수. 그 뒤에 | 를 찍는다
    static void show(int[] a, int fixed) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(pad(a[i]));
            if (i == fixed - 1) System.out.print("  |");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] a = {29, 10, 14, 37, 13, 25, 8, 42};
        int cmp = 0, swap = 0;

        System.out.print("처음        ");
        show(a, 0);

        for (int i = 0; i < a.length - 1; i++) {
            int min = i;                       // 지금까지 가장 작은 값이 있는 칸

            for (int j = i + 1; j < a.length; j++) {
                cmp++;                         // 비교 한 번
                if (a[j] < a[min]) min = j;    // 더 작은 값을 찾으면 기억만 해 둔다
            }

            if (min != i) {                    // 찾은 값을 i 번 자리와 교환
                int t = a[i];
                a[i] = a[min];
                a[min] = t;
                swap++;
            }

            System.out.print(pad(i) + "번 확정  ");
            show(a, i + 1);
        }

        System.out.println();
        System.out.println("비교 " + cmp + "번, 교환 " + swap + "번");
    }
}
