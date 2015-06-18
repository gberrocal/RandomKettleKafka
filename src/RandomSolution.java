import java.util.Random;

public class RandomSolution {

	public static void main(String[] args) {

		Random rand = new Random();
		int j = 0,k = 0, l = 0;
		for (int i = 0; i < 10000000; i++) {
			double num = rand.nextGaussian();
			if (num > 1 && num <= 2) {
				//System.out.println(num);
				j++;
			}
			if (num > 2 && num <= 3) {
				//System.out.println(num);
				k++;
			}
			if (num > 3) {
				//System.out.println(num);
				l++;
			}
		}
		System.out.println(j);
		System.out.println(k);
		System.out.println(l);
	}
}