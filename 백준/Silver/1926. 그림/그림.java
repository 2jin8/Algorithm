import java.io.*;
import java.util.*;

public class Main {
    static int n, m, maxArea = 0, pictureNum = 0;
    static int[][] picture;
    static boolean[][] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        picture = new int[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                picture[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (picture[i][j] == 1 && !visited[i][j]) {
                    maxArea = Math.max(maxArea, bfs(i, j));
                    pictureNum++;
                }
            }
        }
        System.out.println(pictureNum);
        System.out.println(maxArea);
    }

    static int bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        visited[x][y] = true;
        queue.offer(new int[]{x, y});

        int area = 0;
        int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            area++;

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + move[i][0];
                int ny = now[1] + move[i][1];
                // 범위 벗어나거나 이미 방문한 위치면 넘어가기
                if (nx < 0 || ny < 0 || nx >= n || ny >= m || visited[nx][ny]) {
                    continue;
                }

                // 그림이 그려진 곳이면 큐에 넣기 (다음 탐색을 위해)
                if (picture[nx][ny] == 1) {
                    queue.offer(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }
        return area;
    }
}