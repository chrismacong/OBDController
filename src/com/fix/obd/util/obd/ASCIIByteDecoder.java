package com.fix.obd.util.obd;

public class ASCIIByteDecoder extends ByteDecoder{

	@Override
	public String decode(String source, int length) {
		// TODO Auto-generated method stub
		String result = "";
		for(int i = 0 ; i < source.length()/2 ; i+=2){
			int charInteger = Integer.valueOf(source.substring(i*2 , i*2+2) , 16);
			result += charInteger;
		}
		return result;
	}

	public int getStringValue(String effString) {
		// TODO Auto-generated method stub
		int result = 0;
		result += changeHexadecimalToDecimal(effString.charAt(0))*16 +
				changeHexadecimalToDecimal(effString.charAt(1));
		return result;
	}

}
