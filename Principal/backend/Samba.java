package backend;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import jcifs.smb.SmbFile;

public class Samba {

	public static void converter(SmbFile arquivo, File temp) 
	{
		try {
			InputStream leituraRemota = arquivo.getInputStream();
			OutputStream escritaLocal = new FileOutputStream(temp);

			int buffersize = 1024;

			byte[] b = new byte[buffersize];

			int bytes = 0;
			while ((bytes = leituraRemota.read(b)) != -1)
			{
				escritaLocal.write(b, 0, bytes);
			}
			escritaLocal.close();
			leituraRemota.close();
		} catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}
}