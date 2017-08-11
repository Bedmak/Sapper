import javax.swing.*;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class SapperGUI {

	private SapperFunctional sf;
	private JFrame frame;
	private JButton newGame;
	private JLabel time;
	private JLabel bombsCount;
	private JLabel statusLabel;
	private JMenuItem menuGameItem1;
	private JMenuItem menuGameItem2;
	private ImageIcon bombImg;
	private ArrayList<JButton> cells;
	private int fieldSize;
	private int bombsAll;
	private ArrayList<JButton> bombsCells;
	private int[] cellsValue;
	private boolean[] checked;
	private Timer timer;
	private int seconds;

	public SapperGUI() {
		this(9, 10);
	}

	public SapperGUI(int size, int bombs) {

		sf = new SapperFunctional();
		fieldSize = size * size;
		bombsAll = bombs;
		bombImg = new ImageIcon("images/bomb.png");
		cells = new ArrayList<>();
		bombsCells = new ArrayList<>();
		cellsValue = new int[fieldSize];
		checked = new boolean[fieldSize];


		BorderLayout bl = new BorderLayout();
		GridLayout gl1 = new GridLayout(size, size);
		GridLayout gl2 = new GridLayout(1,3);

		JPanel windowedContent = new JPanel();
		windowedContent.setLayout(bl);

		JPanel cellsPanel = new JPanel();
		cellsPanel.setLayout(gl1);



		for (int i = 0; i < fieldSize; i++) {
			JButton cell = new JButton("");
			cell.addActionListener( event -> {
				JButton theButton = (JButton) event.getSource();
				if (bombsCells.contains(theButton)) {
					endGame(false);
				} else if (cells.contains(theButton)) {
					sf.OpenFields(cells.indexOf(theButton), fieldSize, cellsValue, checked);
					if (sf.countCheck == fieldSize - bombsAll) {
						endGame(true);
					}
					refreshFields();
				}
			});
			cellsPanel.add(cell);
			cells.add(cell);
		}

		JPanel fields = new JPanel();
		fields.setLayout(gl2);
		
		newGame = new JButton("New Game");
		newGame.addActionListener( event -> setUpGame() );
		bombsCount = new JLabel();
		time = new JLabel("Time - 0");
		statusLabel = new JLabel("");


		fields.add(time); fields.add(newGame); fields.add(bombsCount);
		
		JMenuBar menu = new JMenuBar();
		JMenu menuGame = new JMenu("Game");
		menuGameItem1 = new JMenuItem("New game");
		menuGameItem1.addActionListener( event -> setUpGame() );
		menuGame.add(menuGameItem1);
		menuGameItem2 = new JMenuItem("Exit");
		menuGameItem2.addActionListener( event -> frame.dispose() );
		menuGame.add(menuGameItem2);
		JMenu menuAbout = new JMenu("About");
		menu.add(menuGame);
		menu.add(menuAbout);
		
		
		windowedContent.add("Center", cellsPanel);
		windowedContent.add("North", fields);
		windowedContent.add("South", statusLabel);

		frame = new JFrame();

		frame.setContentPane(windowedContent);
		frame.setJMenuBar(menu);
		frame.setSize(45 * size, 40 * size);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public void setUpGame() {

		int i = 0;
		for (JButton cell: cells) {
			cell.setEnabled(true);
			cell.setText("");
			cell.setIcon(null);
			checked[i] = false;
			cellsValue[i] = 0;
			i++;
		}
		statusLabel.setText("");
		bombsCount.setText("Bombs - " + bombsAll);
		bombsCells.clear();
		sf.countCheck = 0;

		//Work with a timer
		seconds = 0;
		if(timer != null) {
			timer.cancel();
		}
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				time.setText("Time - " + seconds++);
			}
		}, 0, 1000);


		ArrayList<Integer> bombsCoords = sf.randomBombs(fieldSize, bombsAll);
		for(int coordinate: bombsCoords) {
			bombsCells.add(cells.get(coordinate));
			cells.get(coordinate).setText("");
			cellsValue[coordinate] = -1;
			sf.getNumbers(coordinate, fieldSize, bombsCoords, cellsValue);
		}
	}

	public void refreshFields() {
		for (int index = 0; index < fieldSize; index++) {
			if (checked[index]) {
				cells.get(index).setEnabled(false);
				if(cellsValue[index] != 0)
					cells.get(index).setText("" + cellsValue[index]);

			}
		}

	}

	public void endGame(boolean status) {

		timer.cancel();
		int k = 0;
		for (JButton cell: cells) {
			if (bombsCells.contains(cell)) {
				cell.setIcon(bombImg);
			} else if (cellsValue[k] != 0) {
				cell.setText("" + cellsValue[k]);
			}
			cell.setEnabled(false);
			k++;
		}
		if(status) {
			statusLabel.setText("You win!");
		}else {
			statusLabel.setText("You lose!");
		}
	}


	public static void main(String[] args) {
		SapperGUI sg = new SapperGUI();
		sg.setUpGame();
		
	}

}
