import java.util.Scanner;

class Solution
{
	static int hour1, hour2, min1, min2;
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			sb.append("#").append(test_case).append(" ");
			hour1 = sc.nextInt();
			min1 = sc.nextInt();
			hour2 = sc.nextInt();
			min2 = sc.nextInt();
			
			// 분 계산
			int plusHour = 0; // 추가되는 시간
			int finMin = (min1 + min2) % 60;
			if (min1 + min2 > 59) {
				plusHour = 1;
			}
			
			// 시 계산
			int hour = (hour1 + hour2) % 12 + plusHour;
			int finHour = hour == 0? 12 : hour;
			sb.append(finHour).append(" ").append(finMin).append("\n");
		}
		System.out.println(sb.toString());
	}
}