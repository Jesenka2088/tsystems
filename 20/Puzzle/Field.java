package Puzzle;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Field {

	private final Tile[][] tiles;

	private final int rowCount;

	private final int columnCount;

	public Field(int rowCount, int columnCount) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		tiles = new Tile[rowCount][columnCount];// vzniklo len pole dlazdic, kde budem ukladat svoje dlazdice

		int value = 1;// vytvaram dlazdice v cykle
		for (int row = 0; row < rowCount; row++) {
			for (int column = 0; column < columnCount; column++) {
				tiles[row][column] = new Tile(value);
				value++;
			}
		}
		tiles[rowCount -1][columnCount -1].setValue(0);
	}

	public void handleInput(String userInput) throws WrongFormatException {

		Pattern p = Pattern.compile("[0-9]*");
		Matcher m = p.matcher(userInput);

		if (m.matches()) {
			int valueOfTileToMove = Integer.parseInt(userInput);
			if (valueOfTileToMove > 0 && valueOfTileToMove < rowCount * columnCount) {
				int[] markedCoor = new int[2];
				markedCoor = coordinatesOfTile(valueOfTileToMove);
				if ((markedCoor[0]) > 0 && (tiles[markedCoor[0] - 1][markedCoor[1]].getValue() == 0)) {
					move(valueOfTileToMove);
				} else if (markedCoor[0]+1 < rowCount && (tiles[markedCoor[0] + 1][markedCoor[1]].getValue() == 0)) {
					move(valueOfTileToMove);
				} else if (markedCoor[1] > 0 && (tiles[markedCoor[0]][markedCoor[1] - 1].getValue() == 0)) {
					move(valueOfTileToMove);
				} else if (markedCoor[1]+1 < columnCount && (tiles[markedCoor[0]][markedCoor[1] + 1].getValue() == 0)) {
					move(valueOfTileToMove);
				} else {
					throw new WrongFormatException("Wrong input format.Input must be a number next to zero.");
				}
			} else {
				throw new WrongFormatException("Input out of range.");
			}
		} else {
			throw new WrongFormatException("Wrong input format. Input must be a number.");
		}
	}

	public int[] coordinatesOfTile(int value) {

		int[] coordinates = new int[2];
		for (int row = 0; row < getRowCount(); row++) {
			for (int column = 0; column < getColumnCount(); column++) {
				Tile tile = getTile(row, column);
				if (tile.getValue() == value) {
					coordinates[0] = row;
					coordinates[1] = column;
				}
			}
		}
		return coordinates;
	}

	public void move(int value) {
		int[] markedCoor = coordinatesOfTile(value);
		int[] zeroCoor = coordinatesOfTile(0);

		Tile markedTile = tiles[markedCoor[0]][markedCoor[1]];
		Tile zeroTile = tiles[zeroCoor[0]][zeroCoor[1]];

		tiles[markedCoor[0]][markedCoor[1]] = zeroTile;
		tiles[zeroCoor[0]][zeroCoor[1]] = markedTile;
	}

	public boolean isSolved () {
		int index = 1;
		for (int row = 0; row < rowCount; row++) {
			for (int column = 0; column < columnCount; column++) {
				if (tiles[row][column].getValue() != index) {
					return false;
				}else {
					index++;
					if (index == rowCount*columnCount)
						break;
				}
			}
		}
		return true;
	}
	
	public void shuffle () {
		Random random = new Random();
		for (int i = 0; i < rowCount * columnCount * 1000; i++) {
			move(random.nextInt(rowCount * columnCount -1)+1);
		}
	}
	
	public Tile getTile(int row, int column) {
		return tiles[row][column];
	}

	public int getRowCount() {
		return rowCount;
	}

	public int getColumnCount() {
		return columnCount;
	}

}
