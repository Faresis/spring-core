package ua.dp.mign.service;

import org.apache.commons.io.FileUtils;
import ua.dp.mign.model.Event;

import java.io.File;
import java.io.IOException;

public class FileEventLogger implements EventLogger {
    private final File file;

    public FileEventLogger(String fileName) {
        this.file = new File(fileName);
    }

    public void init() throws IOException {
        if(!file.canWrite()) {
            throw new IllegalArgumentException("File is not available for write. " + file.getName());
        }
    }

    public void logEvent(Event event) {
        try {

            FileUtils.writeStringToFile(file, "\n" + event.toString(), true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
