package com.acme.edu.iteration02;

import com.acme.edu.*;
import com.acme.edu.command.Impl.ByteCommand;
import com.acme.edu.command.Impl.IntCommand;
import com.acme.edu.Logger.Logger;
import com.acme.edu.command.Impl.StringCommand;
import com.acme.edu.exception.FlushException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static java.lang.System.lineSeparator;

/*
public class LoggerTest implements SysoutCaptureAndAssertionAbility {
    //region given
    @Before
    public void setUpSystemOut() throws IOException {
        resetOut();
        captureSysout();
    }

    @After
    public void tearDown() {
        resetOut();
    }
    //endregion

    @Test
    public void shouldLogSequentIntegersAsSum() throws Exception {
        //region when

        Logger tmp = new Logger();

        tmp.log(new StringCommand("str 1"));
        tmp.log(new IntCommand(1));
        tmp.log(new IntCommand(2));
        tmp.log(new StringCommand("str 2"));
        tmp.log(new IntCommand(0));
        tmp.flush();
        //endregion

        //region then
        String newLine = lineSeparator();
        assertSysoutContains("str 1" + newLine);
        assertSysoutContains("3" + newLine);
        assertSysoutContains("str 2" + newLine);
        assertSysoutContains("0" + newLine);
        //endregion
    }

    @Test
    public void shouldLogCorrectlyIntegerOverflowWhenSequentIntegers() throws Exception {
        //region when
        Logger tmp = new Logger();
        tmp.log(new StringCommand("str 1"));
        tmp.log(new IntCommand(10));
        tmp.log(new IntCommand(Integer.MAX_VALUE));
        tmp.log(new StringCommand("str 2"));
        tmp.log(new IntCommand(0));
        tmp.flush();
        //endregion

        //region then
        String newLine = lineSeparator();
        assertSysoutContains("str 1"+ newLine);
        assertSysoutContains("10"+ newLine);
        assertSysoutContains( Integer.MAX_VALUE + newLine);
        assertSysoutContains( "str 2"+ newLine);
        assertSysoutContains("0" + newLine);
        //endregion
    }

    @Test
    public void shouldLogCorrectlyByteOverflowWhenSequentBytes() throws Exception {
        //region when
        Logger tmp = new Logger();
        tmp.log(new StringCommand("str 1"));
        tmp.log(new ByteCommand((byte)10));
        tmp.log(new ByteCommand((byte)Byte.MAX_VALUE));
        tmp.log(new StringCommand("str 2"));
        tmp.log(new IntCommand(0));
        tmp.flush();
        //endregion

        //region then

        String newLine = lineSeparator();
        assertSysoutContains("str 1"+ newLine);
        assertSysoutContains("10"+ newLine);
        assertSysoutContains( Byte.MAX_VALUE + newLine);
        assertSysoutContains( "str 2"+ newLine);
        assertSysoutContains("0" + newLine);

        //endregion
    }

    @Test
    public void shouldLogSameSubsequentStringsWithoutRepeat() throws Exception {
        //region when
        Logger tmp = new Logger();

        tmp.log(new StringCommand("str 1"));
        tmp.log(new StringCommand("str 2"));
        tmp.log(new StringCommand("str 2"));
        tmp.log(new IntCommand(0));
        tmp.log(new StringCommand("str 2"));
        tmp.log(new StringCommand("str 3"));
        tmp.log(new StringCommand("str 3"));
        tmp.log(new StringCommand("str 3"));
        tmp.flush();
        //endregion

        //region then
        String newLine = lineSeparator();
        assertSysoutContains("str 1"+ newLine);
        assertSysoutContains("str 2 (x2)"+ newLine);
        assertSysoutContains("0" + newLine);
        assertSysoutContains( "str 2"+ newLine);
        assertSysoutContains( "str 3 (x3)"+ newLine);

        //endregion
    }
}*/
