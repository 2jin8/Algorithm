import java.io.*;
import java.util.*;

public class Main {
    static int L, R, C;
    static char[][][] building;
    static boolean[][][] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            if (L == 0 && R == 0 && C == 0) break;

            building = new char[R][C][L];
            visited = new boolean[R][C][L];

            int x = 0, y = 0, z = 0;
            for (int l = 0; l < L; l++) {
                for (int r = 0; r < R; r++) {
                    String line = br.readLine();
                    for (int c = 0; c < C; c++) {
                        building[r][c][l] = line.charAt(c);
                        if (building[r][c][l] == 'S') { // 시작 위치
                            x = r;
                            y = c;
                            z = l;
                        }
                    }
                }
                br.readLine();
            }
            int bfs = bfs(x, y, z);
            if (bfs == -1) sb.append("Trapped!\n");
            else sb.append("Escaped in ").append(bfs).append(" minute(s).\n");
        }
        System.out.println(sb.toString());
    }

    public static int bfs(int x, int y, int z) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y, z});
        visited[x][y][z] = true;

        int[][][] dist = new int[R][C][L];
        int[][] move = {{1, 0, 0}, {-1, 0, 0}, {0, 1, 0}, {0, -1, 0}, {0, 0, 1}, {0, 0, -1}};
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            x = now[0]; y = now[1]; z = now[2];
            if (building[x][y][z] == 'E') { // 출구에 도착한 경우
                return dist[x][y][z];
            }

            for (int i = 0; i < 6; i++) {
                int nx = x + move[i][0];
                int ny= y + move[i][1];
                int nz = z + move[i][2];
                if (nx < 0 || ny < 0 || nz < 0 || nx >= R || ny >= C || nz >= L) {
                    continue;
                }

                // 벽이 아니고 이미 방문한 지점이 아니면 이동 가능
                if (building[nx][ny][nz] != '#' && !visited[nx][ny][nz]) {
                    queue.offer(new int[]{nx, ny, nz});
                    visited[nx][ny][nz] = true;
                    dist[nx][ny][nz] = dist[x][y][z] + 1;
                }
            }
        }
        return -1;
    }
}