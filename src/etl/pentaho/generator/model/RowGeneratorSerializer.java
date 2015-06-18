package etl.pentaho.generator.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RowGeneratorSerializer implements Serializable {

	private static final long serialVersionUID = -1194744763232944138L;

	public static RowGenerator deserialize(String serialized)
			throws IOException, ClassNotFoundException {

		Matcher m = Pattern.compile("\\d{3}").matcher(serialized);

		int length = serialized.length() / 3;
		byte[] serializedBytes = new byte[length];
		for (int i = 0; i < length; i++) {
			m.find();
			String encodedBytes = serialized.substring(m.start(), m.end());
			short bytes = Short.parseShort(encodedBytes);
			serializedBytes[i] = (byte) (bytes + Byte.MIN_VALUE);
		}

		ByteArrayInputStream bais = new ByteArrayInputStream(serializedBytes);
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(bais);
			return (RowGenerator) ois.readObject();
		} finally {
			if (ois != null) {
				ois.close();
			}
			if (bais != null) {
				bais.close();
			}
		}
	}

	public static String serialize(RowGenerator generator) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = null;
		DecimalFormat df = new DecimalFormat("000");
		StringBuffer sb = new StringBuffer("");
		try {
			oos = new ObjectOutputStream(baos);
			oos.writeObject(generator);

			// encode
			byte[] byteArray = baos.toByteArray();
			for (int i = 0; i < byteArray.length; i++) {
				short encodedByte = (short) byteArray[i];
				encodedByte -= Byte.MIN_VALUE;
				sb.append(df.format(encodedByte));
			}

			return sb.toString();
		} finally {
			if (oos != null) {
				oos.close();
			}
			if (baos != null) {
				baos.close();
			}
		}
	}
}
