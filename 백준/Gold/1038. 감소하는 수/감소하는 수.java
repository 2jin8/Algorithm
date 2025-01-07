import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static final int INF = 1_000_000;
    static int N, cnt;
    static long ans;
    static int[] nums;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N =  Integer.parseInt(br.readLine()); // N번째 감소하는 수 찾기

        ans = -1;
        // 1자리, 2자리, ..., 10자리
        // 최대로 만들 수 있는 수는 9876543210
        for (int i = 1; i <= 10; i++) {
            // 감소하는 수가 INF보다 크거나 이미 답을 구했으면 종료
            if (cnt > INF || ans != -1) break;

            nums = new int[i];
            makeNum(0, i, 9);
        }
        System.out.println(ans);
    }

    static void makeNum(int depth, int len, int end) {
        // N번째 수를 이미 구했으면 종료
        if (ans != -1) return;

        if (depth == len) {
            // N번째 감소하는 수
            if (cnt++ == N) {
                long sum = 0;
                for (int num : nums) {
                    sum = sum * 10 + num;
                }
                ans = sum;
            }
            return;
        }

        for (int i = 0; i <= end; i++) {
            nums[depth] = i;
            makeNum(depth + 1, len, i - 1);
        }
    }
}