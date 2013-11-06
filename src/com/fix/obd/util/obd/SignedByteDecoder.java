package com.fix.obd.util.obd;

public class SignedByteDecoder extends ByteDecoder{

	@Override
	public String decode(String source, int length) {
		// TODO Auto-generated method stub
		int result = 0;
		if(isNegative(source)){
			for(int i = 0 ; i < source.length() ; i++){
				int cInteger = changeHexadecimalToDecimal(source.charAt(i));
				cInteger = 16 - cInteger;
				result += cInteger * Math.pow(16, source.length()-1-i);
			}
			result += 1;
			return (0-result)+"";
		}
		else{
			for(int i = 0 ; i < source.length() ; i++){
				int cInteger = changeHexadecimalToDecimal(source.charAt(i));
				result += cInteger * Math.pow(16, source.length()-1-i);
			}
			return result+"";

		}
	}

	private boolean isNegative(String source) {
		// TODO Auto-generated method stub
		int firstChar = this.changeHexadecimalToDecimal(source.charAt(0));
		if(firstChar >= 8)
			return true;
		else
			return false;
	}

}
