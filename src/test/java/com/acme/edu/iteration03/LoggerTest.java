package com.acme.edu.iteration03;

import com.acme.edu.IntMatrixCommand;
import com.acme.edu.IntOneDimMassiveCommand;
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
    public void shouldLogIntegersArray() throws IOException {
        //region when
       Logger tmp = new Logger();
       tmp.log(new IntOneDimMassiveCommand(new int[] {-1, 0, 1}));
       tmp.flash();
        //endregion

        //region then
        assertSysoutContains("primitives array: {-1, 0, 1}");
        //endregion
    }

   @Test
    public void shouldLogIntegersMatrix() throws IOException {
        //region when

        Logger tmp = new Logger();
        tmp.log(new IntMatrixCommand(new int[][] {{-1, 0, 1}, {1, 2, 3}, {-1, -2, -3}}));
        tmp.flash();
        //endregion

        //region then
        assertSysoutContains("primitives matrix: {" + lineSeparator() +
                "{-1, 0, 1}" + lineSeparator() +
                "{1, 2, 3}" + lineSeparator() +
                "{-1, -2, -3}" + lineSeparator() +
            "}" + lineSeparator());
        //endregion
    }

    @Test
    public void shouldLogStringsWithOneMethodCall() throws IOException {
        //region when
        Logger tmp = new Logger();
        tmp.log("str1", "string 2", "str 3");
        tmp.flash();
        //endregion

        //region then
        String newLine = lineSeparator();

        assertSysoutContains("str1" + newLine);
        assertSysoutContains("string 2" + newLine);
        assertSysoutContains("str 3" + newLine);
        //endregion
    }
 /*
    TODO: implement Logger solution to match specification as tests
    @Test
    public void shouldCorrectDealWithIntegerOverflowWhenOneMethodCall() throws IOException {
        //region when
        Logger.log(1);
        Logger.log("str");
        Logger.log(Integer.MAX_VALUE - 10);
        Logger.log(11);
        //endregion

        //region then
        //assertSysoutContains(1);
        assertSysoutContains("str");
       // assertSysoutContains(Integer.MAX_VALUE - 10);
        //assertSysoutContains(11);
        //endregion
    }
        @Test
    public void shouldLogIntegersMulitidimentionalArray() throws IOException {
        //region when
        Logger.log(new int[][][][] {{{{0}}}});
        //endregion

        //region then
        assertSysoutEquals(
            "primitives multimatrix: {\n" +
                "{\n" + "{\n" + "{\n" +
                    "0\n" +
                "}\n" + "}\n" + "}\n" +
            "}\n"
        );
        //endregion
    }


    @Test
    public void shouldLogIntegersWithOneMethodCall() throws IOException {
        //region when
        //Logger.log(-1, 0, 1, 3);
        //endregion

        //region then
        //assertSysoutContains("3");
        //endregion
    }
    */
}