package etl.pentaho.generator.model.builder;

import etl.pentaho.generator.model.FieldDistributionModel;

public class FieldDistributionModelBuilderDirector {

	public static enum DistributionType {
		NORMAL, DISCRETE_NORMAL, FREQUENCY
	}

	private DistributionType type;

	private FieldDistributionModelBuilder builder;

	public FieldDistributionModelBuilderDirector(String firstFieldValue) {
		this.type = determineType(firstFieldValue);

		switch (this.type) {
		case NORMAL:
			builder = new NormalFieldDistributionModelBuilder();
			break;
		case DISCRETE_NORMAL:
			builder = new DiscreteNormalFieldDistributionModelBuilder();
			break;
		case FREQUENCY:
			builder = new FrequencyFieldDistributionModelBuilder();
			break;
		}
	}

	void processFieldValue(String fieldValue) {
		builder.processFieldValue(fieldValue);
	}

	public FieldDistributionModel getDistributionFieldModel() {
		return builder.getDistributionFieldModel();
	}

	private DistributionType determineType(String firstFieldValue) {
		try {
			Long.parseLong(firstFieldValue);
			return DistributionType.DISCRETE_NORMAL;
		} catch (NumberFormatException e) {
			try {
				Double.parseDouble(firstFieldValue);
				return DistributionType.NORMAL;
			} catch (NumberFormatException exc) {
				if (firstFieldValue.length() > 3) {
					String noDivisa = firstFieldValue.substring(
							firstFieldValue.length() - 3,
							firstFieldValue.length());
					try {
						Double.parseDouble(noDivisa);
						return DistributionType.NORMAL;
					} catch (NumberFormatException excep) {
						return DistributionType.FREQUENCY;
					}
				}
				return DistributionType.FREQUENCY;
			}
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((builder == null) ? 0 : builder.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		FieldDistributionModelBuilderDirector other = (FieldDistributionModelBuilderDirector) obj;
		if (builder == null) {
			if (other.builder != null)
				return false;
		} else if (!builder.equals(other.builder))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DistributionFieldModelBuilderDirector [type=" + type
				+ ", builder=" + builder + "]";
	}
}
