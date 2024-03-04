import java.io.*;
import java.util.*;

public class Main {
    static int N, max = 0;
    static Schedule[] schedules;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        schedules = new Schedule[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int time = Integer.parseInt(st.nextToken());
            int pay = Integer.parseInt(st.nextToken());
            schedules[i] = new Schedule(time, pay);
        }
        for (int i = 0; i < N; i++) {
            dfs(i, schedules[i].pay); // 0일부터 시작
        }
        System.out.println(max);
    }

    public static void dfs(int day, int total) {
        if (day + schedules[day].time <= N) { // 회사에 있을 때 상담이 가능한 경우 
            max = Math.max(max, total); // 최댓값 갱신
        }
        for (int i = schedules[day].time; day + i < N; i++) { 
            dfs(day + i, total + schedules[day + i].pay);
        }
    }

    static class Schedule {
        int time;
        int pay;

        public Schedule(int time, int pay) {
            this.time = time;
            this.pay = pay;
        }
    }
}