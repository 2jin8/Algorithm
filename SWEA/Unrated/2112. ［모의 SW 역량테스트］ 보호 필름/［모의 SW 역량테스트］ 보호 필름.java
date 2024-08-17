import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	static int D, W, K, minCnt; // D: 깊이, W: 너비, K: 합격 기준, minCnt: 최소 약품 투입 횟수
	static int[][] filmInfo; // 보호 필름 단면의 정보

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			filmInfo = new int[D][W];
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					filmInfo[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			minCnt = Integer.MAX_VALUE;
			// 합격 기준이 1이면 약품을 투입하지 않아도 됨
			if (K == 1) minCnt = 0;
			else dfs(0, 0);
			sb.append("#").append(t).append(" ").append(minCnt).append("\n");
		}
		System.out.println(sb);
	}

	private static void dfs(int depth, int cnt) {
		// 현재까지의 투입 횟수가 최소 투입 횟수보다 크거나 같다면 답이 아니므로 종료하기
		if (cnt >= minCnt) {
			return;
		}
		
		if (depth == D) { // 보호 필름 두께만큼 다 탐색한 경우
			// 검사를 통과한 경우
			if (checkFilm()) {
				minCnt = cnt; // 최소 투입 횟수 갱신
			}
			return;
		}

		// 약품 투입 X
		dfs(depth + 1, cnt);

		// 기존 상태 기록하기
		int[] tmp = filmInfo[depth].clone();

		// A 약품 투입
		Arrays.fill(filmInfo[depth], 0);
		dfs(depth + 1, cnt + 1);

		// B 약품 투입
		Arrays.fill(filmInfo[depth], 1);
		dfs(depth + 1, cnt + 1);

		// 기존 상태로 되돌리기
		filmInfo[depth] = tmp.clone();
	}

	private static boolean checkFilm() {
		for (int j = 0; j < W; j++) {
			int now = filmInfo[0][j], cnt = 1;
			for (int i = 1; i < D; i++) {
				if (filmInfo[i][j] == now) {
					cnt++;
				} else {
					now = filmInfo[i][j];
					cnt = 1;
				}
				
				if (cnt == K) break;
			}
			if (cnt < K) return false;
		}
		return true;
	}
}