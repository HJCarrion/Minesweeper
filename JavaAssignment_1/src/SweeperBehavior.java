import java.util.Random;
import javax.swing.JOptionPane;
import java.awt.Color;

public class SweeperBehavior {

	public static boolean[][] mineField = new boolean[9][9];
	public static boolean[][] flaggedCell = new boolean[9][9];
	public static Color[][] colorField = new Color[9][10];
	private Random randomizer = new Random();
	public int totalMines = 10;

	public static Color[][] getColorField() {
		return colorField;
	}

	public boolean uncoveredCell(int x, int y) {
		if (colorField[x][y].equals(Color.GRAY)) {
			return true;
		}
		return false;
	}

	public void generateMines() { // Generates a total of 10 mines in randomly
									// determined cells.
		int setMines = 0;
		while (setMines < totalMines) {
			int column = randomizer.nextInt(9);
			int row = randomizer.nextInt(9);

			if (mineField[column][row] == false) {
				mineField[column][row] = true;
				setMines++;
			}
		}
	}

	public boolean hasMine(int x, int y) {
		return mineField[x][y];
	}

	public int mineIndicator(int x, int y) {
		int mineCounter = 0;

		for (int i = y - 1; i < y + 2; i++) {
			for (int j = x - 1; j < x + 2; j++) {
				if ((i >= 0 && i < 9) && (j >= 0 && j < 9))
					if (hasMine(j, i))
						mineCounter++;
			}
		}
		return mineCounter;
	}
	
	public void flagCell(int x, int y){
		if( uncoveredCell(x,y) == true){
			colorField[x][y] = Color.RED;
		}
	}

	public void chainReaction(int x, int y) {
		if ((x >= 0 && x < 9) && (y >= 0 && y < 9)) {
			if (hasMine(x, y) == false && uncoveredCell(x, y) == false) {
				colorField[x][y] = Color.WHITE;
				if (mineIndicator(x, y) > 0) {
					return;
				} else {
					// run method recursively for cells
					// in 4 directions around target
					// cell.
					chainReaction(x + 1, y);
					chainReaction(x - 1, y);
					chainReaction(x, y - 1); 
					chainReaction(x, y + 1);
				}
			}
		}
	}

}
