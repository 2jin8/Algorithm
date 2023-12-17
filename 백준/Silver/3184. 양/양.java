import java.util.*;
import java.io.*;

public class Main {
    private static int R, C;
    private static int sheep, fox;
    private static char[][] board;
    private static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];
        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = str.charAt(j);
                if (board[i][j] == 'o') sheep++;
                if (board[i][j] == 'v') fox++;
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] != '#' && !visited[i][j]) { // 울타리가 아니고 방문하지 않은 경우
                    bfs(i, j); // 탐색 시작
                }
            }
        }
        System.out.println(sheep + " " + fox);
    }

    public static void bfs(int r, int c) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(r, c));
        visited[r][c] = true;

        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};
        int foxNum = 0, sheepNum = 0;
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            r = point.r;
            c = point.c;
            if (board[r][c] == 'v') foxNum++; // 늑대 수 세기
            if (board[r][c] == 'o') sheepNum++; // 양의 수 세기

            for (int i = 0; i < 4; i++) {
                int tr = r + dr[i];
                int tc = c + dc[i];

                if (tr < 0 || tc < 0 || tr >= R || tc >= C) // 범위 벗어나는 경우
                    continue;

                if (board[tr][tc] == '#') // 울타리인 경우
                    continue;

                if (!visited[tr][tc]) {
                    queue.offer(new Point(tr, tc));
                    visited[tr][tc] = true;
                }
            }
        }
        // 양의 수 > 늑대의 수 -> 양이 이김
        if (sheepNum > foxNum) fox -= foxNum;
        else sheep -= sheepNum;
    }
}

class Point {
    int r;
    int c;

    public Point(int r, int c) {
        this.r = r;
        this.c = c;
    }
}