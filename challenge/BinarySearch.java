// 이진 검색 : 가운데 값과 비교해 찾는 구간을 절반씩 버린다
//   배열이 반드시 '정렬되어 있어야' 쓸 수 있다
public class BinarySearch {

    static int find(int[] a, int key) {
        int lo = 0;                 // 남은 구간의 왼쪽 끝
        int hi = a.length - 1;      // 남은 구간의 오른쪽 끝
        int step = 0;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            step++;

            System.out.println("   " + step + "단계  lo=" + lo + " mid=" + mid + " hi=" + hi
                             + "   가운데 " + a[mid] + " 와 비교");

            if (a[mid] == key) return mid;     // 찾았다
            if (a[mid] < key)  lo = mid + 1;   // 찾는 값이 더 크다 -> 오른쪽 절반만 남긴다
            else               hi = mid - 1;   // 찾는 값이 더 작다 -> 왼쪽 절반만 남긴다
        }

        return -1;                             // 구간이 비면 없는 값
    }

    public static void main(String[] args) {
        int[] a = {8, 10, 13, 14, 25, 29, 37, 42};   // 정렬해 둔 배열
        int[] keys = {8, 25, 42, 99};          // 순차 검색과 같은 값으로 비교

        for (int k = 0; k < keys.length; k++) {
            System.out.println(keys[k] + " 찾기");
            int at = find(a, keys[k]);

            if (at >= 0) System.out.println("   -> " + at + "번 칸에서 찾음");
            else         System.out.println("   -> 없음 (-1)");

            System.out.println();
        }
    }
}
