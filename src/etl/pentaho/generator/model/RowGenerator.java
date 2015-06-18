package etl.pentaho.generator.model;

import java.io.Serializable;

public interface RowGenerator extends Serializable {

	String generateRow(String separator);
}
