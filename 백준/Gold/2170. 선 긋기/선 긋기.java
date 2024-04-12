import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Line[] lines = new Line[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            lines[i] = new Line(start, end);
        }
        Arrays.sort(lines, new Comparator<Line>() {
            @Override
            public int compare(Line o1, Line o2) {
                if (o1.start == o2.start) return o1.end - o2.end;
                return o1.start - o2.start;
            }
        });

        PriorityQueue<Line> pq = new PriorityQueue<>((l1, l2) -> l2.start - l1.start);
        pq.offer(lines[0]);
        for (int i = 1; i < n; i++) {
            Line line = lines[i];
            Line peek = pq.peek();
            if (line.start >= peek.start && line.start <= peek.end) { // 시작점이 이미 그어진 선과 겹치는 경우
                pq.poll();
                pq.offer(new Line(peek.start, Math.max(peek.end, line.end))); // 종료점을 새로 갱신해서 넣기
            } else {
                pq.offer(line);
            }
        }
        int total = 0;
        while (!pq.isEmpty()) {
            Line line = pq.poll();
            total += line.end - line.start;
        }
        System.out.println(total);
    }
    static class Line {
        int start, end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}