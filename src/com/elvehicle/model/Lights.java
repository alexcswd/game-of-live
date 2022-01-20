package com.elvehicle.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class Lights {
	
	private Map<Cell, AtomicInteger> litCells = new HashMap<>();
	private int dim = 0;
	
	public void enlight(Generation generation) {
		for (Cell cell : generation.getCells()) {
			spark(cell);
			dim = getDim(dim, cell);
		}
	}
	
	public void spark(Cell cell) {
		Set<Cell> adjacentCells = getAdjacentCells(cell);
		for (Cell adjacentCell : adjacentCells) {
			if (! litCells.containsKey(adjacentCell)) {
				litCells.put(adjacentCell, new AtomicInteger(0));
			}
			litCells.get(adjacentCell).incrementAndGet();
		}
	}
	
	private int getDim(int currDim, Cell cell) {
		return Math.max(currDim, Math.max(cell.getX(), cell.getY()));
	}
	
	private Set<Cell> getAdjacentCells(Cell cell) {
		Set<Cell> cells = new HashSet<>();
		cells.add(visibleOnly(new Cell(cell.getX()-1, cell.getY()-1)));
		cells.add(visibleOnly(new Cell(cell.getX(),   cell.getY()-1)));
		cells.add(visibleOnly(new Cell(cell.getX()+1, cell.getY()-1)));
		cells.add(visibleOnly(new Cell(cell.getX()-1, cell.getY())));
		cells.add(visibleOnly(new Cell(cell.getX()+1, cell.getY())));
		cells.add(visibleOnly(new Cell(cell.getX()-1, cell.getY()+1)));
		cells.add(visibleOnly(new Cell(cell.getX(),   cell.getY()+1)));
		cells.add(visibleOnly(new Cell(cell.getX()+1, cell.getY()+1)));
		cells.remove(null);
		return cells;
	}
	
	private Cell visibleOnly(Cell cell) {
		return cell.getX() >= 0 && cell.getY() >= 0 ? cell : null;
	}

	public void display() {
		// .---.---.---.---.---.
		// |   | 1 | 1 | 1 |   |
		// |---|---|---|---|---|
		// |   | 1 | 1 | 2 | 1 |
		// |---|---|---|---|---|
		// | 1 | 3 | 4 | 2 | 1 |
		// |---|---|---|---|---|
		// | 1 | 1 | 2 | 2 | 1 |
		// |---|---|---|---|---|
		// | 1 | 2 | 2 | 1 |   |
		// '---'---'---'---'---'
		StringBuffer sb = new StringBuffer(".");
		for (int i = 1; i <= dim+1; i++) {
			sb.append("---.");
		}
		sb.append("\n");
		for (int i = 1; i <= dim+1; i++) {
			sb.append("|");
			for (int j = 1; j <= dim+1; j++) {
				AtomicInteger lit = litCells.get(new Cell(i, j));
				String cell = lit != null ? String.format( " %d |", lit.get()) : "   |";
				sb.append(cell);
			}
			sb.append("\n");
			if (i == dim+1) {
				sb.append("'");
				for (int d = 1; d <= dim+1; d++) {
					sb.append("---'");
				}
				sb.append("\n");
				
			} else {
				sb.append("|");
				for (int d = 1; d <= dim+1; d++) {
					sb.append("---|");
				}
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}
}
