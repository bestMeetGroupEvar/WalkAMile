package com.meet.walkamile;

import java.util.ArrayList;
import java.util.Random;


public class Exercise {
	String nameOfExercise;
	int numberOfReps;
	static Random random = new Random();

	public Exercise() {
		this.nameOfExercise = exerciseRandomizer();
		this.numberOfReps = repsRandomizer();
	}

	

	public int repsRandomizer() {
		Random random = new Random();
		return random.nextInt(16);

	}

	public String exerciseRandomizer() {
		int x = random.nextInt(9);
		ArrayList<String> exercises = new ArrayList<String>();
		exercises.add("Jumping Jacks");
		exercises.add("Pushups");
		exercises.add("Crunches");
		exercises.add("Mountain Climbers");
		exercises.add("Squats");
		exercises.add("Jumping Squats");
		exercises.add("Spiderman Pushups");
		exercises.add("Lunges");
		exercises.add("Leg Raises");
		exercises.add("V Up");

		return exercises.get(x);

	}

	/**
	 * public static ImageView excercisePictures() { ArrayList<ImageView> pics =
	 * new ArrayList<ImageView>(); pics.add(new
	 * ImageView("pics/jumpingJacks.jpg")); pics.add(new
	 * ImageView("pics/pushups.jpg")); pics.add(new
	 * ImageView("pics/crunches.jpg")); pics.add(new
	 * ImageView("pics/mountainClimber.jpg")); pics.add(new
	 * ImageView("pics/squats.jpg")); pics.add(new
	 * ImageView("pics/jumpingSquats.jpg")); pics.add(new
	 * ImageView("pics/spidermanPushups.jpg")); pics.add(new
	 * ImageView("pics/lunges.jpg")); pics.add(new
	 * ImageView("pics/legRaises.jpg")); pics.add(new
	 * ImageView("pics/vUp.jpg"));
	 * 
	 * return pics.get(x); }
	 */

	public String getNameOfExercise() {
		return nameOfExercise;
	}

	public void setNameOfExercise(String nameOfExercise) {
		this.nameOfExercise = nameOfExercise;
	}

	public int getNumberOfReps() {
		return numberOfReps;
	}

	public void setNumberOfReps(int numberOfReps) {
		this.numberOfReps = numberOfReps;
	}

}