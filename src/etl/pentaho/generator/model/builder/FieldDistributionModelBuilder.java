package etl.pentaho.generator.model.builder;

import etl.pentaho.generator.model.FieldDistributionModel;

public interface FieldDistributionModelBuilder {

	void processFieldValue(String fieldValue);

	FieldDistributionModel getDistributionFieldModel();
}
