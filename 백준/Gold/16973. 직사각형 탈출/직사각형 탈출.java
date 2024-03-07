import java.io.*;
import java.util.*;

public class Main {
    static int N, M, H, W;
    static int[][] map;
    static List<Pos> list = new ArrayList<>();
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) list.add(new Pos(i, j, 0)); // 벽이 있으면 list에 넣기
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        int sr = Integer.parseInt(st.nextToken()) - 1;
        int sc = Integer.parseInt(st.nextToken()) - 1;
        int er = Integer.parseInt(st.nextToken()) - 1;
        int ec = Integer.parseInt(st.nextToken()) - 1;

        visited = new boolean[N][M];
        bfs(sr, sc, er, ec);
    }

    public static void bfs(int sr, int sc, int er, int ec) {
        Queue<Pos> queue = new LinkedList<>();
        queue.offer(new Pos(sr, sc, 0));
        visited[sr][sc] = true;

        int time = -1;
        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};
        while (!queue.isEmpty()) {
            Pos pos = queue.poll();
            if (pos.r == er && pos.c == ec) {
                time = pos.time;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nr = pos.r + dr[i];
                int nc = pos.c + dc[i];
                if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
                if (visited[nr][nc]) continue;

                if (checkWall(nr, nc)) { // 해당 직사각형 내에 벽이 없는 경우
                    queue.offer(new Pos(nr, nc, pos.time + 1));
                    visited[nr][nc] = true;
                }
            }
        }
        System.out.println(time);
    }

    public static boolean checkWall(int r, int c) {
        if (r + H - 1 >= N || c + W - 1 >= M) return false;
        for (Pos pos : list) {
            if ((r <= pos.r && pos.r <= r + H - 1) && (c <= pos.c && pos.c <= c + W - 1)) {
                return false;
            }
        }
        return true;
    }

    static class Pos {
        int r;
        int c;
        int time;

        public Pos(int r, int c, int time) {
            this.r = r;
            this.c = c;
            this.time = time;
        }
    }
}