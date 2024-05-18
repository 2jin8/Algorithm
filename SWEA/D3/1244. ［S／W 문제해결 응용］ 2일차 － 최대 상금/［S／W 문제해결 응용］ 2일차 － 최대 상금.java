import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Base64;
import java.util.HashSet;
import java.util.StringTokenizer;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

class Solution {
	static int[] numbers;
	static int moveCnt, result;
	static HashSet<String> hashSet;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			String str = st.nextToken();
			moveCnt = Integer.parseInt(st.nextToken()); // 교환 횟수
			numbers = new int[str.length()];
			for (int i = 0; i < str.length(); i++) {
				numbers[i] = str.charAt(i) - '0';
			}

			// 카드의 개수만큼 교환할 수 있다면, 모든 조합을 만들 수 있음
//			if (moveCnt > str.length()) moveCnt = str.length();
			hashSet = new HashSet<>();
			result = 0;
			dfs(0);
			sb.append('#').append(t).append(' ').append(result).append('\n');
		}
		System.out.println(sb.toString());
	}

	public static void dfs(int move) {
		if (move == moveCnt) {
			result = Math.max(result, getTotal());
			return;
		}

		for (int i = 0; i < numbers.length - 1; i++) {
			for (int j = i + 1; j < numbers.length; j++) {
				swap(i, j);
				String check = getTotal() + ":" + move; // 획득할 수 있는 상금 : 교환 횟수
				if (!hashSet.contains(check)) { // "상금 : 교환 횟수"를 확인하지 않은 경우에만 계속 dfs 탐색
					hashSet.add(check);
					dfs(move + 1);
				}
				swap(i, j);
			}
		}
	}

	public static int getTotal() {
		int total = 0;
		for (int number : numbers) {
			total = total * 10 + number;
		}
		return total;
	}

	public static void swap(int a, int b) {
		int tmp = numbers[a];
		numbers[a] = numbers[b];
		numbers[b] = tmp;
	}
}