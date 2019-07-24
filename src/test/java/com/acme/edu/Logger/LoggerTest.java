package com.acme.edu.Logger;

import com.acme.edu.command.Command;
import com.acme.edu.command.Impl.ByteCommand;
import com.acme.edu.command.Impl.IntCommand;
import com.acme.edu.command.Impl.massive.IntMassivesCommand;
import com.acme.edu.command.Impl.massive.IntOneDimMassiveCommand;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.stubbing.OngoingStubbing;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

/**
 * Created by Java_2 on 23.07.2019.
 */


public class LoggerTest {
    @Test
    public void flashWhenNotMassive() throws Exception {
        //Given
        Logger sut = new Logger();
        ByteCommand mockCommand = mock(ByteCommand.class);

        //When
        sut.log(mockCommand);
        sut.flash();

        //Then
        verify(mockCommand, times(1)).clean();
    }

    @Test
    public void flashNoWhenIsMassive() throws Exception {
        //Given
        Logger sut = new Logger();
        IntOneDimMassiveCommand mockCommand = mock(IntOneDimMassiveCommand.class);

        //When
        sut.log(mockCommand);
        sut.flash();

        //Then
        verify(mockCommand, times(0)).clean();
    }

    @Test
    public void logCheckIfAccamulate() throws Exception {

        //Given
        Logger sut = new Logger();
        Command mockCommand = mock(Command.class);
        Command defaultLastCommand = sut.getLastCmd();

        //When
        sut.log(mockCommand);

        //Then
        verify(mockCommand, times(1)).accamulate(defaultLastCommand);
    }

    @Test
    public void logCheckIfHandle() throws Exception {
        //Given
        Logger sut = new Logger();
        Command mockCommand = mock(Command.class);

        //When
        sut.log(mockCommand);

        //Then
        verify(mockCommand, times(1)).handle();
    }

    @Test
    public void logCheckIfLastCmdAndCmdSimilarAfterLog() throws Exception {
        //Given
        Logger sut = new Logger();
        Command mockCommand = mock(Command.class);

        //When
        sut.log(mockCommand);

        //Then
        Assert.assertEquals("last command and command must be the same after log", sut.getLastCmd(), mockCommand);
    }
}