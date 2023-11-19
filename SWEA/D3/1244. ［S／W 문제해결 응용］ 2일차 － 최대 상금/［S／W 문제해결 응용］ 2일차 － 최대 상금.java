import java.util.Scanner;

class Solution
{
	static int max, chance;
	static String[] list;
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int T=sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++)
		{
			sb.append("#").append(test_case).append(" ");
			list = String.valueOf(sc.nextInt()).split("");
			chance = sc.nextInt();
			
			if (list.length < chance) { // swap 횟수 > 자릿수
				chance = list.length; // 자릿수만큼만 옮겨도 전부 옮길 수 있음
			}
			max = 0;
			dfs(0, 0);
			sb.append(max).append("\n");
		}	
		System.out.println(sb.toString());
	}	
	
	public static void dfs(int start, int cnt) {
		if (chance == cnt) {
			StringBuilder sb = new StringBuilder();
			for (String s: list) {
				sb.append(s);
			}
			max = Math.max(max, Integer.parseInt(sb.toString()));
			return;
		}
		
		for (int i=start; i<list.length; i++) {
			for (int j=i+1; j<list.length; j++) {
				swap(i, j);
				dfs(i, cnt+1);
				swap(i, j);
			}
		}
	}
	
	public static void swap(int i, int j) {
		String tmp = list[i];
		list[i] = list[j];
		list[j] = tmp;
	}
}