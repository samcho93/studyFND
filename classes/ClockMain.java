public class ClockMain {

    // 시계에 seconds 초 만큼 클럭을 넣으며 출력
    static void run(DigitalClock clock, int seconds) {
        for (int i = 0; i < seconds * 2; i++) {
            clock.setClock(i % 2);

            if (i % 2 == 0) {
                System.out.println(clock);       // toString() 자동 호출
                clock.display();
            }
        }
    }

    public static void main(String[] args) {
        DigitalClock morning = new DigitalClock();
        morning.setTime(11, 59, 58, false);      // 오전 11:59:58

        DigitalClock lunch = new DigitalClock();
        lunch.setTime(12, 59, 58, true);         // 오후 12:59:58

        run(morning, 4);                         // -> 12:00:01 PM
        run(lunch, 4);                           // -> 01:00:01 PM
    }
}
