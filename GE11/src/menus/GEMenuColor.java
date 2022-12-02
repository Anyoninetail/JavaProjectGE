package menus;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.GEConstants;
import constants.GEConstants.EColorMenuItems;
import frames.GEDrawingPanel;

public class GEMenuColor extends JMenu {
	private GEDrawingPanel drawingPanel;
	private ColorMenuHandler colorMenuHandler;
	
	public GEMenuColor(String titleColorMenu) {
		super(titleColorMenu);
		
		colorMenuHandler = new ColorMenuHandler();
		
		for(EColorMenuItems btn: EColorMenuItems.values()) {
			JMenuItem menuItem = new JMenuItem(btn.toString());
			menuItem.addActionListener(colorMenuHandler);
			menuItem.setActionCommand(btn.toString());
			this.add(menuItem);
		}
	}
	
	public void setLineColor() {
		Color lineColor = JColorChooser.showDialog(null, GEConstants.LINECOLOR_TITLE, null);
		if(lineColor != null) {
			drawingPanel.setLineColor(lineColor);
		}
	}
	
	public void setFillColor() {
		Color fillColor = JColorChooser.showDialog(null, GEConstants.FILLCOLOR_TITLE, null);
		if(fillColor != null) {
			drawingPanel.setFillColor(fillColor);
		}
	}
	
	public void delLineColor() {
		drawingPanel.setLineColor(GEConstants.FOREGROUND_COLOR);
	}
	
	public void delFillColor() {
		drawingPanel.setFillColor(GEConstants.BACKGROUND_COLOR);
	}
	
	public void init(GEDrawingPanel dp) {
		drawingPanel = dp;
	}
	
	private class ColorMenuHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			switch(EColorMenuItems.valueOf(e.getActionCommand())) {
            case SetLineColor: setLineColor(); break;
            case SetFillColor: setFillColor(); break;
            case ClearLineColor: delLineColor(); break;
            case ClearFillColor: delFillColor(); break;
            }
		}
	}
}
