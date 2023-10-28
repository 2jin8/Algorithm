import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int year = 0;
        while (true) {
            meltingIce(map); // 높이 조정
            year++;
            boolean[][] visited = new boolean[n][m];
            int iceNum = 0; // 빙산의 수
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] != 0 && !visited[i][j]) {
                        bfs(map, visited, i, j); // bfs 탐색으로 빙산의 수 세기
                        iceNum++;
                    }
                }
            }

            if (iceNum >= 2) { // 빙산이 두 개 이상이면 종료
                System.out.println(year);
                break;
            }
            if (isMelting(map)) { // 빙산이 모두 녹았으면 종료
                System.out.println(0);
                break;
            }
        }
    }

    public static void bfs(int[][] map, boolean[][] visited, int x, int y) {
        visited[x][y] = true;
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(x, y));

        while (!queue.isEmpty()) {
            Point point = queue.poll();
            x = point.x;
            y = point.y;

            for (int i = 0; i < 4; i++) {
                int tx = x + dx[i];
                int ty = y + dy[i];
                if (tx < 0 || ty < 0 || tx >= n || ty >= m)
                    continue;

                if (map[tx][ty] != 0 && !visited[tx][ty]) {
                    queue.offer(new Point(tx, ty));
                    visited[tx][ty] = true;
                }
            }
        }
    }

    public static void meltingIce(int[][] map) {
        int[][] tmpMap = cloneArray(map); // map의 값이 수정되기 때문에 기존의 map 복제
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                if (tmpMap[x][y] != 0) {
                    int cnt = 0;
                    for (int i = 0; i < 4; i++) {
                        int tx = x + dx[i];
                        int ty = y + dy[i];

                        if (tx < 0 || ty < 0 || tx >= n || ty >= m)
                            continue;

                        if (tmpMap[tx][ty] == 0)
                            cnt++;
                    }
                    int melt = tmpMap[x][y] - cnt;
                    map[x][y] = Math.max(melt, 0); // map의 빙산 높이 변경
                }
            }
        }
    }

    public static int[][] cloneArray(int[][] map) {
        int[][] newMap = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                newMap[i][j] = map[i][j];
            }
        }
        return newMap;
    }

    public static boolean isMelting(int[][] map) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] != 0)
                    return false;
            }
        }
        return true;
    }
}

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}