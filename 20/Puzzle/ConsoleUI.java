package Puzzle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleUI {

	private Field field;
	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	private String readLine() {
		try {
			return input.readLine();
		} catch (IOException e) {
			return null;
		}
	}

	public void play(Field field) {
		this.field = field;
		field.shuffle();
		while (!field.isSolved()){
			show();
			processInput();
		} 
		System.out.println("Game COMPLETED! ");
		System.exit(0);
	}

	public void show() {
		for (int row = 0; row < field.getRowCount(); row++) {
			for (int column = 0; column < field.getColumnCount(); column++) {
				Tile tile = field.getTile(row, column);
				System.out.printf(" %d ", tile.getValue());
			}
			System.out.println();
		}
	}

	public void processInput() {
		System.out.println("Enter value of Tile to move.");
		String userInput = readLine();
		try {
			field.handleInput(userInput);
			}catch (WrongFormatException e) {
				System.err.println(e.getMessage());
			};
		
		

	}

}
