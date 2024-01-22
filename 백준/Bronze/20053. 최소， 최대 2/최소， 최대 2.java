import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 1000000;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            int n = Integer.parseInt(br.readLine());
            int[] nums = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int min = INF, max = -INF;
            for (int i = 0; i < n; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
                min = Math.min(min, nums[i]);
                max = Math.max(max, nums[i]);
            }
            sb.append(min).append(" ").append(max).append("\n");
        }
        System.out.println(sb);
    }
}