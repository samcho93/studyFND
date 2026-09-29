// 버블 정렬 : 이웃한 두 칸을 비교해 큰 값을 뒤로 밀어낸다
//   한 바퀴 돌면 가장 큰 값이 맨 뒤에 확정된다
public class BubbleSort {

    static String pad(int v) {
        if (v < 10)  return "  " + v;
        if (v < 100) return " " + v;
        return "" + v;
    }

    // fixed : 뒤쪽으로 확정된 칸 수. 그 앞에 | 를 찍는다
    static void show(int[] a, int fixed) {
        for (int i = 0; i < a.length; i++) {
            if (i == a.length - fixed) System.out.print("  |");
            System.out.print(pad(a[i]));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] a = {29, 10, 14, 37, 13, 25, 8, 42};
        int cmp = 0, swap = 0;

        System.out.print("처음        ");
        show(a, 0);

        for (int pass = 0; pass < a.length - 1; pass++) {
            boolean moved = false;             // 이번 바퀴에 교환이 있었나

            for (int j = 0; j < a.length - 1 - pass; j++) {
                cmp++;                         // 이웃끼리 비교
                if (a[j] > a[j + 1]) {         // 왼쪽이 더 크면 자리를 바꾼다
                    int t = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = t;
                    swap++;
                    moved = true;
                }
            }

            System.out.print(pad(pass) + "바퀴    ");
            show(a, pass + 1);

            if (moved == false) {              // 한 번도 안 바꿨으면 이미 정렬된 것
                System.out.println("교환이 없었으므로 여기서 끝");
                break;
            }
        }

        System.out.println();
        System.out.println("비교 " + cmp + "번, 교환 " + swap + "번");
    }
}
