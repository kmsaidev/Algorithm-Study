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
        boolean isLast = true;

        for(int i = N - 1; i > 0; i--){
            if(nums[i] > nums[i - 1]){
                for(int j = N - 1; j >= i; j--){
                    if(nums[j] > nums[i - 1]) {
                        swap(j, i - 1);
                        int k = N - 1;

                        while(i < k){
                            swap(i, k);
                            i++;
                            k--;
                        }
                        isLast = false;
                        break;
                    }
                }
                if(!isLast)
                    break;
            }
        }
        if(isLast)
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
