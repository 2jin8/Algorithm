import java.util.*;

public class Main {
    static int maxA, maxB, maxC;
    static Set<Integer> results = new HashSet<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        maxA = sc.nextInt();
        maxB = sc.nextInt();
        maxC = sc.nextInt();

        bfs();
        StringBuilder sb = new StringBuilder();
        results.stream().sorted().forEach(s -> sb.append(s).append(" "));
        System.out.println(sb.toString());
    }

    public static void bfs() {
        boolean[][][] visited = new boolean[maxA + 1][maxB + 1][maxC + 1];
        Queue<Bucket> queue = new LinkedList<>();
        queue.offer(new Bucket(0, 0, maxC));

        while (!queue.isEmpty()) {
            Bucket bucket = queue.poll();
            if (visited[bucket.a][bucket.b][bucket.c])
                continue;

            visited[bucket.a][bucket.b][bucket.c] = true;
            if (bucket.a == 0) results.add(bucket.c);


            // A → B
            if (bucket.a + bucket.b <= maxB) {
                queue.offer(new Bucket(0, bucket.a + bucket.b, bucket.c));
            } else {
                queue.offer(new Bucket(bucket.a + bucket.b - maxB, maxB, bucket.c));
            }

            // A → C
            if (bucket.a + bucket.c <= maxC) {
                queue.offer(new Bucket(0, bucket.b, bucket.a + bucket.c));
            } else {
                queue.offer(new Bucket(bucket.a + bucket.c - maxC, bucket.b, maxC));
            }

            // B → A
            if (bucket.b + bucket.a <= maxA) {
                queue.offer(new Bucket(bucket.a + bucket.b, 0, bucket.c));
            } else {
                queue.offer(new Bucket(maxA, bucket.b + bucket.a - maxA, bucket.c));
            }

            // B → C
            if (bucket.b + bucket.c <= maxC) {
                queue.offer(new Bucket(bucket.a, 0, bucket.b + bucket.c));
            } else {
                queue.offer(new Bucket(bucket.a, bucket.b + bucket.c - maxC, maxC));
            }

            // C → A
            if (bucket.c + bucket.a <= maxA) {
                queue.offer(new Bucket(bucket.a + bucket.c, bucket.b, 0));
            } else {
                queue.offer(new Bucket(maxA, bucket.b, bucket.c + bucket.a - maxA));
            }

            // C → B
            if (bucket.c + bucket.b <= maxB) {
                queue.offer(new Bucket(bucket.a, bucket.b + bucket.c, 0));
            } else {
                queue.offer(new Bucket(bucket.a, maxB, bucket.c + bucket.b - maxB));
            }
        }
    }
}

class Bucket {
    int a; int b; int c;

    public Bucket(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
}