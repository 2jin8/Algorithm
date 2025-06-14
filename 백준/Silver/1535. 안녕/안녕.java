import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, ans;
    static int[] healths, happiness;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        healths = new int[N];
        happiness = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            healths[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            happiness[i] = Integer.parseInt(st.nextToken());
        }

        getMaxHappiness(0, 100, 0);
        System.out.println(ans);
    }

    // 얻을 수 있는 최대 기쁨
    // 모든 경우의 수를 확인해야 함 => 부분 집합
    public static void getMaxHappiness(int depth, int health, int happy) {
        if (depth == N) {
            ans = Math.max(ans, happy);
            return;
        }

        // 선택 X
        getMaxHappiness(depth + 1, health, happy); 
        // 선택 O
        if (health - healths[depth] > 0) // 체력이 양수일 때만 기쁨이 유효함
            getMaxHappiness(depth + 1, health - healths[depth], happy + happiness[depth]);
    }
}
