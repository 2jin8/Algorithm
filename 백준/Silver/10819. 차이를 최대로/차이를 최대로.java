import java.util.Scanner;

class Main{
    static int sum,n;
    static int[] visited,arr,selected;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        arr = new int[n];
        visited = new int[n];
        selected = new int[n];
        sum = 0;

        for(int i = 0; i < n ; i ++){
            arr[i] = sc.nextInt();
        }
        
        
        permu(0);

        System.out.println(sum);
        

        sc.close();
    }

    static void permu(int idx){
        if(idx == n){
            //합 구하기
            int temp = 0;
            for(int i = 1; i< n; i++){
                temp += Math.abs(selected[i-1]- selected[i]);
            }
            sum = Math.max(sum, temp);
        }

        for(int i = 0; i < n; i++){
            if(visited[i] == 0){
                visited[i] = 1;
                selected[idx] = arr[i];
                permu(idx+1);
                visited[i] = 0;
            }
        }
        
    }
}