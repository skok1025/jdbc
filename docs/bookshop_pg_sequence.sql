drop sequence seq_author;
create sequence seq_author start 1;

drop sequence seq_book;
create sequence seq_book start 1;


-- cf)
CREATE TABLE "author"
(
	"no"   integer     NOT NULL default nextval('seq_author'), -- 번호
	"name" VARCHAR(30) NOT NULL  -- 이름
);