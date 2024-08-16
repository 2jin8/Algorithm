import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int N = 8;
    static int[] nums = new int[N];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= 10; t++) {
            int tc = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }

            int idx = 0, value = 1;
            while (true) {
                nums[idx] = Math.max(0, nums[idx] - value++);
                if (nums[idx++] == 0) break; // 0이 되면 종료하기

                // 인덱스를 넘어가면 조정하기
                if (idx == N) idx = 0;
                // 1 사이클이 끝나면 감소하는 값 초기화하기
                if (value > 5) value = 1;
            }

            sb.append("#").append(tc).append(" ");
            for (int i = 0; i < N; i++) {
                // 제일 앞(idx)부터부터 총 N개 출력하기
                sb.append(nums[idx++]).append(" ");
                if (idx == N) idx = 0;
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}