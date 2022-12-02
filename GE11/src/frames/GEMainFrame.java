package frames;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import constants.GEConstants;
import menus.GEMenuBar;

public class GEMainFrame extends JFrame {
	private static GEMainFrame uniqueMainFrame = new GEMainFrame(GEConstants.TITLE_MAINFRAME);
	private GEDrawingPanel drawingPanel; //패널 선언
	private GEMenuBar menuBar; //메뉴바 선언
	private GEToolBar shapeToolBar; //툴바 선언
	
	private GEMainFrame(String title) {
		super(title);
		//패널 추가
		drawingPanel = new GEDrawingPanel();
		add(drawingPanel);
		//메뉴바 추가
		menuBar = new GEMenuBar();
		setJMenuBar(menuBar);
		//툴바 추가
		shapeToolBar = new GEToolBar(GEConstants.TITLE_SHAPETOOLBAR);
		add(BorderLayout.NORTH, shapeToolBar);
	}
	
	public static GEMainFrame getUniqueMainFrame() {
		return uniqueMainFrame;
	}
	
	public void init() {
		shapeToolBar.init(drawingPanel);
		menuBar.init(drawingPanel);
		this.setSize(GEConstants.WIDTH_MAINFRAME, GEConstants.HEIGHT_MAINFRAME);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
