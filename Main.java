import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Main {
	
	public static void main(String[] args) {
		List<Integer> sudokuTableNumbers = Arrays.asList(
				 0,3,0,0,0,8,0,6,0,0,7,8,0,4,0,0,9,1,0,1,0,6,0,0,0,5,0,2,0,0,0,0,0,5,0,0,0,8,0,0,0,0,0,2,0,0,0,1,0,0,0,0,0,6,0,6,0,0,0,9,0,8,0,7,4,0,0,8,0,6,1,0,0,2,0,5,0,0,0,7,0);

		Date date = new Date();
		SudokuTable sudokuTable = new SudokuTable(sudokuTableNumbers);
		sudokuTable.setRowsColumnsCells();
		List<Integer> filledTable = sudokuTable.getFilledTable();
		printSudokuTable(filledTable);
		Date date2 = new Date();
		System.out.println(date2.getTime() - date.getTime());
		return;
	}
	
	public Main() {
		super();
		
		//sudoku table numbers in order, from row 1 to row 9, from left to right
		//0 for empty cells.
	}
	
	private static void printSudokuTable(List<Integer> tempTable) {
		System.out.println("last");
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(tempTable.get(i * 9 + j) + " ");
			}
			System.out.println();
		}
	}

}