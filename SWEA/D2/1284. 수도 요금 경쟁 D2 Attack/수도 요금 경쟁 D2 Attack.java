import java.util.Scanner;

class Solution
{
	static int p, q, r, s, w;
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			p = sc.nextInt();
			q = sc.nextInt();
			r = sc.nextInt();
			s = sc.nextInt();
			w = sc.nextInt();
			
			int costA = w * p;
			int costB = 0;
			if (w <= r) costB = q;
			else costB = q + (w - r) * s;
			
			System.out.println("#" + test_case + " " + Math.min(costA, costB));
		}
	}
}