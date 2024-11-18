import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M = 3, K = 9, answerCnt;
	static int[] nums;
	static boolean[] used;
	static Answer[] answers;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		answers = new Answer[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String questions = st.nextToken();
			int strike = Integer.parseInt(st.nextToken());
			int ball = Integer.parseInt(st.nextToken());

			answers[i] = new Answer(questions, strike, ball);
		}

		nums = new int[M];
		used = new boolean[K + 1];
		dfs(0);
		System.out.println(answerCnt);
	}

	static int checkQuestion() {
		for (int i = 0; i < N; i++) {
			Answer answer = answers[i];
			boolean[] isStrike = new boolean[M];
			int strike = 0, ball = 0;

			// strike 계산
			for (int j = 0; j < M; j++) {
				// 숫자가 포함되고 순서가 일치함 > 스트라이크
				if (answer.questions.charAt(j) - '0' == nums[j]) {
					strike++;
					isStrike[j] = true;
				}
			}

			// ball 계산
			for (int j = 0; j < M; j++) {
				int now = answer.questions.charAt(j) - '0';
				for (int k = 0; k < M; k++) {
					// 스트라이크한 번호가 아님 & 숫자가 포함됨 > 볼
					if (!isStrike[j] && now == nums[k]) {
						ball++;
					}
				}
			}

			// 하나라도 만족하지 않으면 가능성이 있는 답이 아님
			if (strike != answer.strike || ball != answer.ball)
				return 0;
		}
		return 1;
	}

	static void dfs(int depth) {
		if (depth == M) {
			answerCnt += checkQuestion();
			return;
		}

		for (int i = 1; i <= K; i++) {
			if (used[i])
				continue;

			used[i] = true;
			nums[depth] = i;
			dfs(depth + 1);
			used[i] = false;
		}
	}

	static class Answer {
		String questions;
		int strike, ball;

		public Answer(String questions, int strike, int ball) {
			this.questions = questions;
			this.strike = strike;
			this.ball = ball;
		}
	}
}
