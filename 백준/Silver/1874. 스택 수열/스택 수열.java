
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		int[] num = new int[n];
		int max = 0, max_i=0;
		for (int i=0; i<n; i++) {
			num[i] = Integer.parseInt(br.readLine());
			if (max < num[i]) {
				max = num[i];
				max_i = i;
			}
		}

		Stack st = new Stack(n);
		
		int j = 0;
		for (int i=0; i<n; i++) {
			st.push(i+1);
			sb.append("+\n");

			
			while (j < n) {
				if (st.top() == num[j]) {
					st.pop();
					sb.append("-\n");
					j++;
				}
				else break;
			}
		}
		if (st.top == 0) System.out.println(sb);
		else System.out.println("NO");
	}

}

class Stack {
	int top = 0;
	int[] stack;
	public Stack(int n) {
		stack = new int[n];
	}
	
	void push(int num) {
		stack[top] = num;
		top++;
	}
	
	void pop() {
		top--;
		if (top >= 0)
			stack[top] = 0;
	}
	
	int top() {
		if (top <= 0) return stack[top];
		return stack[top-1];
	}
}