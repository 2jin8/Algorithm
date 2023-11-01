import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int F = scan.nextInt(); // 최대 층
        int S = scan.nextInt(); // 현재 층
        int G = scan.nextInt(); // 목표 층
        int U = scan.nextInt(); // 위로 가는 버튼
        int D = scan.nextInt(); // 아래로 가는 버튼
        int[] dp = new int[F + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(S);
        dp[S] = 1;

        while (!queue.isEmpty()) {
            int floor = queue.poll();
            if (floor == G) {
                System.out.println(dp[G] - 1);
                return;
            }

            int up = floor + U;
            int down = floor - D;
            if (up >= 1 && up <= F) {
                if (dp[up]== 0) {
                    queue.offer(up);
                    dp[up] = dp[floor] + 1;
                }
            }
            if (down >= 1 && down <= F) {
                if (dp[down] == 0) {
                    queue.offer(down);
                    dp[down] = dp[floor] + 1;
                }
            }
        }
        System.out.println("use the stairs");
    }
}