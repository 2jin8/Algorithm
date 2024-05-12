import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static int minX, minY, maxX = -1, maxY = -1;
    static char[][] map, newMap;
    static Queue<int[]> queue = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // X: 땅, .: 바다
        // 인접한 세 칸, 또는 네 칸에 바다가 있는 칸은 모두 잠김
        // 즉, 상하좌우에 바다가 있으면 잠김
        // 지도의 크기도 작아져야 함
        // 지도를 벗어나는 칸은 모두 바다...!!!!

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        newMap = new char[R][C];
        minX = R; minY = C;
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'X') { // 땅의 위치 큐에 저장
                    queue.offer(new int[]{i, j});
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                newMap[i][j] = map[i][j];
            }
        }
        bfs();

        StringBuilder sb = new StringBuilder();
        for (int i = minX; i <= maxX; i++) {
            for (int j = minY; j <= maxY; j++) {
                sb.append(newMap[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void bfs() {
        int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0], y = now[1];

            // 네 방향 탐색하기
            int sea = 0;
            for (int i = 0; i < 4; i++) {
                int nx = x + move[i][0];
                int ny = y + move[i][1];

                // 지도를 벗어나는 칸은 모두 바다 & 배열의 값이 '.'이면 바다
                if (nx < 0 || ny < 0 || nx >= R || ny >= C || map[nx][ny] == '.') {
                    sea++;
                }
            }

            if (sea >= 3) { // 주변에 바다가 3개 이상이면 물에 잠김
                newMap[x][y] = '.';
            } else { // 지도 크기 조절을 위해 최소/최대 x, y 구하기
                minX = Math.min(minX, x);
                minY = Math.min(minY, y);
                maxX = Math.max(maxX, x);
                maxY = Math.max(maxY, y);
            }
        }
    }
}