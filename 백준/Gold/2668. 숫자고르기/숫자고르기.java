import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] arr;
    static boolean[] visited, done;
    static List<Integer> ans = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        done = new boolean[N + 1];
        visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            if (i == arr[i]) {
                ans.add(i);
                done[i] = true;
            }
        }

        // 사이클이 생기는 것들 모두 더하면 됨
        for (int i = 1; i <= N; i++) {
            if (!done[i]) dfs(i);
        }
        Collections.sort(ans);

        StringBuilder sb = new StringBuilder();
        sb.append(ans.size()).append("\n");
        for (Integer a : ans) {
            sb.append(a).append("\n");
        }
        System.out.println(sb);
    }

    public static void dfs(int v) {
        if (done[v]) return; // 이미 탐색이 완료된 경우라면
        if (visited[v]) { // 탐색이 완료되지 않았는데 이미 방문한 경우라면 → 사이클에 포함
            done[v] = true;
            ans.add(v);
        }

        visited[v] = true;
        dfs(arr[v]);
        visited[v] = false;

        done[v] = true;
    }
}