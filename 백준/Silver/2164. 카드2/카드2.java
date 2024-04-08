import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            queue.offer(i);
        }

        while (queue.size() > 1) {
            queue.poll(); // 제일 위에 있는 카드 버리기
            queue.offer(queue.poll()); // 그 다음으로 제일 위에 있는 카드 밑으로 옮기기
        }
        System.out.println(queue.poll());
    }
}