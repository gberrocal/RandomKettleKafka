package etl.pentaho.generator.model.builder;

import java.io.IOException;

import etl.pentaho.generator.model.RowGenerator;
import etl.pentaho.generator.model.RowGeneratorSerializer;

public class Test2 {

	public static void main(String[] args) throws IOException,
			ClassNotFoundException {

		RowGenerator generator = SampleAnalyzer
				.analyze("C:/Users/JRRZ/Desktop/Kettle-Kaftka/prueba.csv");

		String p = RowGeneratorSerializer.serialize(generator);
		RowGenerator deserialize = RowGeneratorSerializer.deserialize(p);
		System.out.println(deserialize.generateRow(","));
	}

}
