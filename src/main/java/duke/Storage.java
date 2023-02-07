package duke;

import tasks.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the Storage that helps to store the tasks entered by the user
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a new Storage with the specified file path.
     *
     * @param filePath the file path to the storage file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads any existing data from the filePath of the Storage (if such a file in the filePath exists),
     * parses each line into a corresponding task and then adds each task into a
     * newly initialised arraylist of tasks. Returns the arraylist of tasks loaded.
     *
     * @return the arraylist of tasks loaded from the storage file (if any)
     * @throws Exception
     */
    public List<Task> loadFile() throws Exception {
        List<Task> initList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line = reader.readLine().trim();
        while (line != null) {
            String[] strArr = line.split(" \\| ");
            String type = strArr[0];
            boolean isCompleted = Integer.parseInt(strArr[1]) == 1;
            String taskDesc = strArr[2];

            switch (type) {
                case "T":
                    initList.add(new Todo(taskDesc, isCompleted));
                    break;
                case "D":
                    initList.add(new Deadline(taskDesc, isCompleted, strArr[3]));
                    break;
                case "E":
                    initList.add(new Event(taskDesc, isCompleted, strArr[3], strArr[4]));
                    break;
            }
            line = reader.readLine();
        }
        return initList;
    }

    /**
     *  Saves tasks into the Storage file by iterating through the specified TaskList.
     *  Also handles any saving errors by using the Ui to display the error message
     *
     * @param taskList the tasklist to save
     * @param ui the Ui to help inform the user of the error
     */
    public void saveListToFile(TaskList taskList, Ui ui) {
        try {
            FileWriter writer = new FileWriter(filePath, false);
            List<Task> list = taskList.getTaskList();
            for (Task t : list) {
                writer.write(t.parseToSave() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            ui.showSavingError();
        }
    }

}
