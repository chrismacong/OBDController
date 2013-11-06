package com.fix.obd.util;

/**
 * @title BytesOperater.java
 * @package com.fix.obd.util
 * @description �ֽڲ���������
 * @author youme.ma
 * @date 2013-8-29 ����9:03:13
 * @version V1.0
 */
public class BytesOperater {

	/**
	 * ��ָ���ַ���src����ÿ�����ַ��ָ�ת��Ϊ16������ʽ �磺"2B44EFD9" �C> byte[]{0x2B, 0��44, 0xEF,
	 * 0xD9}
	 * 
	 * @param src
	 *            String
	 * @return byte[]
	 */
	public static byte[] HexString2Bytes(String src) {
		if (null == src || 0 == src.length()) {
			return null;
		}
		byte[] ret = new byte[src.length() / 2];
		byte[] tmp = src.getBytes();
		for (int i = 0; i < (tmp.length / 2); i++) {
			ret[i] = uniteBytes(tmp[i * 2], tmp[i * 2 + 1]);
		}
		return ret;
	}

	/**
	 * ������ASCII�ַ��ϳ�һ���ֽڣ� �磺"EF"�C> 0xEF
	 * 
	 * @param src0
	 *            byte
	 * @param src1
	 *            byte
	 * @return byte
	 */
	public static byte uniteBytes(byte src0, byte src1) {
		byte _b0 = Byte.decode("0x" + new String(new byte[] { src0 }))
				.byteValue();
		_b0 = (byte) (_b0 << 4);
		byte _b1 = Byte.decode("0x" + new String(new byte[] { src1 }))
				.byteValue();
		byte ret = (byte) (_b0 ^ _b1);
		return ret;
	}

	/**
	 * ���У��
	 */
	public static byte BCC(byte[] src) {
		byte out = 0x00;

		for (int i = 0; i < src.length; i++) {
			out ^= src[i];
		}

		return out;
	}

	/**
	 * �ֽ�ת16�����ַ���
	 * 
	 * @param b
	 * @return
	 */
	public static String Byte2HexString(byte b) {
		String hex = Integer.toHexString(b & 0xFF);
		if (hex.length() == 1) {
			hex = '0' + hex;
		}
		return hex;
	}

	/**
	 * ��16�����ַ���ת����10������ֵ 0011 --> 17
	 * 
	 * @param hexStr
	 *            16�����ַ���
	 * @return 10������ֵ
	 */
	public static int HexString2Integer(String hexStr) {
		int result = 0;
		byte[] hexByte = HexString2Bytes(hexStr);

		for (byte b : hexByte)
			result += b;

		return result;
	}

	/**
	 * ��ָ��byte������16���Ƶ���ʽ��ӡ������̨
	 * 
	 * @param hint
	 *            String
	 * @param b
	 *            byte[]
	 * @return void
	 */
	public static void printHexString(String hint, byte[] b) {
		//System.out.println(hint);
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			//System.out.print(hex.toUpperCase() + " ");
		}
		//System.out.println("");
	}
}
