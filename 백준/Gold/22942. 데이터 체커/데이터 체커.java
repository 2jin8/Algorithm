import java.io.*;
import java.util.*;

public class Main {
    static boolean flag = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int c = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            pq.offer(new Node(c - r, i));
            pq.offer(new Node(c + r, i));
        }

        if (flag) {
            System.out.println("NO");
            return;
        }

        Stack<Integer> stack = new Stack<>();
        while (!pq.isEmpty()) {
            if (stack.isEmpty()) {
                stack.push(pq.poll().idx);
            } else {
                if (stack.peek().intValue() == pq.peek().idx) {
                    // 스택의 가장 위에 것과 큐의 가장 위에 것이 같은 경우
                    // 즉, 같은 짝(=원)인 경우
                    stack.pop();
                    pq.poll();
                } else {
                    stack.push(pq.poll().idx);
                }
            }
        }
        if (stack.isEmpty()) System.out.println("YES");
        else System.out.println("NO");
    }

    static class Node implements Comparable<Node> {

        int x;
        int idx;

        @Override
        public int compareTo(Node n) {
            if (this.x == n.x) flag = true;
            return this.x - n.x; // 좌표 기준으로 오름차순 정렬
        }

        public Node(int x, int idx) {
            this.x = x;
            this.idx = idx;
        }
    }
}