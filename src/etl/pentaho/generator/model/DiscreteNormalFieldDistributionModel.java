package etl.pentaho.generator.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

import org.apache.commons.math3.random.RandomDataGenerator;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

public class DiscreteNormalFieldDistributionModel implements
		FieldDistributionModel {

	private static final long serialVersionUID = -5520505447390736017L;

	private SummaryStatistics statistics;

	public DiscreteNormalFieldDistributionModel(SummaryStatistics statistics) {
		this.statistics = statistics;
	}

	@Override
	public BigInteger generateRandomField() {

		RandomDataGenerator rdg = new RandomDataGenerator();

		BigDecimal result = new BigDecimal(rdg.nextGaussian(
				statistics.getMean(), statistics.getStandardDeviation()));

		result = result.setScale(0, RoundingMode.HALF_EVEN);
		return result.toBigInteger();
	}
}
