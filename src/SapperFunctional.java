import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class SapperFunctional implements ActionListener {
	
	private SapperGUI sapperGUI;
	private int bombsAll;
	private int countCheck;
	private ArrayList<JButton> cells;
	private ArrayList<JButton> bombsCells;
	private boolean[] checked;
	private int[] cellsValue;
 	private ImageIcon bombImg;
	
	public SapperFunctional(SapperGUI sapperGUI) {
		this.sapperGUI = sapperGUI;
		cells = sapperGUI.getCells();
		bombsCells = new ArrayList<>();
		bombImg = new ImageIcon("images/bomb.png");
		checked = new boolean[sapperGUI.getSize()];
		cellsValue = new int[sapperGUI.getSize()];
		bombsAll = sapperGUI.getBombs();
	}

	
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() instanceof JButton) {
			JButton theButton = (JButton) e.getSource();

			if (theButton == sapperGUI.getButton()) {
				newGame();
			} else if (bombsCells.contains(theButton)) {
				endTheGame(false);
			} else if (cells.contains(theButton)) {
				OpenFields(theButton);
			}
		} else if (e.getSource() instanceof JMenuItem) {
			JMenuItem menuItem = (JMenuItem) e.getSource();

			if (menuItem == sapperGUI.getMenuItem1()) {
				newGame();
			}else if (menuItem == sapperGUI.getMenuItem2()) {
				sapperGUI.dispose();
			}
		}
	} 
	
	public void randomMines(int mines) {
		Random randomGenerate = new Random();
		ArrayList<Integer> bombsCoords = new ArrayList<>();
		int minesCount = 0;
		while(minesCount < mines) {
			int random = randomGenerate.nextInt(sapperGUI.getSize());
			if(!bombsCoords.contains(random)){
				minesCount++;
				bombsCells.add(cells.get(random));
				cellsValue[random] = -1;
				cells.get(random).setText("");
				getNumbers(random);
				bombsCoords.add(random);
				//cells.get(random).setIcon(bombImg); //For check
			}
		}
	}

	public void getNumbers(int index) {

		int[] neighborNums = {-10, -9, -8, -1, 1, 8, 9, 10};

		for(int neighbor: neighborNums) {
			if(index + neighbor >= 0 && index + neighbor <= sapperGUI.getSize() - 1) {
				JButton neighborCell = cells.get(index + neighbor);
				if(!bombsCells.contains(neighborCell) && !(((index % 9 == 0) && (index + neighbor) % 9 == 8) ||
							((index % 9 == 8) && (index + neighbor) % 9 == 0))) { // If the cell is not a bomb and is not on the left or right edge
								if(cellsValue[index + neighbor] != 0){
									cellsValue[index + neighbor]++;
									//neighborCell.setText("" + cellsValue[index + neighbor]); //For check
								}else {
									cellsValue[index + neighbor] = 1;
									//neighborCell.setText("1"); //For check
								}
				}
			}
		}
	}

	public void newGame() {

		int k = 0;
		countCheck = 0;
		for (JButton cell: cells) {
			checked[k] = false;
			cellsValue[k] = 0;
			cell.setEnabled(true);
			cell.setText("");
			cell.setIcon(null);
			k++;
		}
		sapperGUI.setStatus("");
		sapperGUI.setCount(bombsAll);

		bombsCells.clear();
		randomMines(bombsAll);

	}

	public void OpenFields(JButton theButton) {
		int index = cells.indexOf(theButton);
		if(cellsValue[index] != 0) {
			theButton.setText("" + cellsValue[index]);
			theButton.setEnabled(false);
			countCheck++;
			checked[index] = true;
		} else {
			int[] neighborNums = {-10, -9, -8, -1, 1, 8, 9, 10};
			for (int neighbor : neighborNums) {
				theButton.setEnabled(false);
				if(!checked[index]) {
					countCheck++;
					checked[index] = true;
				}
				if((index + neighbor >= 0 && index + neighbor <= sapperGUI.getSize() - 1) && (!checked[index + neighbor]) && !(((index % 9 == 0) && (index + neighbor) % 9 == 8) ||
						((index % 9 == 8) && (index + neighbor) % 9 == 0))) {
					OpenFields(cells.get(index + neighbor));
				}
			}
		}
		if (countCheck == sapperGUI.getSize() - sapperGUI.getBombs()) {
			endTheGame(true);
		}
	}


	public void endTheGame(boolean status) {

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
			sapperGUI.setStatus("You win!");
		}else {
			sapperGUI.setStatus("You lose!");
		}
	}
}
