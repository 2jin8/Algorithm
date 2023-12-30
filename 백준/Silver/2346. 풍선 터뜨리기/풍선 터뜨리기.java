import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        // 풍선 위치, 이동 횟수 설정
        Deque<Balloon> balloons = new ArrayDeque<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            balloons.offer(new Balloon(i, Integer.parseInt(st.nextToken())));
        }

        StringBuilder sb = new StringBuilder();
        while (balloons.size() > 1) {
            Balloon balloon = balloons.pollFirst();
            sb.append(balloon.idx).append(" ");
            int move = balloon.move;
            if (move > 0) { // 오른쪽으로 움직이는 경우
                move--;
                for (int i = 0; i < move; i++) { // 앞에서 빼서 뒤에 넣기
                    balloons.offerLast(balloons.pollFirst());
                }
            } else { // 왼쪽으로 움직이는 경우
                for (int i = 0; i < Math.abs(move); i++) { // 뒤에서 빼서 앞에 넣기
                    balloons.offerFirst(balloons.pollLast());
                }
            }
        }
        sb.append(balloons.pollFirst().idx);
        System.out.println(sb);
    }
}

class Balloon {
    int idx; // 풍선의 위치
    int move; // 이동 횟수

    public Balloon(int idx, int move) {
        this.idx = idx;
        this.move = move;
    }
}