# studyFND — Java로 만드는 FND 카운터와 디지털 시계

디지털 시계 속 숫자가 **카운터(7490) → 디코더(7446) → FND** 순서로 만들어지는 과정을 Java로 따라 만드는 단계별 강좌입니다.

- **1부 · 메서드로 만들기**: 각 칩의 동작을 `static` 메서드 하나씩으로 구현하고 서로 연결합니다.
- **2부 · 클래스로 만들기**: 이전 클럭을 기억해야 하는 7490의 불편함을 클래스로 해결하고, 칩 객체를 조합해 12시간제 디지털 시계를 완성합니다.

## 강좌 보기

`index.html`을 브라우저로 열면 됩니다. 단계마다 설명, 직접 해보는 실습, 전체 코드, 실제 실행 결과, 확인 문제, 연습 과제가 있습니다.

GitHub Pages를 켜면(Settings → Pages → `main` 브랜치, `/ (root)`) 웹에서 바로 볼 수 있습니다.

## 강좌 구성

### 1부 · 메서드로 만들기 (`steps/`)

| 단계 | 내용 | 메서드 | 파일 |
|---|---|---|---|
| 1 | 10진수 → 4비트 배열 | `int[] cnvtDec2Bin(int)` | `Step1.java` |
| 2 | 4비트 → FND 7비트 배열 | `int[] ttl7446(int[])` | `Step2.java` |
| 3 | 7비트 → 5×5 문자 배열, 콘솔 출력 | `makeFnd`, `printFnd` | `Step3.java` |
| 4 | 1 ~ 3 연결, 0 ~ 9 출력 | `dispNum(int)` | `Step4.java` |
| 5 | 4자리 FND, 0 ~ 9999 출력 | `dispNum4(int)` | `Step5.java` |
| 6 | 10진 카운터 (clk 1 → 0에서 카운트, R0/R1/R2 초기화) | `ttl7490(clk, R0, R1, R2)` | `Step6.java` |
| 7 | for문으로 클럭을 넣어 0 ~ 9 카운트 | `main` | `Step7.java` |
| 8 | 1의 자리 b3 → 10의 자리 clk, 100진 카운터 | `counter100(int)` | `Step8.java` |
| 9 | 10의 자리 b2 & b1 → R0, 60진 카운터 | `counter60(int)` | `Step9.java` |

### 2부 · 클래스로 만들기 (`classes/`)

| 단계 | 내용 | 파일 |
|---|---|---|
| 1 | `TTL7490` 클래스: 상태를 필드로 기억하는 카운터 | `TTL7490.java`, `Test7490.java` |
| 2 | `TTL7446` 클래스: static final 진리표 | `TTL7446.java`, `Test7446.java` |
| 3 | `FND` 클래스: `dispFnd()` / `dispFnd(line)` 오버로딩 | `FND.java`, `TestFND.java` |
| 4 | 객체 연결: 100진 카운터 | `Counter100.java` |
| 5 | 리셋 연결: 60진 초 카운터 | `Counter60.java` |
| 6 | 카운터 4개: MM:SS | `ClockMMSS.java` |
| 7 | `DigitalClock` 클래스: 12시간제, AM/PM | `DigitalClock.java`, `ClockMain.java` |

## 폴더 구조

```
.
├── index.html          강좌 페이지
├── steps/              1부 예제 (파일 하나로 실행)
│   ├── Step1.java
│   └── ... Step9.java
└── classes/            2부 예제 (폴더 전체를 함께 컴파일)
    ├── TTL7490.java
    ├── TTL7446.java
    ├── FND.java
    ├── DigitalClock.java
    ├── ClockMain.java
    └── ... 테스트 프로그램
```

## 실행 방법

JDK 17에서 컴파일과 실행을 확인했습니다.

**1부**: 각 파일이 독립적으로 실행됩니다.

```bash
cd steps
javac -encoding UTF-8 Step7.java
java Step7
```

**2부**: 클래스들이 서로를 사용하므로 폴더 전체를 함께 컴파일합니다.

```bash
cd classes
javac -encoding UTF-8 *.java
java ClockMain
```

`Test7490`, `Test7446`, `TestFND`, `Counter100`, `Counter60`, `ClockMMSS`도 같은 방식으로 실행할 수 있습니다.

## 참고

- 7446은 이해하기 쉽도록 **1 = 세그먼트 켜짐**으로 통일했습니다. 실제 74LS46/47은 0일 때 켜지는 Active-Low 칩입니다.
- 7490의 R0/R1/R2(각각 0/1/2로 초기화)는 시계 회로를 만들기 쉽도록 단순화한 모델입니다. 실제 74LS90은 R0(1)·R0(2), R9(1)·R9(2) 핀 쌍을 사용합니다.
- `DigitalClock`은 자정부터 24시간(86,400초) 동안 매초 실제 시각과 비교해 13시 → 01시 리셋과 AM/PM 전환까지 모두 일치하는 것을 확인했습니다.
