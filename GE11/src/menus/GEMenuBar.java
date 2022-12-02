package menus;

import javax.swing.JMenuBar;

import constants.GEConstants;
import frames.GEDrawingPanel;

public class GEMenuBar extends JMenuBar {
	private static final long serialVersionUID = 1L;
	private GEMenuFile fileMenu;
	private GEMenuEdit editMenu;
	private GEMenuColor colorMenu;
	
	public GEMenuBar() {
		fileMenu = new GEMenuFile(GEConstants.TITLE_FILEMENU);
		add(fileMenu);
		editMenu = new GEMenuEdit(GEConstants.TITLE_EDITMENU);
		add(editMenu);
		colorMenu = new GEMenuColor(GEConstants.TITLE_COLORMENU);
		add(colorMenu);
	}
	
	public void init(GEDrawingPanel dp) {
		colorMenu.init(dp);
	}
}
