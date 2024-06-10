import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 회의 종료 시간을 기준으로 오름차순 정렬!!!
        int n = Integer.parseInt(br.readLine()); // 회의의 수
        PriorityQueue<Meeting> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            pq.offer(new Meeting(start, end));
        }

        Meeting firstMeeting = pq.poll();
        int end = firstMeeting.end;
        int meetingCnt = 1;
        while (!pq.isEmpty()) {
            Meeting meeting = pq.poll();
            // 현재 회의 종료 시간보다 다음 회의 시작 시간이 크거나 같으면 회의실 사용 가능
            if (meeting.start >= end) {
                meetingCnt++;
                end = meeting.end;
            }
        }
        System.out.println(meetingCnt);
    }

    static class Meeting implements Comparable<Meeting> {
        int start, end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Meeting meeting) {
            // 미팅의 종료 시간을 기준으로 오름차순 정렬
            // 종료 시간이 같다면 시작 시간을 기준으로 오름차순 정렬
            if (meeting.end == this.end) return this.start - meeting.start;
            return this.end - meeting.end;
        }
    }
}