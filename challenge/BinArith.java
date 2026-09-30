// 방법 ① 산술 연산으로 2진수 만들기
//   2로 나눈 나머지를 거꾸로 모은다 (Step 1 의 cnvtDec2Bin 과 같은 원리)
public class BinArith {

    static String toBin(int n) {
        if (n == 0) return "0";                // 0 은 반복문이 한 번도 돌지 않는다

        String s = "";
        int v = n;

        while (v > 0) {
            s = (v % 2) + s;                   // 나머지를 '앞에' 붙인다
            v = v / 2;                         // 몫으로 다시 나눈다
        }

        return s;
    }

    public static void main(String[] args) {
        // 실제로 값을 입력받으려면 :
        //   Scanner sc = new Scanner(System.in);
        //   int n = sc.nextInt();
        int[] test = {0, 5, 26, 2026, -7};

        for (int i = 0; i < test.length; i++) {
            System.out.println(test[i] + " -> [" + toBin(test[i]) + "]");
        }

        System.out.println();
        System.out.println("-7 은 while (v > 0) 을 한 번도 돌지 못해 빈 문자열이 된다");
    }
}
