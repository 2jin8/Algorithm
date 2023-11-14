import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;



class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);

		for(int test_case = 1; test_case <= 10; test_case++)
		{
			int k = sc.nextInt(); // 덤프 횟수
			PriorityQueue<Integer> lowQueue = new PriorityQueue<>();
			PriorityQueue<Integer> highQueue = new PriorityQueue<>(Collections.reverseOrder());
			int[] heights = new int[100];
			for (int i=0; i<100; i++) {
				heights[i] = sc.nextInt(); 
				lowQueue.offer(heights[i]);
				highQueue.offer(heights[i]);
			}
			
			// 덤프 횟수 만큼 반복
			int diff = 0;
			for (int i=0; i<k; i++) {
				int high = highQueue.poll();
				int low = lowQueue.poll();
				diff = high - low;
				if (diff == 0 || diff == 1) { // 평탄화 작업이 완료된 경우
					break;
				}
				
				// 평탄화 작업 수행
				highQueue.offer(high - 1);
				lowQueue.offer(low + 1);
			}
			diff = highQueue.poll() - lowQueue.poll();
			System.out.println("#"+test_case+" "+diff);
		}
	}
}