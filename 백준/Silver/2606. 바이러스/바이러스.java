import java.io.*;
import java.util.*;

public class Main {

    private static ArrayList<ArrayList<Integer>> computers = new ArrayList<>();
    private static boolean[] visited;
    private static int count = 0;
    public static void main(String[] args) throws IOException {
        // 1번 컴퓨터가 웜 바이러스에 걸림
        // 1번 컴퓨터를 통해 웜 바이러스에 걸리게 되는 컴퓨터의 수 출력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 컴퓨터의 수
        int pair = Integer.parseInt(br.readLine()); // 연결되어 있는 컴퓨터 쌍의 수

        visited = new boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            computers.add(new ArrayList<>());
        }

        for (int i = 0; i < pair; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            computers.get(u).add(v);
            computers.get(v).add(u);
        }

        dfs(1);
        System.out.println(count - 1);
    }

    public static void dfs(int x) {
        visited[x] = true;
        count++;

        for (int i = 0; i < computers.get(x).size(); i++) {
            int y = computers.get(x).get(i);

            if (!visited[y]) dfs(y);
        }
    }
}