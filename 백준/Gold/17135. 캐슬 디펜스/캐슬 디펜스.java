import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M, D, maxKill, totalEnemy;
    static int[][] map, newMap;
    static int[] posC = new int[3]; // 궁수의 열의 위치 (행은 N - 1로 고정)
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 풀이 로직
        // 1. 궁수의 위치 정하기 (3자리만 정하면 됨 => 완탐)
        // 2. 모든 적이 게임판에서 없어질 때까지 시뮬레이션 돌리기
        // 3. 없앤 적의 개수 갱신
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken()); // 궁수의 공격 거리 제한
        map = new int[N][M];
        newMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                totalEnemy += map[i][j]; // 적의 수 계산
            }
        }

        setPosition(0, 0);
        System.out.println(maxKill);
    }

    // 순서 중요 X > 조합
    static void setPosition(int depth, int start) {
        if (depth == 3) {
            maxKill = Math.max(maxKill, simulation());
            return;
        }

        // 궁수의 위치 정하기
        for (int i = start; i < M; i++) {
            posC[depth] = i;
            setPosition(depth + 1, i + 1);
        }
    }

    static int simulation() {
        int kill = 0, enemy = totalEnemy;
        // 기존 배열 새로운 배열로 복사 (1번 실행하고 끝낼 것 아니므로.. 주의!)
        for (int i = 0; i < N; i++) {
            newMap[i] = map[i].clone();
        }

        while (enemy != 0) {
            int[][] tmpMap = new int[N][M];
            for (int i = 0; i < N; i++) {
                tmpMap[i] = newMap[i].clone();
            }

            // 각 궁사별로 가장 가까운 적 구하기
            for (int c : posC) {
                Enemy killEnemy = getMinDistEnemy(N, c);
                if (killEnemy.d == -1) continue; // 죽일 적을 못 찾은 경우

                int killCount = tmpMap[killEnemy.x][killEnemy.y]; // 같은 적을 공격할 수도 있으므로 tmpMap의 값으로 확인하기
                enemy -= killCount;
                kill += killCount;
                tmpMap[killEnemy.x][killEnemy.y] = 0; // 적 제거
            }

            // 마지막 줄에 살아있던 적의 수 빼주기
            for (int last : tmpMap[N - 1]) {
                enemy -= last;
            }

            // 기존 배열에 새로운 배열 붙여넣기 (이때, 한 줄씩 아래로 밀 것)
            for (int i = N - 2; i >= 0; i--) {
                newMap[i + 1] = tmpMap[i].clone();
            }
            Arrays.fill(newMap[0], 0);
        }
        return kill;
    }

    static Enemy getMinDistEnemy(int r, int c) {
        int x = -1, y = -1, d = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (newMap[i][j] == 0) continue;

                // 가장 가까운 거리에 있는 적 찾기
                int dist = Math.abs(i - r) + Math.abs(j - c);
                if (dist > D) continue;

                if (dist < d) {
                    d = dist;
                    x = i;
                    y = j;
                } else if (dist == d) { // 거리가 같은 적이 여러 명이면 가장 왼쪽에 있는 적 공격
                    if (j < y) { // j가 더 왼쪽에 있으면 갱신
                        x = i;
                        y = j;
                    }
                }
            }
        }
        if (d == Integer.MAX_VALUE) d = -1; // 죽일 적을 못 찾는 경우
        return new Enemy(x, y, d);
    }

    static class Enemy {
        int x, y, d;

        public Enemy(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
}
