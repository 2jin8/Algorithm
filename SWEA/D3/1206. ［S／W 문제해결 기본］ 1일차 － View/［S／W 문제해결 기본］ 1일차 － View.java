import java.util.Scanner;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);

		for(int test_case = 1; test_case <= 10; test_case++)
		{
			int n = sc.nextInt();
			int[] height = new int[n];
			for (int i=0; i<n; i++) {
				height[i] = sc.nextInt();
			}
			
			int total = 0; // 조망권이 확보된 세대의 수
			for (int i=2; i<n-2; i++) {
				int now = height[i]; // 현재 빌딩의 높이
				int left = -1, right = -1;
				if (now > height[i-1] && now > height[i-2]) {
					left = Math.min(now - height[i-1], now - height[i-2]);
				}
				if (now > height[i+1] && now > height[i+2]) {
					right = Math.min(now - height[i+1], now - height[i+2]);
				}
				int cnt = Math.min(left, right);
				if (cnt != -1) total += cnt;
			}
			System.out.println("#" + test_case + " " + total);
		}
	}
}