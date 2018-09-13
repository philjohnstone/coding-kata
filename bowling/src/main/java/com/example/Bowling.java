package com.example;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

import java.util.List;

import com.example.Bowling.RollScore.State;

public class Bowling {

	public int calculateScore(String string) {
		List<RollScore> rollScores = asList(string.split(",")).stream().map(s -> new RollScore(s)).collect(toList());
				
		int score = 0;
		
		for (int i = 0; i < rollScores.size(); i++) {
			RollScore rollScore = rollScores.get(i);
			switch (rollScore.state) {
			case INCOMPLETE:
				if (i + 1 < rollScores.size() && rollScores.get(i + 1).state == State.SPARE) {
					score += 0;
				} else {
					score += rollScore.numberOfPins;
				}
				break;
			case STRIKE:
				score += 10;
				if (i + 1 < rollScores.size() && i < rollScores.size() - 3) {
					RollScore nextOne = rollScores.get(i + 1);
					int potentialBonus = nextOne.state == State.INCOMPLETE ? nextOne.numberOfPins : 10;
					
					if (i + 2 < rollScores.size()) {
						RollScore nextTwo = rollScores.get(i + 2);
						score += nextTwo.state == State.INCOMPLETE ? potentialBonus + nextTwo.numberOfPins : nextTwo.state == State.STRIKE ? 10 + 10 : 10;
					}
				}
				break;
			case SPARE:
				score += 10;
				if (i + 1 < rollScores.size()) {
					RollScore nextOne = rollScores.get(i + 1); 
					score += nextOne.state == State.INCOMPLETE ? nextOne.numberOfPins : 10;
				}
				break;
			}
		}
		return score;
	}
	
	static class RollScore {
		enum State {
			INCOMPLETE, SPARE, STRIKE;
		}
		final int numberOfPins;
		final State state;
		RollScore(String roll) {
			if ("/".equals(roll)) {
				numberOfPins = 0;
				state = State.SPARE;
			} else if ("X".equals(roll)) {
				numberOfPins = 0;
				state = State.STRIKE;
			} else {
				numberOfPins = parseInt(roll);
				state = State.INCOMPLETE;
			}
		}
	}

}
