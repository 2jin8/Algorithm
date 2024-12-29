import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M; // 적어도 M미터를 가져가야 함
	static int[] trees;

	// 절단기에 설정할 수 있는 높이의 최댓값을 구해야 함
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		trees = new int[N];
		st = new StringTokenizer(br.readLine());
		int maxTree = 0;
		for (int i = 0; i < N; i++) {
			trees[i] = Integer.parseInt(st.nextToken());
			maxTree = Math.max(maxTree, trees[i]);
		}
		System.out.println(findMaxCuttingHeight(maxTree));
	}

	static int findMaxCuttingHeight(int maxTree) {
		int left = 1, right = maxTree;
		int ans = 0;

		while (left <= right) {
			int mid = (left + right) / 2;

			// M미터 이상의 나무를 가져갈 수 있으면 높이를 증가시키기
			// 우측으로 이동
			if (check(mid)) {
				ans = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return ans;
	}

	static boolean check(int height) {
		long total = 0;
		for (int tree : trees) {
			if (tree > height)
				total += tree - height;
		}
		return total >= M;
	}
}