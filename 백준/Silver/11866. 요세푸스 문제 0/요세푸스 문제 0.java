import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        Deque<Integer> deque = new ArrayDeque<>();
        // 덱에 수(1~N) 버반
        for (int i = 1; i <= N; i++) {
            deque.addLast(i);
        }
        StringBuilder sb = new StringBuilder("<");
        while (deque.size() > 1) {
            for (int i = 0; i < K - 1; i++) {
                deque.addLast(deque.pollFirst());
            }
            sb.append(deque.pollFirst()).append(", ");
        }
        sb.append(deque.pollFirst()).append(">");
        System.out.println(sb);
    }
}