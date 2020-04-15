package extensores;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JPanel;

public class JPanelEx extends JPanel {
	
	
	/**
	 * Adiciona arrays de componentes
	 * @param c - componente
	 */
	public void add(Component[] c) {
		for (int i = 0; i < c.length; i++) {
			this.add(c[i]);
		}
	}
	/**
	 * Adiciona arraylists de componentes
	 * @param c - componente
	 */
	public void add(ArrayList<Component> c) {
		for (int i = 0; i < c.size(); i++) {
			this.add(c.get(i));
		}
	}
	
	public JPanelEx() {
	}
}
