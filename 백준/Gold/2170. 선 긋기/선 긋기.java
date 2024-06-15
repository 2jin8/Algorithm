import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Line[] lines = new Line[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            lines[i] = new Line(x, y);
        }
        Arrays.sort(lines, (l1, l2) -> {
            /// x를 기준으로 오름차순 정렬 (앞에서부터 선을 그릴 것이므로)
            if (l1.x == l2.x) return l1.y - l2.y;
            return l1.x - l2.x;
        });

        ArrayList<Line> lineList = new ArrayList<>();
        lineList.add(lines[0]);
        for (int i = 1; i < n; i++) {
            int nx = lines[i].x;
            int ny = lines[i].y;
            boolean isContinue = false;
            for (Line line : lineList) {
                int x = line.x, y = line.y;
                if (x <= nx && nx <= y) { // 선을 연속해서 그을 수 있는 경우
                    line.y = Math.max(line.y, ny); // 연속해서 긋기(종료 지점이 더 큰 것으로 갱신)
                    isContinue = true;
                    break;
                }
            }
            // 선을 연속해서 긋지 못하면 새로 긋기
            if (!isContinue) lineList.add(lines[i]);
        }
        long total = 0;
        for (Line line : lineList) {
            total += line.y - line.x;
        }
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