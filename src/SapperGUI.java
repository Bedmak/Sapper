import javax.swing.*;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.util.ArrayList;


public class SapperGUI {

	SapperFunctional sf;
	BorderLayout bl;
	GridLayout gl1;
	GridLayout gl2;
	JPanel windowedContent;
	JButton newGame;
	JLabel time;
	JLabel mines;
	JLabel statistic;
	JPanel cellsPanel;
	JPanel fields;
	JMenuBar menu;
	JMenu menuGame;
	JMenu menuAbout;
	JMenuItem menuItem;
	ArrayList<JButton> cells;


	SapperGUI() { 
		
		sf = new SapperFunctional(this);

		bl = new BorderLayout();
		gl1 = new GridLayout(9,9);
		gl2 = new GridLayout(1,3);
		
		

		windowedContent = new JPanel(); 
		windowedContent.setLayout(bl);
		
		
		cellsPanel = new JPanel();
		cellsPanel.setLayout(gl1);


		cells = new ArrayList<>();
		for (int b=0; b<81; b++) {
			JButton cell = new JButton("");
			cell.addActionListener(sf);
			cellsPanel.add(cell);
			cells.add(cell);
		}

		fields = new JPanel();
		fields.setLayout(gl2);
		
		newGame = new JButton("New Game");
		newGame.addActionListener(sf);
		mines = new JLabel("Mines");
		time = new JLabel("Time");
		statistic = new JLabel();


		fields.add(time); fields.add(newGame); fields.add(mines);
		
		menu = new JMenuBar();
		menuGame = new JMenu("Game");
		menuItem = new JMenuItem("New game");
		menuGame.add(menuItem);
		menuItem = new JMenuItem("Exit");
		menuGame.add(menuItem);
		menuAbout = new JMenu("About");
		menu.add(menuGame);
		menu.add(menuAbout);
		
		
		windowedContent.add("Center", cellsPanel);
		windowedContent.add("North", fields);
		windowedContent.add("South", statistic);
		

	}

	public void setUpGame() {
		JFrame frame = new JFrame();

		frame.setContentPane(windowedContent);
		frame.setJMenuBar(menu);
		frame.setSize(400, 300);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);

		sf.newGame();
	}

	
	public static void main(String[] args) {
		SapperGUI SG = new SapperGUI();
		SG.setUpGame();
		
	}

}
