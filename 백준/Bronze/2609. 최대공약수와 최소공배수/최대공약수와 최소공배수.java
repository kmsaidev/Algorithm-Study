import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int A, B;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        int gcd = gcd(A, B);
        int lcm = A * B / gcd;
        System.out.println(gcd);
        System.out.println(lcm);
    }

    static int gcd(int a, int b){
        if(b == 0)
            return a;
        else return gcd(b, a % b);
    }
}
