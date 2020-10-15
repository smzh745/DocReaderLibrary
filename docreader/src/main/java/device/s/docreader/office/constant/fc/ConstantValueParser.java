
package device.s.docreader.office.constant.fc;

import device.s.docreader.office.fc.util.LittleEndianInput;
import device.s.docreader.office.fc.util.LittleEndianOutput;
import device.s.docreader.office.fc.util.StringUtil;


public final class ConstantValueParser {
	// note - these (non-combinable) enum values are sparse.
	private static final int TYPE_EMPTY = 0;
	private static final int TYPE_NUMBER = 1;
	private static final int TYPE_STRING = 2;
	private static final int TYPE_BOOLEAN = 4; 
	private static final int TYPE_ERROR_CODE = 16;
	
	private static final int TRUE_ENCODING = 1; 
	private static final int FALSE_ENCODING = 0;
	
	// TODO - is this the best way to represent 'EMPTY'?
	private static final Object EMPTY_REPRESENTATION = null;

	private ConstantValueParser() {
		// no instances of this class
	}

	public static Object[] parse(LittleEndianInput in, int nValues) {
		Object[] result = new Object[nValues];
		for (int i = 0; i < result.length; i++) {
			result[i] = readAConstantValue(in);
		}
		return result;
	}

	private static Object readAConstantValue(LittleEndianInput in) {
		byte grbit = in.readByte();
		switch(grbit) {
			case TYPE_EMPTY:
				in.readLong(); // 8 byte 'not used' field
				return EMPTY_REPRESENTATION; 
			case TYPE_NUMBER:
				return new Double(in.readDouble());
			case TYPE_STRING:
				return StringUtil.readUnicodeString(in);
			case TYPE_BOOLEAN:
				return readBoolean(in);
			case TYPE_ERROR_CODE:
				int errCode = in.readUShort();
				// next 6 bytes are unused
				in.readUShort();
				in.readInt();
				return ErrorConstant.valueOf(errCode);
		}
		throw new RuntimeException("Unknown grbit value (" + grbit + ")");
	}

	private static Object readBoolean(LittleEndianInput in) {
		byte val = (byte)in.readLong(); // 7 bytes 'not used'
		switch(val) {
			case FALSE_ENCODING:
				return Boolean.FALSE;
			case TRUE_ENCODING:
				return Boolean.TRUE;
		}
		// Don't tolerate unusual boolean encoded values (unless it becomes evident that they occur)
		throw new RuntimeException("unexpected boolean encoding (" + val + ")");
	}

	public static int getEncodedSize(Object[] values) {
		// start with one byte 'type' code for each value
		int result = values.length * 1;
		for (int i = 0; i < values.length; i++) {
			result += getEncodedSize(values[i]);
		}
		return result;
	}

	private static int getEncodedSize(Object object) {
		if(object == EMPTY_REPRESENTATION) {
			return 8;
		}
		Class cls = object.getClass();
		
		if(cls == Boolean.class || cls == Double.class || cls == ErrorConstant.class) {
			return 8;
		}
		String strVal = (String)object;
		return StringUtil.getEncodedSize(strVal);
	}

	public static void encode(LittleEndianOutput out, Object[] values) {
		for (int i = 0; i < values.length; i++) {
			encodeSingleValue(out, values[i]);
		}
	}

	private static void encodeSingleValue(LittleEndianOutput out, Object value) {
		if (value == EMPTY_REPRESENTATION) {
			out.writeByte(TYPE_EMPTY);
			out.writeLong(0L);
			return;
		}
		if (value instanceof Boolean) {
			Boolean bVal = ((Boolean)value);
			out.writeByte(TYPE_BOOLEAN);
			long longVal = bVal.booleanValue() ? 1L : 0L;
			out.writeLong(longVal);
			return;
		}
		if (value instanceof Double) {
			Double dVal = (Double) value;
			out.writeByte(TYPE_NUMBER);
			out.writeDouble(dVal.doubleValue());
			return;
		}
		if (value instanceof String) {
			String val = (String) value;
			out.writeByte(TYPE_STRING);
			StringUtil.writeUnicodeString(out, val);
			return;
		}
		if (value instanceof ErrorConstant) {
			ErrorConstant ecVal = (ErrorConstant) value;
			out.writeByte(TYPE_ERROR_CODE);
			long longVal = ecVal.getErrorCode();
			out.writeLong(longVal);
			return;
		}

		throw new IllegalStateException("Unexpected value type (" + value.getClass().getName() + "'");
	}
}
