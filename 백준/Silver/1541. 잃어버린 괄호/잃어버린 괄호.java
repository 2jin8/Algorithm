import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // '-'로 식 나누기
        StringTokenizer minusTokenizer = new StringTokenizer(br.readLine(), "-");
        Queue<Integer> queue = new LinkedList<>();
        while (minusTokenizer.hasMoreTokens()) {
            // '+'로 식 한번 더 나누기
            StringTokenizer plusTokenizer = new StringTokenizer(minusTokenizer.nextToken(), "+");
            int total = 0;
            while (plusTokenizer.hasMoreTokens()) {
                total += Integer.parseInt(plusTokenizer.nextToken());
            }
            queue.offer(total);
        }
        int answer = queue.poll();
        while (!queue.isEmpty()) {
            answer -= queue.poll();
        }
        System.out.println(answer);
    }
}