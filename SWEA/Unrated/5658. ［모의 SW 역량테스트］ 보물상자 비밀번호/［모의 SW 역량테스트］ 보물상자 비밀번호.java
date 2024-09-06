import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Solution {

	static int N, K, M;
	static HashMap<Character, Integer> pwMap = new HashMap<>();
	static HashSet<String> pwSet = new HashSet<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;

		init();
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			M = N / 4; //
			String s = br.readLine();
			s += s.substring(0, M);
			pwSet.clear();
			for (int i = 0; i < M; i++) { // 시작점
				int start = i;
				for (int j = 0; j < 4; j++) { // 4번
					pwSet.add(s.substring(start, start + M));
					start += M;
				}
			}

			ArrayList<Integer> list = new ArrayList<>();
			for (String pw : pwSet) {
				char[] chars = pw.toCharArray();
				int number = 0, mul = 0;
				for (int i = chars.length - 1; i >= 0; i--) {
					if (chars[i] - 'A' < 0) {
						number += Math.pow(16, mul++) * (chars[i] - '0');
					} else {
						number += Math.pow(16, mul++) * (pwMap.get(chars[i]));
					}
				}
				list.add(number);
			}
			Collections.sort(list, (i1, i2) -> Integer.compare(i2, i1));
			sb.append("#").append(t).append(" ").append(list.get(K - 1)).append("\n");
		}
		System.out.println(sb);
	}

	static void init() {
		pwMap.put('A', 10);
		pwMap.put('B', 11);
		pwMap.put('C', 12);
		pwMap.put('D', 13);
		pwMap.put('E', 14);
		pwMap.put('F', 15);
	}
}