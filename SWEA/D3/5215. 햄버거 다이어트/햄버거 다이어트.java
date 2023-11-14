import java.util.Scanner;

class Solution
{
	static int n, l, max;
	static int[] tastes, kcals;
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T=sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++)
		{
			n = sc.nextInt();
			l = sc.nextInt();
			tastes = new int[n];
			kcals = new int[n];
			for (int i=0; i<n; i++) {
				tastes[i] = sc.nextInt();
				kcals[i] = sc.nextInt(); 
			}
			max = 0;
			dfs(0, 0, 0);
			System.out.println("#"+test_case+" "+max);
		}
	}
	public static void dfs(int cnt, int taste, int kcal) {
		if (kcal > l) // 칼로리를 초과하면 종료
			return;
		if (cnt == n) { // cnt가 n이면 탐색 완료
			max = Math.max(max, taste);
			return;
		}
		
		dfs(cnt+1, taste + tastes[cnt], kcal + kcals[cnt]); // 선택 O
		dfs(cnt+1, taste, kcal); // 선택 x
	}
}