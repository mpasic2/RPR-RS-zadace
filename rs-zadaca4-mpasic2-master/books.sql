BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "books" (
	"id"	INTEGER,
	"author"	TEXT,
	"title"	TEXT,
	"isbn"	TEXT,
	"pagecount"	INTEGER,
	"publishdate"	DATE,
	PRIMARY KEY("id")
);
INSERT INTO "books" ("id","author","title","isbn","pagecount","publishdate") VALUES (1,'Meša Selimović','Tvrđava','abcd',500,1558562400000);
INSERT INTO "books" ("id","author","title","isbn","pagecount","publishdate") VALUES (2,'Ivo Andrić','Travnička hronika','abcd',500,1558562400000);
INSERT INTO "books" ("id","author","title","isbn","pagecount","publishdate") VALUES (3,'J. K. Rowling','Harry Potter','abcd',500,1558562400000);
COMMIT;
