import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		for (int tc=0; tc<10; tc++) {
			int n = sc.nextInt();
			Queue<Integer> queue = new LinkedList<Integer>();
			for (int i=0; i<8; i++) {
				queue.offer(sc.nextInt());
			}
			
			int minus = 1;
			while (true) {
				if (minus > 5) minus = 1; 
				int num = queue.poll() - minus++;
				if (num <= 0) {
					queue.offer(0);
					break;
				}
				queue.offer(num);
			}
			
			StringBuilder sBuilder = new StringBuilder("#"+n+" ");
			while (!queue.isEmpty()) {
				sBuilder.append(queue.poll()+" ");
			}
			System.out.println(sBuilder.toString());
		}
	}
}