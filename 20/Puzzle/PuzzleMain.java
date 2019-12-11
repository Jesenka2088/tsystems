package Puzzle;

public class PuzzleMain {

	private ConsoleUI userConsole;
	
	private PuzzleMain() {
		userConsole = new ConsoleUI();
		
		Field field = new Field(2, 2);
		userConsole.play(field);
	}

	public static void main(String[] args) {
		new PuzzleMain();

	}

}
