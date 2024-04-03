package command;

import activeedge.ui.CommandUi;
import activeedge.ExerciseData;
import java.time.LocalDateTime;

/**
 * The AddExerciseItemCommand class represents a command to add a new exercise item to the ActiveEdge application.
 * It encapsulates the exercise name, duration, calories burnt per minute, and the date and time of the exercise.
 */
public class AddExerciseItemCommand {
    protected String exerciseName; // The name of the exercise
    protected int duration; // The duration of the exercise in minutes
    protected int caloriesBurntPerMinute; // The number of calories burnt per minute during the exercise
    protected LocalDateTime dateTime; // The date and time when the exercise was performed

    /**
     * Constructs an AddExerciseItemCommand with the specified exercise details.
     * @param exerciseName The name of the exercise.
     * @param duration The duration of the exercise in minutes.
     * @param caloriesBurntPerMinute The number of calories burnt per minute during the exercise.
     * @param dateTime The date and time when the exercise was performed.
     */
    public AddExerciseItemCommand(String exerciseName, int duration, int caloriesBurntPerMinute,
                                  LocalDateTime dateTime) {
        this.exerciseName = exerciseName;
        this.duration = duration;
        this.caloriesBurntPerMinute = caloriesBurntPerMinute;
        this.dateTime = dateTime;
    }

    /**
     * Executes the AddExerciseItemCommand by adding the exercise to the list of exercises, printing a success message,
     * and logging the exercise in the application's data.
     * @throws ActiveEdgeException If an error occurs during the execution of the command.
     */
    public void execute() throws ActiveEdgeException {
        if (ExerciseData.exerciseExists(exerciseName)) {
            // Exercise activity exists, log it
            CommandUi.promptLogExerciseMessage(exerciseName);
        } else {
            String[] newExercise = {exerciseName, Integer.toString(caloriesBurntPerMinute)};
            ExerciseData.exercisesList = appendItem(ExerciseData.exercisesList, newExercise);
            CommandUi.printAddExerciseMessage(exerciseName);
            LogExerciseCommand logExerciseCommand = new LogExerciseCommand(exerciseName, duration,
                    caloriesBurntPerMinute * duration, dateTime, true);
            logExerciseCommand.execute();
        }
    }

    /**
     * Appends a new exercise item to the array of exercises.
     * @param originalArray The original array of exercises.
     * @param newItem The new exercise item to be appended.
     * @return The updated array of exercises with the new item appended.
     */
    public static String[][] appendItem(String[][] originalArray, String[] newItem) {
        // Create a new array with one more row than the original
        String[][] newArray = new String[originalArray.length + 1][2]; // Assuming each item has 2 elements

        // Copy the contents of the original array to the new array
        for (int i = 0; i < originalArray.length; i++) {
            newArray[i] = originalArray[i];
        }

        // Add the new item to the last position of the new array
        newArray[newArray.length - 1] = newItem;

        return newArray;
    }
}