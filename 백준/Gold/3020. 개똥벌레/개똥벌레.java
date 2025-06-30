import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, H;
    static int[] diff, count;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        diff = new int[H + 1];
        for (int i = 0; i < N; i++) {
            int h = Integer.parseInt(br.readLine());
            if (i % 2 == 0) {
                diff[0]++;
                diff[h]--;
            } else {
                diff[H - h]++;
                diff[H]--;
            }
        }

        count = new int[H];
        count[0] = diff[0];
        for (int i = 1; i < H; i++) {
            count[i] = count[i - 1] + diff[i];
        }

        int min = N;
        int[] answer = new int[N]; // i: 부숴야 하는 장애물의 개수, answer[i]: 구간의 개수
        for (int c : count) {
            answer[c]++;
            min = Math.min(min, c);
        }
        System.out.println(min + " " + answer[min]);
    }
}
