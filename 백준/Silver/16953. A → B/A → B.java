import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        bfs(A, B);
    }

    public static void bfs(long A, long B) {
        Queue<Long> queue = new LinkedList<>();
        queue.offer(A);

        int cnt = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            cnt++;
            for (int i = 0; i < size; i++) {
                long x = queue.poll();
                if ((x * 2 == B) || (x * 10 + 1 == B)) {
                    System.out.println(cnt);
                    return;
                }

                // 2를 곱하기
                if (x * 2 <= B) {
                    queue.offer(x * 2);
                }

                // 오른쪽에 1을 더하기
                if (x * 10 + 1 <= B) {
                    queue.offer(x * 10 + 1);
                }
            }
        }
        System.out.println(-1);
    }
}