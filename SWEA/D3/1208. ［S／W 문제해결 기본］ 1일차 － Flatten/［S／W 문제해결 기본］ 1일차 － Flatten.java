import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Solution {
	static final int N = 100;
	static int[] heights;
	static int min, minIdx, max, maxIdx;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= 10; t++) {
			int dump = Integer.parseInt(br.readLine()); // 덤프 횟수
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			heights = new int[N]; // 상자의 높이
			for (int i = 0; i < N; i++) {
				heights[i] = Integer.parseInt(st.nextToken());
			}

			// 덤프하기
			for (int d = 0; d < dump; d++) {
				Arrays.sort(heights);
				heights[0]++;
				heights[N - 1]--;
				int diff = heights[N - 1] - heights[0];
				if (diff == 0 || diff == 1) { // 주어진 덤프 횟수 이내에 평탄화가 완료된 경우
					break;
				}
			}
			Arrays.sort(heights);
			sb.append('#').append(t).append(' ').append(heights[N - 1] - heights[0]).append('\n');
		}
		System.out.println(sb.toString());
	}

	static void findMinMax() { // 최고점, 최저점 찾기
		min = heights[0];
		minIdx = 0;
		max = heights[0];
		maxIdx = 0;
		for (int i = 1; i < N; i++) {
			if (heights[i] < min) {
				min = heights[i];
				minIdx = i;
			} else if (heights[i] > max) {
				max = heights[i];
				maxIdx = i;
			}
		}
	}
}