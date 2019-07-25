package com.acme.edu.checkOutToFile;

import com.acme.edu.Logger.Logger;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
import com.acme.edu.command.Impl.IntCommand;
import com.acme.edu.command.Impl.StringCommand;
import com.acme.edu.exception.FlushException;
import com.acme.edu.exception.IllegalArgumentException;
import com.acme.edu.saver.LogFileSaver;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import static java.lang.System.lineSeparator;

/**
 * Created by Java_2 on 25.07.2019.
 */
public class FileSaverTest implements SysoutCaptureAndAssertionAbility {
    @Test
    public void logLogicTest () throws IOException {

        try {
            //Given
            Logger logger = new Logger(new LogFileSaver("test.txt"));
            //When
            logger.log(new StringCommand("str1"));
            logger.log(new StringCommand("str1"));
            logger.log(new IntCommand(10));
            logger.flush();
        } catch (IOException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (FlushException e) {
            e.printStackTrace();
            e.getMessage();
        }

        //When
        File path = new File( "test.txt");
       if (path.canRead()) {
           Files.readAllLines(path.toPath()).forEach(System.out::println);
       }

    }
}
