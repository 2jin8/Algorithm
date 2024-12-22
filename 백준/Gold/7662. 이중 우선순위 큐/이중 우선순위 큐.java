import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

	static TreeMap<Integer, Integer> map = new TreeMap<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			int n = Integer.parseInt(br.readLine());
            map.clear();
            
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				String cmd = st.nextToken();
				int k = Integer.parseInt(st.nextToken());
				
				if (cmd.equals("I")) { // 데이터 삽입 연산
					map.put(k, map.getOrDefault(k, 0) + 1);
				} else if (cmd.equals("D")) { // 데이터 삭제 연산
					// k가 1이면 최댓값 삭제, -1이면 최솟값 삭제
					if (map.size() == 0)
						continue;

					int num = (k == -1 ? map.firstKey() : map.lastKey());

					// map에서 remove 개수 하나 감소
					// 하나만 남았었더라면(현재 0이 되면) map에서 제거하기
					// map.put(.., ..) 결과 = 이전 값 반환
					if (map.put(num, map.get(num) - 1) == 1)
						map.remove(num);

					// 이거랑 같은 것!
//					map.put(num,  map.get(num) - 1);
//					if (map.get(num) == 0) {
//						map.remove(num);
//					}
				}
			}
			sb.append(map.size() == 0 ? "EMPTY" : map.lastKey() + " " + map.firstKey()).append("\n");
		}
		System.out.println(sb);
	}

	static class Number {
		int order, k;

		public Number(int order, int k) {
			this.order = order;
			this.k = k;
		}
	}
}