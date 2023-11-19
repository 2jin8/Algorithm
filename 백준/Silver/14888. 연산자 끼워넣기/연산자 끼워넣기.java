import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main
{
	static int min, max;
	static int n;
	static int[] nums, operator;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		nums = new int[n];
		for (int i=0; i<n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		operator = new int[4];
		for (int i=0; i<4; i++) {
			operator[i] = Integer.parseInt(st.nextToken());
		}
		
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;
		dfs(nums[0], 1);
		System.out.println(max);
		System.out.println(min);
	}
	
	public static void dfs(int total, int idx) {
		if (idx == n) {
			min = Math.min(min, total);
			max = Math.max(max, total);
			return;
		}
		
		for (int i=0; i<4; i++) {
			if (operator[i] > 0) {
				operator[i]--;
				
				switch(i) {
					case 0: 
						dfs(total + nums[idx], idx+1);
						break;
					case 1: 
						dfs(total - nums[idx], idx+1);
						break;
					case 2: 
						dfs(total * nums[idx], idx+1);
						break;
					case 3:
						dfs(total / nums[idx], idx+1);
						break;
				}
				operator[i]++;
			}
		}
	}
}
