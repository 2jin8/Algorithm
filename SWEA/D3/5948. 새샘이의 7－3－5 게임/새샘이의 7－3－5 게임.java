import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class Solution
{
	static final int N = 7;
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			sb.append("#").append(test_case).append(" ");
			int[] ary = new int[N];
			for (int i=0; i<N; i++) {
				ary[i] = sc.nextInt();
			}
			
			Set<Integer> set = new HashSet<>();
			for (int i=0; i<N-2; i++) {
				for (int j=i+1; j<N-1; j++) {
					int ijSum = ary[i] + ary[j];
					for (int k=j+1; k<N; k++) {
						set.add(ijSum + ary[k]);
					}
				}
			}
			
			int[] reAry = new int[set.size()];
			int idx = 0;
			for (int s : set) {
				reAry[idx++] = s;
			}
			Arrays.sort(reAry); 
			sb.append(reAry[set.size()-5]).append("\n");
		}
		System.out.println(sb.toString());
	}	
}
