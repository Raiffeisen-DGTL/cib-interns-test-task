CREATE OR REPLACE FUNCTION remove_rows_that_have_zero_quantity() RETURNS trigger
    LANGUAGE plpgsql AS
$$
BEGIN
    DELETE FROM socks WHERE quantity = 0;
    RETURN null;
END;
$$;

CREATE TRIGGER remove_rows_with_zero_quantity
    AFTER UPDATE
    ON socks
    EXECUTE PROCEDURE remove_rows_that_have_zero_quantity();
