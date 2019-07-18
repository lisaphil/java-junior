package com.acme.edu.iteration02;

import com.acme.edu.Logger;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static java.lang.System.lineSeparator;

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
    public void shouldLogSequentIntegersAsSum() throws IOException {
        //region when

        Logger.log("str 1");
        Logger.log(1);
        Logger.log(2);
        Logger.log("str 2");
        Logger.log(0);
        Logger.flash();
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
    public void shouldLogCorrectlyIntegerOverflowWhenSequentIntegers() {
        //region when
        Logger.log("str 1");
        Logger.log(10);
        Logger.log(Integer.MAX_VALUE);
        Logger.log("str 2");
        Logger.log(0);
        Logger.flash();
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
    public void shouldLogCorrectlyByteOverflowWhenSequentBytes() {
        //region when
        Logger.log("str 1");
        Logger.log((byte)10);
        Logger.log((byte)Byte.MAX_VALUE);
        Logger.log("str 2");
        Logger.log(0);
        Logger.flash();
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
    public void shouldLogSameSubsequentStringsWithoutRepeat() throws IOException {
        //region when
        Logger.log("str 1");
        Logger.log("str 2");
        Logger.log("str 2");
        Logger.log(0);
        Logger.log("str 2");
        Logger.log("str 3");
        Logger.log("str 3");
        Logger.log("str 3");
        Logger.flash();
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
 /*
    TODO: implement Logger solution to match specification as tests
    */
}