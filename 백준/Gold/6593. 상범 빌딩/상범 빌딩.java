import java.util.*;
import java.io.*;

public class Main {

    public static int L, R, C;
    public static Point start, end;
    public static char[][][] building;
    public static int[][][] time;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        while (L != 0 && R != 0 && C != 0) {
            building = new char[L][R][C];
            time = new int[L][R][C];
            for (int l = 0; l < L; l++) {
                for (int r = 0; r < R; r++) {
                    String str = br.readLine();
                    for (int c = 0; c < C; c++) {
                        building[l][r][c] = str.charAt(c);
                        if (building[l][r][c] == 'S') start = new Point(l, r, c);
                        if (building[l][r][c] == 'E') end = new Point(l, r, c);
                    }
                }
                br.readLine();
            }
            int x = bfs();
            if (x == -1) bw.write("Trapped!\n");
            else bw.write("Escaped in " + x + " minute(s).\n");

            st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
        }
        bw.flush();
        br.close(); bw.close();
    }

    public static int bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(start);
        time[start.l][start.r][start.c] = 1;

        int[] dl = {-1, 1};
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            int l = point.l;
            int r = point.r;
            int c = point.c;
            if (l == end.l && r == end.r && c == end.c)
                return time[l][r][c] - 1;

            // 동, 서, 남, 북 탐색
            for (int i = 0; i < 4; i++) {
                int tr = r + dr[i];
                int tc = c + dc[i];

                if (tr < 0 || tc < 0 || tr >= R || tc >= C) // 범위 벗어나면 이동 불가
                    continue;

                if (building[l][tr][tc] == '#') // 벽이면 이동 불가
                    continue;

                if (time[l][tr][tc] == 0) { // 방문하지 않은 경우
                    time[l][tr][tc] = time[l][r][c] + 1;
                    queue.offer(new Point(l, tr, tc));
                }
            }

            // 상, 하 탐색
            for (int i = 0; i < 2; i++) {
                int tl = l + dl[i];

                if (tl < 0 || tl >= L)
                    continue;

                if (building[tl][r][c] == '#')
                    continue;

                if (time[tl][r][c] == 0) {
                    time[tl][r][c] = time[l][r][c] + 1;
                    queue.offer(new Point(tl, r, c));
                }
            }
        }
        return -1;
    }
}

class Point {
    int l;
    int r;
    int c;

    public Point(int l, int r, int c) {
        this.l = l;
        this.r = r;
        this.c = c;
    }
}