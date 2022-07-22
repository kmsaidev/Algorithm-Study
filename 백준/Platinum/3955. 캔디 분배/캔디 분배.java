import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int T;
    static long A, B;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        for(int i = 0; i < T; i++){
            st = new StringTokenizer(br.readLine());
            A = Long.parseLong(st.nextToken());
            B = Long.parseLong(st.nextToken());
            ExtendedGcd eg = getFirstResult(A, B);
            if(eg.r != 1) {
                System.out.println("IMPOSSIBLE");
                continue;
            }
            long x0 = eg.s;
            long y0 = eg.t;

            //등호가 있으면 내림, 등호가 없으면 올림 후에 -1
            long kFromY = (long) Math.ceil((double) y0 / (double) A) - 1;
            long kFromX = (long) Math.ceil((double) -x0 / (double) B) - 1;
            long k = Math.min(kFromY, kFromX);

            long kLimitFromY = (long) Math.ceil((y0 - 1e9) / (double) A);

            if(kLimitFromY <= k){
                System.out.println(y0 - A * k);
            } else
                System.out.println("IMPOSSIBLE");
        }
    }

    static ExtendedGcd getFirstResult(long a, long b){
        long s0 = 1, t0 = 0, r0 = a;
        long s1 = 0, t1 = 1, r1 = b;

        long temp;
        while (r1 != 0) {
            long q = r0 / r1;

            temp = r0 - q * r1;
            r0 = r1;
            r1 = temp;

            temp = s0 - q * s1;
            s0 = s1;
            s1 = temp;

            temp = t0 - q * t1;
            t0 = t1;
            t1 = temp;
        }
        return new ExtendedGcd(s0, t0, r0);
    }
}

class ExtendedGcd{
    long s, t, r;

    public ExtendedGcd(long s, long t, long r) {
        this.s = s;
        this.t = t;
        this.r = r;
    }
}
