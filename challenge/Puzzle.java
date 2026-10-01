import java.util.Scanner;

// 그림퍼즐 : n x n 판에서 숫자를 밀어 1 ~ n*n-1 순서로 맞추는 게임
//   빈칸은 0 으로 두고, 빈칸 옆의 숫자만 빈칸으로 밀려 들어간다
public class Puzzle {

    static int n;                          // 판 크기
    static int[][] b;                      // 판 (0 = 빈칸)

    // ---- 맞춰진 판 만들기 : 1 2 3 … 그리고 오른쪽 아래는 빈칸 ----
    static void start(int size) {
        n = size;
        b = new int[n][n];

        for (int i = 0; i < n * n - 1; i++) b[i / n][i % n] = i + 1;

        b[n - 1][n - 1] = 0;
    }

    // ---- 빈칸 찾기 : 자리를 r * n + c 한 수로 돌려준다 ----
    static int blank() {
        for (int r = 0; r < n; r++)
            for (int c = 0; c < n; c++)
                if (b[r][c] == 0) return r * n + c;

        return -1;
    }

    // ---- 칸 하나 그리기 : 어떤 숫자든 5글자로 맞춘다 ----
    static String cell(int v) {
        if (v == 0)  return "     ";
        if (v < 10)  return "  " + v + "  ";

        return "  " + v + " ";
    }

    // ---- 판 그리기 ----
    static void draw() {
        String bar = "+";
        for (int c = 0; c < n; c++) bar = bar + "-----+";

        for (int r = 0; r < n; r++) {
            System.out.println(bar);

            String line = "|";
            for (int c = 0; c < n; c++) line = line + cell(b[r][c]) + "|";

            System.out.println(line);
        }

        System.out.println(bar);
    }

    // ---- 숫자 한 개 밀기 : 움직였으면 true ----
    //   W 는 '빈칸이 위로' 가 아니라 '아래 숫자가 위로' 다
    static boolean move(char k) {
        int p = blank(), r = p / n, c = p % n;
        int sr = r, sc = c;                          // 밀려 올 숫자의 자리

        if      (k == 'w' || k == 'W') sr = r + 1;   // 아래 숫자가 위로
        else if (k == 's' || k == 'S') sr = r - 1;   // 위 숫자가 아래로
        else if (k == 'a' || k == 'A') sc = c + 1;   // 오른쪽 숫자가 왼쪽으로
        else if (k == 'd' || k == 'D') sc = c - 1;   // 왼쪽 숫자가 오른쪽으로
        else return false;

        if (sr < 0 || sr >= n || sc < 0 || sc >= n) return false;   // 판 밖이면 못 움직인다

        b[r][c] = b[sr][sc];                         // 숫자를 빈칸으로
        b[sr][sc] = 0;                               // 있던 자리가 빈칸이 된다

        return true;
    }

    // ---- 섞기 : 되는 방향으로만 여러 번 움직인다 ----
    //   숫자를 아무렇게나 늘어놓으면 '절대 못 푸는 판' 이 절반이나 나온다
    static void shuffle(int cnt) {
        String keys = "wasd";

        for (int i = 0; i < cnt; i++) move(keys.charAt((int)(Math.random() * 4)));
    }

    // ---- 다 맞췄나 : 1 2 3 … 순서이고 끝은 빈칸 ----
    static boolean done() {
        for (int i = 0; i < n * n - 1; i++)
            if (b[i / n][i % n] != i + 1) return false;

        return b[n - 1][n - 1] == 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("판 크기 n (3 이상) : ");
        int size = sc.nextInt();
        if (size < 3) size = 3;

        start(size);
        shuffle(size * size * 20);
        while (done()) shuffle(size * size * 20);     // 섞다가 맞아 버리면 다시

        int cnt = 0;
        draw();

        while (done() == false) {
            System.out.print("W A S D (q 는 그만) : ");
            String in = sc.next();
            char k = in.charAt(0);

            if (k == 'q' || k == 'Q') {
                System.out.println("그만둡니다");
                return;
            }

            if (move(k)) {
                cnt++;
                draw();
                System.out.println(cnt + "번 움직였습니다");
            }
            else {
                System.out.println("그 방향으로는 움직일 수 없습니다");
            }
        }

        System.out.println();
        System.out.println("맞췄습니다! " + cnt + "번 움직였습니다");
    }
}
