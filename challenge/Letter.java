// Letter : 8x8 char 배열에 '가' 를 그려 두는 클래스
//   글자 부분은 '#', 빈 곳은 ' ' 로 채운다
class Letter {

    static char[][] ga() {
        char[][] a = new char[8][8];

        // 1) 먼저 전부 빈칸
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                a[i][j] = ' ';
            }
        }

        // 2) ㄱ : 가로획(1행) + 세로획(4열)
        for (int j = 0; j <= 4; j++) a[1][j] = '#';
        for (int i = 1; i <= 6; i++) a[i][4] = '#';

        // 3) ㅏ : 세로획(6열) + 오른쪽으로 뻗는 짧은 획
        for (int i = 0; i <= 7; i++) a[i][6] = '#';
        a[4][7] = '#';

        return a;
    }
}
