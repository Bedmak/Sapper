import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class SapperFunctional implements ActionListener {
	
	SapperGUI parent;
	int minesAll;
	double time;
	int random;
	ArrayList<JButton> minesCells = new ArrayList<>();
	//ArrayList<Integer> bombsCoords = new ArrayList<>();
	ImageIcon bombImg = new ImageIcon("images/Mine1.gif");
	//ImageIcon mine2 = new ImageIcon("images/Mine2.gif");
	
	SapperFunctional(SapperGUI parent) {
		this.parent = parent;
	}

	
	public void actionPerformed(ActionEvent e) {
		JButton theButton = (JButton) e.getSource();
		
		if (theButton == parent.newGame) { newGame(); }

		if (minesCells.contains(theButton)) { endTheGame(); }

		if (parent.cells.contains(theButton)) {
			theButton.setEnabled(false);
		}

	} 
	
	public void randomMines(int mines) {
		Random randomGenerate = new Random();
		ArrayList<Integer> bombsCoords = new ArrayList<>();
		int minesCount = 0;
		while(minesCount < mines) {
			random = randomGenerate.nextInt(81);
			if(!bombsCoords.contains(random)){
				minesCount++;
				minesCells.add(parent.cells.get(random));
				bombsCoords.add(random);
				parent.cells.get(random).setIcon(bombImg); // Temporarily
			}
		}
	}

	public void getNumbers() {

		int[] neighborNums = {-10, -9, -8, -1, 1, 8, 9, 10};

		for(JButton cell: minesCells){
			int index = parent.cells.indexOf(cell);
			for(int neighbor: neighborNums) {
				try{
					JButton neighborCell = parent.cells.get(index + neighbor);
					if(!minesCells.contains(neighborCell) &&
							!(((index % 9 == 0) && (index + neighbor) % 9 == 8) ||
							((index % 9 == 8) && (index + neighbor) % 9 == 0))) { // If the cell is not a bomb and is not on the left or right edge
								if(!neighborCell.getLabel().equals("")){
									int count = Integer.parseInt(neighborCell.getLabel()) + 1;
									neighborCell.setLabel("" + count);
								}else {
									neighborCell.setLabel("1");
								}
					}
				}catch(IndexOutOfBoundsException e) {
					continue;
				}
			}
		}
	}

	public void newGame() {

		for (int b=0; b<81; b++) {
			parent.cells.get(b).setEnabled(true);
			parent.cells.get(b).setLabel("");
			parent.cells.get(b).setIcon(null);
		}
		minesAll = 10;
		time = 0.0;

		minesCells.clear();

		randomMines(minesAll);
		getNumbers();
	}
	/*
	void OpenFields() {
		for (int b=0; b<81; b++) {
			if (!(amoun[b]==0)) {
			parent.cells[b].setLabel(""+amoun[b]);
			parent.cells[b].setEnabled(false);
				for(int r=0; r<10; r++) {
					if (!(b-1 == ran[r])) {
						parent.cells[b-1].setLabel(""+amoun[b-1]);
					} 
				} 
			}
			if (!(b==0)) {
				while (parent.cells[b-1].equals("")) {
					parent.cells[b-1].setEnabled(false);
			
				}
			}
		}
	}
*/

	void endTheGame() {
		for (JButton cell: parent.cells) {
			cell.setEnabled(false);
			//if(minesCells.contains(cell))
			//	cell.setIcon(bombImg);
			}
        parent.statistic.setText("You lose");
	}
}
