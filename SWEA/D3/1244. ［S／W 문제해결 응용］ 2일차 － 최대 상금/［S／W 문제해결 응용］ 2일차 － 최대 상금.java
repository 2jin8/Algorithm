import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
    static int n, change, max;
    static final int MAX = 1000000;
    static boolean[][] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String num = st.nextToken();
            n = num.length();
            change = Integer.parseInt(st.nextToken());
            if (change > n) change = n;
            visited = new boolean[change][MAX];
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = num.charAt(i) - '0';
            }

            max = 0;
            dfs(0, nums);
            sb.append("#").append(t).append(" ").append(max).append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(int changeCnt, int[] nums) {
        int total = nums[0];
        for (int i = 1; i < n; i++) {
            total = total * 10 + nums[i];
        }        
        
        if (changeCnt == change) {
            max = Math.max(max, total);
            return;
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (!visited[changeCnt][total]) {
                    swap(nums, i, j);
                    visited[changeCnt][total] = true;
                    dfs(changeCnt + 1, nums);
                    visited[changeCnt][total] = false;
                    swap(nums, i, j);
                }

            }
        }
    }

    static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}