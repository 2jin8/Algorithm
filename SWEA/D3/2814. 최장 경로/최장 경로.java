import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Solution
{
	static int n, m, max;
	static int[] count;
	static boolean[] visited;
	static ArrayList<ArrayList<Integer>> graph;
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc=1; tc<=T; tc++) {
			n = sc.nextInt();
			m = sc.nextInt();
			
			count = new int[n+1];
			visited = new boolean[n+1];
			graph = new ArrayList<>();
			for (int i=0; i<=n; i++) {
				graph.add(new ArrayList<Integer>());
			}
			
			for (int i=0; i<m; i++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				graph.get(x).add(y);
				graph.get(y).add(x);
			}
		
			max = 0;
			for (int i=1; i<=n; i++) {
				visited = new boolean[n+1];
				dfs(i, 1);
			}
			System.out.println("#"+ tc+" "+max);
		}
	}
	public static void dfs(int x, int cnt) {
		visited[x]=true;
		
		// 해당 점에서 갈 수 있는 거 없으면 종료
		for (int i=0; i<graph.get(x).size(); i++) {
			int tx = graph.get(x).get(i);
			if (!visited[tx]) {
				dfs(tx, cnt+1);
				visited[tx]=false;
			}
		}
		
		max = Math.max(max, cnt);
	}
}