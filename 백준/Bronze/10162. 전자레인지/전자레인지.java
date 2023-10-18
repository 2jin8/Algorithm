import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        int[] time = {300, 60, 10};
        int A = 300, B = 60, C = 10; // 초단위
        int[] count = new int[3];

        for (int i = 0; i < 3; i++) {
            if (T / time[i] > 0) {
                count[i] = T / time[i];
                T = T % time[i];
            }
        }

        if (T == 0) {
            for (int i : count) {
                System.out.print(i+" ");
            }
        } else {
            System.out.println(-1);
        }
    }

    // A: 5분, B: 1분, C: 10초
    // 시간의 합이 정확히 T초가 되도록
    // A, B, C를 누른 횟수의 합은 항상 최소

}