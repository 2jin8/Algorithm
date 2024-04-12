import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // x를 기준으로 오름차순 정렬, x가 같다면 y를 기준으로 오름차순 정렬
        PriorityQueue<Line> pq = new PriorityQueue<>(new Comparator<Line>() {
            @Override
            public int compare(Line o1, Line o2) {
                if (o1.x == o2.x) return o1.y - o2.y;
                return o1.x - o2.x;
            }
        });

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            pq.offer(new Line(x, y));
        }

        Line line = pq.poll();
        int start = line.x, end = line.y, total = 0;
        while (!pq.isEmpty()) {
            line = pq.poll();
            if (line.x >= start && line.x <= end) { // 그으려는 선이 이미 그어진 선과 겹치는 경우라면
                end = Math.max(line.y, end); // 두 y값 중 더 큰 값으로 갱신
            } else { // 그렇지 않다면
                total += end - start; // 이때까지 그은 선의 길이 세기
                start = line.x; // 새로운 start, end 값으로 갱신하기
                end = line.y;
            }
        }
        total += end - start;
        System.out.println(total);
    }

    static class Line {
        int x, y;

        public Line(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}