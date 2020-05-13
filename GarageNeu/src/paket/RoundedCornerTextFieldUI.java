package paket;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.border.AbstractBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.metal.MetalTextFieldUI;

public class RoundedCornerTextFieldUI extends MetalTextFieldUI {
	private final static int ARC_WIDTH = 22;
	private final static int ARC_HEIGHT = 22;
	private final static int x = 0;
	private final static int y = 0;
	private final static int BORDER_TOP = 4;
	private final static int BORDER_BOTTOM = 4;
	private final static int BORDER_LEFT = 4;
	private final static int BORDER_RIGHT = 4;

	public static ComponentUI createUI(JComponent jComponent) {
		return new RoundedCornerTextFieldUI();
	}

	public void installUI(JComponent jComponent) {
		super.installUI(jComponent);
		jComponent.setBorder(new RoundedBorder());
		jComponent.setOpaque(false);
	}

	protected void paintSafely(Graphics graphics) {
		JComponent component = getComponent();
		if (!component.isOpaque()) {
			graphics.setColor(component.getBackground());
			graphics.fillRoundRect(x, y, component.getWidth() - 1, component.getHeight() - 1, ARC_WIDTH, ARC_HEIGHT);
		}
		super.paintSafely(graphics);
	}

	private static class RoundedBorder extends AbstractBorder {
		private static final long serialVersionUID = 1L;

		public void paintBorder(Component coponent, Graphics graphics, int x, int y, int width, int height) {
			Color oldColor = graphics.getColor();
			graphics.setColor(Color.gray);
			graphics.drawRoundRect(x, y, width - 1, height - 1, ARC_WIDTH, ARC_HEIGHT);
			graphics.setColor(oldColor);
		}

		public Insets getBorderInsets(Component c) {
			return new Insets(BORDER_TOP, BORDER_LEFT, BORDER_BOTTOM, BORDER_RIGHT);
		}

	}

}
