package com.acme.edu.cavarage;

import com.acme.edu.Logger.Logger;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
import com.acme.edu.command.Impl.ByteCommand;
import com.acme.edu.command.Impl.IntCommand;
import com.acme.edu.command.Impl.StringCommand;
import com.acme.edu.command.Impl.massive.IntMatrixCommand;
import com.acme.edu.command.Impl.massive.IntOneDimMassiveCommand;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static java.lang.System.lineSeparator;

public class CavarageOfTest implements SysoutCaptureAndAssertionAbility {
    private Logger tmp = new Logger();
    final String newLine = lineSeparator();

    @Before
    public void setUpSystemOut() throws IOException {
        resetOut();
        captureSysout();
    }
//======================================================================================================================
    @Test
    public void shouldLogSameStrings() throws IOException {
        //region when
        tmp.log(new StringCommand("str1"));
        tmp.log(new StringCommand("str1"));
        tmp.log(new IntCommand(10));
        tmp.flash();
        //endregion

        //region then
        assertSysoutEquals(
                "string: str1 (x2)" + newLine
                        + "primitive: 10" + newLine
        );
        //endregion
    }

    @Test
    public void shouldLogDifferentStringsAndNotAccumulate() throws IOException {
        //region when
        tmp.log(new StringCommand("str1"));
        tmp.log(new StringCommand("str2"));
        tmp.log(new IntCommand(10));
        tmp.flash();
        //endregion

        //region then
        assertSysoutEquals(
                "string: str1" + newLine
                        + "string: str2" + newLine
                        + "primitive: 10" + newLine
        );
        //endregion
    }
    @Test
    public void shouldLogSameStringsAndNotAccumulateNotTogether() throws IOException {
        //region when
        tmp.log(new StringCommand("str1"));
        tmp.log(new IntCommand(10));
        tmp.log(new StringCommand("str1"));
        tmp.flash();
        //endregion

        //region then
        assertSysoutEquals(
                "string: str1" + newLine
                        + "primitive: 10" + newLine
                        + "string: str1" + newLine
        );
        //endregion
    }

    @Test
    public void shouldLogStringsAndAccumulateIntType() throws IOException {
        //region when
        tmp.log(new StringCommand("str1"));
        tmp.log(new IntCommand(10));
        tmp.log(new IntCommand(33));
        tmp.log(new StringCommand("str2"));
        tmp.log(new IntCommand(2));
        tmp.flash();
        //endregion

        //region then

        assertSysoutEquals(
                "string: str1" + newLine
                        + "primitive: 43" + newLine
                        + "string: str2" + newLine
                        + "primitive: 2" + newLine
        );
        //endregion
    }
    //======================================================================================================================
    @Test
    public void shouldLogFirstIntType() throws IOException {
        //region when
        tmp.log(new IntCommand(10));
        tmp.log(new IntCommand(33));
        tmp.log(new StringCommand("str2"));
        tmp.log(new IntCommand(2));
        tmp.flash();
        //endregion

        //region then
        assertSysoutEquals(
                "primitive: 43" + newLine
                        + "string: str2" + newLine
                        + "primitive: 2" + newLine
        );
        //endregion
    }

    @Test
    public void shouldLogFirstIntTypeWithOverflow() throws IOException {
        //region when
        tmp.log(new IntCommand(10));
        tmp.log(new IntCommand( Integer.MAX_VALUE));
        tmp.log(new StringCommand("str2"));
        tmp.log(new IntCommand(2));
        tmp.flash();
        //endregion

        //region then
        assertSysoutEquals(
                "primitive: 10" + newLine
                        + "primitive: " + Integer.MAX_VALUE + newLine
                        + "string: str2" + newLine
                        + "primitive: 2" + newLine
        );
        //endregion
    }

    @Test
    public void shouldLogIntAndNotAccumulate() throws IOException {
        //region when
        tmp.log(new IntCommand(70));
        tmp.log(new StringCommand("str1"));
        tmp.log(new IntCommand(10));
        tmp.flash();
        //endregion

        //region then
        assertSysoutEquals("primitive: 70" + newLine
                +"string: str1" + newLine
                + "primitive: 10" + newLine
        );
        //endregion
    }
    //======================================================================================================================

    @Test
    public void shouldLogFirstByteType() throws IOException {
        //region when
        tmp.log(new ByteCommand((byte)10));
        tmp.log(new ByteCommand((byte)33));
        tmp.log(new StringCommand("str2"));
        tmp.log(new ByteCommand((byte)2));
        tmp.flash();
        //endregion

        //region then
        assertSysoutEquals(
                "primitive: 43" + newLine
                        + "string: str2" + newLine
                        + "primitive: 2" + newLine
        );
        //endregion
    }

    @Test
    public void shouldLogFirstByteTypeWithOverflow() throws IOException {
        //region when
        tmp.log(new ByteCommand((byte)70));
        tmp.log(new ByteCommand(Byte.MAX_VALUE));
        tmp.log(new StringCommand("str2"));
        tmp.log(new ByteCommand((byte)10));
        tmp.flash();
        //endregion

        //region then
        assertSysoutEquals(
                "primitive: 70" + newLine
                        + "primitive: " + Byte.MAX_VALUE + newLine
                        + "string: str2" + newLine
                        + "primitive: 10" + newLine
        );
        //endregion
    }

    @Test
    public void shouldLogByteAndNotAccumulate() throws IOException {
        //region when
        tmp.log(new ByteCommand((byte)70));
        tmp.log(new StringCommand("str1"));
        tmp.log(new ByteCommand((byte)10));
        tmp.flash();
        //endregion

        //region then
        assertSysoutEquals("primitive: 70" + newLine
                +"string: str1" + newLine
                + "primitive: 10" + newLine
        );
        //endregion
    }

    //======================================================================================================================
    @Test
    public void shouldLogFirstIntMass() throws IOException {
        //region when
        tmp.log(new IntOneDimMassiveCommand(new int[] {-1, 0, 1}));
        tmp.log(new StringCommand("str1"));
        tmp.flash();
        //endregion

        //region then

        assertSysoutEquals( "primitives array: {-1, 0, 1}" + newLine
                +"string: str1"  + newLine
        );
        //endregion
    }

    @Test
    public void shouldLogSecondIntMassType() throws IOException {
        //region when
        tmp.log(new StringCommand("str1"));
        tmp.log(new IntOneDimMassiveCommand(new int[] {-1, 0, 1}));
        tmp.flash();
        //endregion

        //region then
        assertSysoutEquals( "string: str1"  + newLine
                + "primitives array: {-1, 0, 1}" + newLine
        );
        //endregion
    }

    @Test
    public void shouldLogMassAndNotAccumulate() throws IOException {
        //region when
        tmp.log(new IntOneDimMassiveCommand(new int[] {-7, 9, 100}));
        tmp.log(new IntOneDimMassiveCommand(new int[] {-1, 0, 1}));
        tmp.log(new StringCommand("str1"));
        tmp.flash();
        //endregion

        //region then
        assertSysoutEquals( "primitives array: {-7, 9, 100}" + newLine
                + "primitives array: {-1, 0, 1}" + newLine
                + "string: str1"  + newLine

        );
        //endregion
    }
    //======================================================================================================================
    @Test
    public void shouldLogFirstIntMatrix() throws IOException {
        //region when
        tmp.log(new IntMatrixCommand(new int[][] {{-1, 0, 1}, {1, 2, 3}, {-1, -2, -3}}));
        tmp.log(new StringCommand("str1"));
        tmp.flash();
        //endregion

        //region then


        assertSysoutEquals( "primitives matrix: {" + newLine +
                "{-1, 0, 1}" + newLine +
                "{1, 2, 3}" + newLine +
                "{-1, -2, -3}" + newLine +
                "}" + newLine
                +"string: str1"  + newLine
        );
        //endregion
    }

    @Test
    public void shouldLogSecondIntMatrixType() throws IOException {
        //region when
        tmp.log(new StringCommand("str1"));
        tmp.log(new IntMatrixCommand(new int[][] {{-1, 0, 1}, {1, 2, 3}, {-1, -2, -3}}));
        tmp.flash();
        //endregion

        //region then

        assertSysoutEquals( "string: str1"  + newLine
                + "primitives matrix: {" + newLine +
                "{-1, 0, 1}" + newLine +
                "{1, 2, 3}" + newLine +
                "{-1, -2, -3}" + newLine +
                "}" + newLine
        );
        //endregion
    }

    @Test
    public void shouldLogMatrixAndNotAccumulate() throws IOException {
        //region when
        tmp.log(new IntMatrixCommand(new int[][] {{-1, 0, 1}, {1, 2, 3}, {-1, -2, -3}}));
        tmp.log(new IntMatrixCommand(new int[][] {{-1, 0, 1}, {3, 3, 3}, {-5, -5, -5}}));
        tmp.log(new StringCommand("str1"));
        tmp.flash();
        //endregion

        //region then
        assertSysoutEquals( "primitives matrix: {" + lineSeparator() +
                "{-1, 0, 1}" + lineSeparator() +
                "{1, 2, 3}" + lineSeparator() +
                "{-1, -2, -3}" + lineSeparator() +
                "}" + lineSeparator()
                + "primitives matrix: {" + lineSeparator() +
                "{-1, 0, 1}" + lineSeparator() +
                "{3, 3, 3}" + lineSeparator() +
                "{-5, -5, -5}" + lineSeparator() +
                "}" + lineSeparator()
                +"string: str1"  + newLine
        );
        //endregion
    }
}
