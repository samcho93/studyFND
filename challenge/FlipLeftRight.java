// 도전 : 좌우 반전 — 좌우를 뒤집어 출력한다
//   출력하는 자리(i행 j열)는 그대로 두고, 읽어 오는 칸만 바꾼다
public class FlipLeftRight {

    public static void main(String[] args) {
        char[][] a = Letter.ga();

        for (int i = 0; i < 8; i++) {          // i = 출력할 행
            for (int j = 0; j < 8; j++) {      // j = 출력할 열
                System.out.print(a[i][7 - j]);
            }
            System.out.println();
        }
    }
}
