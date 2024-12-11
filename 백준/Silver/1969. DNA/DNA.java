import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[][] count;
	static HashMap<Character, Integer> charToInt = new HashMap<>();
	static HashMap<Integer, Character> intToChar = new HashMap<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		count = new int[M][4]; // A, C, G, T

		init();
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				int idx = charToInt.get(str.charAt(j));
				count[j][idx]++;
			}
		}

		StringBuilder sb = new StringBuilder();
		int sum = 0;
		for (int i = 0; i < M; i++) {
			int max = count[i][0], maxIdx = 0;
			for (int j = 1; j < 4; j++) { 
				if (max < count[i][j]) { // 같으면 사전순으로 기록해야 하므로 등호 X
					max = count[i][j];
					maxIdx = j;
				}
			}
			sum += N - count[i][maxIdx]; // i번째 자리의 Hamming Distance
			sb.append(intToChar.get(maxIdx));
		}
		System.out.println(sb);
		System.out.println(sum);
	}

	static void init() {
		charToInt.put('A', 0);
		charToInt.put('C', 1);
		charToInt.put('G', 2);
		charToInt.put('T', 3);

		intToChar.put(0, 'A');
		intToChar.put(1, 'C');
		intToChar.put(2, 'G');
		intToChar.put(3, 'T');
	}
}
