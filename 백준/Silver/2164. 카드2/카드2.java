import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            deque.add(i);
        }

        while (deque.size() > 1) {
            // 1. 제일 위에 있는 카드 버리기
            deque.removeFirst();

            // 2. 제일 위에 있는 카드를 제일 아래에 있는 카드 밑에 넣기
            if (deque.size() == 1) break;
            int remove = deque.removeFirst();
            deque.offerLast(remove);
        }
        System.out.println(deque.getFirst());
    }
}