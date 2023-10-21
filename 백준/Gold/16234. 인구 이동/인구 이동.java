import java.io.*;
import java.util.*;

public class Main {

    static int N, L, R;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static ArrayList<ArrayList<Point>> checkList;
    static boolean[][] visited;
    static int[][] world;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        world = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                world[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int totalWorld = N * N;
        int day = 0;
        while (true) {
            boolean isExist = true; // 연합 존재 여부
            checkList = new ArrayList<>();
            for (int i = 0; i < totalWorld; i++) {
                checkList.add(new ArrayList<>());
            }
            visited = new boolean[N][N]; // 방문 여부 저장

            // 국경선을 열 나라 찾기 - 상하좌우에 위치한 나라와 인구 차이 비교
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    // 상하좌우 나라 탐색
                    for (int k = 0; k < 4; k++) {
                        int x = i + dx[k];
                        int y = j + dy[k];

                        if (x < 0 || y < 0 || x >= N || y >= N)
                            continue;

                        int abs = Math.abs(world[i][j] - world[x][y]);
                        if (abs >= L && abs <= R) { // 인구 차이가 L명 이상, R명 이하
                            int idx = N * i + j;
                            checkList.get(idx).add(new Point(x, y));
                            isExist = false;
                        }
                    }
                }
            }
            if (isExist) // 연합이 없는 경우
                break;

            // 연합 bfs 탐색 시작
            for (int i = 0; i < totalWorld; i++) {
                for (int j = 0; j < checkList.get(i).size(); j++) {
                    int x = checkList.get(i).get(j).x;
                    int y = checkList.get(i).get(j).y;
                    if (!visited[x][y]) {
                        bfs(x, y);
                    }
                }
            }
            day++;
        }
        System.out.println(day);
    }

    public static void bfs(int x, int y) {
        visited[x][y] = true;
        Queue<Point> queue = new LinkedList<>(); // bfs 탐색을 위한 큐
        queue.offer(new Point(x, y));

        Queue<Point> unionList = new LinkedList<>(); // 연합 정보를 저장하기 위한 큐
        int sum = 0;
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            unionList.add(point);
            sum += world[point.x][point.y];
            int idx = N * point.x + point.y;

            for (int i = 0; i < checkList.get(idx).size(); i++) {
                Point nextPoint = checkList.get(idx).get(i);
                int nx = nextPoint.x;
                int ny = nextPoint.y;
                if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.offer(new Point(nx, ny));
                }
            }
        }

        // 연합을 이루고 있는 나라들의 인구수 조정
        int newNum = sum / unionList.size();
        while (!unionList.isEmpty()) {
            Point union = unionList.poll();
            world[union.x][union.y] = newNum;
        }
    }
}

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}