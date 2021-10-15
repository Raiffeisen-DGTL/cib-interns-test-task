package ru.pkaranda.cibinternstesttask.model;

public enum Message {

    SUCCESS(0, "message.success"),
    NOT_ENOUGH_SOCKS(1, "error.norEnoughSocks"),
    COLOR_NOT_FOUND(2, "error.colorNotFound"),
    WRONG_OPERATION(3, "error.wrongOperation"),
    WRONG_COTTON_PART_VALUE(4, "error.wrongCottonPartValue"),
    WRONG_QUANTITY_VALUE(5, "error.wrongQuantityValue");

    private final int code;
    private final String text;

    Message(int code, String text) {
        this.code = code;
        this.text = text;
    }

    public int getCode() {
        return code;
    }

    public String getText() {
        return text;
    }

    public static Message valueOf(int code) {
        for (Message message : Message.values())
            if (message.getCode() == code)
                return message;
        return null;
    }
}
