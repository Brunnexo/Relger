package janelas;

import java.awt.AWTException;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.net.MalformedURLException;

public class SystemTrayNotification {

	public SystemTrayNotification() {
	}

	public void displayTray(String message, String submessage, MessageType messageType) throws AWTException, MalformedURLException {
		SystemTray tray = SystemTray.getSystemTray();
		
		TrayIcon trayIcon = new TrayIcon(Toolkit.getDefaultToolkit().createImage("icon.png"));
		
		tray.add(trayIcon);
		
		trayIcon.displayMessage(message, submessage, messageType);
	}

	public static void notifyTray(String message, String sub, MessageType type) {
		SystemTrayNotification stn = new SystemTrayNotification();

		if (SystemTray.isSupported()) {
			try{
				stn.displayTray(message, sub, type);
			}catch(AWTException ex){

			}catch(MalformedURLException ex){

			}
		} else {
			System.err.println("Bandeija não suportada!");
		}

	}
}