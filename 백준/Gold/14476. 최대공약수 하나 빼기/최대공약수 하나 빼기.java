import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static int N, result, max = 0, num;
    static Integer[] nums;
    static int[] acGcdUp;
    static int[] acGcdDown;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        nums = new Integer[N];
        acGcdUp = new int[N];
        acGcdDown = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            nums[i] = Integer.parseInt(st.nextToken());

        acGcdUp[0] = nums[0];
        for(int i = 1; i < N; i++){
            if(acGcdUp[i - 1] >= nums[i])
                acGcdUp[i] = gcd(acGcdUp[i - 1], nums[i]);
            else
                acGcdUp[i] = gcd(nums[i], acGcdUp[i - 1]);
        }

        acGcdDown[N - 1] = nums[N - 1];
        for(int i = N - 2; i >= 0; i--){
            if(acGcdDown[i + 1] >= nums[i])
                acGcdDown[i] = gcd(acGcdDown[i + 1], nums[i]);
            else
                acGcdDown[i] = gcd(nums[i], acGcdDown[i + 1]);
        }

        for(int i = 0; i < N; i++){
            if(i == 0)
                result = acGcdDown[1];
            else if(i == N - 1)
                result = acGcdUp[N - 2];
            else{
                result = gcd(acGcdUp[i - 1], acGcdDown[i + 1]);
            }
            if(nums[i] % result != 0 && max < result) {
                num = nums[i];
                max = result;
            }
        }

        if(max == 0)
            System.out.println("-1");
        else
            System.out.println(max + " " + num);
    }

    static int gcd(int a, int b){
        if(b == 0)
            return a;
        else
            return gcd(b, a % b);
    }
}
