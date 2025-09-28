import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        // negPq: 작은 수 기준 정렬, posPq: 큰 수 기준 정렬
        PriorityQueue<Integer> negPq = new PriorityQueue<>();
        PriorityQueue<Integer> posPQ = new PriorityQueue<>((i1, i2) -> Integer.compare(i2, i1));

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num > 0) {
                posPQ.offer(num);
            } else {
                negPq.offer(num);
            }
        }
        
        // 양수는 양수끼리, 음수는 음수끼리 곱해야 큰 수를 만들 수 있음
        // 0은 양수랑은 더하고 음수랑은 곱해야 큰 수를 만들 수 있음
        int total = 0;
        while (!posPQ.isEmpty()) {
            int p1 = posPQ.poll();
            // 하나 더 꺼낼 수 있으면 두 값을 곱하는게 더 큼
            if (!posPQ.isEmpty()) {
                int p2 = posPQ.poll();
                // 둘 중 하나가 1이면 더하는 값이 더 큼
                if (p1 == 1 || p2 == 1) total += p1 + p2;
                else total += p1 * p2;
            } else {
                total += p1;
            }
        }

        while (!negPq.isEmpty()) {
            int n1 = negPq.poll();
            if (!negPq.isEmpty()) {
                int n2 = negPq.poll();

                total += n1 * n2;
            } else {
                total += n1;
            }
        }
        System.out.println(total);
    }
}
