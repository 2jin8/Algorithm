import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class Main {
    static int maxA, maxB, maxC;
    static HashSet<Integer> hashSet = new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        maxA = Integer.parseInt(st.nextToken());
        maxB = Integer.parseInt(st.nextToken());
        maxC = Integer.parseInt(st.nextToken());
        bfs();
        List<Integer> list = hashSet.stream().sorted().collect(Collectors.toList());
        StringBuilder sb = new StringBuilder();
        for (int i : list) {
            sb.append(i).append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void bfs() {
        Queue<Bucket> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[maxA + 1][maxB + 1][maxC + 1];
        queue.offer(new Bucket(0, 0, maxC));

        while (!queue.isEmpty()) {
            Bucket bucket = queue.poll();
            int a = bucket.a, b = bucket.b, c = bucket.c;
            if (visited[a][b][c]) continue; // 이미 방문했다면 넘어가기

            visited[a][b][c] = true; // 방문 처리
            if (a == 0) {
                hashSet.add(c); // 물통 A가 0이라면 물통 C의 용량 기록하기
            }

            // a → b
            if (a + b <= maxB) { // 물통 B의 용량을 넘지 않는 경우
                queue.offer(new Bucket(0, a + b, c));
            } else {
                queue.offer(new Bucket(a + b - maxB, maxB, c));
            }

            // a → c
            if (a + c <= maxC) {
                queue.offer(new Bucket(0, b, a + c));
            } else {
                queue.offer(new Bucket(a + c - maxC, b, maxC));
            }

            // b → a
            if (b + a <= maxA) {
                queue.offer(new Bucket(b + a, 0, c));
            } else {
                queue.offer(new Bucket(maxA, b + a - maxA, c));
            }

            // b → c
            if (b + c <= maxC) {
                queue.offer(new Bucket(a, 0, b + c));
            } else {
                queue.offer(new Bucket(a, b + c - maxC, maxC));
            }

            // c → a
            if (c + a <= maxA) {
                queue.offer(new Bucket(c + a, b, 0));
            } else {
                queue.offer(new Bucket(maxA, b, c + a - maxA));
            }

            // c → b
            if (c + b <= maxB) {
                queue.offer(new Bucket(a, c + b, 0));
            } else {
                queue.offer(new Bucket(a, maxB, c + b - maxB));
            }
        }
    }

    static class Bucket {
        int a, b, c;

        public Bucket(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
}