import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int MIN = 1, N = 6;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine()); // 1m2의 넓이에 자라는 참외의 개수
        int maxW = 1, maxH = 1, wIdx = -1, hIdx = -1;
        Point[] points = new Point[N];
        for (int i = 0; i < 6; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int direction = Integer.parseInt(st.nextToken()); // 이동 방향(1: 동, 2: 서, 3: 남, 4: 북)
            int length = Integer.parseInt(st.nextToken()); // 이동 길이
            points[i] = new Point(direction, length);
            if ((direction == 1 || direction == 2) && maxW < length) {
                maxW = length;
                wIdx = i;
            } else if ((direction == 3 || direction == 4) && maxH < length) {
                maxH = length;
                hIdx = i;
            }
        }

        // 포함되지 않는 사각형의 높이 구하기 = 최대 너비의 양 옆
        int h1 = (wIdx - 1 < 0) ? points[N - 1].length : points[wIdx - 1].length;
        int h2 = (wIdx + 1 >= N) ? points[0].length : points[wIdx + 1].length;

        // 포함되지 않는 사각형의 너비 구하기 = 최대 높이의 양 옆
        int w1 = (hIdx - 1 < 0) ? points[N - 1].length : points[hIdx - 1].length;
        int w2 = (hIdx + 1 >= N) ? points[0].length : points[hIdx + 1].length;
        
        int area = maxW * maxH - (Math.abs(h1 - h2) * Math.abs(w1 - w2));
        System.out.println(area * K);
    }

    static class Point {
        int direction;
        int length;

        public Point(int direction, int length) {
            this.direction = direction;
            this.length = length;
        }
    }
}