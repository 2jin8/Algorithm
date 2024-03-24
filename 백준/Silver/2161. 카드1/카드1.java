import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            deque.offer(i);
        }

        StringBuilder sb = new StringBuilder();
        while (deque.size() > 1) {
            sb.append(deque.pollFirst()).append(" ");
            deque.offerLast(deque.pollFirst());
        }
        sb.append(deque.pollFirst());
        System.out.println(sb.toString());
    }
}