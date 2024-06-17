import java.io.*;
import java.util.*;

public class Main {
    static int r, c;
    static char[][] map;
    static boolean[][] visited;
    static Queue<int[]> fireQueue = new LinkedList<>();
    static Queue<int[]> jhQueue = new LinkedList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        visited = new boolean[r][c];
        int startX = 0, startY = 0; // 지훈이의 초기 위치
        for (int i = 0; i < r; i++) {
            String line = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'F') {
                    fireQueue.offer(new int[]{i, j});
                    visited[i][j] = true;
                } else if (map[i][j] == 'J') {
                    jhQueue.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }
        int bfs = bfs(startX, startY);
        System.out.println(bfs == -1 ? "IMPOSSIBLE" : bfs);
    }

    static int bfs(int x, int y) {
        int[][] dist = new int[r][c];
        int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!jhQueue.isEmpty()) {
            // 불 이동
            int size = fireQueue.size();
            for (int i = 0; i < size; i++) {
                int[] now = fireQueue.poll();

                for (int j = 0; j < 4; j++) {
                    int nx = now[0] + move[j][0];
                    int ny = now[1] + move[j][1];
                    if (nx < 0 || ny < 0 || nx >= r || ny >= c || visited[nx][ny]) {
                        continue;
                    }

                    if (map[nx][ny] != '#') { // 벽이 아니면 불이 퍼질 수 있음
                        visited[nx][ny] = true;
                        fireQueue.offer(new int[]{nx, ny});
                    }
                }
            }

            // 지훈이 이동
            size = jhQueue.size();
            for (int i = 0; i < size; i++) {
                int[] now = jhQueue.poll();

                for (int j = 0; j < 4; j++) {
                    int nx = now[0] + move[j][0];
                    int ny = now[1] + move[j][1];
                    // 미로 범위를 벗어나면 탈출한 것
                    if (nx < 0 || ny < 0 || nx >= r || ny >= c) {
                        return dist[now[0]][now[1]] + 1;
                    }

                    // 불이 퍼지지 않았고 지나갈 수 있는 공간인 경우
                    if (!visited[nx][ny] && map[nx][ny] == '.') {
                        jhQueue.offer(new int[]{nx, ny});
                        visited[nx][ny] = true;
                        dist[nx][ny] = dist[now[0]][now[1]] + 1;
                    }
                }
            }
        }
        return -1;
    }
}