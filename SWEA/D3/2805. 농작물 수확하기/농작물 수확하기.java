import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Solution
{
	static int n, total;
	static int[][] d1 = {{1,-1}, {1,0}, {1,1}};
	static int[][] d2 = {{-1,-1}, {-1,0}, {-1,1}};
	static int[][] map;
	static boolean[][] visited;
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T = Integer.parseInt(sc.nextLine());
		for(int tc = 1; tc <= T; tc++)
		{
			n = Integer.parseInt(sc.nextLine());
			map = new int[n][n];
			visited = new boolean[n][n];
			for (int i=0; i<n; i++) {
				String[] strings = sc.nextLine().split("");
				for (int j=0; j<n; j++) {
					map[i][j] = Integer.parseInt(strings[j]); 
				}
			}
			total = 0;
			if (n == 1) {
				total = map[0][0];
			} else {
				bfs(0, n/2, d1);
				bfs(n-1, n/2, d2);	
			}
			System.out.println("#"+tc+" "+total);
		}
	}
	
	public static void bfs(int x, int y, int[][] d) {
		Queue<Point> queue = new LinkedList<Point>();
		visited[x][y] = true;
		queue.offer(new Point(x, y));
		
		while (!queue.isEmpty()) {
			Point point = queue.poll();
			x = point.x;
			y = point.y;
			total += map[x][y];

			if (x == n / 2) continue; 
			for (int i=0; i<3; i++) {
				int dx = x + d[i][0];
				int dy = y + d[i][1];
				
				if (dx < 0 || dy < 0 || dx >= n || dy >= n) continue;
				
				if (!visited[dx][dy]) {
					queue.offer(new Point(dx,  dy));
					visited[dx][dy] = true; 
				}
			}
		}
	}
}

class Point {
	int x;
	int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}