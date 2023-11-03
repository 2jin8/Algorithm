import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[][] lectures = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            lectures[i][0] = Integer.parseInt(st.nextToken());
            lectures[i][1] = Integer.parseInt(st.nextToken());
        }

        // 시작 시간 기준 정렬(같으면 종료 시간 기준 정렬)
        Arrays.sort(lectures, ((o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]));

        PriorityQueue<Integer> endTime = new PriorityQueue<>();
        endTime.offer(lectures[0][1]);
        for (int i = 1; i < n; i++) {
            int end = endTime.peek(); // 가장 빨리 끝나는 시간이랑만 비교하면 됨
            if (end <= lectures[i][0]) {
                endTime.poll();
            }
            endTime.offer(lectures[i][1]);
        }
        System.out.println(endTime.size());
    }
}