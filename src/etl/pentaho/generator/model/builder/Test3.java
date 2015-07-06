package etl.pentaho.generator.model.builder;

import java.io.IOException;

import etl.pentaho.generator.factory.RowGeneratorFactory;
import etl.pentaho.generator.model.RowGenerator;

public class Test3 {

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		RowGenerator generator = RowGeneratorFactory.createRowGeneratorFromSample("C:/Users/JRRZ/Desktop/Kettle-Kaftka/prueba.csv");
		
		System.out.println(generator.generateRow(","));
		
		Object[] o = new Object[100];
		
		}

}
