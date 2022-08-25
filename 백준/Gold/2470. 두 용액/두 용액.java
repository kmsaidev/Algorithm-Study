import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] nums;
    static int[] answer;
    static int minDiff = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        nums = new int[N];
        answer = new int[2];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            nums[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(nums);
        solution(0, N - 1);
        Arrays.sort(answer);
        System.out.print(answer[0] + " " + answer[1]);
    }
    static void solution(int left, int right){
        while(left < right){
            int diff = nums[left] + nums[right];
            if(minDiff > Math.abs(diff)){
                minDiff = Math.abs(diff);
                answer[0] = nums[left];
                answer[1] = nums[right];
            }
            if(diff == 0)
                break;
            else if(diff > 0)
                right--;
            else left++;
        }
    }
}
