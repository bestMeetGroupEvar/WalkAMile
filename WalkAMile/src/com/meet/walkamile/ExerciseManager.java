package com.meet.walkamile;

public class ExerciseManager {
	public Exercise randomExercise() {
		return new Exercise(Exercise.exerciseRandomizer(),
				Exercise.repsRandomizer());
	}
}