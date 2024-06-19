import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static ArrayList<Integer> apartments = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    apartments.add(bfs(i, j));
                }
            }
        }
        Collections.sort(apartments); // 단지내 집의 수 오름차순으로 정렬

        StringBuilder sb = new StringBuilder();
        sb.append(apartments.size()).append("\n");
        for (int apartment : apartments) {
            sb.append(apartment).append("\n");
        }
        System.out.println(sb.toString());
    }

    public static int bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        int house = 0;
        int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            house++;

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + move[i][0];
                int ny = now[1] + move[i][1];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                    continue;
                }

                if (map[nx][ny] == 1 && !visited[nx][ny]) {
                    queue.offer(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }
        return house;
    }
}