import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class SapperFunctional implements ActionListener {
	
	SapperGUI parent;
	
	SapperFunctional(SapperGUI parent) {
		this.parent = parent;
	}
	
	int minesAll;
	double time;
	int random;
	int amount;
	int[] ran = new int[10]; // Exists temporarily
	int[] amoun = new int[81];
	ArrayList<JButton> mines = new ArrayList<>();
	ArrayList<Integer> bombsCoords = new ArrayList<>();
	ImageIcon bombImg = new ImageIcon("images/Mine1.gif");
	//ImageIcon mine2 = new ImageIcon("images/Mine2.gif");
	
	
	public void actionPerformed(ActionEvent e) {
		JButton theButton = (JButton) e.getSource();
		
		if (theButton == parent.newGame) { newGame(); }

		if (mines.contains(theButton)) { endTheGame(); }

		if (parent.cells1.contains(theButton)) {
			int index = parent.cells1.indexOf(theButton);
			JButton cell = parent.cells1.get(index);
			if (!(amoun[index]==0)) {
				cell.setLabel(""+amoun[index]);

				cell.setEnabled(false);
				for (int r=0;r<10;r++) {
					if (mines.contains(theButton)) {
						cell.setLabel("");
					}
				}
			}
			if (amoun[index]==0) {
				cell.setEnabled(false);
			}
		}

	} 
	
	public void randomMines() {
		Random randomGenerate = new Random();
		int minesCount = 0;
		while(minesCount < 10) {
			random = randomGenerate.nextInt(81);
			if(!bombsCoords.contains(random)){
				minesCount++;
				mines.add(parent.cells1.get(random));
				bombsCoords.add(random);
				parent.cells1.get(random).setIcon(bombImg); // Temporarily
			}
		}
	}
	/*
	public void getNumbers() {
		
			for (int b=0; b<81; b++) {
				amount=0;
				for(int r=0; r<10; r++) {
					
				  if (b==0) {
					  if (parent.cells[b+1] == parent.cells[ran[r]]) {
							amount++;
						}
					  if (parent.cells[b+9] == parent.cells[ran[r]]) {
							amount++;
						}
					  if (parent.cells[b+10] == parent.cells[ran[r]]) {
							amount++;
						}
				  }else if (b==8) {
					  if (parent.cells[b-1] == parent.cells[ran[r]]) {
							amount++;
						}
					  if (parent.cells[b+8] == parent.cells[ran[r]]) {
							amount++;
						}
					  if (parent.cells[b+9] == parent.cells[ran[r]]) {
							amount++;
						}
				  }else if (b==72) {
					  if (parent.cells[b+1] == parent.cells[ran[r]]) {
							amount++;
						}
					  if (parent.cells[b-8] == parent.cells[ran[r]]) {
							amount++;
						}
					  if (parent.cells[b-9] == parent.cells[ran[r]]) {
							amount++;
						}  
				  }else if (b==80) {
					  if (parent.cells[b-1] == parent.cells[ran[r]]) {
							amount++;
						}
					  if (parent.cells[b-9] == parent.cells[ran[r]]) {
							amount++;
						}
					  if (parent.cells[b-10] == parent.cells[ran[r]]) {
							amount++;
						} 
				  }else if (b==9 || b==18 || b==27 || b==36 || b==45 || b==54 || b==63) {
						if (parent.cells[b-9] == parent.cells[ran[r]]) {
							amount++;
						}
						if (parent.cells[b-8] == parent.cells[ran[r]]) {
							amount++;
						}
						if (parent.cells[b+1] == parent.cells[ran[r]]) {
							amount++;
						}
						if (parent.cells[b+9] == parent.cells[ran[r]]) {
							amount++;
						}
						if (parent.cells[b+10] == parent.cells[ran[r]]) {
							amount++;
						}	
				  }else if (b==17 || b==26 || b==35 || b==44 || b==53 || b==62 || b==71) {
							if (parent.cells[b-10] == parent.cells[ran[r]]) {
								amount++;
							}
							if (parent.cells[b-9] == parent.cells[ran[r]]) {
								amount++;
							}
							if (parent.cells[b-1] == parent.cells[ran[r]]) {
								amount++;
							}
							if (parent.cells[b+8] == parent.cells[ran[r]]) {
								amount++;
							}
							if (parent.cells[b+9] == parent.cells[ran[r]]) {
								amount++;
							}
				  }else if (b>=1 && b<=7) {
						if (parent.cells[b-1] == parent.cells[ran[r]]) {
							amount++;
						}
						if (parent.cells[b+1] == parent.cells[ran[r]]) {
							amount++;
						}
						if (parent.cells[b+8] == parent.cells[ran[r]]) {
							amount++;
						}
						if (parent.cells[b+9] == parent.cells[ran[r]]) {
							amount++;
						}
						if (parent.cells[b+10] == parent.cells[ran[r]]) {
							amount++;
						}	
				  }else if (b>=73 && b<=79) {
					  if (parent.cells[b-10] == parent.cells[ran[r]]) {
							amount++;
						}
						if (parent.cells[b-9] == parent.cells[ran[r]]) {
							amount++;
						}
						if (parent.cells[b-8] == parent.cells[ran[r]]) {
							amount++;
						}
						if (parent.cells[b-1] == parent.cells[ran[r]]) {
							amount++;
						}
						if (parent.cells[b+1] == parent.cells[ran[r]]) {
							amount++;
						}
				  }else {
					if (parent.cells[b-10] == parent.cells[ran[r]]) {
						amount++;
					}
					if (parent.cells[b-9] == parent.cells[ran[r]]) {
						amount++;
					}
					if (parent.cells[b-8] == parent.cells[ran[r]]) {
						amount++;
					}
					if (parent.cells[b-1] == parent.cells[ran[r]]) {
						amount++;
					}
					if (parent.cells[b+1] == parent.cells[ran[r]]) {
						amount++;
					}
					if (parent.cells[b+8] == parent.cells[ran[r]]) {
						amount++;
					}
					if (parent.cells[b+9] == parent.cells[ran[r]]) {
						amount++;
					}
					if (parent.cells[b+10] == parent.cells[ran[r]]) {
						amount++;
					}
				  }  
				}
				if (b>=0 && b<81) {
					
					if (!(amount==0)) {
					parent.cells[b].setLabel(""+ amount); 
					}
					for(int r=0; r<10; r++) {
						if (b == ran[r]) {
							parent.cells[b].setLabel("");
					    }
					}
				}
				amoun[b] = amount;
			}
		
		

	}
*/
	public void newGame() {

		for (int b=0; b<81; b++) {
			parent.cells1.get(b).setEnabled(true);
			parent.cells1.get(b).setLabel("");
			parent.cells1.get(b).setIcon(null);

		}
		minesAll = 10;
		time = 0.0;

		mines.clear();
		bombsCoords.clear();

		randomMines();
		//getNumbers();

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
		int k=0;
		for (int b=0; b<81; b++) {
			
			if(!(amoun[b]==0)) {
			parent.cells1.get(b).setLabel(""+ amoun[b]);
			}
			parent.cells1.get(b).setEnabled(false);
			for(int r=0; r<10; r++) {
				parent.cells1.get(bombsCoords.get(r)).setIcon(bombImg);
				if (b == ran[r]) {
					parent.cells1.get(b).setLabel("");
				}
			}
			parent.statistic.setText("You lose");
			
		}
	}
	
}
