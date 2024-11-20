import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int K;
	static String[] words;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 0; t < T; t++) {
			K = Integer.parseInt(br.readLine());
			words = new String[K];
			for (int i = 0; i < K; i++) {
				words[i] = br.readLine();
			}
			sb.append(makeWord()).append("\n");
		}
		System.out.println(sb);
	}

	static String makeWord() {
		// 두 문자 합치기
		// 붙이는 순서에 따라 문자열이 달라짐 => 순열 느낌
		for (int i = 0; i < K; i++) {
			for (int j = 0; j < K; j++) {
				if (i == j) continue;
				String word = words[i] + words[j];
				// 두 단어 같은지 확인하기
				if (isEqual(word))
					return word;
			}
		}
		return "0";
	}

	static boolean isEqual(String word) {
		int len = word.length();
		for (int i = 0; i < len; i++) {
			char c1 = word.charAt(i), c2 = word.charAt(len - i - 1);
			if (c1 != c2)
				return false;
		}
		return true;
	}
}
