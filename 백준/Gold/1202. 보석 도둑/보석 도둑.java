import java.io.*;
import java.util.*;

public class Main {

    private static int N, K;
    public static Jewel[] jewels;
    private static int[] bags;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        jewels = new Jewel[N];
        bags = new int[K];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            jewels[i] = new Jewel(m, v);
        }
        Arrays.sort(jewels, new Comparator<Jewel>() {

            @Override
            public int compare(Jewel o1, Jewel o2) {
                if (o1.M == o2.M)
                    return o2.V - o1.V;
                return o1.M - o2.M;
            }
        });

        for (int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(bags);

        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        Long sum = 0L;

        for (int i = 0, j = 0; i < K; i++) {
            int bag = bags[i];

            while (j < N && jewels[j].M <= bag) {
                queue.add(jewels[j++].V);
            }

            if (!queue.isEmpty()) {
                sum += queue.poll();
            }
        } 

        System.out.println(sum);
    }
}

class Jewel {
    int M;
    int V;

    public Jewel(int M, int V) {
        this.M = M;
        this.V = V;
    }
}