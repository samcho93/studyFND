// 도전 : 시계 방향 90도 — 오른쪽으로 90도 돌려 출력한다
//   출력하는 자리(i행 j열)는 그대로 두고, 읽어 오는 칸만 바꾼다
public class RotateRight {

    public static void main(String[] args) {
        char[][] a = Letter.ga();

        for (int i = 0; i < 8; i++) {          // i = 출력할 행
            for (int j = 0; j < 8; j++) {      // j = 출력할 열
                System.out.print(a[7 - j][i]);
            }
            System.out.println();
        }
    }
}
