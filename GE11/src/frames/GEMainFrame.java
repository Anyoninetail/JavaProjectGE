package frames;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import constants.GEConstants;
import menus.GEMenuBar;

public class GEMainFrame extends JFrame {
	private static GEMainFrame uniqueMainFrame = new GEMainFrame(GEConstants.TITLE_MAINFRAME);
	private GEDrawingPanel drawingPanel; //�г� ����
	private GEMenuBar menuBar; //�޴��� ����
	private GEToolBar shapeToolBar; //���� ����
	
	private GEMainFrame(String title) {
		super(title);
		//�г� �߰�
		drawingPanel = new GEDrawingPanel();
		add(drawingPanel);
		//�޴��� �߰�
		menuBar = new GEMenuBar();
		setJMenuBar(menuBar);
		//���� �߰�
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
