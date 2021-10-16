package cib.test.cibtest.exceptions.excclass;

import cib.test.cibtest.enums.EnumForError;

public class DatabaseException extends Exception{
    private final EnumForError enumForError;
    public DatabaseException(EnumForError enumForError){
        this.enumForError = enumForError;
    }
}
