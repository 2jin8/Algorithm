import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Lecture[] lectures = new Lecture[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            lectures[i] = new Lecture(start, end);
        }

        // 시작 시간을 기준으로 오름차순 정렬
        Arrays.sort(lectures, (l1, l2) -> {
            if (l1.start == l2.start) return l1.end - l2.end;
            return l1.start - l2.start;
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(lectures[0].end);
        for (int i = 1; i < n; i++) {
            int end = pq.peek(); // 가장 빨리 끝나는 시간만 비교하면 됨
            if (end <= lectures[i].start) { // 현재 강의의 종료 시간 <= 다음 강의의 시작 시간
                pq.poll();
            }
            pq.offer(lectures[i].end);
        }
        System.out.println(pq.size());
    }

    static class Lecture {
        int start, end;

        public Lecture(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}