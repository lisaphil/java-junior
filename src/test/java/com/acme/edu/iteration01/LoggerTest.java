package com.acme.edu.iteration01;

import com.acme.edu.*;
import com.acme.edu.command.Impl.ByteCommand;
import com.acme.edu.command.Impl.IntCommand;
import com.acme.edu.Logger.Logger;
import com.acme.edu.command.Impl.StringCommand;
import com.acme.edu.exception.FlushException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;


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
    public void shouldLogInteger() throws Exception {
        //region when
        Logger tmp = new Logger();
        tmp.log(new IntCommand(1));
        tmp.log(new IntCommand(0));
        tmp.log(new IntCommand(-1));
        tmp.flash();
        //endregion

        //region then
        assertSysoutContains("primitive: ");

       // String newLine = lineSeparator();
        //assertSysoutEquals("primitive: 1");
        //endregion
    }

    @Test
    public void shouldLogByte() throws Exception {
        //region when
        Logger tmp = new Logger();

        tmp.log(new ByteCommand((byte)1));
        tmp.log(new ByteCommand((byte)0));
        tmp.log(new ByteCommand((byte)-1));
        tmp.flash();
        //endregion

        //region then
        assertSysoutContains("primitive: ");
        //endregion
    }


    @Test
    public void shouldLogString() throws Exception {
        //region when
        Logger tmp = new Logger();

        tmp.log(new StringCommand("test string 1"));
        tmp.log(new StringCommand("other str"));
        tmp.flash();
        //endregion

        //region then
        assertSysoutContains("string: ");
        assertSysoutContains("test string 1");
        assertSysoutContains("other str");
        //endregion
    }
    /*
    TODO: implement Logger solution to match specification as tests
    */
}