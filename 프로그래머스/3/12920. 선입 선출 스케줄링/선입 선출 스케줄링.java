class Solution {
    
    public int solution(int n, int[] cores) {
        
        // time: n개 이상의 작업이 시작될 수 있는 최소 시간
        // work: time까지 시작된 일의 개수
        int time = 0, work = 0; 
        int low = 1, high = 10_000*n;
        while (low <= high) {
            int mid = (low + high) / 2;
            
            // 작업처리시간이 mid일 때, 처리가능한 일의 개수
            int cnt = workCnt(cores, mid);
            if (cnt >= n) { // 처리해야 할 작업의 개수보다 많거나 같음 >> 처리시간 줄이기
                time = mid;
                work = cnt; // 현재까지 시작된 작업의 수
                high = mid - 1;
            } else { // 처리해야 할 작업의 개수보다 적음 >> 처리시간 늘리기
                low = mid + 1;
            }
        }
        
        work -= n; // n번 이후 작업 버리기
        // 2개 이상의 코어가 남을 경우 앞의 코어부터 처리 >> 뒤쪽의 코어가 더 늦게 끝남
        int answer = 0;
        for (int i=cores.length-1; i>=0; i--) {
            // time에 코어가 시작되고 이후 작업이 없는 경우
            if (time % cores[i] == 0) {
                if (work == 0) {
                    answer = i+1;
                    break;
                }
                work--;
            }
        }
        return answer;
    }
    
    // time 내에 작업이 시작된 일의 개수 세기
    public int workCnt(int[] cores, int time) {
        // 0초에 모두 시작
        int cnt = cores.length;

        // 0초 이후 작업 개수 세기
        for (int core : cores) {
            cnt += time / core;
        }
        return cnt;
    }
}
// 수행 시간을 이분 탐색으로 정하고
// 수행 시간 내에