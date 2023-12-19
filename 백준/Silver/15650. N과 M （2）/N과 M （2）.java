import java.util.*;

public class Main {

    static int n, m;
    static int[] result; // 결과 저장 배열
    static boolean[] used; // 숫자 사용 여부
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 1~n까지 m개 고르기
        n = sc.nextInt();
        m = sc.nextInt();

        used = new boolean[n + 1];
        result = new int[m];

        dfs(0, 0);
        System.out.println(sb);
    }

    private static void dfs(int start, int cnt) {
        if (cnt == m) {
            for (int r : result) {
                sb.append(r).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = start + 1; i <= n; i++) {
            if (!used[i]) {
                used[i] = true;
                result[cnt] = i;
                dfs(i, cnt + 1);
                used[i] = false;
            }
        }
    }
}