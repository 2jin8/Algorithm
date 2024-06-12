import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int[] heights = new int[W];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < W; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }

        int totalRain = 0;
        for (int i = 1; i < W - 1; i++) { // 양 끝 블록에는 물이 고일 수 없음 (벽 역할을 해줘야 함)
            int leftHeight = 0, rightHeight = 0; // 현재 블록을 기준으로, 왼쪽/오른쪽에 위치하는 블록의 최고 높이
            // 왼쪽 찾기
            for (int j = 0; j < i; j++) {
                leftHeight = Math.max(leftHeight, heights[j]);
            }

            // 오른쪽 찾기
            for (int j = i + 1; j < W; j++) {
                rightHeight = Math.max(rightHeight, heights[j]);
            }

            // 현재 블록의 높이가 왼쪽/오른쪽에 위치하는 블록의 최고 높이보다 낮다면
            if (heights[i] < leftHeight && heights[i] < rightHeight) { // 빗물이 고일 수 있음
                totalRain += Math.min(leftHeight, rightHeight) - heights[i];
            }
        }
        System.out.println(totalRain);
    }
}