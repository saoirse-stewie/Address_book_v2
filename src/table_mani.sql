use addressbook;



ALTER TABLE addresses CHANGE COLUMN county `county` VARCHAR(10) NOT NULL;

/*ALTER TABLE addresses
DROP COLUMN zipcode;