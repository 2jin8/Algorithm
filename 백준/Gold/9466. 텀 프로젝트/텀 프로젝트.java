import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, students;
	static int[] arr;
	static boolean[] visited, completed;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		for (int t = 0; t < T; t++) {
			students = 0;
			N = Integer.parseInt(br.readLine());
			arr = new int[N + 1];
			completed = new boolean[N + 1];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				if (arr[i] == i) { // 자기 자신을 선택한 경우
					students++;
					completed[i] = true;
				}
			}

			visited = new boolean[N + 1];
			for (int i = 1; i <= N; i++) {
				if (!completed[i]) {
					Arrays.fill(visited, false);
					dfs(i);
					completed[i] = true;
				}
			}
			sb.append(N - students).append("\n");
		}
		System.out.println(sb);
	}
	
	static void dfs(int x) {
		 // 이미 탐색이 완료된 학생이라면 종료
		if (completed[x]) return;
		
		// 탐색이 완료되지 않았는데 이미 방문한 학생 == 사이클 발생
		if (visited[x]) {
			students++;
			completed[x] = true;
			// 팀에 구성되는 사람(탐색 완료 X & 방문 완료 O)들을 계속 찾아야 하므로 return하면 안됨!
		}
		
		visited[x] = true; // 방문 처리
		dfs(arr[x]); // 선택한 학생 탐색
		completed[x] = true; // 이미 방문해서 팀 구성 여부를 알았으므로 탐색 완료 처리
	}
}