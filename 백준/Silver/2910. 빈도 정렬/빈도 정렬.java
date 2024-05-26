import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        // 1. 등장 횟수 2. 나온 순서
        HashMap<Integer, Number> hashMap = new HashMap<>();
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (hashMap.containsKey(num)) {
                Number number = hashMap.get(num);
                number.addCount();
                hashMap.put(num, number);
            } else {
                hashMap.put(num, new Number(num, i, 1));
            }
        }

        PriorityQueue<Number> pq = new PriorityQueue<>((n1, n2) -> {
            if (n1.count == n2.count) return n1.order - n2.order; // 나온 순서를 기준으로 오름차순 정렬
            return n2.count - n1.count; // 등장 횟수 기준으로 내림차순 정렬
        });
        for (Number number : hashMap.values()) {
            pq.offer(number);
        }

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            Number number = pq.poll();
            String num = number.num + " ";
            sb.append(num.repeat(number.count));
        }
        System.out.println(sb.toString());
    }

    static class Number {
        int num, order, count;

        public Number(int num, int order, int count) {
            this.num = num;
            this.order = order;
            this.count = count;
        }

        public void addCount() {
            this.count++;
        }
    }
}