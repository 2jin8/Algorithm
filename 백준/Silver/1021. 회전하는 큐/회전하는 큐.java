import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        LinkedList<Integer> deque = new LinkedList<>();
        for (int i = 1; i <= N; i++) { // 덱에 1 ~ N 넣기
            deque.offer(i);
        }

        st = new StringTokenizer(br.readLine(), " ");
        int[] poll = new int[M];
        for (int i = 0; i < M; i++) {
            poll[i] = Integer.parseInt(st.nextToken());
        }

        int total = 0;
        for (int p : poll) {
            int idx = deque.indexOf(p);
            int mid = deque.size() / 2;

            if (idx <= mid) {
                while (deque.getFirst() != p) {
                    deque.offerLast(deque.pollFirst());
                    total++;
                }
            } else {
                while (deque.getFirst() != p) {
                    deque.offerFirst(deque.pollLast());
                    total++;
                }
            }
            deque.pollFirst();
        }
        System.out.println(total);
    }
}