--------------------------------------------------------
--  File created - Thứ Tư-tháng 10-22-2025   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Type LOGMNR$COL_GG_REC
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TYPE "SYSTEM"."LOGMNR$COL_GG_REC" 

/
--------------------------------------------------------
--  DDL for Type LOGMNR$COL_GG_RECS
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TYPE "SYSTEM"."LOGMNR$COL_GG_RECS" 

/
--------------------------------------------------------
--  DDL for Type LOGMNR$GSBA_GG_REC
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TYPE "SYSTEM"."LOGMNR$GSBA_GG_REC" 

/
--------------------------------------------------------
--  DDL for Type LOGMNR$GSBA_GG_RECS
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TYPE "SYSTEM"."LOGMNR$GSBA_GG_RECS" 

/
--------------------------------------------------------
--  DDL for Type LOGMNR$KEY_GG_REC
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TYPE "SYSTEM"."LOGMNR$KEY_GG_REC" 

/
--------------------------------------------------------
--  DDL for Type LOGMNR$KEY_GG_RECS
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TYPE "SYSTEM"."LOGMNR$KEY_GG_RECS" 

/
--------------------------------------------------------
--  DDL for Type LOGMNR$SEQ_GG_REC
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TYPE "SYSTEM"."LOGMNR$SEQ_GG_REC" 

/
--------------------------------------------------------
--  DDL for Type LOGMNR$SEQ_GG_RECS
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TYPE "SYSTEM"."LOGMNR$SEQ_GG_RECS" 

/
--------------------------------------------------------
--  DDL for Type LOGMNR$TAB_GG_REC
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TYPE "SYSTEM"."LOGMNR$TAB_GG_REC" 

/
--------------------------------------------------------
--  DDL for Type LOGMNR$TAB_GG_RECS
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TYPE "SYSTEM"."LOGMNR$TAB_GG_RECS" 

/
--------------------------------------------------------
--  DDL for Type LOGMNR$USER_GG_REC
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TYPE "SYSTEM"."LOGMNR$USER_GG_REC" 

/
--------------------------------------------------------
--  DDL for Type LOGMNR$USER_GG_RECS
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TYPE "SYSTEM"."LOGMNR$USER_GG_RECS" 

/
--------------------------------------------------------
--  DDL for Sequence LOGMNR_DIDS$
--------------------------------------------------------

   CREATE SEQUENCE  "SYSTEM"."LOGMNR_DIDS$"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 NOCACHE  ORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence LOGMNR_EVOLVE_SEQ$
--------------------------------------------------------

   CREATE SEQUENCE  "SYSTEM"."LOGMNR_EVOLVE_SEQ$"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 NOCACHE  ORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence LOGMNR_SEQ$
--------------------------------------------------------

   CREATE SEQUENCE  "SYSTEM"."LOGMNR_SEQ$"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 NOCACHE  ORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence LOGMNR_UIDS$
--------------------------------------------------------

   CREATE SEQUENCE  "SYSTEM"."LOGMNR_UIDS$"  MINVALUE 100 MAXVALUE 99999 INCREMENT BY 1 START WITH 100 NOCACHE  ORDER  CYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence MVIEW$_ADVSEQ_GENERIC
--------------------------------------------------------

   CREATE SEQUENCE  "SYSTEM"."MVIEW$_ADVSEQ_GENERIC"  MINVALUE 1 MAXVALUE 4294967295 INCREMENT BY 1 START WITH 1 CACHE 50 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence MVIEW$_ADVSEQ_ID
--------------------------------------------------------

   CREATE SEQUENCE  "SYSTEM"."MVIEW$_ADVSEQ_ID"  MINVALUE 1 MAXVALUE 4294967295 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence ROLLING_EVENT_SEQ$
--------------------------------------------------------

   CREATE SEQUENCE  "SYSTEM"."ROLLING_EVENT_SEQ$"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 NOCACHE  ORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence SEQ_CLASSROOM_ID
--------------------------------------------------------

   CREATE SEQUENCE  "SYSTEM"."SEQ_CLASSROOM_ID"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 12 NOCACHE  NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence SQL2_DB_NHANSU_BANLUONG_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "SYSTEM"."SQL2_DB_NHANSU_BANLUONG_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence SQL2_DB_NHANSU_NHANVIEN_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "SYSTEM"."SQL2_DB_NHANSU_NHANVIEN_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence SQL2_DB_NHANSU_PHONGBAN_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "SYSTEM"."SQL2_DB_NHANSU_PHONGBAN_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence SQL2_DB_NHANSU_THAMNIEN_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "SYSTEM"."SQL2_DB_NHANSU_THAMNIEN_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Table JV3_CATEGORIES
--------------------------------------------------------

  CREATE TABLE "SYSTEM"."JV3_CATEGORIES" 
   (	"ID" VARCHAR2(50 BYTE), 
	"NAME" VARCHAR2(200 BYTE)
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Table JV3_NEWS
--------------------------------------------------------

  CREATE TABLE "SYSTEM"."JV3_NEWS" 
   (	"ID" VARCHAR2(50 BYTE), 
	"TITLE" VARCHAR2(500 BYTE), 
	"CONTENT" CLOB, 
	"IMAGE" VARCHAR2(500 BYTE), 
	"POSTEDDATE" DATE DEFAULT SYSDATE, 
	"AUTHOR" VARCHAR2(50 BYTE), 
	"VIEWCOUNT" NUMBER DEFAULT 0, 
	"CATEGORYID" VARCHAR2(50 BYTE), 
	"HOME" CHAR(1 BYTE)
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" 
 LOB ("CONTENT") STORE AS BASICFILE (
  TABLESPACE "SYSTEM" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION 
  NOCACHE LOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)) ;
--------------------------------------------------------
--  DDL for Table JV3_NEWSLETTERS
--------------------------------------------------------

  CREATE TABLE "SYSTEM"."JV3_NEWSLETTERS" 
   (	"EMAIL" VARCHAR2(100 BYTE), 
	"ENABLED" CHAR(1 BYTE) DEFAULT 'Y'    -- 'Y' = còn hiệu lực, 'N' = hủy

   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Table JV3_USERS
--------------------------------------------------------

  CREATE TABLE "SYSTEM"."JV3_USERS" 
   (	"ID" VARCHAR2(50 BYTE), 
	"PASSWORD" VARCHAR2(100 BYTE), 
	"FULLNAME" VARCHAR2(200 BYTE), 
	"BIRTHDAY" DATE, 
	"GENDER" CHAR(1 BYTE), 
	"MOBILE" VARCHAR2(20 BYTE), 
	"EMAIL" VARCHAR2(100 BYTE), 
	"ROLE" CHAR(1 BYTE)
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
GRANT READ ON "SYSTEM"."PRODUCT_PRIVS" TO PUBLIC;
GRANT SELECT ON "SYSTEM"."SCHEDULER_JOB_ARGS" TO "SELECT_CATALOG_ROLE";
GRANT SELECT ON "SYSTEM"."SCHEDULER_PROGRAM_ARGS" TO "SELECT_CATALOG_ROLE";
REM INSERTING into SYSTEM.JV3_CATEGORIES
SET DEFINE OFF;
Insert into SYSTEM.JV3_CATEGORIES (ID,NAME) values ('CAT001','Công nghệ');
Insert into SYSTEM.JV3_CATEGORIES (ID,NAME) values ('CAT002','Kinh tế');
Insert into SYSTEM.JV3_CATEGORIES (ID,NAME) values ('CAT003','Giáo dục');
Insert into SYSTEM.JV3_CATEGORIES (ID,NAME) values ('CAT004','Thể thao');
Insert into SYSTEM.JV3_CATEGORIES (ID,NAME) values ('CAT005','Môi trường');
Insert into SYSTEM.JV3_CATEGORIES (ID,NAME) values ('CAT006','Xã hội');
Insert into SYSTEM.JV3_CATEGORIES (ID,NAME) values ('CAT007','Văn hóa');
Insert into SYSTEM.JV3_CATEGORIES (ID,NAME) values ('CAT008','Pháp luật');
REM INSERTING into SYSTEM.JV3_NEWS
SET DEFINE OFF;
Insert into SYSTEM.JV3_NEWS (ID,TITLE,IMAGE,POSTEDDATE,AUTHOR,VIEWCOUNT,CATEGORYID,HOME) values ('NEWS006','Thị trường chứng khoán biến động trước thềm cuộc họp chính sách','thitruongchungkhoan.png',to_date('10-10-2025','DD-MM-RRRR'),'REP002','1502','CAT002','Y');
Insert into SYSTEM.JV3_NEWS (ID,TITLE,IMAGE,POSTEDDATE,AUTHOR,VIEWCOUNT,CATEGORYID,HOME) values ('NEWS001','Công nghệ AI đang thay đổi thế giới','congngheai.jpg',to_date('15-10-2025','DD-MM-RRRR'),'REP001','1261','CAT001','N');
Insert into SYSTEM.JV3_NEWS (ID,TITLE,IMAGE,POSTEDDATE,AUTHOR,VIEWCOUNT,CATEGORYID,HOME) values ('NEWS002','Kinh tế Việt Nam tăng trưởng mạnh','kinhtevietnam.png',to_date('14-10-2025','DD-MM-RRRR'),'REP002','984','CAT002','Y');
Insert into SYSTEM.JV3_NEWS (ID,TITLE,IMAGE,POSTEDDATE,AUTHOR,VIEWCOUNT,CATEGORYID,HOME) values ('NEWS004','Thể thao: Việt Nam giành huy chương vàng','thethaovietnam.jpg',to_date('12-10-2025','DD-MM-RRRR'),'REP003','2102','CAT004','Y');
Insert into SYSTEM.JV3_NEWS (ID,TITLE,IMAGE,POSTEDDATE,AUTHOR,VIEWCOUNT,CATEGORYID,HOME) values ('NEWS007','BGD công bố phương án thi tốt nghiệp THPT mới','phuongantotnghiep2025.png',to_date('09-10-2025','DD-MM-RRRR'),'REP001','1801','CAT003','N');
Insert into SYSTEM.JV3_NEWS (ID,TITLE,IMAGE,POSTEDDATE,AUTHOR,VIEWCOUNT,CATEGORYID,HOME) values ('NEWS008','Đội tuyển quốc gia chuẩn bị cho vòng loại World Cup','doituyenquocgia.png',to_date('08-10-2025','DD-MM-RRRR'),'REP003','2501','CAT004','Y');
Insert into SYSTEM.JV3_NEWS (ID,TITLE,IMAGE,POSTEDDATE,AUTHOR,VIEWCOUNT,CATEGORYID,HOME) values ('NEWS009','Chiến dịch trồng 1 tỷ cây xanh đạt kết quả tích cực','mottycayxanh.png',to_date('07-10-2025','DD-MM-RRRR'),'REP002','950','CAT005','N');
Insert into SYSTEM.JV3_NEWS (ID,TITLE,IMAGE,POSTEDDATE,AUTHOR,VIEWCOUNT,CATEGORYID,HOME) values ('NEWS010','Xu hướng làm việc từ xa tiếp tục phát triển sau đại dịch','lamviectuxa.png',to_date('06-10-2025','DD-MM-RRRR'),'REP001','1100','CAT006','Y');
Insert into SYSTEM.JV3_NEWS (ID,TITLE,IMAGE,POSTEDDATE,AUTHOR,VIEWCOUNT,CATEGORYID,HOME) values ('NEWS011','Lễ hội Áo dài di sản văn hóa được tổ chức tại Huế','lehoiaodaihue.png',to_date('05-10-2025','DD-MM-RRRR'),'REP003','800','CAT007','N');
Insert into SYSTEM.JV3_NEWS (ID,TITLE,IMAGE,POSTEDDATE,AUTHOR,VIEWCOUNT,CATEGORYID,HOME) values ('NEWS012','Luật bảo vệ dữ liệu cá nhân mới chính thức có hiệu lực','luatbaovedulieucanhan.png',to_date('04-10-2025','DD-MM-RRRR'),'REP002','1350','CAT008','Y');
Insert into SYSTEM.JV3_NEWS (ID,TITLE,IMAGE,POSTEDDATE,AUTHOR,VIEWCOUNT,CATEGORYID,HOME) values ('NEWS013','Mạng 6G bắt đầu được nghiên cứu và phát triển','mang6g.png',to_date('03-10-2025','DD-MM-RRRR'),'REP001','1900','CAT001','Y');
Insert into SYSTEM.JV3_NEWS (ID,TITLE,IMAGE,POSTEDDATE,AUTHOR,VIEWCOUNT,CATEGORYID,HOME) values ('NEWS014','Ngành du lịch phục hồi ấn tượng sau thời gian dài đóng băng','dulichphuchoi.png',to_date('02-10-2025','DD-MM-RRRR'),'REP003','2200','CAT002','Y');
Insert into SYSTEM.JV3_NEWS (ID,TITLE,IMAGE,POSTEDDATE,AUTHOR,VIEWCOUNT,CATEGORYID,HOME) values ('NEWS015','Giải Grand Slam quần vợt chứng kiến bất ngờ lớn','giaigrandslamquanvot.png',to_date('01-10-2025','DD-MM-RRRR'),'REP002','1750','CAT004','N');
Insert into SYSTEM.JV3_NEWS (ID,TITLE,IMAGE,POSTEDDATE,AUTHOR,VIEWCOUNT,CATEGORYID,HOME) values ('NEWS016','Dự án tàu điện ngầm đô thị đi vào giai đoạn vận hành thử','taudienngamdothi.png',to_date('30-09-2025','DD-MM-RRRR'),'REP001','2801','CAT006','Y');
Insert into SYSTEM.JV3_NEWS (ID,TITLE,IMAGE,POSTEDDATE,AUTHOR,VIEWCOUNT,CATEGORYID,HOME) values ('NEWS017','Phim điện ảnh Việt Nam được đề cử tại liên hoan phim quốc tế','dienanhvietnam.png',to_date('29-09-2025','DD-MM-RRRR'),'REP003','1200','CAT007','N');
Insert into SYSTEM.JV3_NEWS (ID,TITLE,IMAGE,POSTEDDATE,AUTHOR,VIEWCOUNT,CATEGORYID,HOME) values ('NEWS018','Cảnh báo về các hình thức lừa đảo trực tuyến tinh vi mới','luadaoonline.png',to_date('28-09-2025','DD-MM-RRRR'),'REP002','1600','CAT008','Y');
Insert into SYSTEM.JV3_NEWS (ID,TITLE,IMAGE,POSTEDDATE,AUTHOR,VIEWCOUNT,CATEGORYID,HOME) values ('NEWS020','Nông sản Việt Nam chinh phục thị trường châu Âu','nongsanvietnam.png',to_date('26-09-2025','DD-MM-RRRR'),'REP003','1950','CAT002','Y');
Insert into SYSTEM.JV3_NEWS (ID,TITLE,IMAGE,POSTEDDATE,AUTHOR,VIEWCOUNT,CATEGORYID,HOME) values ('NEWS021','Giải pháp năng lượng tái tạo được đẩy mạnh triển khai','nangluongtaitao.png',to_date('25-09-2025','DD-MM-RRRR'),'REP002','1050','CAT005','N');
Insert into SYSTEM.JV3_NEWS (ID,TITLE,IMAGE,POSTEDDATE,AUTHOR,VIEWCOUNT,CATEGORYID,HOME) values ('NEWS022','Mô hình "Trường học hạnh phúc" được nhân rộng','truonghochanhphuc.png',to_date('24-09-2025','DD-MM-RRRR'),'REP001','1300','CAT003','Y');
Insert into SYSTEM.JV3_NEWS (ID,TITLE,IMAGE,POSTEDDATE,AUTHOR,VIEWCOUNT,CATEGORYID,HOME) values ('NEWS003','Giáo dục đại học: Xu hướng mới','xuhuonggiaoduc.jpg',to_date('13-10-2025','DD-MM-RRRR'),'REP001','765','CAT003','N');
Insert into SYSTEM.JV3_NEWS (ID,TITLE,IMAGE,POSTEDDATE,AUTHOR,VIEWCOUNT,CATEGORYID,HOME) values ('NEWS005','Môi trường: Bảo vệ rừng Amazon','baoverungamazon.jpg',to_date('11-10-2025','DD-MM-RRRR'),'REP002','896','CAT005','Y');
Insert into SYSTEM.JV3_NEWS (ID,TITLE,IMAGE,POSTEDDATE,AUTHOR,VIEWCOUNT,CATEGORYID,HOME) values ('NEWS023','TEST123123123456789','congngheai.jpg',to_date('22-10-2025','DD-MM-RRRR'),'REP001','0','CAT001','N');
REM INSERTING into SYSTEM.JV3_NEWSLETTERS
SET DEFINE OFF;
Insert into SYSTEM.JV3_NEWSLETTERS (EMAIL,ENABLED) values ('reader1@gmail.com','Y');
Insert into SYSTEM.JV3_NEWSLETTERS (EMAIL,ENABLED) values ('reader2@gmail.com','N');
Insert into SYSTEM.JV3_NEWSLETTERS (EMAIL,ENABLED) values ('ichisora167@gmail.com','Y');
REM INSERTING into SYSTEM.JV3_USERS
SET DEFINE OFF;
Insert into SYSTEM.JV3_USERS (ID,PASSWORD,FULLNAME,BIRTHDAY,GENDER,MOBILE,EMAIL,ROLE) values ('admin01','admin01','Nguyễn Trần Duy Nhất',to_date('12-05-1990','DD-MM-RRRR'),'M','0901234567','admin@gmail.com','A');
Insert into SYSTEM.JV3_USERS (ID,PASSWORD,FULLNAME,BIRTHDAY,GENDER,MOBILE,EMAIL,ROLE) values ('REP004','123456','Văn Danh',to_date('22-10-2025','DD-MM-RRRR'),null,null,'vandanh123@gmail.com','R');
Insert into SYSTEM.JV3_USERS (ID,PASSWORD,FULLNAME,BIRTHDAY,GENDER,MOBILE,EMAIL,ROLE) values ('REP001','password123','Nguyễn Văn An',to_date('15-05-1990','DD-MM-RRRR'),'M','0901234567','an.nguyen@abcnews.com','R');
Insert into SYSTEM.JV3_USERS (ID,PASSWORD,FULLNAME,BIRTHDAY,GENDER,MOBILE,EMAIL,ROLE) values ('REP002','password123','Trần Thị Bình',to_date('20-08-1992','DD-MM-RRRR'),'F','0907654321','binh.tran@abcnews.com','R');
Insert into SYSTEM.JV3_USERS (ID,PASSWORD,FULLNAME,BIRTHDAY,GENDER,MOBILE,EMAIL,ROLE) values ('REP003','password123','Lê Minh Hùng',to_date('01-11-1988','DD-MM-RRRR'),'M','0988112233','hung.le@abcnews.com','R');
--------------------------------------------------------
--  DDL for Index SYS_C007674
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYSTEM"."SYS_C007674" ON "SYSTEM"."JV3_CATEGORIES" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index SYS_C007680
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYSTEM"."SYS_C007680" ON "SYSTEM"."JV3_NEWS" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index SYS_C007683
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYSTEM"."SYS_C007683" ON "SYSTEM"."JV3_NEWSLETTERS" ("EMAIL") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index SYS_C007678
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYSTEM"."SYS_C007678" ON "SYSTEM"."JV3_USERS" ("EMAIL") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index SYS_C007677
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYSTEM"."SYS_C007677" ON "SYSTEM"."JV3_USERS" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Function LOGMNR$COL_GG_TABF_PUBLIC
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE FUNCTION "SYSTEM"."LOGMNR$COL_GG_TABF_PUBLIC" 

/
--------------------------------------------------------
--  DDL for Function LOGMNR$GSBA_GG_TABF_PUBLIC
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE FUNCTION "SYSTEM"."LOGMNR$GSBA_GG_TABF_PUBLIC" 

/
--------------------------------------------------------
--  DDL for Function LOGMNR$KEY_GG_TABF_PUBLIC
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE FUNCTION "SYSTEM"."LOGMNR$KEY_GG_TABF_PUBLIC" 

/
--------------------------------------------------------
--  DDL for Function LOGMNR$SEQ_GG_TABF_PUBLIC
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE FUNCTION "SYSTEM"."LOGMNR$SEQ_GG_TABF_PUBLIC" 

/
--------------------------------------------------------
--  DDL for Function LOGMNR$TAB_GG_TABF_PUBLIC
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE FUNCTION "SYSTEM"."LOGMNR$TAB_GG_TABF_PUBLIC" 

/
--------------------------------------------------------
--  DDL for Function LOGMNR$USER_GG_TABF_PUBLIC
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE FUNCTION "SYSTEM"."LOGMNR$USER_GG_TABF_PUBLIC" 

/
--------------------------------------------------------
--  Constraints for Table JV3_CATEGORIES
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."JV3_CATEGORIES" MODIFY ("NAME" NOT NULL ENABLE);
  ALTER TABLE "SYSTEM"."JV3_CATEGORIES" ADD PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
--------------------------------------------------------
--  Constraints for Table JV3_NEWS
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."JV3_NEWS" MODIFY ("TITLE" NOT NULL ENABLE);
  ALTER TABLE "SYSTEM"."JV3_NEWS" ADD PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
--------------------------------------------------------
--  Constraints for Table JV3_NEWSLETTERS
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."JV3_NEWSLETTERS" ADD PRIMARY KEY ("EMAIL")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
--------------------------------------------------------
--  Constraints for Table JV3_USERS
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."JV3_USERS" MODIFY ("PASSWORD" NOT NULL ENABLE);
  ALTER TABLE "SYSTEM"."JV3_USERS" MODIFY ("FULLNAME" NOT NULL ENABLE);
  ALTER TABLE "SYSTEM"."JV3_USERS" ADD PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "SYSTEM"."JV3_USERS" ADD UNIQUE ("EMAIL")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table JV3_NEWS
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."JV3_NEWS" ADD CONSTRAINT "FK_NEWS_AUTHOR" FOREIGN KEY ("AUTHOR")
	  REFERENCES "SYSTEM"."JV3_USERS" ("ID") ENABLE;
  ALTER TABLE "SYSTEM"."JV3_NEWS" ADD CONSTRAINT "FK_NEWS_CATEGORY" FOREIGN KEY ("CATEGORYID")
	  REFERENCES "SYSTEM"."JV3_CATEGORIES" ("ID") ENABLE;
