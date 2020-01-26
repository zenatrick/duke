package seedu.duke.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import seedu.duke.common.Messages;
import seedu.duke.exception.InvalidStorageFilePathException;
import seedu.duke.exception.StorageOperationException;
import seedu.duke.task.TaskList;

public class Storage {
    private static final String DEFAULT_STORAGE_FILEPATH = "data/duke.txt";

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy H:m");

    private File file;

    public Storage() throws Exception {
        this(DEFAULT_STORAGE_FILEPATH);
    }

    public Storage(String filePath) throws InvalidStorageFilePathException {
        file = new File(filePath);
        if (!isValidFilePath()) {
            throw new InvalidStorageFilePathException(Messages.INVALID_FILE_MSG);
        }
    }

    private boolean isValidFilePath() {
        return file.getPath().endsWith(".txt");
    }

    public void saveTaskListToStorage(TaskList taskList) throws StorageOperationException {
        file.getParentFile().mkdirs();
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(TaskEncoder.encodeTasksList(taskList));
            fileWriter.close();
        } catch (IOException e) {
            throw new StorageOperationException(Messages.generateWriteToFileErrorMessage(file.getAbsolutePath()));
        }
    }

    public TaskList loadTaskListFromStorage() throws StorageOperationException {
        if (!file.exists()) {
            return new TaskList();
        }

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            List<String> taskListInString = bufferedReader
                    .lines()
                    .collect(Collectors.toList());
            bufferedReader.close();
            return TaskDecoder.decodeTasksList(taskListInString);
        } catch (FileNotFoundException e) {
            throw new AssertionError("A non-existent file scenario is already handled earlier.");
        } catch (IOException e) {
            throw new StorageOperationException(Messages.generateReadFromFileErrorMessage(file.getAbsolutePath()));
        }
    }
}
