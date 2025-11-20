-- GV Database
CREATE TABLE purchase
(
	pur_num		INTEGER,
	sup_id		INTEGER,
 	complete_date	DATE,
 	item_num	INTEGER,
 	quantity	INTEGER,
 	unit_cost	INTEGER,
 	note	 	VARCHAR(20),
 	PRIMARY KEY (pur_num, complete_date)
);

CREATE TABLE factory
(
	fac_id		INTEGER,
	fac_name	VARCHAR(15),
	address		VARCHAR(34),
	zip_code	VARCHAR(5),
	phone		VARCHAR(12),
	manager		INTEGER,
	PRIMARY KEY (fac_id)
);

CREATE TABLE skill
(
	sk_code		INTEGER,
	sk_title	VARCHAR(21),
	sk_description  VARCHAR(50),
	sk_level	VARCHAR(12)
        CHECK(sk_level IN ('beginner', 'intermediate', 'advanced')),
	PRIMARY KEY (sk_code)
);

CREATE TABLE person
(
	per_id		INTEGER,
	last_name	VARCHAR(20),
	first_name	VARCHAR(20),
	address		VARCHAR(50),
	zip_code	VARCHAR(5),
	email		VARCHAR(50),
	gender		VARCHAR(6),
	PRIMARY KEY (per_id)
);

CREATE TABLE position
(
	pos_code	INTEGER,
	pos_title	VARCHAR(34),
	description	VARCHAR(122),
	pay_range_high	INTEGER,
	pay_range_low	INTEGER,
	PRIMARY KEY (pos_code)
);

CREATE TABLE job
(
	job_code	INTEGER,
	j_title     VARCHAR(22),
	store_id	INTEGER,
	fac_id		INTEGER,
	comp_id 	INTEGER,
	pos_code	INTEGER,
	emp_mode	VARCHAR(9),
	pay_rate	INTEGER,
	pay_type	VARCHAR(9),
	cate_code	INTEGER,
	PRIMARY KEY (job_code),
	FOREIGN KEY (pos_code) REFERENCES position
		ON DELETE SET NULL,
	FOREIGN KEY (fac_id) REFERENCES factory
		ON DELETE SET NULL
);

CREATE TABLE works
(
	per_id		INTEGER,
	job_code	INTEGER,
	strt_date	DATE,
	end_date	DATE,
	PRIMARY KEY (per_id, job_code),
	FOREIGN KEY (per_id) REFERENCES person 
		ON DELETE CASCADE,
	FOREIGN KEY (job_code) REFERENCES job 
		ON DELETE CASCADE
);

CREATE TABLE requires
(
	sk_code		INTEGER,
	pos_code	INTEGER,
	PRIMARY KEY (pos_code, sk_code),
	FOREIGN KEY (sk_code) REFERENCES skill
		ON DELETE CASCADE,
	FOREIGN KEY (pos_code) REFERENCES position
		ON DELETE CASCADE
);

CREATE TABLE has_skill
(
	per_id		INTEGER,
	sk_code		INTEGER,
	PRIMARY KEY (per_id, sk_code),
	FOREIGN KEY (per_id) REFERENCES person
		ON DELETE CASCADE,
	FOREIGN KEY (sk_code) REFERENCES skill
		ON DELETE CASCADE
);

CREATE TABLE course
(
	c_code		INTEGER, 
	c_title		VARCHAR(28),
	c_level		VARCHAR(9),
	description	VARCHAR(96),
	status		VARCHAR(12),
	retail_price	NUMERIC(8,2),
	PRIMARY KEY (c_code)
);

CREATE TABLE teaches
(
	c_code 		INTEGER,
	sk_code		INTEGER,
	PRIMARY KEY (c_code, sk_code),
	FOREIGN KEY (c_code) REFERENCES course
		ON DELETE CASCADE,
	FOREIGN KEY (sk_code) REFERENCES skill
		ON DELETE CASCADE
);

CREATE TABLE takes
(
	per_id		INTEGER,
	c_code		INTEGER,
	sec_no		INTEGER,
	complete_date	DATE,
	year		INTEGER,
	offered_by	VARCHAR(50),
	format		VARCHAR(20),
	price		NUMERIC(8,2),
	PRIMARY KEY (per_id, c_code),
	FOREIGN KEY (per_id) REFERENCES person
		ON DELETE CASCADE,
	FOREIGN KEY (c_code) REFERENCES course
		ON DELETE CASCADE
);