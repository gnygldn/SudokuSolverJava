import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SudokuTable {
	List<Integer> sudokuTable = new ArrayList<Integer>();
	List<List<Integer>> rows = new ArrayList<List<Integer>>();
	List<List<Integer>> columns = new ArrayList<List<Integer>>();
	List<List<Integer>> cells = new ArrayList<List<Integer>>();
	boolean tableFilled = false;

	public SudokuTable(List<Integer> table) {
		this.sudokuTable = table;
	}

	public void setRowsColumnsCells() {

		for (int i = 0; i < 9; i++) {
			rows.add(new ArrayList<Integer>());
			columns.add(new ArrayList<Integer>());
			cells.add(new ArrayList<Integer>());
		}

		for (int i = 0; i < 81; i++) {
			int rowIndex = i / 9;
			int colIndex = i % 9;
			int cellIndex = ((rowIndex / 3) * 3) + (colIndex / 3);
			int number = sudokuTable.get(i);
			rows.get(rowIndex).add(number);
			columns.get(colIndex).add(number);
			cells.get(cellIndex).add(number);
		}
	}

	public void startFilling() {
		fillCellsWithOnlyOnePossible(sudokuTable);
	}

	public void findPossiblesForEveryIndex() {
		for (int i = 0; i < 81; i++) {
			findPossibles(i, sudokuTable);
		}
	}

	private void fillCellsWithOnlyOnePossible(List<Integer> tempTable) {
		boolean change = false;
		boolean cellWithZero = false;

		for (int i = 0; i < 81; i++) {
			if (tempTable.get(i) == 0) {
				cellWithZero = true;
				List<Integer> possibleList = findPossibles(i, tempTable);
				if (possibleList.size() == 1) {
					change = true;
					int number = possibleList.get(0);
					tempTable.set(i, number);
				}
			}
		}

		if (!cellWithZero) {
			sudokuTable = new ArrayList<Integer>(tempTable);
			tableFilled = true;
			return;
		}

		if (change) {
			fillCellsWithOnlyOnePossible(tempTable);
		} else {
			fillCellsWithMoreThenOnePossibles(tempTable);
		}
	}

	private void fillCellsWithMoreThenOnePossibles(List<Integer> table) {

		if (tableFilled) {
			return;
		}

		List<Integer> tempTable = null;

		for (int i = 0; i < 81; i++) {

			if (table.get(i) == 0) {
				List<Integer> possibleList = findPossibles(i, table);

				if (possibleList.size() == 0) {
					break;
				}

				for (int j = 0; j < possibleList.size(); j++) {
					if (tableFilled) {
						return;
					}
					tempTable = new ArrayList<Integer>(table);
					int number = possibleList.get(j);
					tempTable.set(i, number);
					fillCellsWithOnlyOnePossible(tempTable);
				}
				break;
			}
		}
		return;

	}

	private List<Integer> findPossibles(int index, List<Integer> tempTable) {
		int rowIndex = index / 9;
		int colIndex = index % 9;
		HashSet<Integer> notPossibles = new HashSet<Integer>();
		List<Integer> possibles = new ArrayList<Integer>();

		for (int i = rowIndex * 9; i < rowIndex * 9 + 9; i++) {
			if (tempTable.get(i) != 0) {
				notPossibles.add(tempTable.get(i));
			}
		}

		for (int i = colIndex; i < 81; i = i + 9) {
			if (tempTable.get(i) != 0) {
				notPossibles.add(tempTable.get(i));
			}
		}

		int startrow = rowIndex / 3 * 27;
		int startcol = colIndex / 3 * 3;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (tempTable.get(startrow + startcol + j) != 0) {
					notPossibles.add(tempTable.get(startrow + startcol + j));
				}
			}
			startrow += 9;

		}

		for (int i = 1; i < 10; i++) {
			if (!notPossibles.contains(i)) {
				possibles.add(i);
			}
		}
		return possibles;

	}

	public List<Integer> getFilledTable() {
		startFilling();
		return sudokuTable;
	}

}
