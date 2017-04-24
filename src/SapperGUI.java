import javax.swing.*;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.util.ArrayList;


public class SapperGUI {

	SapperFunctional sf;
	BorderLayout bl;
	GridLayout gl1;
	GridLayout gl2;
	JFrame frame;
	JPanel windowedContent;
	JButton newGame;
	JLabel time;
	JLabel bombsCount;
	JLabel statistic;
	JPanel cellsPanel;
	JPanel fields;
	JMenuBar menu;
	JMenu menuGame;
	JMenu menuAbout;
	JMenuItem menuGameItem1;
	JMenuItem menuGameItem2;
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
		bombsCount = new JLabel("Bombs -" + 0);
		time = new JLabel("Time");
		statistic = new JLabel("");


		fields.add(time); fields.add(newGame); fields.add(bombsCount);
		
		menu = new JMenuBar();
		menuGame = new JMenu("Game");
		menuGameItem1 = new JMenuItem("New game");
		menuGameItem1.addActionListener(sf);
		menuGame.add(menuGameItem1);
		menuGameItem2 = new JMenuItem("Exit");
		menuGameItem2.addActionListener(sf);
		menuGame.add(menuGameItem2);
		menuAbout = new JMenu("About");
		menu.add(menuGame);
		menu.add(menuAbout);
		
		
		windowedContent.add("Center", cellsPanel);
		windowedContent.add("North", fields);
		windowedContent.add("South", statistic);

		frame = new JFrame();

		frame.setContentPane(windowedContent);
		frame.setJMenuBar(menu);
		frame.setSize(400, 350);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public void setUpGame() {
		sf.newGame(10);
	}

	
	public static void main(String[] args) {
		SapperGUI sg = new SapperGUI();
		sg.setUpGame();
		
	}

}
