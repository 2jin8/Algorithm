import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 2. 퇴장 시간 >= 총회 끝낸 시간 && 퇴장 시간 <= 스트리밍 끝낸 시간 && 입장한 사람 => 세기

        // 22:00 > 2200 변경하기
        int S = changeTime(st.nextToken()); // 개강총회 시작 시간
        int E = changeTime(st.nextToken()); // 개강총회 끝낸 시간
        int Q = changeTime(st.nextToken()); // 스트리밍 끝낸 시간

        HashSet<String> enter = new HashSet<>();
        String str = "";
        int answer = 0; // 출석이 확인된 인원 수
        while ((str = br.readLine()) != null && str.length() != 0) {
            st = new StringTokenizer(str, " ");
            int time = changeTime(st.nextToken());
            String name = st.nextToken();
            // 입장 시간 확인
            if (time <= S){ // 시간 <= 시작 시간
                enter.add(name);
            }
            // 종료 시간 확인
            if (enter.contains(name) && time >= E && time <= Q) { // 입장 & 시간 >= 총회 끝난 시간 & 시간 <= 스트리밍 끝낸 시간
                answer++;
                enter.remove(name);
            }
        }
        System.out.println(answer);

    }
    public static int changeTime(String time) {
        StringTokenizer st = new StringTokenizer(time, ":");
        int hour = Integer.parseInt(st.nextToken()) * 100;
        int min = Integer.parseInt(st.nextToken());

        return hour + min;
    }
}