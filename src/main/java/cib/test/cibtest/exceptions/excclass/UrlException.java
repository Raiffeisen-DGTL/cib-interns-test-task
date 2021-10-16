package cib.test.cibtest.exceptions.excclass;

import cib.test.cibtest.enums.EnumForError;

public class UrlException extends Exception{
    private final EnumForError enumForError;
    public UrlException(EnumForError enumForError){
        this.enumForError = enumForError;
    }
}
