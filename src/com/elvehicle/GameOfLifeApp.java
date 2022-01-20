package com.elvehicle;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import com.elvehicle.model.Generation;
import com.elvehicle.model.Lights;
import com.elvehicle.model.Rule;

public class GameOfLifeApp {
	
	private Set<Rule> setOfRulesToApply = new HashSet<>();
	
	public GameOfLifeApp() {
		// init the set of rules on startup
		setOfRulesToApply.add(null);
	}
	
	/**
	 * The pure function that makes the time tick, transforming the generation
	 * from one state to another;
	 * 
	 * @param generation
	 * @return
	 */
	public Generation tick(Generation generation) {
		return null;
	}

	
	public static void main(String args[]) {
		GameOfLifeApp gol = new GameOfLifeApp();
		Generation generation = new Generation("3,2;4,3;2,4;3,4;4,4");
		Scanner input = new Scanner(System.in);
		// c - is the halting signal		
		while (! "c".equals(input.nextLine())) {
//			generation = gol.tick(generation);
			generation.display();
			System.out.println();
			System.out.println();
			System.out.println();
			Lights lights = new Lights();
			lights.enlight(generation);
			lights.display();
		}
		input.close();
	}
}