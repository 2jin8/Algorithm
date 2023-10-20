import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        PriorityQueue<Long> queue = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            queue.offer(Long.parseLong(st.nextToken()));
        }

        for (int i = 0; i < m; i++) {
            long sum = queue.poll() + queue.poll();
            queue.offer(sum);
            queue.offer(sum);
        }

        Long total = 0L;
        for (Long q : queue) {
            total += q;
        }
        System.out.println(total);
    }
}