import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int N = 9;
    static boolean isFind;
    static int[][] map;
    static ArrayList<Pos> emptyList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {
                    emptyList.add(new Pos(i, j));
                }
            }
        }
        dfs(0);
    }

    static void dfs(int depth) {
        if (isFind) return;
        if (depth == emptyList.size()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    sb.append(map[i][j]).append(" ");
                }
                sb.append("\n");
            }
            System.out.println(sb);
            isFind = true;
            return;
        }

        // 가로 & 세로 & 3 by 3 확인
        Pos pos = emptyList.get(depth);
        int x = pos.x, y = pos.y;
        for (int i = 1; i <= N; i++) {
            map[x][y] = i;
            if (checkRowCol(x, y) && checkSquare(x, y))
                dfs(depth + 1);
            map[x][y] = 0;
        }
    }

    static boolean checkRowCol(int x, int y) {
        boolean[] rowUsed = new boolean[N + 1];
        for (int c = 0; c < N; c++) {
            if (map[x][c] == 0) continue;
            if (rowUsed[map[x][c]]) return false;

            rowUsed[map[x][c]] = true;
        }

        boolean[] colUsed = new boolean[N + 1];
        for (int r = 0; r < N; r++) {
            if (map[r][y] == 0) continue;
            if (colUsed[map[r][y]]) return false;

            colUsed[map[r][y]] = true;
        }
        return true;
    }

    static boolean checkSquare(int x, int y) {
        boolean[] squareUsed = new boolean[N + 1];
        int startR = (x / 3) * 3, startC = (y / 3) * 3;
        for (int r = startR; r < startR + 3 && r < N; r++) {
            for (int c = startC; c < startC + 3 && c < N; c++) {
                if (map[r][c] == 0) continue;
                if (squareUsed[map[r][c]]) return false;

                squareUsed[map[r][c]] = true;
            }
        }
        return true;
    }

    static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
