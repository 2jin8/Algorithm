import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] scores;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            scores = new int[N + 1];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int paperScore = Integer.parseInt(st.nextToken());
                int talkScore = Integer.parseInt(st.nextToken());
                scores[paperScore] = talkScore;
            }

            int choice = 1; // 서류 점수가 1등이면 무조건 뽑힘
            int minOrder = scores[1];
            for (int i = 2; i <= N; i++) {
                // 자신보다 서류 순위가 높은 지원자보다 면접 순위가 높으면 선발 가능
                if (scores[i] < minOrder) {
                    choice++;
                }

                // 서류 순위가 높은 지원자 중 가장 높은 면접 순위 갱신
                minOrder = Math.min(minOrder, scores[i]);
            }
            sb.append(choice).append("\n");
        }
        System.out.println(sb);
    }
}
