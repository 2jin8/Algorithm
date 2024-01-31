import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        Meet[] meets = new Meet[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            meets[i] = new Meet(start, end);
        }
        // 회의 시작 시간을 기준으로 오름차순 정렬
        Arrays.sort(meets, new Comparator<Meet>() {
            @Override
            public int compare(Meet o1, Meet o2) {
                if (o1.start == o2.start) return o1.end - o2.end;
                return o1.start - o2.start;
            }
        });

        // 우선순위 큐는 회의 종료 시간을 기준으로 오름차순 정렬
        PriorityQueue<Meet> pq = new PriorityQueue<>(new Comparator<Meet>() {
            @Override
            public int compare(Meet o1, Meet o2) {
                return o1.end - o2.end;
            }
        });
        pq.offer(meets[0]);
        int total = 1;
        for (int i = 1; i < n; i++) {
            int start = meets[i].start;
            Meet meet = pq.poll();
            pq.offer(meets[i]);
            if (start < meet.end) { //  새로운 회의실을 사용해야 하는 경우
                pq.offer(meet);
                total++;
            }
        }
        System.out.println(total);
    }

    static class Meet {
        int start;
        int end;

        public Meet(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}