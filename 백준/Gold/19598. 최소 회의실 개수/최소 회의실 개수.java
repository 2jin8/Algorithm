import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Lecture[] lectures = new Lecture[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            lectures[i] = new Lecture(s, e);
        }

        // 회의실 시작 시간을 기준으로 오름차순 정렬
        Arrays.sort(lectures, new Comparator<Lecture>() {
            @Override
            public int compare(Lecture o1, Lecture o2) {
                if (o1.start == o2.start) return o1.end - o2.end;
                return o1.start - o2.start;
            }
        });

        int total = 1;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(lectures[0].end);
        for (int i = 1; i < n; i++) {
            Lecture lecture = lectures[i];
            if (lecture.start >= pq.peek().intValue()) {
                pq.poll();
                pq.offer(lecture.end);
            } else {
                total++;
                pq.offer(lecture.end);
            }
        }
        System.out.println(total);

    }

    static class Lecture {
        int start;
        int end;

        public Lecture(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}