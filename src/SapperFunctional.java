import java.util.ArrayList;
import java.util.Random;

public class SapperFunctional {

	int countCheck;

	public ArrayList<Integer> randomBombs(int size, int bombs) {
		Random randomGenerate = new Random();
		ArrayList<Integer> bombsCoords = new ArrayList<>();
		int bombsCount = 0;
		while(bombsCount < bombs) {
			int random = randomGenerate.nextInt(size);
			if(!bombsCoords.contains(random)){
				bombsCount++;
				bombsCoords.add(random);
			}
		}
		return bombsCoords;
	}

	public void getNumbers(int index, int size, ArrayList<Integer> bombsCoords, int[] cellsValue) {

		int[] neighborNums = {-10, -9, -8, -1, 1, 8, 9, 10};

		for(int neighbor: neighborNums) {
			if(index + neighbor >= 0 && index + neighbor <= size - 1) {
				if(!bombsCoords.contains(index + neighbor) && !(((index % 9 == 0) && (index + neighbor) % 9 == 8) ||
							((index % 9 == 8) && (index + neighbor) % 9 == 0))) { // If the cell is not a bomb and is not on the left or right edge
								if(cellsValue[index + neighbor] != 0){
									cellsValue[index + neighbor]++;
								}else {
									cellsValue[index + neighbor] = 1;
								}
				}
			}
		}
	}

	public void OpenFields(int index, int size, int[] cellsValue, boolean checked[]) {

		if(cellsValue[index] != 0) {
			countCheck++;
			checked[index] = true;
		} else {
			int[] neighborNums = {-10, -9, -8, -1, 1, 8, 9, 10};
			for (int neighbor : neighborNums) {
				if(!checked[index]) {
					countCheck++;
					checked[index] = true;
				}
				if((index + neighbor >= 0 && index + neighbor <= size - 1) && (!checked[index + neighbor]) && !(((index % 9 == 0) && (index + neighbor) % 9 == 8) ||
						((index % 9 == 8) && (index + neighbor) % 9 == 0))) {
					OpenFields(index + neighbor, size, cellsValue, checked);
				}
			}
		}
	}

}
