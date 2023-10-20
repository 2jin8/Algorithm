import java.io.*;
import java.util.*;

public class Main {
    private static ArrayList<Integer>[] lists;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());

        init(n + 1);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 양방향
            lists[a].add(b);
            lists[b].add(a);
        }
        br.close();

        for (int i = 1; i <= n; i++) {
            Collections.sort(lists[i]);
        }

        DFS(v);
        System.out.println();
        BFS(v);
    }

    private static void init(int n) {
        lists = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            lists[i] = new ArrayList<>();
        }
        visited = new boolean[n];
    }

    private static void DFS(int v) {
        visited[v] = true;

        System.out.print(v + " ");
        for (int l : lists[v]) {
            if (!visited[l]) {
                DFS(l);
            }
        }
    }

    private static void BFS(int v) {
        visited = new boolean[visited.length];
        LinkedList<Integer> queue = new LinkedList<>();

        visited[v] = true;
        queue.add(v);

        while (queue.size() != 0) {
            v = queue.poll();
            System.out.print(v + " ");

            Iterator<Integer> i = lists[v].listIterator();
            while (i.hasNext()) {
                int n = i.next();

                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }
}