import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static long[] heights;
    public static void main(String[] args) throws IOException {
        heights = new long[100000];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            if(N == 0)
                break;
            for (int i = 0; i < N; i++)
                heights[i] = Long.parseLong(st.nextToken());
            System.out.println(solution(0, N - 1));
        }
    }

    static long solution(int s, int e){
        if(s == e)
            return heights[s];

        int mid = (s + e) / 2;
        long leftResult = solution(s, mid);
        long rightResult = solution(mid + 1, e);

        long max = Math.max(leftResult, rightResult);

        return Math.max(max, getMaxMidArea(s, mid, e));
    }

    static long getMaxMidArea(int s, int m, int e){
        int left = m - 1;
        int right = m + 1;

        long height = heights[m];
        long max = height;
        while(left >= s && right <= e){
            if(heights[left] > heights[right]){
                height = Math.min(height, heights[left]);
                left--;
            } else{
                height = Math.min(height, heights[right]);
                right++;
            }
            max = Math.max(max, height * (right - left - 1));
        }
        while(left >= s){
            height = Math.min(height, heights[left]);
            left--;
            max = Math.max(max, height * (e - left));
        }
        while(right <= e){
            height = Math.min(height, heights[right]);
            right++;
            max = Math.max(max, height * (right - s));
        }
        return max;
    }
}
