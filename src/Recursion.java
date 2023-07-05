import java.util.Objects;
import java.util.Scanner;

public class Recursion {

    public static int distinctSubsequences(String s , String t) {
      String[] subsequences = subsequences(s);
      int count = 0;

      for(String str : subsequences) {
          if(Objects.equals(str, t)) {
              count++;
          }
      }

      return count;
    }

    public static String[] subsequences(String s) {
        if(s.length() == 0) return new String[]{""};

        String[] smallAns = subsequences(s.substring(1));
        String[] ans = new String[smallAns.length * 2];

        for(int i = 0 ; i < smallAns.length; i++) {
            ans[i] = smallAns[i];
        }

        for(int i = 0 ; i < smallAns.length; i++) {
            ans[smallAns.length + i] = s.charAt(0) + smallAns[i];
        }

        return ans;
    }


    public static String[] keypadReturn(int n) {
        if(n == 0) {
            return new String[]{" "};
        }

        String[] smallAns = keypadReturn(n / 10);
        String[] singleDigit = singleDigitOut(n % 10);
        String[] ans = new String[singleDigit.length * smallAns.length];
        int k = 0;

        for (String s : singleDigit) {
            for (String smallAn : smallAns) {
                ans[k++] = smallAn + s;
            }
        }
        return ans;
    }


    private static String[] singleDigitOut(int n) {
        if (n <= 1 || n >= 10) {
            System.exit(0);
        }

        return switch (n) {
            case 2 -> new String[]{"a", "b", "c"};
            case 3 -> new String[]{"d", "e", "f"};
            case 4 -> new String[]{"g", "h", "i"};
            case 5 -> new String[]{"j", "k", "l"};
            case 6 -> new String[]{"m", "n", "o"};
            case 7 -> new String[]{"p", "q", "r","s"};
            case 8 -> new String[]{"t", "u", "v"};
            default -> new String[]{"w", "x", "y","z"};
        };
    }

    public static void main(String[] args) {
        System.out.println(distinctSubsequences("rabbbit","rabbit"));
        System.out.println(distinctSubsequences("babgbag","bag"));
        String[] keyReturns = keypadReturn(234);
        for(String s : keyReturns) {
            System.out.print(s+" ");
        }
    }
}
