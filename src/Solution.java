import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

class Solution {

	public static void main(String[] argh) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			String IP = in.next();
			System.out.println(IP.matches(new myRegex().pattern));
		}
	}
}

class myRegex {
	public final String pattern = "(\\d{1,2}|0\\d\\d|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|0\\d\\d|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|0\\d\\d|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|0\\d\\d|1\\d\\d|2[0-4]\\d|25[0-5])";
}
