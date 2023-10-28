import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static int N, M;
    private static int[][] box;
    private static int[] dx = {1, 0, -1, 0};
    private static int[] dy = {0, -1, 0, 1};
    private static Queue<int[]> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        box = new int[M][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
                if (box[i][j] == 1) {
                    queue.add(new int[]{i, j});
                }
            }
        }

        BFS();
        checkBox();
    }
    
    private static void BFS() {
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int x = point[0];
            int y = point[1];

            for (int i = 0; i < 4; i++) {
                int cx = x + dx[i];
                int cy = y + dy[i];

                if (cx < 0 || cy < 0 || cx >= M || cy >= N) {
                    continue;
                }

                if (box[cx][cy] == 0) { // 토마토가 익지 않은 경우
                    queue.add(new int[]{cx, cy});
                    box[cx][cy] = box[x][y] + 1;
                }
            }
        }
    }

    private static void checkBox() {
        int ans = 0;

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (box[i][j] == 0) {
                    System.out.println(-1);
                    return;
                }
                ans = Math.max(ans, box[i][j]);
            }
        }
        System.out.println(ans - 1);
    }
}