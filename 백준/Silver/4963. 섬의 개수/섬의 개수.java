import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    private static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    private static boolean[][] visited;
    private static int[][] land;
    private static int w, h, count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken()); // 너비
            h = Integer.parseInt(st.nextToken()); // 높이
            if (w == 0 || h == 0) return;

            count = 0;
            land = new int[h][w];
            visited = new boolean[h][w];

            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    land[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (land[i][j] == 1 && !visited[i][j]) { // 아직 방문하지 않은 섬인 경우
                        DFS(i, j);
                        count++;
                    }
                }
            }
            System.out.println(count);
        }
    }

    private static void DFS(int i, int j) {
        visited[i][j] = true;

        for (int k = 0; k < 8; k++) {
            int x = i + dx[k];
            int y = j + dy[k];

            if (x < 0 || y < 0 || x >= h || y >= w)
                continue;

            if (land[x][y] == 1 && !visited[x][y]) {
                DFS(x, y);
            }
        }
    }
}
