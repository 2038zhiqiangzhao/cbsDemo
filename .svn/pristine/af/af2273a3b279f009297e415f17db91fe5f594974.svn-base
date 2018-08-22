package com.people2000.common.base.mybatis.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Map;
import org.apache.commons.lang.time.DateUtils;

public class DataTypeUtils {
	public static final int DT_Unknown = 0;
	public static final int DT_byte = 1;
	public static final int DT_short = 2;
	public static final int DT_int = 3;
	public static final int DT_long = 4;
	public static final int DT_float = 5;
	public static final int DT_double = 6;
	public static final int DT_char = 7;
	public static final int DT_boolean = 8;
	public static final int DT_Byte = 10;
	public static final int DT_Short = 11;
	public static final int DT_Integer = 12;
	public static final int DT_Long = 13;
	public static final int DT_Float = 14;
	public static final int DT_Double = 15;
	public static final int DT_Character = 16;
	public static final int DT_Boolean = 17;
	public static final int DT_String = 20;
	public static final int DT_BigInteger = 21;
	public static final int DT_BigDecimal = 22;
	public static final int DT_Date = 23;
	public static final int DT_Time = 24;
	public static final int DT_DateTime = 25;
	public static final int DT_Clob = 26;
	public static final int DT_Blob = 27;
	public static final int DT_Array = 30;
	public static final int DT_List = 31;
	public static final int DT_Map = 34;
	public static final int DT_Set = 37;
	public static final int DT_Object = 40;
	public static final int DT_Class = 41;
	public static final int DT_ENUM = 42;
	public static final int DT_UserDefine = 50;
	private static Map<String, Integer> dataTypeMap = new Hashtable();

	private static String deletePrefix(String str, String prefix) {
		if (str.startsWith(prefix)) {
			str = str.substring(prefix.length());
		}
		return str;
	}

	public static String toSimpleType(String typeName) {
		typeName = deletePrefix(typeName, "class ");
		typeName = deletePrefix(typeName, "java.lang.");
		typeName = deletePrefix(typeName, "java.util.");
		typeName = deletePrefix(typeName, "java.sql.");
		typeName = deletePrefix(typeName, "java.math.");
		return typeName;
	}

	public static int getDataType(Object obj) {
		if (obj == null) {
			return 0;
		}

		return getDataType(obj.getClass().getName());
	}

	public static int getDataType(Class cls) {
		if (cls == null) {
			return 0;
		}

		return getDataType(cls.getName());
	}

	public static int getDataType(String typeName) {
		typeName = toSimpleType(typeName);

		if (typeName.charAt(0) == '[') {
			return 30;
		}

		Integer iType = (Integer) dataTypeMap.get(typeName);
		return iType == null ? 50 : iType.intValue();
	}

	public static String toUnifyTypeName(String sName) {
		return matchBracket(sName, "<", ">", false);
	}

	public static String getElementTypeName(String collectionTypeName) {
		return getElementTypeName(collectionTypeName, 0);
	}

	public static String getElementTypeName(String collectionTypeName,
			int itemIndex) {
		String typeName = toSimpleType(collectionTypeName);
		int iType = getDataType(typeName);
		if (iType == 30)
			switch (typeName.charAt(1)) {
			case 'B':
				return "byte";
			case 'S':
				return "short";
			case 'I':
				return "int";
			case 'J':
				return "long";
			case 'Z':
				return "boolean";
			case 'C':
				return "char";
			case 'F':
				return "float";
			case 'D':
				return "double";
			case 'L':
				if (typeName.charAt(typeName.length() - 1) == ';') {
					return typeName.substring(2, typeName.length() - 1);
				}
				return typeName.substring(2);
			case '[':
				return typeName.substring(1);
			case 'E':
			case 'G':
			case 'H':
			case 'K':
			case 'M':
			case 'N':
			case 'O':
			case 'P':
			case 'Q':
			case 'R':
			case 'T':
			case 'U':
			case 'V':
			case 'W':
			case 'X':
			case 'Y':
			}
		String str = matchBracket(typeName, "<", ">", true);
		int iLen = str.length();
		for (int i = 0; i <= itemIndex; i++) {
			str = matchBracket(str, "<", ">", false);
			int iLen1 = str.length();
			if (iLen1 == iLen) {
				break;
			}
			iLen = iLen1;
		}

		int iBegin = 0;
		int iEnd = str.length();
		int iPos = 0;
		for (int i = 0; i < itemIndex + 1; i++) {
			iPos = str.indexOf(44, iPos);
			if (iPos == -1) {
				break;
			}
			if (i == itemIndex - 1)
				iBegin = iPos;
			else if (i == itemIndex) {
				iEnd = iPos;
			}
		}

		return str.substring(iBegin + 1, iEnd);
	}

	public static int getElementDataType(String collectionTypeName) {
		return getElementDataType(collectionTypeName, 0);
	}

	public static int getElementDataType(String collectionTypeName,
			int itemIndex) {
		return getDataType(getElementTypeName(collectionTypeName, itemIndex));
	}

	public static Object toType(Object value, String targetType) {
		int destType = getDataType(targetType);
		return toType(value, destType);
	}

	public static Object toType(Object value, int targetType) {
		String fromType = value.getClass().getName();
		int srcType = getDataType(fromType);
		return toType(value, srcType, targetType);
	}

	public static Object toType(Object value, int srcType, int targetType) {
		srcType = toObjectType(srcType);
		targetType = toObjectType(targetType);
		if (srcType == targetType) {
			return value;
		}

		if (value == null) {
			return null;
		}

		Object retObj = null;
		if (srcType == 20) {
			String str = ((String) value).trim();
			if ((str.length() < 1) || (str.equalsIgnoreCase("null"))) {
				return null;
			}
		}

		if ((srcType >= 1) && (srcType <= 8)) {
			srcType += 9;
		}
		if ((targetType >= 1) && (targetType <= 8)) {
			targetType += 9;
		}

		if (srcType == targetType) {
			return value;
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			switch (targetType) {
			case 10:
				switch (srcType) {
				case 11:
					retObj = Byte.valueOf(((Short) value).byteValue());
					break;
				case 12:
					retObj = Byte.valueOf(((Integer) value).byteValue());
					break;
				case 13:
					retObj = Byte.valueOf(((Long) value).byteValue());
					break;
				case 21:
					retObj = Byte.valueOf(((BigInteger) value).byteValue());
					break;
				case 14:
					retObj = Byte.valueOf(((Float) value).byteValue());
					break;
				case 15:
					retObj = Byte.valueOf(((Double) value).byteValue());
					break;
				case 22:
					retObj = Byte.valueOf(((BigDecimal) value).byteValue());
					break;
				case 16:
					retObj = Byte.valueOf(Byte.parseByte(((Character) value)
							.toString()));
					break;
				case 20:
					retObj = Byte.valueOf(Byte.parseByte((String) value));
					break;
				case 17:
					retObj = Byte.valueOf((byte) (((Boolean) value)
							.booleanValue() ? 1 : 0));
				case 18:
				case 19:
				}
				break;
			case 11:
				switch (srcType) {
				case 10:
					retObj = Short.valueOf(((Byte) value).shortValue());
					break;
				case 12:
					retObj = Short.valueOf(((Integer) value).shortValue());
					break;
				case 13:
					retObj = Short.valueOf(((Long) value).shortValue());
					break;
				case 21:
					retObj = Short.valueOf(((BigInteger) value).shortValue());
					break;
				case 14:
					retObj = Short.valueOf(((Float) value).shortValue());
					break;
				case 15:
					retObj = Short.valueOf(((Double) value).shortValue());
					break;
				case 22:
					retObj = Short.valueOf(((BigDecimal) value).shortValue());
					break;
				case 16:
					retObj = Short.valueOf(Short.parseShort(((Character) value)
							.toString()));
					break;
				case 20:
					retObj = Short.valueOf(Short.parseShort((String) value));
					break;
				case 17:
					retObj = Short.valueOf((short) (((Boolean) value)
							.booleanValue() ? 1 : 0));
				case 11:
				case 18:
				case 19:
				}
				break;
			case 12:
				switch (srcType) {
				case 10:
					retObj = Integer.valueOf(((Byte) value).intValue());
					break;
				case 11:
					retObj = Integer.valueOf(((Short) value).intValue());
					break;
				case 13:
					retObj = Integer.valueOf(((Long) value).intValue());
					break;
				case 21:
					retObj = Integer.valueOf(((BigInteger) value).intValue());
					break;
				case 14:
					retObj = Integer.valueOf(((Float) value).intValue());
					break;
				case 15:
					retObj = Integer.valueOf(((Double) value).intValue());
					break;
				case 22:
					retObj = Integer.valueOf(((BigDecimal) value).intValue());
					break;
				case 16:
					retObj = Integer.valueOf(Integer
							.parseInt(((Character) value).toString()));
					break;
				case 20:
					String strValue = value.toString().replace(",", "");
					retObj = Integer.valueOf(Integer.parseInt(strValue));
					break;
				case 17:
					retObj = Integer
							.valueOf(((Boolean) value).booleanValue() ? 1 : 0);
					break;
				case 23:
					retObj = Integer.valueOf((int) ((java.util.Date) value)
							.getTime());
					break;
				case 24:
					retObj = Integer.valueOf((int) ((Time) value).getTime());
					break;
				case 25:
					retObj = Integer.valueOf((int) ((Timestamp) value)
							.getTime());
				case 12:
				case 18:
				case 19:
				}
				break;
			case 13:
				switch (srcType) {
				case 10:
					retObj = Long.valueOf(((Byte) value).longValue());
					break;
				case 11:
					retObj = Long.valueOf(((Short) value).longValue());
					break;
				case 12:
					retObj = Long.valueOf(((Integer) value).longValue());
					break;
				case 21:
					retObj = Long.valueOf(((BigInteger) value).longValue());
					break;
				case 14:
					retObj = Long.valueOf(((Float) value).longValue());
					break;
				case 15:
					retObj = Long.valueOf(((Double) value).longValue());
					break;
				case 22:
					retObj = Long.valueOf(((BigDecimal) value).longValue());
					break;
				case 16:
					retObj = Long.valueOf(Long.parseLong(((Character) value)
							.toString()));
					break;
				case 20:
					String strValue = value.toString().replace(",", "");
					retObj = Long.valueOf(Long.parseLong(strValue));
					break;
				case 17:
					retObj = Long.valueOf(((Boolean) value).booleanValue() ? 1
							: 0);
					break;
				case 23:
					retObj = Long.valueOf(((java.util.Date) value).getTime());
					break;
				case 24:
					retObj = Long.valueOf(((Time) value).getTime());
					break;
				case 25:
					retObj = Long.valueOf(((Timestamp) value).getTime());
				case 13:
				case 18:
				case 19:
				}
				break;
			case 21:
				switch (srcType) {
				case 10:
					retObj = BigInteger.valueOf(((Byte) value).longValue());
					break;
				case 11:
					retObj = BigInteger.valueOf(((Short) value).longValue());
					break;
				case 12:
					retObj = BigInteger.valueOf(((Integer) value).longValue());
					break;
				case 13:
					retObj = BigInteger.valueOf(((Long) value).longValue());
					break;
				case 14:
					retObj = BigInteger.valueOf(((Float) value).longValue());
					break;
				case 15:
					retObj = BigInteger.valueOf(((Double) value).longValue());
					break;
				case 22:
					retObj = BigInteger.valueOf(((BigDecimal) value)
							.longValue());
					break;
				case 16:
					retObj = BigInteger.valueOf(Long
							.parseLong(((Character) value).toString()));
					break;
				case 20:
					String strValue = value.toString().replace(",", "");
					retObj = BigInteger.valueOf(Long.parseLong(strValue));
					break;
				case 17:
					retObj = BigInteger.valueOf(((Boolean) value)
							.booleanValue() ? 1 : 0);
					break;
				case 23:
					retObj = BigInteger.valueOf(((java.util.Date) value)
							.getTime());
					break;
				case 24:
					retObj = BigInteger.valueOf(((Time) value).getTime());
					break;
				case 25:
					retObj = BigInteger.valueOf(((Timestamp) value).getTime());
				case 18:
				case 19:
				case 21:
				}
				break;
			case 14:
				switch (srcType) {
				case 10:
					retObj = Float.valueOf(((Byte) value).floatValue());
					break;
				case 11:
					retObj = Float.valueOf(((Short) value).floatValue());
					break;
				case 12:
					retObj = Float.valueOf(((Integer) value).floatValue());
					break;
				case 13:
					retObj = Float.valueOf(((Long) value).floatValue());
					break;
				case 21:
					retObj = Float.valueOf(((BigInteger) value).floatValue());
					break;
				case 15:
					retObj = Float.valueOf(((Double) value).floatValue());
					break;
				case 22:
					retObj = Float.valueOf(((BigDecimal) value).floatValue());
					break;
				case 16:
					retObj = Float.valueOf(Float.parseFloat(((Character) value)
							.toString()));
					break;
				case 20:
					String strValue = value.toString().replace(",", "");
					retObj = Float.valueOf(Float.parseFloat(strValue));
					break;
				case 17:
					retObj = Float.valueOf(((Boolean) value).booleanValue() ? 1
							: 0);
				case 14:
				case 18:
				case 19:
				}
				break;
			case 22:
				switch (srcType) {
				case 10:
					retObj = BigDecimal.valueOf(((Byte) value).doubleValue());
					break;
				case 11:
					retObj = BigDecimal.valueOf(((Short) value).doubleValue());
					break;
				case 12:
					retObj = BigDecimal.valueOf(((Integer) value).intValue());
					break;
				case 13:
					retObj = BigDecimal.valueOf(((Long) value).longValue());
					break;
				case 15:
					retObj = BigDecimal.valueOf(((Double) value).doubleValue());
					break;
				case 14:
					retObj = BigDecimal.valueOf(((Float) value).doubleValue());
					break;
				case 16:
					retObj = BigDecimal.valueOf(Double
							.parseDouble(((Character) value).toString()));
					break;
				case 20:
					String strValue = value.toString().replace(",", "");
					retObj = BigDecimal.valueOf(Double.parseDouble(strValue));
					break;
				case 17:
					retObj = ((Boolean) value).booleanValue() ? BigDecimal.ONE
							: BigDecimal.ZERO;
				case 18:
				case 19:
				}
				break;
			case 15:
				switch (srcType) {
				case 10:
					retObj = Double.valueOf(((Byte) value).doubleValue());
					break;
				case 11:
					retObj = Double.valueOf(((Short) value).doubleValue());
					break;
				case 12:
					retObj = Double.valueOf(((Integer) value).doubleValue());
					break;
				case 13:
					retObj = Double.valueOf(((Long) value).doubleValue());
					break;
				case 21:
					retObj = Double.valueOf(((BigInteger) value).doubleValue());
					break;
				case 14:
					retObj = Double.valueOf(((Float) value).doubleValue());
					break;
				case 22:
					retObj = Double.valueOf(((BigDecimal) value).doubleValue());
					break;
				case 16:
					retObj = Double.valueOf(Double
							.parseDouble(((Character) value).toString()));
					break;
				case 20:
					String strValue = value.toString().replace(",", "");
					retObj = Double.valueOf(Double.parseDouble(strValue));
					break;
				case 17:
					retObj = Double
							.valueOf(((Boolean) value).booleanValue() ? 1 : 0);
				case 15:
				case 18:
				case 19:
				}
				break;
			case 16:
				switch (srcType) {
				case 10:
					retObj = Character.valueOf(Character.toChars(((Byte) value)
							.byteValue())[0]);
					break;
				case 11:
					retObj = Character.valueOf(Character
							.toChars(((Short) value).shortValue())[0]);
					break;
				case 12:
					retObj = Character.valueOf(Character
							.toChars(((Integer) value).intValue())[0]);
					break;
				case 13:
					retObj = Character.valueOf(Character.toChars(((Long) value)
							.intValue())[0]);
					break;
				case 21:
					retObj = Character.valueOf(Character
							.toChars(((BigInteger) value).intValue())[0]);
					break;
				case 14:
					retObj = Character.valueOf(Character
							.toChars(((Float) value).intValue())[0]);
					break;
				case 15:
					retObj = Character.valueOf(Character
							.toChars(((Double) value).intValue())[0]);
					break;
				case 22:
					retObj = Character.valueOf(Character
							.toChars(((BigDecimal) value).intValue())[0]);
					break;
				case 20:
					retObj = Character.valueOf(((String) value).charAt(0));
					break;
				case 17:
					retObj = Double
							.valueOf(((Boolean) value).booleanValue() ? 84 : 70);
				case 16:
				case 18:
				case 19:
				}
				break;
			case 17:
				switch (srcType) {
				case 10:
					retObj = Boolean.valueOf(((Byte) value).byteValue() != 0);
					break;
				case 11:
					retObj = Boolean.valueOf(((Short) value).shortValue() != 0);
					break;
				case 12:
					retObj = Boolean.valueOf(((Integer) value).intValue() != 0);
					break;
				case 13:
					retObj = Boolean.valueOf(((Long) value).longValue() != 0L);
					break;
				case 21:
					retObj = Boolean
							.valueOf(((BigInteger) value).longValue() != 0L);
					break;
				case 14:
					retObj = Boolean.valueOf(((Float) value).intValue() != 0);
					break;
				case 15:
					retObj = Boolean.valueOf(((Double) value).intValue() != 0);
					break;
				case 22:
					retObj = Boolean
							.valueOf(((BigDecimal) value).longValue() != 0L);
					break;
				case 16:
					retObj = Boolean
							.valueOf(((Character) value).charValue() == 'T');
					break;
				case 20:
					String strValue = (String) value;
					if (strValue.equalsIgnoreCase("true"))
						retObj = Boolean.valueOf(true);
					else if (strValue.equalsIgnoreCase("t"))
						retObj = Boolean.valueOf(true);
					else if (strValue.equalsIgnoreCase("yes"))
						retObj = Boolean.valueOf(true);
					else if (strValue.equalsIgnoreCase("y"))
						retObj = Boolean.valueOf(true);
					else if (strValue.equalsIgnoreCase("是"))
						retObj = Boolean.valueOf(true);
					else
						retObj = Boolean.valueOf(false);
				case 17:
				case 18:
				case 19:
				}
				break;
			case 20:
				switch (srcType) {
				case 10:
					retObj = ((Byte) value).toString();
					break;
				case 11:
					retObj = ((Short) value).toString();
					break;
				case 12:
					retObj = ((Integer) value).toString();
					break;
				case 13:
					retObj = ((Long) value).toString();
					break;
				case 21:
					retObj = ((BigInteger) value).toString();
					break;
				case 14:
					retObj = ((Float) value).toString();
					break;
				case 15:
					retObj = ((Double) value).toString();
					break;
				case 22:
					retObj = ((BigDecimal) value).toString();
					break;
				case 16:
					retObj = ((Character) value).toString();
					break;
				case 17:
					retObj = ((Boolean) value).toString();
					break;
				case 23:
					if ((value instanceof java.sql.Date)) {
						sdf.applyPattern("yyyy-MM-dd");
					}
					retObj = sdf.format((java.util.Date) value);
					break;
				case 24:
					sdf.applyPattern("HH:mm:ss");
					retObj = sdf.format((java.util.Date) value);
					break;
				case 25:
					retObj = sdf.format((java.util.Date) value);
				case 18:
				case 19:
				case 20:
				}
				break;
			case 23:
				switch (srcType) {
				case 20:
					String[] datePatterns = { "yyyy-MM-dd HH:mm:ss",
							"yyyy-MM-dd HH:mm", "yyyy-MM-dd HH", "yyyy-MM-dd",
							"yyyy-MM", "yyyy" };

					retObj = DateUtils
							.parseDate(value.toString(), datePatterns);
					break;
				case 12:
				case 13:
				case 25:
					Calendar cal = Calendar.getInstance();
					if (srcType == 25)
						cal.setTime((java.util.Date) value);
					else if (srcType == 13)
						cal.setTimeInMillis(((Long) value).longValue());
					else {
						cal.setTimeInMillis(((Integer) value).intValue());
					}
					cal.set(11, 0);
					cal.set(12, 0);
					cal.set(13, 0);
					cal.set(14, 0);
					retObj = new java.sql.Date(cal.getTimeInMillis());
				}

				break;
			case 24:
				switch (srcType) {
				case 20:
					retObj = Time.valueOf((String) value);
					break;
				case 12:
				case 13:
				case 25:
					Calendar cal = Calendar.getInstance();
					if (srcType == 25)
						cal.setTime((java.util.Date) value);
					else if (srcType == 13)
						cal.setTimeInMillis(((Long) value).longValue());
					else {
						cal.setTimeInMillis(((Integer) value).intValue());
					}
					cal.set(1, 0);
					cal.set(2, 0);
					cal.set(5, 0);
					cal.set(14, 0);
					retObj = new Time(cal.getTimeInMillis());
				}

				break;
			case 25:
				switch (srcType) {
				case 20:
					retObj = Timestamp.valueOf((String) value);
					break;
				case 12:
				case 13:
				case 23:
				case 24:
					Calendar cal = Calendar.getInstance();
					if ((srcType == 23) || (srcType == 24))
						cal.setTime((java.util.Date) value);
					else if (srcType == 13)
						cal.setTimeInMillis(((Long) value).longValue());
					else {
						cal.setTimeInMillis(((Integer) value).intValue());
					}
					cal.set(14, 0);
					retObj = new Timestamp(cal.getTimeInMillis());
				case 14:
				case 15:
				case 16:
				case 17:
				case 18:
				case 19:
				case 21:
				case 22:
				}
			case 18:
			case 19:
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return retObj;
	}

	private static String matchBracket(String str, String matchBegin,
			String matchEnd, boolean bGet) {
		int iLen = str.length();
		int iMatchLen = matchBegin.length();
		char cBegin = '[';
		char cEnd = ']';
		int iBeginPos = -1;
		int iEndPos = -1;
		boolean bFindMatch = false;
		int iCount = -1;
		for (int i = 0; i < iLen; i++) {
			char ch = str.charAt(i);
			if (bFindMatch) {
				if (ch == cBegin) {
					iCount++;
				} else if (ch == cEnd) {
					iCount--;
					if (iCount == 0) {
						iEndPos = i;
						break;
					}
				}
			} else
				for (int k = 0; k < iMatchLen; k++) {
					cBegin = matchBegin.charAt(k);
					if (ch == cBegin) {
						bFindMatch = true;
						cEnd = matchEnd.charAt(k);
						iBeginPos = i;
						iCount = 1;
						break;
					}
				}

		}

		if (bFindMatch) {
			if (bGet) {
				return str.substring(iBeginPos + 1, iEndPos);
			}
			return str.substring(0, iBeginPos) + str.substring(iEndPos + 1);
		}

		if (bGet) {
			return "";
		}
		return str;
	}

	public static boolean isSimpleType(int iType) {
		switch (iType) {
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 10:
		case 11:
		case 12:
		case 13:
		case 14:
		case 15:
		case 16:
		case 17:
		case 20:
		case 21:
		case 22:
		case 23:
		case 24:
		case 25:
		case 26:
		case 27:
			return true;
		case 9:
		case 18:
		case 19:
		}
		return false;
	}

	public static int toObjectType(int iType) {
		if ((iType >= 1) && (iType <= 8)) {
			iType += 9;
		}
		return iType;
	}

	public static boolean isCollectionType(int dataType) {
		switch (dataType) {
		case 30:
		case 31:
		case 37:
			return true;
		}
		return false;
	}

	public static boolean isMapType(int dataType) {
		return dataType == 34;
	}

	static {
		dataTypeMap.put("byte", Integer.valueOf(1));
		dataTypeMap.put("Byte", Integer.valueOf(10));
		dataTypeMap.put("short", Integer.valueOf(2));
		dataTypeMap.put("Short", Integer.valueOf(11));
		dataTypeMap.put("int", Integer.valueOf(3));
		dataTypeMap.put("Integer", Integer.valueOf(12));
		dataTypeMap.put("long", Integer.valueOf(4));
		dataTypeMap.put("Long", Integer.valueOf(13));
		dataTypeMap.put("boolean", Integer.valueOf(8));
		dataTypeMap.put("Boolean", Integer.valueOf(17));
		dataTypeMap.put("char", Integer.valueOf(7));
		dataTypeMap.put("Character", Integer.valueOf(16));
		dataTypeMap.put("float", Integer.valueOf(5));
		dataTypeMap.put("Float", Integer.valueOf(14));
		dataTypeMap.put("double", Integer.valueOf(6));
		dataTypeMap.put("Double", Integer.valueOf(15));
		dataTypeMap.put("BigInteger", Integer.valueOf(21));
		dataTypeMap.put("BigDecimal", Integer.valueOf(22));
		dataTypeMap.put("String", Integer.valueOf(20));
		dataTypeMap.put("Date", Integer.valueOf(23));
		dataTypeMap.put("Time", Integer.valueOf(24));
		dataTypeMap.put("Timestamp", Integer.valueOf(25));
		dataTypeMap.put("List", Integer.valueOf(31));
		dataTypeMap.put("ArrayList", Integer.valueOf(31));
		dataTypeMap.put("LinkedList", Integer.valueOf(31));
		dataTypeMap.put("Map", Integer.valueOf(34));
		dataTypeMap.put("HashMap", Integer.valueOf(34));
		dataTypeMap.put("Hashtable", Integer.valueOf(34));
		dataTypeMap.put("TreeMap", Integer.valueOf(34));
		dataTypeMap.put("Set", Integer.valueOf(37));
		dataTypeMap.put("HashSet", Integer.valueOf(37));
		dataTypeMap.put("Object", Integer.valueOf(40));
		dataTypeMap.put("Class", Integer.valueOf(41));
		dataTypeMap.put("clob", Integer.valueOf(26));
		dataTypeMap.put("blob", Integer.valueOf(27));
	}
}