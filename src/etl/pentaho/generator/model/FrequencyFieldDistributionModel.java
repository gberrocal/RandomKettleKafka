package etl.pentaho.generator.model;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.math3.random.RandomDataGenerator;

public class FrequencyFieldDistributionModel implements FieldDistributionModel {

	private static final long serialVersionUID = 1931427467183466390L;

	private String[] values;

	private Long[] frecuencyDistribution;

	private Long count;

	public FrequencyFieldDistributionModel(Long count,
			List<Long> frecuencyDistribution, List<String> values) {
		this.count = count;
		this.values = values.toArray(new String[0]);
		this.frecuencyDistribution = frecuencyDistribution.toArray(new Long[0]);
	}

	@Override
	public String generateRandomField() {

		String res = null;
		RandomDataGenerator rdg = new RandomDataGenerator();
		long randomKey = rdg.nextLong(0, count);

		int rank = Arrays.binarySearch(frecuencyDistribution, randomKey);
		if (rank >= 0) {

		} else {
			rank++;
			rank *= -1;
		}
		if (rank == values.length) {
			res = values[rank - 1];
		} else {
			res = values[rank];
		}
		return res;
	}
}
