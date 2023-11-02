import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < n; i++) {
            pq.offer(scan.nextInt());
        }

        long gold = 0L;
        while (true) {
            int card1 = pq.poll();
            int card2 = pq.poll();
            gold += card1 + card2;
            if (pq.isEmpty()) {
                System.out.println(gold);
                return;
            }
            pq.offer(card1);
        }
    }
}