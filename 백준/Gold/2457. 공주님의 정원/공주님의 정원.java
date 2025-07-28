import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.concurrent.Flow;

public class Main {

    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Flower[] flowers = new Flower[N];
        StringTokenizer st = null;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int startM = Integer.parseInt(st.nextToken());
            int startD = Integer.parseInt(st.nextToken());
            int start = startM * 100 + startD;

            int endM = Integer.parseInt(st.nextToken());
            int endD = Integer.parseInt(st.nextToken());
            int end = endM * 100 + endD;
            flowers[i] = new Flower(start, end);
        }
        Arrays.sort(flowers, (f1, f2) -> {
            if (f1.start == f2.start)
                return Integer.compare(f1.end, f2.end);
            return Integer.compare(f1.start, f2.start);
        });

        int start = 301, end = 1201;
        int flowerCount = 0, idx = 0, maxEnd = 0;
        while (start < end) {
            boolean isFind = false;
            for (int i = idx; i < N; i++) {
                // 시작일보다 늦게 피면 의미 없음
                Flower flower = flowers[i];
                if (flower.start > start)
                    break;

                // 가장 늦게까지 피어있는 꽃 찾기
                if (flower.end > maxEnd) {
                    maxEnd = flower.end;
                    idx = i + 1; // 현재 꽃 다음부터 찾도록 인덱스 기록
                    isFind = true;
                }
            }

            // 선택할 꽃을 찾은 경우
            if (isFind) {
                start = maxEnd;
                flowerCount++;
            } else { // 선택할 꽃을 찾기 못하면 종료
                break;
            }
        }
        // 제일 늦게까지 피는 꽃이 12월 1일까지 피어있지 않으면 0 출력
        System.out.println(maxEnd < end ? 0 : flowerCount);
    }

    static class Flower {
        int start, end;

        public Flower(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
