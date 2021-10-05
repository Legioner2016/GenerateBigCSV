CREATE TABLE IF NOT EXISTS test (
  --id INT AUTO_INCREMENT  PRIMARY KEY, --h2db
  id SERIAL  PRIMARY KEY, --postgresql
  owner_id INT,
  string1 VARCHAR(256),
  string2 VARCHAR(256),
  string3 VARCHAR(256),
  string4 VARCHAR(256),
  string5 VARCHAR(256),
  int1 INT,
  int2 INT,
  int3 INT,
  int4 INT,
  int5 INT,
  numeric1 Numeric(5, 2),
  numeric2 Numeric(5, 2),
  numeric3 Numeric(5, 2),
  numeric4 Numeric(5, 2),
  numeric5 Numeric(5, 2)
);

-- H2DB too long 
--INSERT INTO test(id, owner_id, string1, int1, numeric1)
--SELECT * FROM 
--(SELECT X, 1, 'test for owner 1', 125, 18.2  
--FROM SYSTEM_RANGE(1, 1000000) X
--UNION ALL 
--SELECT X, 2, 'test for owner 2', 4558, 6.4  
--FROM SYSTEM_RANGE(1000001, 2500000) X
--UNION ALL 
--SELECT X, 3, 'test for owner 3', 0, 7.3  
--FROM SYSTEM_RANGE(2500001, 3000000) X) as tab;

--postgresql. Execute manually (no dml script used)
INSERT INTO test(id, owner_id, string1, int1, numeric1)
SELECT * FROM 
(SELECT X, 1, 'test for owner 1', 125, 18.2  
FROM generate_series(1, 1000000) X
UNION ALL 
SELECT X, 2, 'test for owner 2', 4558, 6.4  
FROM generate_series(1000001, 2500000) X
UNION ALL 
SELECT X, 3, 'test for owner 3', 0, 7.3  
FROM generate_series(2500001, 3000000) X) as tab;


