package baseHangul;

import java.io.*;

public class BOM {
	
	static final byte[] UTF8	  	= { (byte)0xEF, (byte)0xBB, (byte)0xBF };
	static final byte[] UTF16LE	= { (byte)0xFF, (byte)0xFE };
	static final byte[] UTF16BE	= { (byte)0xFE, (byte)0xFF };

	public static int length(String filename) throws IOException {
		FileInputStream in = new FileInputStream(filename);
		byte[] BOM = new byte[3];
		in.read(BOM, 0, 3);
		in.close();

		if(BOM[0]==UTF8[0] && BOM[1]==UTF8[1] && BOM[2]==UTF8[2])
			return 3;
		if(BOM[0]==UTF16LE[0] && BOM[1]==UTF16LE[1])
			return 2;
		if(BOM[0]==UTF16BE[0] && BOM[1]==UTF16BE[1])
			return 2;

		return 0;
	}

	public static String encoding(String filename) throws IOException {
		FileInputStream in = new FileInputStream(filename);
		byte[] BOM = new byte[3];
		in.read(BOM, 0, 3);
		in.close();

		if(BOM[0]==UTF8[0] && BOM[1]==UTF8[1] && BOM[2]==UTF8[2])
			return "UTF-8";
		if(BOM[0]==UTF16LE[0] && BOM[1]==UTF16LE[1])
			return "UTF-16LE";
		if(BOM[0]==UTF16BE[0] && BOM[1]==UTF16BE[1])
			return "UTF-16BE";

		return "EUC-KR"; // 일단은 ANSI를 EUC-KR로 인식
	}
	
	public static byte[] getBytes(String encoding) {
		switch(encoding) {
			case "UTF-8"    : return UTF8;
			case "UTF-16LE" : return UTF16LE;
			case "UTF-16BE" : return UTF16BE;
		}
		return new byte[0];
	}
}
