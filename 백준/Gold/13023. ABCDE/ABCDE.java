import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static boolean exist;
	static boolean[] visited;
	static ArrayList<Integer>[] graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			graph[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			// 양방향 연결
			graph[a].add(b);
			graph[b].add(a);
		}

		for (int i = 0; i < N; i++) {
			// 조건에 맞는게 존재하면 더 이상 탐색할 필요 X
			if (exist) break;
			visited = new boolean[N];
			dfs(0, i);
		}
		
		// 조건에 맞는게 존재하면 1 출력 
		if (exist) System.out.println(1);
		else System.out.println(0);
	}

	static void dfs(int depth, int now) {
		// 조건에 맞는게 존재하면 더 이상 탐색할 필요 X
		if (exist) return;

		// 깊이가 5면 조건에 맞는 것
		if (depth == 5) {
			exist = true;
			return;
		}

		for (int next : graph[now]) {
			if (!visited[next]) {
				visited[next] = true;
				dfs(depth + 1, next);
				visited[next] = false;
			}
		}
	}
}