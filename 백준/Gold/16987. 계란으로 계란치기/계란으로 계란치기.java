import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] dura;
    static int[] weight;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        dura = new int[n];
        weight = new int[n];
        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            dura[i] = Integer.parseInt(st.nextToken());
            weight[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0); // 0번째 계란부터 시작, 깨진 계란 0개
        System.out.println(max);
    }

    static void dfs(int idx, int cnt) {
        // 가장 오른쪽에 위치한 계란을 든 경우
        if (idx == n) {
            max = Math.max(cnt, max);
            return;
        }

        // 손에 든 계란이 깨지거나 깨지지 않은 다른 계란이 없는 경우
        if (dura[idx] <= 0 || cnt == n-1) {
            dfs(idx + 1, cnt);
            return;
        }

        int nCnt = cnt;
        for (int i=0; i<n; i++) {
            if (idx == i) continue; // 치려는 계란이 들고 있는 계란인 경우
            if (dura[i] <= 0) continue; // 치려는 계란이 이미 깨진 계란인 경우

            // 계란 치기(내구도 감소)
            dura[i] -= weight[idx];
            dura[idx] -= weight[i];

            if (dura[i] <= 0) cnt++;
            if (dura[idx] <= 0) cnt++;

            // 다음 계란 들기
            dfs(idx + 1, cnt);

            // 원상복구(return되지 않고 돌아왔을 경우)
            dura[i] += weight[idx];
            dura[idx] += weight[i];
            cnt = nCnt;
        }
    }
}