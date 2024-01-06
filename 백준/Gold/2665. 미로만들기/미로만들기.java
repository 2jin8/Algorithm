import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    static int n;
    static int[][] map;
    static boolean[][] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }
        System.out.println(bfs());
    }

    public static int bfs() {
        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.offer(new Point(0, 0, 0));
        visited[0][0] = true;

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        while (!pq.isEmpty()) {
            Point point = pq.poll(); // 이동 횟수가 가장 적은 Point 나옴
            if (point.x == n - 1 && point.y == n - 1) // 끝방에 도착한 경우
                return point.cnt; // 방을 바꾼 횟수 return

            for (int i = 0; i < 4; i++) {
                int tx = point.x + dx[i];
                int ty = point.y + dy[i];
                if (tx < 0 || ty < 0 || tx >= n || ty >= n)
                    continue;

                if (visited[tx][ty]) continue;

                if (map[tx][ty] == 0) { // 검은 방이면 흰 방으로 바꾸고 이동(cnt + 1)
                    pq.offer(new Point(tx, ty, point.cnt + 1));
                } else { // 흰 방이면 그냥 이동(cnt)
                    pq.offer(new Point(tx, ty, point.cnt));
                }
                visited[tx][ty] = true;
            }
        }
        return -1;
    }
}

class Point implements Comparable<Point> {
    int x;
    int y;
    int cnt; // 검은 방을 흰 방으로 바꾼 수

    public Point(int x, int y, int cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }

    @Override
    public int compareTo(Point o) {
        return cnt - o.cnt; // 변경 횟수를 기준으로 오름차순 정렬
    }
}