import java.util.Scanner;

class Solution
{
	static int[] moneys = {50000, 10000, 5000, 1000, 500, 100, 50, 10};
	static int[] count;
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int money = sc.nextInt();
			count = new int[8];
			for (int i=0; i<moneys.length; i++) {
				if (money < moneys[i]) continue; 
				if (money == 0) break;
				count[i] = money / moneys[i];
				money %= moneys[i];
			}
			
			System.out.println("#"+test_case);
			StringBuilder sBuilder = new StringBuilder();
			for (int c: count) {
				sBuilder.append(c+" ");
			}
			System.out.println(sBuilder.toString());
		}	
	}
}