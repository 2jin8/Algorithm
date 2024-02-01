import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static int A, T;
    private static boolean flag;
    private static int falseNum = 0, trueNum = 0, count = 0, people = -1;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        A = Integer.parseInt(br.readLine());
        T = Integer.parseInt(br.readLine());
        // T번째 '뻔' 또는 '데기'를 외치는 사람이 몇 번인지
        flag = Integer.parseInt(br.readLine()) == 1; // false: 뻔, true: 데기

        while (true) { // 1번이 1회차
            count++;
            for (int i = 0; i < 2; i++) {
                // 뻔
                int result = speakFalse();
                if (result != -1) {
                    System.out.println(result);
                    return;
                }
                // 데기
                result = speakTrue();
                if (result != -1) {
                    System.out.println(result);
                    return;
                }
            }

            // 뻔 * (n + 1)
            for (int i = 0; i <= count; i++) {
                int result = speakFalse();
                if (result != -1) {
                    System.out.println(result);
                    return;
                }
            }
            // 데기 * (n + 1)
            for (int i = 0; i <= count; i++) {
                int result = speakTrue();
                if (result != -1) {
                    System.out.println(result);
                    return;
                }
            }
        }
    }

    private static int speakFalse() {
        falseNum++;
        people = (people + 1) % A;
        if (!flag && falseNum == T) return people;
        return -1;
    }

    private static int speakTrue() {
        trueNum++;
        people = (people + 1) % A;
        if (flag && trueNum == T) return people;
        return -1;
    }
}