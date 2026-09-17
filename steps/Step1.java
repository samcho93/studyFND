public class Step1 {

    // [Step 1] 10진수 -> 4비트 2진수 배열
    // out[0] = 2^0 자리(LSB), out[3] = 2^3 자리(MSB)
    static int[] cnvtDec2Bin(int num) {
        int[] out = new int[4];

        for (int i = 0; i < 4; i++) {
            out[i] = num % 2;   // 나머지 = 현재 자리의 비트
            num = num / 2;      // 몫으로 다음 자리 계산
        }

        return out;
    }

    // 같은 기능을 비트 연산으로 구현한 버전
    static int[] cnvtDec2BinBit(int num) {
        int[] out = new int[4];

        for (int i = 0; i < 4; i++) {
            out[i] = (num & 0x01);  // 맨 오른쪽 비트만 꺼냄
            num >>= 1;              // 오른쪽으로 한 칸 이동
        }

        return out;
    }

    // 사람이 읽기 쉽게 MSB부터 출력
    static void printBin(int[] bin) {
        for (int i = 3; i >= 0; i--) {
            System.out.print(bin[i]);
        }
    }

    public static void main(String[] args) {
        for (int n = 0; n < 10; n++) {
            int[] bin = cnvtDec2Bin(n);

            System.out.print(n + " -> ");
            printBin(bin);
            System.out.println("   배열: {" + bin[0] + "," + bin[1] + "," + bin[2] + "," + bin[3] + "}");
        }
    }
}
