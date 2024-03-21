import java.io.*;
import java.util.*;

public class Main {
    static int N, L, R;
    static boolean isMove; // 인구 이동 여부
    static int[][] map, arr;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int day = 0;
        while (true) {
            arr = map.clone();
            visited = new boolean[N][N];
            isMove = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        bfs(i, j);
                    }
                }
            }
            map = arr.clone();
            if (!isMove) break;
            day++;
        }
        System.out.println(day);
    }

    public static void bfs(int x, int y) {
        Queue<Pos> queue = new LinkedList<>();
        queue.offer(new Pos(x, y));
        visited[x][y] = true;

        int total = 0, cnt = 0; // 이동하는 인구의 수와 국가의 수
        List<Pos> posList = new ArrayList<>();
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        while (!queue.isEmpty()) {
            Pos pos = queue.poll();
            x = pos.x; y = pos.y;
            total += map[x][y];
            cnt++;
            posList.add(new Pos(x, y));

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) continue;

                int diff = Math.abs(map[x][y] - map[nx][ny]); // 인구 차이
                if (diff >= L && diff <= R) { // 인구 차이가 L명 이상, R명 이하인 경우
                    queue.offer(new Pos(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }

        int value = total / cnt; // 새로 변경될 연합의 인구 수
        for (Pos pos : posList) { // 인구 이동
            map[pos.x][pos.y] = value;
        }
        isMove |= cnt != 1; // cnt가 1이면 인구 이동이 없었다는 의미
    }

    static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}