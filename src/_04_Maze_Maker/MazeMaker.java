package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeMaker {

	private static int width;
	private static int height;

	private static Maze maze;

	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();

	public static Maze generateMaze(int w, int h) {
		width = w;
		height = h;
		maze = new Maze(width, height);
		int randNumWidth;
		int randNumHeight;
		// 4. select a random cell to start

		randNumWidth = randGen.nextInt(maze.getWidth());
		randNumHeight = randGen.nextInt(maze.getHeight());

		// 5. call selectNextPath method with the randomly selected cell

		selectNextPath(maze.getCell(randNumWidth, randNumHeight));

		return maze;
	}

	// 6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		// A. mark cell as visited
		currentCell.setBeenVisited(true);
		// B. check for unvisited neighbors using the cell
		ArrayList<Cell> neighborsUnvisited = getUnvisitedNeighbors(currentCell);

		// C. if has unvisited neighbors,
		if (neighborsUnvisited.size() > 0) {
			Random r = new Random();
			int randCellW = r.nextInt(neighborsUnvisited.size());
			int randCellH = r.nextInt(neighborsUnvisited.size());

			Cell newCell = uncheckedCells.push(maze.getCell(randCellW, randCellH));
			removeWalls(currentCell, newCell);
			currentCell = newCell;
			currentCell.setBeenVisited(true);
			selectNextPath(currentCell);
		}

		// D
		if (neighborsUnvisited.size() == 0) {
			if (uncheckedCells.size() > 0) {
				Cell poppedCell = uncheckedCells.pop();
				currentCell = poppedCell;
				selectNextPath(currentCell);
			}
		}
		// C1. select one at random.

		// C2. push it to the stack

		// C3. remove the wall between the two cells

		// C4. make the new cell the current cell and mark it as visited

		// C5. call the selectNextPath method with the current cell

		// D. if all neighbors are visited

		// D1. if the stack is not empty

		// D1a. pop a cell from the stack

		// D1b. make that the current cell

		// D1c. call the selectNextPath method with the current cell

	}

	// 7. Complete the remove walls method.
	// This method will check if c1 and c2 are adjacent.
	// If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {

		if (c1.getX() == c2.getX() + 1) {
			c1.setWestWall(false);
			c2.setEastWall(false);
		} else if (c1.getY() == c2.getY() + 1) {
			c1.setNorthWall(false);
			c2.setSouthWall(false);
		} else if (c2.getX() == c1.getX() + 1) {
			c1.setEastWall(false);
			c2.setWestWall(false);
		} else if (c2.getY() == c1.getY() + 1) {
			c1.setSouthWall(false);
			c2.setNorthWall(false);
		}
	}

	// 8. Complete the getUnvisitedNeighbors method
	// Any unvisited neighbor of the passed in cell gets added
	// to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		ArrayList<Cell> unvisitedNeighbors = new ArrayList<Cell>();

		if (c.getX() > 0) {
			Cell leftC = maze.getCell(c.getX() - 1, c.getY());
			if (leftC.hasBeenVisited() == false) {
				unvisitedNeighbors.add(leftC);
			}
		}
		if (c.getX() < width - 1) {
			Cell rightC = maze.getCell(c.getX() + 1, c.getY());
			if (rightC.hasBeenVisited() == false) {
				unvisitedNeighbors.add(rightC);
			}
		}
		if (c.getY() > 0) {
			Cell upC = maze.getCell(c.getX(), c.getY() - 1);
			if (upC.hasBeenVisited() == false) {
				unvisitedNeighbors.add(upC);
			}
		}
		if (c.getY() < height - 1) {
			Cell downC = maze.getCell(c.getX(), c.getY() + 1);
			if (downC.hasBeenVisited() == false) {
				unvisitedNeighbors.add(downC);
			}
		}

		return unvisitedNeighbors;
	}
}
