package backend;

import java.util.prefs.Preferences;

public class Prefs {

	//Identidade da Aplicação
	private static String APPID = "jss.bnx.relger";
	private Preferences prefs = Preferences.userRoot().node(APPID);

	//Chaves SQL
	public final static String SQL_IP = "IP@SQL";
	public final static String SQL_PORT = "PORT@SQL";
	public final static String SQL_DB = "DATABASE@SQL";
	public final static String SQL_USER = "USER@SQL";
	public final static String SQL_PASSWORD = "PASSWORD@SQL";
	
	//Chaves DESKTOP
	public final static String SHARED_DOMAIN = "DOMAIN@SHARED";
	public final static String SHARED_USER = "USER@SHARED";
	public final static String SHARED_PASSWORD = "PASSWORD@SHARED";
	public final static String SHARED_ADDRESS = "ADDRESS@SHARED";
	public final static String SHARED_FOLDER = "FOLDER@SHARED";
	public final static String SHARED_FILE = "FILE@SHARED";
	
	public void setBoolean(String key, boolean value) {
		this.prefs.putBoolean(key, value);
		System.out.println("Set: " + key + " = " + value);
	}
	
	public boolean getBoolean(String key, boolean def) {
		return this.prefs.getBoolean(key, def);
	}
	
	public void setString(String key, String value) {
		this.prefs.put(key, value);
		System.out.println("Set: " + key + " = " + value);
	}
	
	public String getString(String key, String def) {
		return this.prefs.get(key, def);
	}
	
	public void setInt(String key, int value) {
		this.prefs.putInt(key, value);
		System.out.println("Set: " + key + " = " + value);
	}
	
	public int getInt(String key, int def) {
		return this.prefs.getInt(key, def);
	}
}