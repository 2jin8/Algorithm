import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static ArrayList<Pos> zeroList = new ArrayList<>();
    static ArrayList<Pos> oneList = new ArrayList<>();
    static HashMap<Integer, Integer> group = new HashMap<>(); // 그룹 번호, 그룹의 크기
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) zeroList.add(new Pos(i, j)); // 칸에 들어있는 수가 0이면 따로 저장하기
                else oneList.add(new Pos(i, j));
            }
        }

        // 그룹 찾기
        int groupNum = 1;
        for (Pos pos : oneList) {
            if (!visited[pos.x][pos.y]) { // map[i][j]가 1인 것들은 이미 방문 처리가 완료됨
                findGroup(pos.x, pos.y, groupNum++);
            }
        }

        // 그룹 연결하기
        int maxArea = 0;
        for (Pos pos : zeroList) {
            maxArea = Math.max(maxArea, linkedGroup(pos.x, pos.y));
        }
        System.out.println(maxArea);
    }

    static void findGroup(int x, int y, int groupNum) {
        Queue<Pos> queue = new LinkedList<>();
        queue.offer(new Pos(x, y));
        visited[x][y] = true;

        int area = 0;
        while (!queue.isEmpty()) {
            Pos now = queue.poll();
            map[now.x][now.y] = groupNum;
            area++;

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny] || map[nx][ny] == 0) {
                    continue;
                }

                queue.offer(new Pos(nx, ny));
                visited[nx][ny] = true;
            }
        }
        group.put(groupNum, area);
    }

    static int linkedGroup(int x, int y) {
        HashSet<Integer> checkGroup = new HashSet<>();
        int area = 1;
        for (int i = 0; i < 4; i++) { // 현재 위치를 기준으로 네 방향 중에 그룹이 있는지만 찾으면 됨!
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] == 0) {
                continue;
            }

            int groupNum = map[nx][ny];
            if (!checkGroup.contains(groupNum)) {
                area += group.get(groupNum);
                checkGroup.add(groupNum);
            }
        }
        return area;
    }

    static class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}