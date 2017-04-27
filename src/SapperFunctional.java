import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class SapperFunctional implements ActionListener {
	
	SapperGUI parent;
	int bombsAll;
	int random;
	ArrayList<JButton> bombsCells;
	boolean[] checked;
 	ImageIcon bombImg;
	
	SapperFunctional(SapperGUI parent) {
		this.parent = parent;
		bombsCells = new ArrayList<>();
		bombImg = new ImageIcon("images/bomb.png");
		checked = new boolean[parent.getSize()];
		bombsAll = parent.getBombs();
	}

	
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() instanceof JButton) {
			JButton theButton = (JButton) e.getSource();

			if (theButton == parent.newGame) {
				newGame();
			} else if (bombsCells.contains(theButton)) {
				endTheGame();
			} else if (parent.cells.contains(theButton)) {
				OpenFields(theButton);
			}
		} else if (e.getSource() instanceof JMenuItem) {
			JMenuItem menuItem = (JMenuItem) e.getSource();

			if (menuItem == parent.menuGameItem1) {
				newGame();
			}else if (menuItem == parent.menuGameItem2) {
				parent.frame.dispose();
			}
		}
	} 
	
	public void randomMines(int mines) {
		Random randomGenerate = new Random();
		ArrayList<Integer> bombsCoords = new ArrayList<>();
		int minesCount = 0;
		while(minesCount < mines) {
			random = randomGenerate.nextInt(parent.getSize());
			if(!bombsCoords.contains(random)){
				minesCount++;
				bombsCells.add(parent.cells.get(random));
				parent.cells.get(random).setText("");
				getNumbers(random);
				bombsCoords.add(random);
				parent.cells.get(random).setIcon(bombImg); // Temporarily
			}
		}
	}

	public void getNumbers(int index) {

		int[] neighborNums = {-10, -9, -8, -1, 1, 8, 9, 10};

		for(int neighbor: neighborNums) {
			if(index + neighbor >= 0 && index + neighbor <= parent.getSize() - 1) {
				JButton neighborCell = parent.cells.get(index + neighbor);
				if(!bombsCells.contains(neighborCell) && !(((index % 9 == 0) && (index + neighbor) % 9 == 8) ||
							((index % 9 == 8) && (index + neighbor) % 9 == 0))) { // If the cell is not a bomb and is not on the left or right edge
								if(!neighborCell.getText().equals("")){
									int count = Integer.parseInt(neighborCell.getText()) + 1;
									neighborCell.setText("" + count);
								}else {
									neighborCell.setText("1");
								}
				}
			}
		}
	}

	public void newGame() {


		int k = 0;
		for (JButton cell: parent.cells) {
			checked[k] = false;
			cell.setEnabled(true);
			cell.setText("");
			cell.setIcon(null);
			k++;
		}
		parent.statistic.setText("");
		parent.bombsCount.setText("Bombs - " + bombsAll);

		bombsCells.clear();
		randomMines(bombsAll);
	}

	public void OpenFields(JButton theButton) {

		if(!theButton.getText().equals("")) {
			theButton.setEnabled(false);
		} else {
			int[] neighborNums = {-10, -9, -8, -1, 1, 8, 9, 10};
			for (int neighbor : neighborNums) {
				theButton.setEnabled(false);
				int index = parent.cells.indexOf(theButton);
				checked[index] = true;
				if((index + neighbor >= 0 && index + neighbor <= parent.getSize() - 1) && (!checked[index + neighbor]) && !(((index % 9 == 0) && (index + neighbor) % 9 == 8) ||
						((index % 9 == 8) && (index + neighbor) % 9 == 0))) {
					OpenFields(parent.cells.get(index + neighbor));	// StackOverflowError
				}
			}
		}
	}


	public void endTheGame() {
		for (JButton cell: parent.cells) {
			cell.setEnabled(false);
			//if(bombsCells.contains(cell))
			//	cell.setIcon(bombImg);
			}
		parent.statistic.setText("You lose");
	}
}
