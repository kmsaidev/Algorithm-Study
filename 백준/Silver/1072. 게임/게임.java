import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static double X, Y;
    static int Z;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        X = Double.parseDouble(st.nextToken());
        Y = Double.parseDouble(st.nextToken());
        Z = (int)((Y * 100) / X);
        double start = 0, end = X;
        int result = -1;
        while(true){
            if(start > end)
                break;
            int mid = (int)((start + end) / 2);
            double new_x = X + mid, new_y = Y + mid;
            int new_z = (int)((new_y * 100) / new_x);
            if(new_z > Z) {
                result = mid;
                end = mid - 1;
            } else{
                start = mid + 1;
            }
        }

        System.out.println(result);
    }
}
