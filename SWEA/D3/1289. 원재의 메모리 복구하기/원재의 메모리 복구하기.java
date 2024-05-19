import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Solution {
	static int n;
	static int[] original, reset;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			String[] strings = br.readLine().split("");
			n = strings.length;
			original = new int[n]; // 원래 상태
			reset = new int[n]; // 초기화 상태
			for (int i = 0; i < n; i++) {
				original[i] = Integer.parseInt(strings[i]);
			}

			int modifyCnt = 0;
			for (int i = 0; i < n; i++) { // 하나씩 비교하기
				if (original[i] != reset[i]) {
					modifyCnt++;
					Arrays.fill(reset, i, n, original[i]); // i ~ n-1을 original[i] 값으로 변경하기
				}
			}
			sb.append("#").append(t).append(" ").append(modifyCnt).append("\n");
		}
		System.out.println(sb.toString());
	}

}