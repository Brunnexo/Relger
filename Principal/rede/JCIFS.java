package rede;

import backend.Crypto;
import backend.Prefs;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;

public class JCIFS {
	
	public static SmbFile sharedFile;
	
	public static boolean getConnection() {
		Prefs prefs = new Prefs();

		try {
			sharedFile = new SmbFile(

					"smb://share:@" +
							prefs.getString(Prefs.SHARED_ADDRESS, "") +
							prefs.getString(Prefs.SHARED_FOLDER, "") +
							prefs.getString(Prefs.SHARED_FILE, ""),

							new NtlmPasswordAuthentication(
									prefs.getString(Prefs.SHARED_DOMAIN, ""),
									Crypto.descriptogragar(prefs.getString(Prefs.SHARED_USER, "")),
									Crypto.descriptogragar(prefs.getString(Prefs.SHARED_PASSWORD, "")))
					);
			if (sharedFile.exists())
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}
}