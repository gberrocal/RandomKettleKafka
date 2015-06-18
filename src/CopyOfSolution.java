import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// The part of the program involving reading from STDIN and writing to STDOUT has been provided by us.

public class CopyOfSolution {

	public static void main(String[] args) throws NumberFormatException,
			IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int K = Integer.parseInt(in.readLine());
		int[] list = new int[N];

		for (int i = 0; i < N; i++)
			list[i] = Integer.parseInt(in.readLine());

		int unfairness = Integer.MAX_VALUE;

		/*
		 * Write your code here, to process numPackets N, numKids K, and the
		 * packets of candies Compute the ideal value for unfairness over here
		 */

		// Ordeno la entrada
		Arrays.sort(list);

		// Calculo el unfairness para cada K elementos consecutivos
		for (int i = 0; i < N - K + 1; i++) {
			int min = list[i];
			int max = list[i + K - 1];
			int unfairnessCandidate = max - min;
			if (unfairnessCandidate < unfairness) {
				unfairness =  unfairnessCandidate;
			}
			
		}

		System.out.println(unfairness);
	}
}