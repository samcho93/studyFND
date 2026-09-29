// LetterTable : 글자를 '표'로 미리 만들어 두는 방법
//   획을 반복문으로 긋는 대신, 8x8 모양을 0과 1로 그대로 적어 둔다
//   2부의 TTL7446 진리표와 같은 방식이다 (숫자 대신 글자 모양을 적어 둔 것)
public class LetterTable {

    // 0 = 빈칸, 1 = 글자.  소스 안에서 '가' 모양이 그대로 보인다
    private static final int[][] GA = {
        {0, 0, 0, 0, 0, 0, 1, 0},
        {1, 1, 1, 1, 1, 0, 1, 0},
        {0, 0, 0, 0, 1, 0, 1, 0},
        {0, 0, 0, 0, 1, 0, 1, 0},
        {0, 0, 0, 0, 1, 0, 1, 1},
        {0, 0, 0, 0, 1, 0, 1, 0},
        {0, 0, 0, 0, 1, 0, 1, 0},
        {0, 0, 0, 0, 0, 0, 1, 0}
    };

    // 표를 보고 char 배열로 바꾼다 : 1 이면 '#', 0 이면 ' '
    static char[][] ga() {
        char[][] a = new char[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (GA[i][j] == 1) a[i][j] = '#';
                else               a[i][j] = ' ';
            }
        }

        return a;
    }

    public static void main(String[] args) {
        char[][] a = ga();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(a[i][j]);
            }
            System.out.println();
        }
    }
}
