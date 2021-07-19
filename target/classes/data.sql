INSERT INTO DOG( BREED, AGE, NAME) values ( 'SPANIEL', 2, 'Lucy');
INSERT INTO DOG( BREED, AGE, NAME) values ( 'LABRADOR', 1, 'Molly');
INSERT INTO DOG( BREED, AGE, NAME) values ( 'SPANIEL', 0, 'Boi');
INSERT INTO DOG( BREED, AGE, NAME) values ( 'VIZSLA', 2, 'Tango');
INSERT INTO DOG( BREED, AGE, NAME) values ( 'PUG', 4, 'Alfa');

INSERT INTO DOG( BREED, AGE, NAME) values ( 'LABRADOR', 1, 'Gel');
INSERT INTO DOG( BREED, AGE, NAME) values ( 'SPANIEL', 0, 'Boyo');

INSERT INTO DOG( BREED, AGE, NAME) values ( 'LABRADOR', 5, 'Pupeh');
INSERT INTO DOG( BREED, AGE, NAME) values ( 'SPANIEL', 5, 'Dadio');
INSERT INTO DOG( BREED, AGE, NAME) values ( 'LABRADOR', 10, 'GranGran');
INSERT INTO DOG( BREED, AGE, NAME) values ( 'SPANIEL', 10, 'GranPapa');


INSERT INTO PEDIGREE (PUPPY_ID, MOM_ID, DAD_ID) values (3, 1, 2);
INSERT INTO PEDIGREE (PUPPY_ID, MOM_ID, DAD_ID) values (6, 1, 2);
INSERT INTO PEDIGREE (PUPPY_ID, MOM_ID, DAD_ID) values (7, 1, 2);
INSERT INTO PEDIGREE (PUPPY_ID, MOM_ID, DAD_ID) values (null, null, 9);
INSERT INTO PEDIGREE (PUPPY_ID, MOM_ID, DAD_ID) values (8, 10, 11);
INSERT INTO PEDIGREE (PUPPY_ID, MOM_ID, DAD_ID) values (9, null, null);
INSERT INTO PEDIGREE (PUPPY_ID, MOM_ID, DAD_ID) values (9, null, null);

INSERT INTO TRICK(NAME) values ('sit');
INSERT INTO TRICK(NAME) values ('high five');
INSERT INTO TRICK(NAME) values ('handshake');
INSERT INTO TRICK(NAME) values ('roll over');

INSERT INTO SKILL(DOG_ID, TRICK_ID, LEVEL) values (1, 1, 1);
INSERT INTO SKILL(DOG_ID, TRICK_ID, LEVEL) values (1, 2, 1);
INSERT INTO SKILL(DOG_ID, TRICK_ID, LEVEL) values (1, 3, 1);
INSERT INTO SKILL(DOG_ID, TRICK_ID, LEVEL) values (1, 4, 1);
INSERT INTO SKILL(DOG_ID, TRICK_ID) values (2, 2);
INSERT INTO SKILL(DOG_ID, TRICK_ID) values (3, 2);
INSERT INTO SKILL(DOG_ID, TRICK_ID) values (3, 3);
INSERT INTO SKILL(DOG_ID, TRICK_ID) values (4, 2);


