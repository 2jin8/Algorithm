import java.io.*;
import java.util.*;

class Solution {
	static final int N = 282;
	static HashMap<Integer, Point> nums = new HashMap<>();
	static HashMap<String, Integer> points = new HashMap<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		init();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			Point P = nums.get(Integer.parseInt(st.nextToken()));
			Point Q = nums.get(Integer.parseInt(st.nextToken()));
			int x = P.x + Q.x;
			int y = P.y + Q.y;
			int result = points.get(x+","+y);
			System.out.println("#"+tc+" "+result);
		}
	}
	
	public static void init() {
		int a = 1, b = 2;
		for (int i=1; i<N; i++) {
			int c = a;
			int d = b;
			for (int j=1; j<N; j++) {
				points.put(i+","+j, c);
				nums.put(c, new Point(i, j));
				c += d++;
			}
			a += i;
			b++;
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