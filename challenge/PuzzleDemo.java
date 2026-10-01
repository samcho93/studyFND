// PuzzleDemo : 키보드 없이 돌려 보는 시범
//   Puzzle 의 메서드를 그대로 쓰고, 누를 키만 미리 적어 둔다 (돌릴 때마다 같은 판)
public class PuzzleDemo {

    // 반대 방향 : w <-> s, a <-> d
    static char back(char k) {
        if (k == 'w') return 's';
        if (k == 's') return 'w';
        if (k == 'a') return 'd';

        return 'a';
    }

    public static void main(String[] args) {
        String mix  = "ssddwwaassdd";                 // 섞으려고 누르는 키
        String real = "";                             // 그중 실제로 움직인 키만 모은다

        Puzzle.start(3);
        System.out.println("① 맞춰진 판 — 오른쪽 아래가 빈칸");
        Puzzle.draw();

        for (int i = 0; i < mix.length(); i++)
            if (Puzzle.move(mix.charAt(i))) real = real + mix.charAt(i);

        System.out.println("② [" + mix + "] 를 눌러 섞은 판   (" + real.length() + "번 움직였다)");
        Puzzle.draw();

        System.out.println("③ 움직인 키를 뒤에서부터 반대 방향으로 누르면 되돌아온다");

        for (int i = real.length() - 1; i >= 0; i--) {
            char k = back(real.charAt(i));

            Puzzle.move(k);
            System.out.println("[" + k + "]");
            Puzzle.draw();
        }

        System.out.println(Puzzle.done() ? "맞췄습니다!" : "아직 안 맞았습니다");
        System.out.println();
        System.out.println("※ 못 움직인 키는 되돌릴 것도 없다 — 그래서 '실제로 움직인 키' 만 모아 둔다");
    }
}
