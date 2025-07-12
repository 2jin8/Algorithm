import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int S, T;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int C = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < C; t++) {
            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            T = Integer.parseInt(st.nextToken());
            sb.append(bfs()).append("\n");
        }
        System.out.println(sb);
    }

    static int bfs() {
        Queue<Game> queue = new ArrayDeque<>();
        queue.offer(new Game(S, T, 0));
        while (!queue.isEmpty()) {
            Game now = queue.poll();
            if (now.scoreS == now.scoreT)
                return now.punch;

            // S가 T보다 크면 절대 S와 T가 같아질 수 없음
            // 1번 발차기
            if (now.scoreS * 2 <= now.scoreT + 3)
                queue.offer(new Game(now.scoreS * 2, now.scoreT + 3, now.punch + 1));

            // 2번 발차기
            if (now.scoreS < now.scoreT)
                queue.offer(new Game(now.scoreS + 1, now.scoreT, now.punch + 1));
        }
        return -1;
    }

    static class Game {
        int scoreS, scoreT, punch;

        public Game(int scoreS, int scoreT, int punch) {
            this.scoreS = scoreS;
            this.scoreT = scoreT;
            this.punch = punch;
        }
    }
}
