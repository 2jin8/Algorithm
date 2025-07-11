import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] dist;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        dist = new int[10000]; // 최대 9999
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            Arrays.fill(dist, -1);

            int bfs = bfs(new Number(A, getNumbers(A)), new Number(B, getNumbers(B)));
            sb.append(bfs == -1 ? "Impossible" : bfs).append("\n");
        }
        System.out.println(sb);
    }

    static int bfs(Number numberA, Number numberB) {
        Queue<Number> queue = new ArrayDeque<>();
        queue.offer(numberA);
        dist[numberA.number] = 0;

        while (!queue.isEmpty()) {
            Number now = queue.poll();
            if (now.number == numberB.number)
                break;

            // 첫 번째 자리
            int[] numbers = now.numbers;
            int first = numbers[0];
            for (int i = 1; i <= 9; i++) {
                if (first == i) continue;

                numbers[0] = i;
                int number = getNumber(numbers);
                if (dist[number] == -1 && isPrime(number)) {
                    queue.offer(new Number(number, Arrays.copyOf(numbers, 4)));
                    dist[number] = dist[now.number] + 1;
                }
            }
            numbers[0] = first;

            // 두 번째 자리
            for (int i = 1; i < 4; i++) {
                int tmp = numbers[i];
                for (int j = 0; j <= 9; j++) {
                    if (tmp == j) continue;

                    numbers[i] = j;
                    int number = getNumber(numbers);
                    if (dist[number] == -1 && isPrime(number)) {
                        queue.offer(new Number(number, Arrays.copyOf(numbers, 4)));
                        dist[number] = dist[now.number] + 1;
                    }
                }
                numbers[i] = tmp;
            }
        }
        return dist[numberB.number];
    }

    static boolean isPrime(int number) {
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0)
                return false;
        }
        return true;
    }

    static int[] getNumbers(int number) {
        int[] numbers = new int[4];
        for (int i = 3; i >= 0; i--) {
            numbers[i] = number % 10;
            number /= 10;
        }
        return numbers;
    }

    static int getNumber(int[] numbers) {
        int number = 0;
        for (int n : numbers) {
            number = number * 10 + n;
        }
        return number;
    }

    static class Number {
        int number;
        int[] numbers;

        public Number(int number, int[] numbers) {
            this.number = number;
            this.numbers = numbers;
        }
    }
}
