import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Flower[] flowers = new Flower[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int startM = Integer.parseInt(st.nextToken());
            int startD = Integer.parseInt(st.nextToken());
            int endM = Integer.parseInt(st.nextToken());
            int endD = Integer.parseInt(st.nextToken());
            int start = startM * 100 + startD;
            int end = endM * 100 + endD;
            flowers[i] = new Flower(start, end);
        }
        Arrays.sort(flowers, (f1, f2) -> {
            if (f1.start == f2.start)
                return f2.end - f1.end; // 피는 날이 같다면 지는 날을 기준으로 내림차순 정렬
            return f1.start - f2.start; // 피는 날을 기준으로 오름차순 정렬
        });

        int startDay = 301, endDay = 1201;
        int idx = 0, totalFlower = 0, maxEnd = 0;
        while (startDay < endDay) { // 12월 1일 이후는 살펴볼 필요가 없음
            boolean findFlower = false;

            for (int i = idx; i < n; i++) {
                // 현재 선택한 꽃이 지는 날보다 다음에 선택할 꽃이 피는 날이 늦는 경우
                if (startDay < flowers[i].start) break; // 매일 꽃이 피어있을 수 없음

                // 가장 늦게까지 피어있는 꽃 찾기
                if (flowers[i].end > maxEnd) {
                    maxEnd = flowers[i].end;
                    idx = i + 1; // 현재 선택한 꽃 이후부터 탐색하도록
                    findFlower = true;
                }
            }

            if (findFlower) { // 선택할 꽃을 찾았다면 값 갱신하기
                startDay = maxEnd;
                totalFlower++;
            } else break; // 그렇지 않다면 종료하기
        }
        // 11월 30일까지 꽃이 피어있지 못하는 경우(지는 날이 12월 1일이어야 11월 30일까지 꽃이 피어있음)
        if (maxEnd < endDay) System.out.println(0);
        else System.out.println(totalFlower);
    }

    static class Flower {
        int start, end; // 피는 시간, 지는 시간

        public Flower(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}