import java.util.*;
import java.io.*;

public class Main {
    static int R, C, N;
    static char[][] map, full;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        full = new char[R][C];
        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
            }
            Arrays.fill(full[i], 'O');
        }

        if (N % 2 == 0) { // 2의 배수 초라면 폭탄으로 가득찬 상태
            print(full);
            return;
        }

        int time = 1;
        while (time != N) {
            time += 2;
            bfs();
        }
        print(map);
    }

    public static void print(char[][] arr) { // 출력을 위한 메소드
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sb.append(arr[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    static char[][] copyArr(char[][] arr) {
        char[][] tmp = new char[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                tmp[i][j] = arr[i][j];
            }
        }
        return tmp;
    }

    static void bfs() {
        // 폭탄이 설치된 위치 기록하기
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'O') queue.offer(new int[]{i, j});
            }
        }

        // 폭발시키기
        char[][] tmp = copyArr(full); // 폭탄으로 가득찬 상태에서 폭발이 발생함
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            tmp[now[0]][now[1]] = '.';

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                if (nx < 0 || ny < 0 || nx >= R || ny >= C || tmp[nx][ny] == '.') {
                    continue;
                }

                tmp[nx][ny] = '.';
            }
        }
        map = copyArr(tmp);
    }
}