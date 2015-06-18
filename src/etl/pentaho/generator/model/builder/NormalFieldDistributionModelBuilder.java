package etl.pentaho.generator.model.builder;

import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

import etl.pentaho.generator.model.FieldDistributionModel;
import etl.pentaho.generator.model.NormalFieldDistributionModel;

public class NormalFieldDistributionModelBuilder implements
		FieldDistributionModelBuilder {

	private SummaryStatistics statistics;

	public NormalFieldDistributionModelBuilder() {
		this.statistics = new SummaryStatistics();
	}

	@Override
	public void processFieldValue(String fieldValue) {
		try {
			statistics.addValue(Double.parseDouble(fieldValue));
		} catch (NumberFormatException exc) {
			if (fieldValue.length() > 3) {
				String noDivisa = fieldValue.substring(0,
						fieldValue.length() -3);
				statistics.addValue(Double.parseDouble(noDivisa));
			}
		}
	}

	@Override
	public FieldDistributionModel getDistributionFieldModel() {
		return new NormalFieldDistributionModel(statistics);
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
		NormalFieldDistributionModelBuilder other = (NormalFieldDistributionModelBuilder) obj;
		if (statistics == null) {
			if (other.statistics != null)
				return false;
		} else if (!statistics.equals(other.statistics))
			return false;
		return true;
	}
}
