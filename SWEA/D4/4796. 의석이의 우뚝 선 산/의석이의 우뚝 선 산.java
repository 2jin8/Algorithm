import java.util.ArrayList;
import java.util.Scanner;

public class Solution {

	static int N;
	static int[] height;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		StringBuilder sb = new StringBuilder();

		for (int t = 1; t <= T; t++) {
			N = sc.nextInt();
			height = new int[N];

			for (int i = 0; i < N; i++) {
				height[i] = sc.nextInt();
			}

			int total = findTop();
			sb.append("#").append(t).append(" ").append(total).append("\n");
		}
		System.out.println(sb);
	}

	static int findTop() {
		ArrayList<Integer> topList = new ArrayList<>();
		for (int i = 1; i < N - 1; i++) {
			// 앞과 뒤보다 값이 크면 봉우리
			if (height[i] > height[i - 1] && height[i] > height[i + 1]) {
				topList.add(i);
			}
		}

		int total = 0;
		for (int i = 0; i < topList.size(); i++) {
			int left = 0, right = 0;
			int top = topList.get(i);
			for (int j = top; j > 0; j--) {
				if (height[j] <= height[j - 1]) // 봉우리까지 값이 증가하지 않는 경우
					break;

				left++;
			}

			for (int j = top; j < N - 1; j++) {
				if (height[j] <= height[j + 1]) // 감소하지 않는 경우
					break;

				right++;
			}
			total += left * right;
		}
		return total;
	}
}