import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static final int INF = 1_000_000_000;
    static int N, M, ans = INF;
    static int[][] map;
    static ArrayList<Pos> houseList = new ArrayList<>();
    static ArrayList<Pos> chickenList = new ArrayList<>();
    static Pos[] choicePos;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 치킨 거리: 집과 가장 가까운 치킨집 사이의 거리
        // 각자의 집은 치킨 거리를 가지고 있음
        // 도시의 치킨 거리 = 모든 집의 치킨 거리의 합
        // 두 칸 사이의 거리: |r1 - r2| + |c1 - c2|
        // 0: 빈 칸, 1: 집, 2: 치킨집
        // 치킨집 중에서 M개만 고름
        // 도시의 치킨 거리가 가장 작게
        // 치킨집을 폐업시키면 그거랑은 치킨거리 구하면 안되잖아
        // 그럼 폐업시키지 않을 치킨집 고르고(nCm) 모든 집과 극 치킨집 사이의 치킨거리 구하고 최솟값 갱신하는 느낌으로 진행해야?
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    houseList.add(new Pos(i, j));
                } else if (map[i][j] == 2) {
                    chickenList.add(new Pos(i, j));
                }
            }
        }

        choicePos = new Pos[M];
        choiceChickenHouse(0, 0);
        System.out.println(ans);
    }

    // N개의 치킨집 중 M개 선택 (순서 상관 X) => 조합
    static void choiceChickenHouse(int count, int start) {
        if (count == M) {
            // 도시의 치킨 거리 구하기
            calcChickenDistance();
            return;
        }

        for (int i = start; i < chickenList.size(); i++) {
            choicePos[count] = chickenList.get(i);
            choiceChickenHouse(count + 1, i + 1);
        }
    }

    static void calcChickenDistance() {
        int chickenDistance = 0;
        for (Pos house : houseList) {
            int minDistance = INF;
            for (Pos chicken : choicePos) {
                int distX = Math.abs(house.x - chicken.x);
                int distY = Math.abs(house.y - chicken.y);
                minDistance = Math.min(minDistance, distX + distY);
            }
            chickenDistance += minDistance;
        }

        ans = Math.min(ans, chickenDistance);
    }

    static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
