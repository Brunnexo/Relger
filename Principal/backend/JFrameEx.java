package backend;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JFrame;
import javax.swing.event.MouseInputAdapter;

public class JFrameEx extends JFrame {

	Point location;
	MouseEvent pressed;

	MouseInputAdapter mip = new MouseInputAdapter() {
		public void mousePressed(MouseEvent me) {
			pressed = me;
		}
		public void mouseDragged(MouseEvent me) {
			Component component = me.getComponent();
			location = component.getLocation(location);
			int x = location.x - pressed.getX() + me.getX();
			int y = location.y - pressed.getY() + me.getY();
			component.setLocation(x, y);
		}
	};

	/**
	 * Define a janela como arrastável pelo mouse</br>
	 * Automaticamente, o parâmetro <code>setUndecorated</code></br>
	 * será definido como <b>true</b>
	 * @param b
	 */
	public void setDraggable(boolean b) {
		if (b) {
			if (this.isUndecorated()) {
				this.addMouseListener(mip);
				this.addMouseMotionListener(mip);
			} else {
				this.setUndecorated(b);
				this.addMouseListener(mip);
				this.addMouseMotionListener(mip);
			}
		} else {
			if (this.isUndecorated()) {
				this.removeMouseListener(mip);
				this.removeMouseMotionListener(mip);
			}
		}
	}

	/**
	 * Define os cantos arredondados da janela</br>
	 * Automaticamente o parâmetro <code>setUndecorated</code></br>
	 * será definido como <b>true</b>.
	 * @param radius
	 */
	public void setRoundCorner(int radius) {
		if (this.isUndecorated()) {
			this.setShape(new RoundRectangle2D.Double(0,0,this.getSize().width,this.getSize().height,radius,radius));
		} else {
			this.setUndecorated(true);
			this.setShape(new RoundRectangle2D.Double(0,0,this.getSize().width,this.getSize().height,radius,radius));
		}
	}

	public JFrameEx(String titulo) {
	}
}