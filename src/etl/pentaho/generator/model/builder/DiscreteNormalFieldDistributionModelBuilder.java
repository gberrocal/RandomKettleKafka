package etl.pentaho.generator.model.builder;

import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

import etl.pentaho.generator.model.DiscreteNormalFieldDistributionModel;
import etl.pentaho.generator.model.FieldDistributionModel;

public class DiscreteNormalFieldDistributionModelBuilder implements
		FieldDistributionModelBuilder {

	private SummaryStatistics statistics;

	public DiscreteNormalFieldDistributionModelBuilder() {
		this.statistics = new SummaryStatistics();
	}

	@Override
	public void processFieldValue(String fieldValue) {
		statistics.addValue(Double.parseDouble(fieldValue));
	}

	@Override
	public FieldDistributionModel getDistributionFieldModel() {
		return new DiscreteNormalFieldDistributionModel(statistics);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((statistics == null) ? 0 : statistics.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DiscreteNormalFieldDistributionModelBuilder other = (DiscreteNormalFieldDistributionModelBuilder) obj;
		if (statistics == null) {
			if (other.statistics != null)
				return false;
		} else if (!statistics.equals(other.statistics))
			return false;
		return true;
	}
}
