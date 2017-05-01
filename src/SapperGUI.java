import javax.swing.*;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.util.ArrayList;


public class SapperGUI {

	private SapperFunctional sf;
	private JFrame frame;
	private JButton newGame;
	private JLabel time;
	private JLabel bombsCount;
	private JLabel status;
	private JMenuItem menuGameItem1;
	private JMenuItem menuGameItem2;
	private ArrayList<JButton> cells;
	private int fieldSize;
	private int bombsAll;

	public SapperGUI() {
		this(9, 10);
	}

	public SapperGUI(int size, int bombs) {

		setSize(size);
		setBombs(bombs);


		BorderLayout bl = new BorderLayout();
		GridLayout gl1 = new GridLayout(size, size);
		GridLayout gl2 = new GridLayout(1,3);



		JPanel windowedContent = new JPanel();
		windowedContent.setLayout(bl);


		JPanel cellsPanel = new JPanel();
		cellsPanel.setLayout(gl1);


		cells = new ArrayList<>();
		sf = new SapperFunctional(this);
		for (int i = 0; i < fieldSize; i++) {
			JButton cell = new JButton("");
			cell.addActionListener(sf);
			cellsPanel.add(cell);
			cells.add(cell);
		}

		JPanel fields = new JPanel();
		fields.setLayout(gl2);
		
		newGame = new JButton("New Game");
		newGame.addActionListener(sf);
		bombsCount = new JLabel();
		time = new JLabel("Time");
		status = new JLabel("");


		fields.add(time); fields.add(newGame); fields.add(bombsCount);
		
		JMenuBar menu = new JMenuBar();
		JMenu menuGame = new JMenu("Game");
		menuGameItem1 = new JMenuItem("New game");
		menuGameItem1.addActionListener(sf);
		menuGame.add(menuGameItem1);
		menuGameItem2 = new JMenuItem("Exit");
		menuGameItem2.addActionListener(sf);
		menuGame.add(menuGameItem2);
		JMenu menuAbout = new JMenu("About");
		menu.add(menuGame);
		menu.add(menuAbout);
		
		
		windowedContent.add("Center", cellsPanel);
		windowedContent.add("North", fields);
		windowedContent.add("South", status);

		frame = new JFrame();

		frame.setContentPane(windowedContent);
		frame.setJMenuBar(menu);
		frame.setSize(45 * size, 40 * size);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public void setUpGame() {
		sf.newGame();
	}

	public void setSize(int size) { fieldSize = size * size; }
	public int getSize() { return fieldSize; }

	public void setBombs(int bombs) { bombsAll = bombs ;}
	public int getBombs() { return bombsAll;}

	public void setCount(int bombs) { bombsCount.setText("Bombs - " + bombs); }
	public void setStatus(String text) { status.setText(text); }

	public void dispose() { frame.dispose(); }

	public JButton getButton() { return newGame; }
	public JMenuItem getMenuItem1() { return menuGameItem1; }
	public JMenuItem getMenuItem2() { return menuGameItem2; }
	public ArrayList<JButton> getCells() { return cells; }


	public static void main(String[] args) {
		SapperGUI sg = new SapperGUI();
		sg.setUpGame();
		
	}

}
