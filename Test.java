package baseHangul;

import java.io.*;

public class Test {

	public static void main(String[] args) throws Exception {
		String[] testStr = {"123ab", "1", "123d", "This is an encoded string"};

		String encoded;
		byte[] decoded = new byte[0];
		String charset;
		
		charset = "UTF-8";
		System.out.println("==" + charset + "==");
		for(String x : testStr) {
			encoded = BaseHangul.encode(x.getBytes(charset));
			System.out.println(encoded);
			decoded = BaseHangul.decode(encoded);
			System.out.println(new String(decoded, charset));
			System.out.println("=========");
		}
		System.out.println();
		
		charset = "UTF-16LE";
		System.out.println("==" + charset + "==");
		for(String x : testStr) {
			encoded = BaseHangul.encode(x.getBytes(charset));
			System.out.println(encoded);
			decoded = BaseHangul.decode(encoded);
			System.out.println(new String(decoded, charset));
			System.out.println("============");
		}
		System.out.println();
		
		charset = "EUC-KR";
		System.out.println("==" + charset + "==");
		for(String x : testStr) {
			encoded = BaseHangul.encode(x.getBytes(charset));
			System.out.println(encoded);
			decoded = BaseHangul.decode(encoded);
			System.out.println(new String(decoded, charset));
			System.out.println("==========");
		}
		System.out.println();

		//----------------------파일 입출력----------------------//
		String filename = "C:\\BaseHangul\\input";
		System.out.println("From file \"" + filename + "\"");

		charset = BOM.encoding(filename);
		System.out.println("Encoding : " + charset);

		FileInputStream in = new FileInputStream(filename);

		encoded = BaseHangul.encode(in, BOM.length(filename));
		System.out.println(encoded);
		decoded = BaseHangul.decode(encoded);
		System.out.println(new String(decoded, charset));
		
		FileOutputStream out = new FileOutputStream("C:\\BaseHangul\\output");
		out.write(BOM.getBytes(charset));
		out.write(decoded);
		out.close();
	}
}

/*
==UTF-8==
꺽먹꼍녜
123ab
=========
꺽흐흐흐
1
=========
꺽먹꼐빎
123d
=========
넥라똔먈늴멥갯놓궂뗐밸뮤뉴뗐뀄굡덜멂똑뚤
This is an encoded string
=========

==UTF-16LE==
꺽갇궁게각뒨갯됩
123ab
============
꺽가흐흐
1
============
꺽갇궁게각땟가흐
123d
============
네갊됩괵각멜갑가늰감꺄걍각뒨갸됩궁갊냘굇각따갸릴뉩갊냘괠가됩갹릴덛감궁괵각뤄갰릴
This is an encoded string
============

==EUC-KR==
꺽먹꼍녜
123ab
==========
꺽흐흐흐
1
==========
꺽먹꼐빎
123d
==========
넥라똔먈늴멥갯놓궂뗐밸뮤뉴뗐뀄굡덜멂똑뚤
This is an encoded string
==========

From file "C:\BaseHangul\input"
Encoding : EUC-KR
꺽먹꼍녜뉜떨농긴룀렉낸닭봐흐흐흐
123abcde가나다라
*/
