package ua.dp.mign.service;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ua.dp.mign.model.Event;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Component("fileLogger")
public class FileEventLogger implements EventLogger {
    private final File file;

    public FileEventLogger(@Value("app.log") String fileName) {
        this.file = new File(fileName);
    }

    @PostConstruct
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
