package godo.compression;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Gzip {

	public static int decode(InputStream in, OutputStream out) throws IOException {

		
		
		
		GZIPInputStream gin = new GZIPInputStream(in);

		byte buffer[] = new byte[1024];
		int cin = 0;
		int cout = 0;

		while ((cin = gin.read(buffer)) != -1) {
			out.write(buffer, 0, cin);
			cout += cin;
		}

		gin.close();

		return cout;

	}

	public static int encode(InputStream in, OutputStream out) throws IOException {

		class CustomGZIPOutputStream extends GZIPOutputStream{
			public CustomGZIPOutputStream(OutputStream out) throws IOException {
				super(out);
				def.setLevel(9);		
			}
		}
		
		CustomGZIPOutputStream cGout = new CustomGZIPOutputStream(out);				
		
		byte buffer[] = new byte[2048];
		int cin = 0;
		int cout = 0;

		while ((cin = in.read(buffer)) != -1) {
			cGout.write(buffer, 0, cin);
			cout += cin;
		}

		cGout.close();

		return cout;
	}

}

