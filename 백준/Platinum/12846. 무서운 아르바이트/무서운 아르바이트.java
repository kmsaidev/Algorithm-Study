import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] T;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        T = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            T[i] = Integer.parseInt(st.nextToken());

        System.out.println(solution(0, N - 1));

    }

    static int solution(int s, int e){
        if(s == e)
            return T[s];

        int mid = (s + e) / 2;
        int leftMax = solution(s, mid);
        int rightMax = solution(mid + 1, e);

        int max = Math.max(leftMax, rightMax);

        return Math.max(max, getMidMaxArea(s, mid, e));
    }

    static int getMidMaxArea(int s, int m, int e){
        int left = m - 1;
        int right = m + 1;

        int height = T[m];

        int max = height;

        while(left >= s && right <= e){
            if(T[left] > T[right]){
                height = Math.min(height, T[left]);
                left--;
            } else{
                height = Math.min(height, T[right]);
                right++;
            }

            max = Math.max(max, height * (right - left - 1));
        }
        while(left >= s){
            height = Math.min(height, T[left]);
            left--;
            max = Math.max(max, height * (e - left));
        }
        while(right <= e){
            height = Math.min(height, T[right]);
            right++;
            max = Math.max(max, height * (right - s));
        }
        return max;
    }
}
