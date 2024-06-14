import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n =Integer.parseInt(br.readLine());
        PriorityQueue<Integer> negativePq = new PriorityQueue<>();
        PriorityQueue<Integer> positivePq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num <= 0) {
                negativePq.offer(num);
            } else {
                positivePq.offer(num);
            }
        }

        int total = 0;
        while (positivePq.size() > 1) { // 두 개씩 뽑기
            int a = positivePq.poll();
            int b = positivePq.poll();
            total += Math.max(a + b, a * b);
        }

        while (negativePq.size() > 1) { // 두 개씩 뽑기
            int a = negativePq.poll();
            int b = negativePq.poll();
            total += Math.max(a + b, a * b);
        }

        if (!positivePq.isEmpty() && !negativePq.isEmpty()) { // 양수 큐와 음수 큐에 값이 아직 존재하는 경우
            int a = positivePq.poll();
            int b = negativePq.poll();
            total += Math.max(a + b, a * b);
        } else if (!positivePq.isEmpty()) { // 양수 큐에만 값이 존재하는 경우
            total += positivePq.poll();
        } else if (!negativePq.isEmpty()) { // 음수 큐에만 값이 존재하는 경우
            total += negativePq.poll();
        }
        System.out.println(total);
    }
}