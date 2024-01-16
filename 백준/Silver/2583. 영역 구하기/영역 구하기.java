import java.io.*;
import java.util.*;

// 몇 개의 분리된 영역으로 나누어지는지
// 각 영역의 넓이가 얼마인지
// 칠하지 않은 영역부터 BFS OR DFS 적용
// 0, 0부터 해당 값이 FALSE면 탐색 시작 & 지나가면 TRUE로 변경

public class Main {
    private static int[] dx = {-1, 0, 1, 0}; // 시계 방향
    private static int[] dy = {0, 1, 0, -1};
    private static int m, n, k, area;
    private static boolean[][] square;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        square = new boolean[n][m];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for (int j = x1; j < x2; j++) {
                for (int l = y1; l < y2; l++) {
                    square[j][l] = true;
                }
            }
        }

        int total = 0;
        ArrayList<Integer> areas = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!square[i][j]) {
                    area = 0;
                    total++;
                    dfs(i, j);
                    areas.add(area);
                }
            }
        }

        System.out.println(total);
        Collections.sort(areas);
        for (Integer area : areas) {
            System.out.print(area+" ");
        }
    }

    private static void dfs(int x, int y) {
        square[x][y] = true;
        area++;

        for (int i = 0; i < 4; i++) {
            int tx = x + dx[i];
            int ty = y + dy[i];

            if (tx < 0 || ty < 0 || tx >= n || ty >= m)
                continue;

            if (!square[tx][ty]) {
                dfs(tx, ty);
            }
        }
    }
}

