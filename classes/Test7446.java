public class Test7446 {

    static int val(int[] q) {
        return q[3] * 8 + q[2] * 4 + q[1] * 2 + q[0];
    }

    static void printArray(int[] arr) {
        System.out.print("{");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(",");
        }
        System.out.print("}");
    }

    public static void main(String[] args) {
        TTL7490 counter = new TTL7490();
        TTL7446 decoder = new TTL7446();

        for (int i = 0; i < 20; i++) {
            counter.setClock(i % 2);

            if (i % 2 == 0) {
                decoder.setInput(counter.getOutput());   // 7490 출력 -> 7446 입력
                System.out.print("num=" + decoder.getNum() + "  seg=");
                printArray(decoder.getOutput());
                System.out.println();
            }
        }

        // setInput 을 부르지 않으면?
        counter.setClock(1);
        counter.setClock(0);                             // 9 -> 0
        System.out.println("counter=" + val(counter.getOutput()) + ", decoder=" + decoder.getNum() + "  (setInput 전)");
        decoder.setInput(counter.getOutput());
        System.out.println("counter=" + val(counter.getOutput()) + ", decoder=" + decoder.getNum() + "  (setInput 후)");
    }
}
