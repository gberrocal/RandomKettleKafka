package etl.pentaho.generator.model;

import java.math.BigDecimal;

import org.apache.commons.math3.random.RandomDataGenerator;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

public class NormalFieldDistributionModel implements FieldDistributionModel {

	private static final long serialVersionUID = 7185951507014383385L;

	private SummaryStatistics statistics;

	public NormalFieldDistributionModel(SummaryStatistics statistics) {
		this.statistics = statistics;
	}

	@Override
	public BigDecimal generateRandomField() {
		RandomDataGenerator rdg = new RandomDataGenerator();
		BigDecimal result = new BigDecimal(rdg.nextGaussian(
				statistics.getMean(), statistics.getStandardDeviation()));

		return result;
	}
}
