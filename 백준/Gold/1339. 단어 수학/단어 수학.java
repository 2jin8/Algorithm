import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static int[] alphabet = new int[26];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			char[] words = br.readLine().toCharArray();
			int mul = 1;
			// 자리에 따른 값 매기기
			for (int j = words.length - 1; j >= 0; j--) {
				alphabet[words[j] - 'A'] += mul;
				mul *= 10;
			}
		}

		Arrays.sort(alphabet);
		int sum = 0, mul = 9;
		// 값이 큰 것부터 확인하기
		for (int i = alphabet.length - 1; i >= 0; i--) {
			if (alphabet[i] == 0)
				break;

			sum += alphabet[i] * mul--;
		}
		System.out.println(sum);
	}
}
