import java.io.*;
import java.util.*;

public class Main {
    static int n, minDiff = Integer.MAX_VALUE;
    static int[] sTastes, bTastes;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        sTastes = new int[n]; // 신맛
        bTastes = new int[n]; // 쓴맛
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            sTastes[i] = Integer.parseInt(st.nextToken());
            bTastes[i] = Integer.parseInt(st.nextToken());
        }
        dfs(0, 0, 1, 0);
        System.out.println(minDiff);

    }

    static void dfs(int depth, int cnt, int sTaste, int bTaste) {
        if (cnt > 0) { // 재료를 적어도 하나 사용한 경우
            // 신맛과 쓴맛의 차이가 더 작은 값으로 갱신
            minDiff = Math.min(minDiff, Math.abs(sTaste - bTaste));
        }

        if (depth == n) {
            return;
        }
        // 해당 재료 사용 O
        dfs(depth + 1, cnt + 1, sTaste * sTastes[depth], bTaste + bTastes[depth]);
        // 해당 재료 사용 X
        dfs(depth + 1, cnt, sTaste, bTaste);
    }
}