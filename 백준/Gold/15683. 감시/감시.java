import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, ans = Integer.MAX_VALUE;
    static int[][] map;
    static boolean[][] visited;
    static ArrayList<Pos> cctvList = new ArrayList<>();
    static int[] dx = {0, -1, 0, 1}, dy = {-1, 0, 1, 0};
    static ArrayList<Pos>[] choiceList;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                // CCTV 위치 기록
                if (map[i][j] >= 1 && map[i][j] <= 5) {
                    cctvList.add(new Pos(i, j, map[i][j]));
                }
            }
        }

        choiceList = new ArrayList[cctvList.size()];
        for (int i = 0; i < cctvList.size(); i++) {
            choiceList[i] = new ArrayList<>();
        }

        choiceDirection(0);
        System.out.println(ans);
    }
    
    // 1. CCTV의 방향 선택하기
    static void choiceDirection(int depth) {
        if (depth == cctvList.size()) {
            checkArea();
            return;
        }

        // CCTV 종류에 따라 방향 선택하기
        int kind = cctvList.get(depth).kind;
        switch (kind) {
            case 1: // 선택 가능한 방향: 4개
                for (int i = 0; i < 4; i++) {
                    choiceList[depth].clear();
                    choiceList[depth].add(new Pos(dx[i], dy[i], kind));
                    choiceDirection(depth + 1);
                }
                break;
            case 2: // 선택 가능한 방향: 2개
                for (int i = 0; i < 2; i++) {
                    choiceList[depth].clear();
                    choiceList[depth].add(new Pos(dx[i], dy[i], kind));
                    choiceList[depth].add(new Pos(dx[i + 2], dy[i + 2], kind));
                    choiceDirection(depth + 1);
                }
                break;
            case 3: // 선택 가능한 방향: 4개
                for (int i = 0; i < 4; i++) {
                    choiceList[depth].clear();
                    choiceList[depth].add(new Pos(dx[i], dy[i], kind));
                    choiceList[depth].add(new Pos(dx[(i + 1) % 4], dy[(i + 1) % 4], kind));
                    choiceDirection(depth + 1);
                }
                break;
            case 4: // 선택 가능한 방향: 4개
                for (int i = 0; i < 4; i++) {
                    choiceList[depth].clear();
                    choiceList[depth].add(new Pos(dx[i], dy[i], kind));
                    choiceList[depth].add(new Pos(dx[(i + 1) % 4], dy[(i + 1) % 4], kind));
                    choiceList[depth].add(new Pos(dx[(i + 2) % 4], dy[(i + 2) % 4], kind));
                    choiceDirection(depth + 1);
                }
                break;
            case 5: // 선택 가능한 방향: 1개
                choiceList[depth].clear();
                choiceList[depth].add(new Pos(dx[0], dy[0], kind));
                choiceList[depth].add(new Pos(dx[1], dy[1], kind));
                choiceList[depth].add(new Pos(dx[2], dy[2], kind));
                choiceList[depth].add(new Pos(dx[3], dy[3], kind));
                choiceDirection(depth + 1);
                break;
        }
    }

    // 2. 감시 가능한 영역 체크 (기존 배열 복사해서)
    static void checkArea() {
        int[][] checkMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            checkMap[i] = map[i].clone();
        }

        for (int i = 0; i < cctvList.size(); i++) {
            Pos cctvPos = cctvList.get(i);
            for (Pos pos : choiceList[i]) {
                int nx = cctvPos.x, ny = cctvPos.y;
                while (true) {
                    nx += pos.x;
                    ny += pos.y;
                    // 범위를 벗어나거나 벽에 닿으면 더 이상 감시 불가능
                    if (nx < 0 || ny < 0 || nx >= N || ny >= M || checkMap[nx][ny] == 6) break;

                    checkMap[nx][ny] = pos.kind;
                }
            }
        }

        int minArea = 0;
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (checkMap[i][j] == 0 && !visited[i][j]) {
                    minArea += calcArea(checkMap, i, j);
                }
            }
        }
        ans = Math.min(ans, minArea);
    }

    // 3. 영역 구하기 (BFS)
    static int calcArea(int[][] checkMap, int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        int area = 0;
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            area++;

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny])
                    continue;

                if (checkMap[nx][ny] == 0) {
                    queue.offer(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }
        return area;
    }

    static class Pos {
        int x, y, kind;

        public Pos(int x, int y, int kind) {
            this.x = x;
            this.y = y;
            this.kind = kind;
        }
    }
}
