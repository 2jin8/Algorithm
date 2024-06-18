import java.io.*;
import java.util.*;

public class Main {
    static int w, h;
    static char[][] map;
    static boolean[][] visited;
    static Queue<int[]> fireQueue, sgQueue;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            map = new char[h][w];
            visited = new boolean[h][w];
            fireQueue = new LinkedList<>();
            sgQueue = new LinkedList<>();
            for (int i = 0; i < h; i++) {
                String line = br.readLine();
                for (int j = 0; j < w; j++) {
                    map[i][j] = line.charAt(j);
                    if (map[i][j] == '*') { // 불의 시작 위치 저장하기
                        fireQueue.offer(new int[]{i, j});
                        visited[i][j] = true;
                    } else if (map[i][j] == '@') { // 상근이의 초기 위치 저장하기
                        sgQueue.offer(new int[]{i, j});
                        visited[i][j] = true;
                    }
                }
            }
            int bfs = bfs();
            sb.append(bfs == -1 ? "IMPOSSIBLE" : bfs).append("\n");
        }
        System.out.println(sb.toString());
    }

    static int bfs() {
        int time = 0;
        int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!sgQueue.isEmpty()) { // 상근이가 더 이상 이동할 수 없는 경우 종료
            time++;

            // 불 먼저 이동하기
            int size = fireQueue.size();
            for (int i = 0; i < size; i++) {
                int[] now = fireQueue.poll();

                for (int j = 0; j < 4; j++) {
                    int nx = now[0] + move[j][0];
                    int ny = now[1] + move[j][1];
                    if (nx < 0 || ny < 0 || nx >= h || ny >= w || visited[nx][ny]) {
                        continue;
                    }

                    if (map[nx][ny] != '#') { // 불은 벽을 제외하고 모두 번질 수 있음
                        fireQueue.offer(new int[]{nx, ny});
                        visited[nx][ny] = true;
                    }
                }
            }
            // 상근이 이동하기
            size = sgQueue.size();
            for (int i = 0; i < size; i++) {
                int[] now = sgQueue.poll();
                int x = now[0], y = now[1];

                for (int j = 0; j < 4; j++) {
                    int nx = x + move[j][0];
                    int ny = y + move[j][1];
                    if (nx < 0 || ny < 0 || nx >= h || ny >= w) { // 지도 범위를 벗어나면 탈출한 것
                        return time;
                    }

                    // 방문하지 않았거나 불이 붙지 않았고 빈 공간인 경우 이동 가능
                    if (!visited[nx][ny] && map[nx][ny] == '.') {
                        sgQueue.offer(new int[]{nx, ny});
                        visited[nx][ny] = true;
                    }
                }
            }
        }
        return -1;
    }
}