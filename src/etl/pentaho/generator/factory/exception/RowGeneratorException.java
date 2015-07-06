package etl.pentaho.generator.factory.exception;

public class RowGeneratorException extends Exception {
	
	private static final long serialVersionUID = 2771452835750327033L;
	
	public RowGeneratorException(){
		super("Exception occurs. The object with the sample analized was unnable to access.");
	}
}
