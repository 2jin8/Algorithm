import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int R, C, T;
    static int[][] map, newMap;
    static ArrayList<Integer> airConditionR;
    static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // T초가 지난 후 방에 남아있는 미세먼지의 양
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        airConditionR = new ArrayList<>();


        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1) { // 공청기 좌표 기록 (열은 무조건 0)
                    airConditionR.add(i);
                }
            }
        }

        for (int t = 0; t < T; t++) {
            newMap = new int[R][C];
            newMap[airConditionR.get(0)][0] = -1;
            newMap[airConditionR.get(1)][0] = -1;

            // 미세먼지가 퍼뜨리기
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (map[i][j] > 0) {
                        spreadDust(i, j);
                    }
                }
            }

            // 배열 복사: map <- newMap
            copyArray(newMap, map);

            // 공청기 작동
            circulateDust();

            copyArray(newMap, map);
        }

        int totalDust = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                totalDust += map[i][j];
            }
        }
        System.out.println(totalDust + 2); // 공청기는 -1로 게산되니까 2 더하기
    }

    static void spreadDust(int x, int y) {
        int spreadCount = 0, newValue = map[x][y] / 5;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny < 0 || nx >= R || ny >= C || map[nx][ny] == -1) {
                continue;
            }

            newMap[nx][ny] += newValue;
            spreadCount++;
        }
        newMap[x][y] += map[x][y] - newValue * spreadCount;
    }

    static void copyArray(int[][] from, int[][] to) {
        for (int i = 0; i < R; i++) {
            to[i] = from[i].clone();
        }
    }

    static void circulateDust() {
        // 위 공청기
        int idx = 0;
        int x = airConditionR.get(0), y = 0;
        newMap[x][y] = -1;
        while (true) {
            int nx = x + dx[idx];
            int ny = y + dy[idx];
            if (nx < 0 || ny < 0 || nx >= R || ny >= C) {
                if (--idx < 0) idx = 3;
                nx = x + dx[idx];
                ny = y + dy[idx];

            }

            // 공청기에 닿으면 좋료
            if (newMap[nx][ny] == -1) {
                break;
            }

            newMap[nx][ny] = map[x][y] == -1? 0 : map[x][y];
            x = nx; y = ny;
        }

        // 아래 공청기
        idx = 0;
        x = airConditionR.get(1);
        y = 0;
        newMap[x][y] = -1;
        while (true) {
            int nx = x + dx[idx];
            int ny = y + dy[idx];
            if (nx < 0 || ny < 0 || nx >= R || ny >= C) {
                if (++idx > 3) idx = 0;
                nx = x + dx[idx];
                ny = y + dy[idx];
            }

            // 공청기에 닿으면 좋료
            if (newMap[nx][ny] == -1)
                break;

            newMap[nx][ny] = map[x][y] == -1? 0 : map[x][y];
            x = nx; y = ny;
        }
    }
}