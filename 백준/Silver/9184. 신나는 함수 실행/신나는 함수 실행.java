import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N = 101, M = 50;
    static boolean[][][] checked;
    static int[][][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        StringBuilder sb = new StringBuilder();
        checked = new boolean[N][N][N];
        dp = new int[N][N][N];

        checked = new boolean[N][N][N];
        dp = new int[N][N][N];
        while (true) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (a == -1 && b == -1 && c == -1)
                break;

            sb.append("w(").append(a).append(", ").append(b).append(", ").append(c).append(") = ");
            sb.append(w(a + M, b + M, c + M)).append("\n");
        }
        System.out.println(sb);
    }

    static int w(int a, int b, int c) {
        if (checked[a][b][c])
            return dp[a][b][c];

        int A = a - M, B = b - M, C = c - M;

        if (A <= 0 || B <= 0 || C <= 0)
            dp[a][b][c] = 1;
        else if (A > 20 || B > 20 || C > 20)
            dp[a][b][c] = w(20 + M, 20 + M, 20 + M);
        else if (A < B && B < C)
            dp[a][b][c] = w(a, b, c - 1) + w(a, b - 1, c - 1) - w(a, b - 1, c);
        else
            dp[a][b][c] = w(a - 1, b, c) + w(a - 1, b - 1, c) + w(a - 1, b, c - 1) - w(a - 1, b - 1, c - 1);

        checked[a][b][c] = true;
        return dp[a][b][c];
    }
}
