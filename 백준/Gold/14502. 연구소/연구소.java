import java.io.*;
import java.util.*;

public class Main {
    static int N, M, max = 0;
    static int[][] map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0);
        System.out.println(max);
    }

    public static void dfs(int wall) { // dfs로 벽 3개 세우기(완전탐색)
        if (wall == 3) { // 벽 3개 세웠으면 바이러스 퍼뜨리기
            bfs();
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    dfs(wall + 1);
                    map[i][j] = 0;
                }
            }
        }
    }

    public static void bfs() { // bfs로 바이러스 퍼뜨리기
        Queue<Point> queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 2) {
                    queue.offer(new Point(i, j));
                }
            }
        }

        int[][] tmp = new int[N][M]; // 탐색을 위해 새로운 배열 사용
        for (int i = 0; i < N; i++) {
            tmp[i] = map[i].clone();
        }

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        while (!queue.isEmpty()) {
            Point point = queue.poll();

            for (int i = 0; i < 4; i++) {
                int tx = point.x + dx[i];
                int ty = point.y + dy[i];
                if (tx < 0 || ty < 0 || tx >= N || ty >= M) continue;

                if (tmp[tx][ty] == 0) {
                    tmp[tx][ty] = 2;
                    queue.offer(new Point(tx, ty));
                }
            }
        }
        safeZone(tmp);
    }

    public static void safeZone(int[][] arr) { // 안전영역 구하기
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 0) cnt++;
            }
        }
        max = Math.max(cnt, max);
    }

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}