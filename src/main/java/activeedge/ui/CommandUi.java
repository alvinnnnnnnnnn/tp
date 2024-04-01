package activeedge.ui;

import static activeedge.task.TaskList.tasksList;

import activeedge.task.LogExercise;
import activeedge.task.Task;
import activeedge.task.WaterTask;
import activeedge.task.MealTask;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class CommandUi {

    static final String LINE = "____________________________________________________________\n";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm");

    public static void printWelcomeMessage() {
        String logo = "🌟 ACTIVE EDGE 🌟";
        System.out.println("Welcome to " + logo);
        System.out.println("🚀✨ Take the next step in your Healthy Lifestyle! ✨🚀");
    }

    public static void printFullList() {
        System.out.println("Logged data for today:");

        System.out.println("Food ");
        int j = 1;
        for (int i = 0; i < tasksList.size(); i++) {
            if (tasksList.get(i).toString().startsWith("Meal")) {
                System.out.print(j + ". " + tasksList.get(i).toString().substring(5));
                System.out.println("");
                j++;
            }
        }
        System.out.println("Water:");
        int k = 1;
        for (int i = 0; i < tasksList.size(); i++) {
            if (tasksList.get(i).toString().startsWith("Water")) {
                System.out.print(k + ". " + tasksList.get(i).toString().substring(6));
                System.out.println("");
                k++;
            }
        }
        System.out.println("Exercises:");
        int l = 1;
        for (int i = 0; i < tasksList.size(); i++) {
            if (tasksList.get(i).toString().startsWith("Exercise")) {
                System.out.print(l + ". " + tasksList.get(i).toString().substring(9));
                System.out.println("");
                l++;
            }
        }

    }

    public static void printMealLogMessage(MealTask mealTask) {
        System.out.println("You've logged " + Integer.toString(mealTask.getServings()) +
                " servings" + " of " + mealTask.getFoodName() + ".") ;
        System.out.println("Estimated calories: " + Integer.toString(mealTask.getMealCalories()) + " kcal");
    }

    public static void printExerciseLogMessage(LogExercise logExercise) {
        System.out.println("You've logged " + Integer.toString(logExercise.getDuration()) +
                " minutes" + " of " + logExercise.getExerciseName() + ".") ;
        System.out.println("Estimated calories burnt: " + Integer.toString(logExercise.getCaloriesBurnt()) + " kcal");
    }

    public static void printShowCalMessage() {
        int totalCalories = 0;
        int totalCaloriesFromMeals = 0;
        int totalCaloriesFromExercises = 0;
        String goal = "0";
        for (int i = 0; i < tasksList.size(); i++) {
            String[] parts = tasksList.get(i).toString().split(" ");
            int len = parts.length;
            String taskString = tasksList.get(i).toString();
            int kcalIndex = -1;
            for (int j = 0; j < len; j++) {
                if (parts[j].equals("kcal")) {
                    kcalIndex = j - 1; // Assuming calorie value is just before "kcal"
                    break;
                }
            }

            // Check if kcal index is found and the part at that index is a valid integer
            if (kcalIndex >= 0 && kcalIndex < parts.length) {
                String calorieString = parts[kcalIndex];
                if (calorieString.matches("\\d+")) { // Check if it's a valid integer
                    int calories = Integer.parseInt(calorieString);
                    if (taskString.startsWith("Meal")) {
                        totalCaloriesFromMeals += calories;
                    } else if (taskString.startsWith("Exercise")) {
                        totalCaloriesFromExercises += calories;
                    }
                } else {
                    System.out.println("Skipping non-integer calorie value: " + calorieString);
                }
            }
            if(tasksList.get(i).toString().startsWith("Goal")) {
                if (parts[1].equals("c")) {
                    goal = parts[2].toString();
                }
            }
        }
        totalCalories = totalCaloriesFromMeals - totalCaloriesFromExercises;
        System.out.print("Total calories today: ");
        System.out.println(totalCalories + " kcal out of " + goal + " kcal");
    }

    public static void printWaterLogMessage(WaterTask newWaterTask) {
        System.out.println("Successfully logged " + newWaterTask.getQuantity() + " ml of water.");
    }

    public static void printWaterIntakeMessage(int totalWaterIntake, int waterGoal) {
        double percentage = ((double) totalWaterIntake / waterGoal) * 100;
        System.out.println("Total water consumed today: " + totalWaterIntake +
                " ml (" + String.format("%.0f%%", percentage) + " of " + waterGoal + "ml goal).");
    }


    public static void printMatchingTasks(String word) {
        System.out.println(LINE + " Here are the matching tasks in your list:");
        int matchingTasksIndex = 1;
        boolean found = false;

        // Search in the food section
        for (int i = 0; i < tasksList.size(); i++) {
            if (tasksList.get(i).toString().startsWith("Meal") && tasksList.get(i).toString().contains(word)) {
                System.out.print(matchingTasksIndex + ". ");
                System.out.println(tasksList.get(i).toString().substring(5) + " kcal");
                matchingTasksIndex++;
                found = true; // Indicate that a match was found
            }
        }

        // Search in the water section
        for (int i = 0; i < tasksList.size(); i++) {
            if (tasksList.get(i).toString().startsWith("Water") && tasksList.get(i).toString().contains(word)) {
                System.out.print(matchingTasksIndex + ". ");
                System.out.println(tasksList.get(i).toString().substring(6) + " ml");
                matchingTasksIndex++;
                found = true; // Indicate that a match was found
            }
        }

        // If no matching tasks were found, print a message
        if (!found) {
            System.out.println("No matching tasks found.");
        }

        System.out.println(LINE);
    }

    public static void printInvalidDeleteFormatMessage() {
        System.out.println("This is an invalid request. Please try again!");
    }

    public static void printTaskDeletedMessage(Task deletedTask) {
        System.out.println("Task deleted: " + deletedTask.getDescription());
    }

    public static void printFoodItemNotFoundMessage(String description){
        System.out.println(description + " is not found in our food database.\n" +
                "Please enter the following command to add it to the database and log your mea.l\n\n" +
                "add m/[FOOD] c/[CALORIES_PER_SERVING(kCal)] s/[NUMBER_OF_SERVINGS]\n\n" +
                "Eg: 'add m/"+ description +" c/120 s/2'\n"
        );
    }

    public static void printExerciseItemNotFoundMessage(String exerciseName){
        System.out.println(exerciseName + " is not found in our food database.\n" +
                "Please enter the following command to add it to the database and log your mea.l\n\n" +
                "add m/[FOOD] c/[CALORIES_PER_SERVING(kCal)] s/[NUMBER_OF_SERVINGS]\n\n" +
                "Eg: 'add m/"+ exerciseName +" c/120 d/2'\n"
        );
    }

    public static void printAddFoodItemMessage(String description){
        System.out.println(description + " has been added to the food database.\n" +
                "logging your meal.......\n"
        );
    }

    public static void printAddExerciseMessage(String exerciseName) {
        System.out.println(exerciseName + " has been added to the exercise database.\n" +
                "logging your exercise.......\n"
        );
    }


    public static void printTaskNotFoundMessage() {
        System.out.println("Task not found.");
    }
    public static void printShowSummaryMessage(int totalCalories,int totalWaterIntake, int totalCaloriesBurnt,
                                              String calorieGoal, String waterGoal,
                                               int netCalories, String calorieStatus) {
        System.out.println("Daily Summary:");
        System.out.println("Total calories consumed: " + totalCalories + " kcal");
        System.out.println("Total water consumed: " + totalWaterIntake + " ml");
        System.out.println("Total calories burnt: " + totalCaloriesBurnt + " kcal");

        System.out.println("Calorie goal: " + calorieGoal + " kcal");
        System.out.println("Water goal: " + waterGoal + " ml");

        System.out.println("Net calories: " + netCalories + " kcal");
        System.out.println("Calorie status: " + calorieStatus);
    }

    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DATE_TIME_FORMATTER);
    }
    public static void printAllTasksClearedMessage() {
        System.out.println("All logged data has been cleared.");
    }
    public static void printDataAlreadyClearedMessage() {
        System.out.println("Logged data has already been cleared.");
    }

}
