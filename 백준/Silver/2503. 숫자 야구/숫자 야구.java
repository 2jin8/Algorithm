import java.io.*;
import java.util.*;

public class Main {
    static int n, total = 0;
    static int[][] record;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        record = new int[n][3];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 3; j++) {
                record[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                if (i == j) continue;
                for (int k = 1; k <= 9; k++) {
                    if (i == k || j == k) continue;
                    // 기록이랑 비교하기
                    check(new int[]{i, j, k});
                }
            }
        }
        System.out.println(total);
    }
    public static void check(int[] now) {
        for (int i = 0; i < n; i++) {
            String[] nums = String.valueOf(record[i][0]).split("");
            int s = 0, b = 0;
            // 스트라이크 체크
            for (int j = 0; j < 3; j++) {
                if (Integer.parseInt(nums[j]) == now[j]) { // 각 자리가 같은 경우
                    s++; // 스트라이크
                }
            }

            // 볼 체크
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if (j == k) continue;
                    if (Integer.parseInt(nums[j]) == now[k]) { // 세 자리수에 있으나 다른 자리에 위치하는 경우
                        b++; // 볼
                    }
                }
            }

            if (record[i][1] != s || record[i][2] != b) return;
        }
        total++;
    }
}