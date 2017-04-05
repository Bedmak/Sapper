import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class SapperFunctional implements ActionListener {
	
	SapperGUI parent;
	
	SapperFunctional(SapperGUI parent) {
		this.parent = parent;
	}
	
	int mines = 10;
	double time = 0.0;
	int random;
	int amount;
	int[] ran = new int[10];
	int[] amoun = new int[81];
	ImageIcon bombImg = new ImageIcon("images/Mine1.gif");
	//ImageIcon mine2 = new ImageIcon("images/Mine2.gif");
	
	
	public void actionPerformed(ActionEvent e) {
		JButton theButton = (JButton) e.getSource();
		
		if (theButton == parent.newGame) { newGame(); }
		
		for (int r=0; r<10; r++) {
			if (theButton == parent.cells[ran[r]]) { EndTheGame(); }
		}
		
		for (int b=0; b<81; b++) {
			if (theButton == parent.cells[b]) {
				if (!(amoun[b]==0)) {
				parent.cells[b].setLabel(""+amoun[b]);
			
				parent.cells[b].setEnabled(false);
					for (int r=0;r<10;r++) {
						if (theButton == parent.cells[ran[r]]) {
							parent.cells[ran[r]].setLabel("");
						}
					}
				}
				if (amoun[b]==0) {
				parent.cells[b].setEnabled(false);
				}
			}
		}
	} 
	
	public void RandomMines() {
		int p=-1;
		Random randomGenerate = new Random();
		for (int i=0; i<10; i++) {
			 ran[i] = -1;
		}
		for (int r=0; r<10; r++) {
		random = randomGenerate.nextInt(81);	
		
			for (int ra=0; ra<10; ra++) {
				if (ran[ra] == random) {
					r--;
				}
			}
			if (p==r) {
			continue;
			}
		ran[r] = random;
		parent.cells[random].setIcon(bombImg);
		p = r;
		}
				
		
	}
	
	public void GetNumbers() {
		
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

	public void newGame() {

		for (int b=0; b<81; b++) {
			parent.cells[b].setEnabled(true);
			parent.cells[b].setLabel("");
			parent.cells[b].setIcon(null);

		}

		RandomMines();
		GetNumbers();

		mines = 10;
		time = 0.0;
	}
	
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

	
	
	void EndTheGame() {
		int k=0;
		for (int b=0; b<81; b++) {
			
			if(!(amoun[b]==0)) {
			parent.cells[b].setLabel(""+ amoun[b]);
			}
			parent.cells[b].setEnabled(false);
			for(int r=0; r<10; r++) {
				parent.cells[ran[r]].setIcon(bombImg);
				if (b == ran[r]) {
					parent.cells[b].setLabel("");
				}
			}
			parent.statistic.setText("You lose");
			
		}
	}
	
}
