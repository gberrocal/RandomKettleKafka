package etl.pentaho.generator.model.builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import etl.pentaho.generator.model.FieldDistributionModel;
import etl.pentaho.generator.model.FrequencyFieldDistributionModel;

public class FrequencyFieldDistributionModelBuilder implements
		FieldDistributionModelBuilder {

	private Map<String, Long> valueFrequencyMap;

	private Long count;

	public FrequencyFieldDistributionModelBuilder() {
		this.count = 0L;
		this.valueFrequencyMap = new HashMap<>();
	}

	public FieldDistributionModel getDistributionFieldModel() {

		Long rankLimit = 0L;
		List<String> values = new ArrayList<>();
		List<Long> frecuencyDistribution = new ArrayList<>();

		for (String fieldValue : valueFrequencyMap.keySet()) {
			Long frequency = valueFrequencyMap.get(fieldValue);
			rankLimit += frequency;
			frecuencyDistribution.add(rankLimit);
			values.add(fieldValue);
		}
		return new FrequencyFieldDistributionModel(count,
				frecuencyDistribution, values);
	}

	@Override
	public void processFieldValue(String fieldValue) {
		this.count++;
		Long frequency = this.valueFrequencyMap.get(fieldValue);
		if (frequency == null) {
			frequency = 1L;
		} else {
			frequency++;
		}
		this.valueFrequencyMap.put(fieldValue, frequency);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((count == null) ? 0 : count.hashCode());
		result = prime
				* result
				+ ((valueFrequencyMap == null) ? 0 : valueFrequencyMap
						.hashCode());
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
		FrequencyFieldDistributionModelBuilder other = (FrequencyFieldDistributionModelBuilder) obj;
		if (count == null) {
			if (other.count != null)
				return false;
		} else if (!count.equals(other.count))
			return false;
		if (valueFrequencyMap == null) {
			if (other.valueFrequencyMap != null)
				return false;
		} else if (!valueFrequencyMap.equals(other.valueFrequencyMap))
			return false;
		return true;
	}

}
