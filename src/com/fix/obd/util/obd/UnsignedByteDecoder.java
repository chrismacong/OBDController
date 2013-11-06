package com.fix.obd.util.obd;

public class UnsignedByteDecoder extends ByteDecoder{

	@Override
	public String decode(String source, int length) {
		// TODO Auto-generated method stub
		int result = 0;
		for(int i = 0 ; i < source.length() ; i++){
			int cInteger = changeHexadecimalToDecimal(source.charAt(i));
			result += cInteger * Math.pow(16, source.length()-1-i);
		}
		return result+"";
	}

}
