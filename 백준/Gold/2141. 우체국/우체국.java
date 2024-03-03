import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static Node[] nodes;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        nodes = new Node[n];
        long total = 0;
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int idx = Integer.parseInt(st.nextToken());
            int people = Integer.parseInt(st.nextToken());
            nodes[i] = new Node(idx, people);
            total += people;
        }
        Arrays.sort(nodes, new Comparator<Node>() { // 마을 위치를 기준으로 오름차순 정렬
            @Override
            public int compare(Node o1, Node o2) {
                return o1.idx - o2.idx;
            }
        });

        long mid = (total + 1) / 2, peopleSum = 0;
        for (int i = 0; i < n; i++) {
            peopleSum += nodes[i].people;
            if (mid <= peopleSum) { // 중앙값보다 같거나 클 때, 우체국 설치하기
                System.out.println(nodes[i].idx);
                break;
            }
        }
    }

    static class Node {
        int idx;
        int people;

        public Node(int idx, int people) {
            this.idx = idx;
            this.people = people;
        }
    }
}