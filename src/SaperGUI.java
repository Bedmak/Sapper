import javax.swing.*;

//import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;

public class SaperGUI {

	JPanel windowedContent;
	JButton newGame;
	JLabel Time;
	JLabel Mines;
	JLabel Stat;
	JPanel Cells;
	JPanel Fields;
	JMenuBar Menu;
	JMenu MenuGame;
	JMenu MenuAbout;
	JMenuItem MenuItem;
	JButton cells[];


	SaperGUI() { 
		
		SaperFunctional SF = new SaperFunctional(this);
		
		BorderLayout BL = new BorderLayout();
		GridLayout GL1 = new GridLayout(9,9);
		GridLayout GL2 = new GridLayout(1,3);
	//	GridBagLayout GBL = new GridBagLayout();
		
		

		windowedContent = new JPanel(); 
		windowedContent.setLayout(BL);
		
		
		Cells = new JPanel();
		Cells.setLayout(GL1);
		
		cells = new JButton[81];
		for (int b=0; b<81; b++) {
			cells[b] = new JButton("");
			cells[b].addActionListener(SF);
			Cells.add(cells[b]);
		}
		

		
		Fields = new JPanel();
		Fields.setLayout(GL2);
		
		newGame = new JButton("New Game");
		newGame.addActionListener(SF);
		Mines = new JLabel("Mines");
		Time = new JLabel("Time");
		Stat = new JLabel();


		Fields.add(Time); Fields.add(newGame); Fields.add(Mines);
		
		Menu = new JMenuBar();
		MenuGame = new JMenu("Game");
		MenuItem = new JMenuItem("New game");
		MenuGame.add(MenuItem);
		MenuItem = new JMenuItem("Exit");
		MenuGame.add(MenuItem);
		MenuAbout = new JMenu("About");
		Menu.add(MenuGame);
		Menu.add(MenuAbout);
		
		
		windowedContent.add("Center",Cells);
		windowedContent.add("North", Fields);
		windowedContent.add("South", Stat);
		
		JFrame frame = new JFrame();
		frame.setContentPane(windowedContent);
		frame.setJMenuBar(Menu);
		frame.setSize(400, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
		
		

	}

	
	public static void main(String[] args) {
		SaperGUI SG = new SaperGUI();
		
	}

}
