package cib.test.cibtest.enums;

public enum EnumForError {
    IncorrectURL("IncorrectURL"),NullResultForRequest("NullResultForRequest");
    private final String errorString;
    EnumForError(String errorString){
        this.errorString = errorString;
    }

}
