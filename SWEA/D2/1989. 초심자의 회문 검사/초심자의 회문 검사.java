import java.util.Scanner;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T = Integer.parseInt(sc.nextLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
			String[] strings = sc.nextLine().split("");
			int i = 0, j = strings.length-1;
			int ans = 1;
			while (i < j) {
				if (!strings[i++].equals(strings[j--])) {
					ans = 0;
					break;
				}
			}
			System.out.println("#"+test_case+" "+ans);
		}
	}
}