import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int N, d, k, c; // d: 초밥 가짓수, k: 연속해서 먹는 접시의 수, c: 쿠폰 번호
	static int[] arr, visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		arr = new int[N];
		visited = new int[d + 1];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		getMaxKind();
	}

	static void getMaxKind() {
		ArrayList<Integer> list = new ArrayList<>();

		// 초기값 넣기
		int kind = 1;
		list.add(c);
		visited[c]++;
		for (int i = 0; i < k; i++) {
			if (visited[arr[i]] == 0) {
				kind++;
			}
			visited[arr[i]]++;
			list.add(arr[i]);
		}

		int end = k - 1;
		int maxKind = kind;
		// 각 위치를 기준으로 연속한 k개의 접시 확인하기
		for (int i = 1; i < N; i++) {
			end++;
			if (end == N)
				end = 0;

			int remove = list.remove(1);
			visited[remove]--;
			// 제거한 값이 더 이상 list에 있지 않다면 1 감소
			if (visited[remove] == 0)
				kind--;

			list.add(arr[end]);
			visited[arr[end]]++;
			// 추가하려는 값이 처음으로 등록되는 것이면 1 증가
			if (visited[arr[end]] == 1)
				kind++;
			maxKind = Math.max(maxKind, kind);
		}
		System.out.println(maxKind);
	}
}