import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static ArrayList<Fireball>[][] list;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        list = new ArrayList[N][N];
        init(list);
        for (int i = 0; i < M; i++) { // 파이어볼 정보 넣기
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            list[r][c].add(new Fireball(m, d, s));
        }

        // 명령 K번 실행
        for (int i = 0; i < K; i++) {
            move(); // d 방향으로 s만큼 이동하기
            change(); // 2개 이상의 파이어볼이 있는 칸에서 변경..
        }

        // 남아있는 파이어볼 질량의 합
        System.out.println(getM());
    }

    public static void move() {
        int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
        ArrayList<Fireball>[][] tmp = new ArrayList[N][N];
        init(tmp);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < list[i][j].size(); k++) {
                    // d 방향으로 s만큼 이동하기 (연결되어 있음 → %N 해줘야 함)
                    Fireball fb = list[i][j].get(k);
                    int r = getPos(i + dx[fb.d] * fb.s);
                    int c = getPos(j + dy[fb.d] * fb.s);
                    tmp[r][c].add(new Fireball(fb.m, fb.d, fb.s));
                }
            }
        }
        for (int i = 0; i < N; i++) {
            list[i] = tmp[i].clone();
        }
    }

    public static void init(ArrayList<Fireball>[][] lists) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                lists[i][j] = new ArrayList<>();
            }
        }
    }

    public static void change() {
        // 같은 칸에 있는 파이어볼 모두 하나로 합쳐짐
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int size = list[i][j].size(); // 합쳐질 파이어볼의 개수
                if (size >= 2) { // 2개 이상의 파이어볼이 있다면 하나로 합쳐짐
                    int m = 0, s = 0, d = 0;
                    for (int k = 0; k < size; k++) {
                        Fireball fb = list[i][j].remove(0);
                        m += fb.m; s += fb.s;
                        if (fb.d % 2 == 0) d++; // 짝수인 경우에만 세기
                    }
                    // 4개의 파이어볼로 나누어짐
                    m /= 5; s /= size;
                    if (m == 0) continue; // 질량이 0인 파이어볼은 소멸
                    int newD = (d == size || d == 0) ? 0 : 1; // 방향이 모두 짝수 또는 홀수면 0부터 시작
                    for (int k = 0; k < 4; k++) {
                        list[i][j].add(new Fireball(m, newD, s));
                        newD += 2;
                    }
                }
            }
        }
    }

    public static int getPos(int x) {
        if (x < 0) {
            int mul = (int) Math.ceil((double) Math.abs(x) / N);
            x += mul * N;
        }
        return x % N;
    }

    public static int getM() {
        int total = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < list[i][j].size(); k++) {
                    total += list[i][j].get(k).m;
                }
            }
        }
        return total;
    }

    static class Fireball {
        int m; // 질량
        int d; // 방향
        int s; // 속력

        public Fireball(int m, int d, int s) {
            this.m = m;
            this.d = d;
            this.s = s;
        }
    }
}