import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] nums;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            nums[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(nums);
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++){
            int target = Integer.parseInt(st.nextToken());
            if(binarySearch(0, N - 1, target))
                System.out.println("1");
            else System.out.println("0");
        }
    }

    static boolean binarySearch(int left, int right, int target){
        while(left <= right){
            int mid = (left + right) / 2;
            if(nums[mid] == target)
                return true;
            else if(nums[mid] < target)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return false;
    }
}
