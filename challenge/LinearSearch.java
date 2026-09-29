// 순차 검색 : 앞에서부터 하나씩 비교한다
//   배열이 정렬되어 있지 않아도 쓸 수 있다
public class LinearSearch {

    // 찾으면 그 칸 번호, 없으면 -1
    static int find(int[] a, int key) {
        for (int i = 0; i < a.length; i++) {
            System.out.println("   " + i + "번 칸 " + a[i] + " 와 비교");
            if (a[i] == key) return i;         // 찾는 순간 끝낸다
        }

        return -1;
    }

    public static void main(String[] args) {
        // 순차 검색은 정렬이 필요 없지만, 이진 검색과 나란히 비교하려고 같은 배열을 쓴다
        int[] a = {8, 10, 13, 14, 25, 29, 37, 42};
        int[] keys = {8, 25, 42, 99};          // 맨 앞 · 가운데 · 맨 뒤 · 없는 값

        for (int k = 0; k < keys.length; k++) {
            System.out.println(keys[k] + " 찾기");
            int at = find(a, keys[k]);

            if (at >= 0) System.out.println("   -> " + at + "번 칸에서 찾음 (비교 " + (at + 1) + "번)");
            else         System.out.println("   -> 없음 (-1), 비교 " + a.length + "번");

            System.out.println();
        }
    }
}
