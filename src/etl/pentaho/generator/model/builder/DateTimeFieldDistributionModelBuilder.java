package etl.pentaho.generator.model.builder;

import etl.pentaho.generator.model.DateTimeFieldDistributionModel;
import etl.pentaho.generator.model.FieldDistributionModel;
import etl.pentaho.generator.model.FrequencyFieldDistributionModel;


public class DateTimeFieldDistributionModelBuilder extends
		DateFieldDistributionModelBuilder {

	public DateTimeFieldDistributionModelBuilder(){
		super();
	}
	
	@Override
	public FieldDistributionModel getDistributionFieldModel() {
		FrequencyFieldDistributionModel frequencyFieldDistributionModel = (FrequencyFieldDistributionModel)getFrequencyFieldDistributionModelBuilder().getDistributionFieldModel();
		DateTimeFieldDistributionModel dateTimeFieldDistributionModel =	new DateTimeFieldDistributionModel(frequencyFieldDistributionModel);
		
		return dateTimeFieldDistributionModel;
	}
}
