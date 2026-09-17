public class Step3 {

    // [Step 3] 7비트 세그먼트 데이터 -> 5x5 문자 배열
    static char[][] makeFnd(int[] seg) {
        char[][] fnd = new char[5][5];

        // 1) 공백으로 초기화
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                fnd[i][j] = ' ';

        // 2) 켜진 세그먼트 위치에 '#' 채우기
        if (seg[0] == 1) for (int i = 0; i < 5; i++) fnd[0][i]   = '#';  // a : 윗줄
        if (seg[1] == 1) for (int i = 0; i < 3; i++) fnd[i][4]   = '#';  // b : 오른쪽 위
        if (seg[2] == 1) for (int i = 0; i < 3; i++) fnd[i+2][4] = '#';  // c : 오른쪽 아래
        if (seg[3] == 1) for (int i = 0; i < 5; i++) fnd[4][i]   = '#';  // d : 아랫줄
        if (seg[4] == 1) for (int i = 0; i < 3; i++) fnd[i+2][0] = '#';  // e : 왼쪽 아래
        if (seg[5] == 1) for (int i = 0; i < 3; i++) fnd[i][0]   = '#';  // f : 왼쪽 위
        if (seg[6] == 1) for (int i = 0; i < 5; i++) fnd[2][i]   = '#';  // g : 가운데

        return fnd;
    }

    // 5x5 문자 배열을 콘솔에 출력
    static void printFnd(char[][] fnd) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(fnd[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // 0 ~ 9 의 세그먼트 데이터를 직접 넣어서 모양 확인
        int[][] segData = {
            {1,1,1,1,1,1,0}, {0,1,1,0,0,0,0}, {1,1,0,1,1,0,1},
            {1,1,1,1,0,0,1}, {0,1,1,0,0,1,1}, {1,0,1,1,0,1,1},
            {1,0,1,1,1,1,1}, {1,1,1,0,0,1,0}, {1,1,1,1,1,1,1},
            {1,1,1,1,0,1,1}
        };

        for (int n = 0; n < 10; n++) {
            System.out.println("[" + n + "]");
            printFnd(makeFnd(segData[n]));
        }
    }
}
