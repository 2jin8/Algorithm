import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] people = new int[9];
		int[] check = new int[9];		
		
		int sum = 0, num = 0;
		
		for (int i=0; i<9; i++) {
			people[i] = Integer.parseInt(br.readLine());
			sum += people[i];
		}

		Arrays.sort(people);
		
		Loop: for (int i=0; i<9; i++) {
			int tmp = sum;
			tmp -= people[i];
			check[i] = 1;
			for (int j=0; j<9; j++) {
				if (i == j) continue; 
				else if (tmp - people[j] == 100) {
					check[j] = 1;
					break Loop;
				}
				
			}
			check[i] = 0;
		}
		
		int[] result = new int[7];
		int k=0;
		
		for (int i=0; i<9; i++) {
			if (check[i] == 0)
				System.out.println(people[i]);
		}

	}
}