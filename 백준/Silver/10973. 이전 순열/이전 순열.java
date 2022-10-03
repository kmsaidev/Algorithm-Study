import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] nums;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        nums = new int[N];
        for(int i = 0; i < N; i++)
            nums[i] = Integer.parseInt(st.nextToken());

        solution();
    }
    static void solution() {
        int rear_key = 0, front_key = -1;
        for(int i = N - 2; i >= 0; i--){
            for(int j = i + 1; j < N; j++){
                if(nums[i] > nums[j]){
                    rear_key = j;
                    front_key = i;
                }
            }
            if(front_key != -1){
                swap(front_key, rear_key);
                int start = i + 1;
                int end = N - 1;
                while(start < end){
                    swap(start, end);
                    start++;
                    end--;
                }
                break;
            }
        }
        if(front_key == -1)
            System.out.println("-1");
        else {
            for (int i = 0; i < N; i++)
                System.out.print(nums[i] + " ");
            System.out.println();
        }
    }

    static void swap(int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
