import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;

class Queue {
	int front, back;
	int[] q;
	Queue(int n) {
		front = 0;
		back = 0;
		q = new int[n];
	}
	
	void push(int num) {
		q[back++] = num;
	}
	
	int pop() {
		if (!empty()) {
			int tmp = q[front++];
			return tmp;
		}
		return -1;
	}
	
	int size() {
		return back-front;
	}
	
	boolean empty() {
		return front == back;
	}

	int front() {
		if (empty()) return -1;
		return q[front];
	}

	int back() {
		if (empty()) return -1;
		return q[back - 1];
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		Queue q = new Queue(n);
		
		for (int i=0; i<n; i++) {
			String[] s = br.readLine().split(" ");
			switch(s[0]) {
			case "push":
				q.push(Integer.parseInt(s[1]));
				break;
			case "pop":
				System.out.println(q.pop());
				break;
			case "size":
				System.out.println(q.size());
				break;
			case "empty":
				if (q.empty()) System.out.println("1");
				else System.out.println("0");
				break;
			case "front":
				System.out.println(q.front());
				break;
			case "back":
				System.out.println(q.back());
				break;
			}
		}
	}
}
