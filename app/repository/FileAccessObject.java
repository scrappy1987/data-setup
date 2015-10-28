package repository;


import com.typesafe.config.ConfigFactory;
import play.Logger;
import util.FileReaderUtility;
import util.FileWriterUtility;
import util.MoveFileUtility;
import util.PropsReaderUtility;

import javax.inject.Inject;

/**
 * Created by a585493 on 27/10/2015.
 */
public class FileAccessObject {

    private final Logger.ALogger logger = Logger.of(this.getClass());
    private FileReaderUtility reader;
    private FileWriterUtility writer;
    private MoveFileUtility mover;

    @Inject
    public FileAccessObject(FileReaderUtility reader, FileWriterUtility writer, MoveFileUtility mover){
        this.reader = reader;
        this.writer = writer;
        this.mover =  mover;
    }

    public FileWriterUtility getWriter() {
        return writer;
    }

    public FileReaderUtility getReader() {
        return reader;
    }

    public MoveFileUtility getFileMover() {
        return mover;
    }

    public final String getFileLocation(Long id)
    {
        PropsReaderUtility props = new PropsReaderUtility(ConfigFactory.load().getString("test.file.source"));
        return props.getFileLocation(id);
    }
}
