CREATE TABLE purchase
(
	pur_num		NUMERIC(8,0),
	sup_id		VARCHAR(8),
 	complete_date	VARCHAR(8),
 	item_num	NUMERIC(8,0),
 	quantity	NUMERIC(8,0),
 	unit_cost	NUMERIC(8,0),
 	note	 	VARCHAR(20),
 	PRIMARY KEY (pur_num, complete_date)
);

CREATE TABLE factory
(
	fac_id		VARCHAR(8),
	fac_name	VARCHAR(15),
	address		VARCHAR(34),
	zip_code	VARCHAR(5),
	phone		VARCHAR(12),
	manager		VARCHAR(8),
	PRIMARY KEY (fac_id)
);

CREATE TABLE skill
(
	sk_code		VARCHAR(8),
	sk_title	VARCHAR(21),
	sk_description  VARCHAR(30),
	sk_level	VARCHAR(12)
        CHECK(sk_level IN ('beginner', 'intermediate', 'advanced')),
	PRIMARY KEY (sk_code)
);

CREATE TABLE person
(
	per_id		VARCHAR(8),
	last_name	VARCHAR(20),
	first_name	VARCHAR(20),
	address		VARCHAR(31),
	zip_code	VARCHAR(5),
	email		VARCHAR(24),
	gender		VARCHAR(6),
	PRIMARY KEY (per_id)
);

CREATE TABLE position
(
	pos_code	VARCHAR(8),
	pos_title	VARCHAR(34),
	description	VARCHAR(122),
	pay_range_high	NUMERIC(8,0),
	pay_range_low	NUMERIC(8,0),
	PRIMARY KEY (pos_code)
);

CREATE TABLE job
(
	job_code	VARCHAR(8),
	j_title     	VARCHAR(22),
    	fac_id 		VARCHAR(8),
	pos_code	VARCHAR(8),
	per_id		VARCHAR(8),
	emp_mode	VARCHAR(9),
	pay_rate	NUMERIC(8,0),
	pay_type	VARCHAR(9),
	cate_code	VARCHAR(8),
	company		VARCHAR(9),
	PRIMARY KEY (job_code),
	FOREIGN KEY (per_id) REFERENCES person
		ON DELETE SET NULL,
	FOREIGN KEY (pos_code) REFERENCES position
		ON DELETE SET NULL,
	FOREIGN KEY (fac_id) REFERENCES factory
		ON DELETE SET NULL
);

CREATE TABLE requires
(
	sk_code		VARCHAR(8),
	pos_code	VARCHAR(8),
	PRIMARY KEY (pos_code, sk_code),
	FOREIGN KEY (sk_code) REFERENCES skill
		ON DELETE CASCADE,
	FOREIGN KEY (pos_code) REFERENCES position
		ON DELETE CASCADE
);

CREATE TABLE has_skill
(
	per_id		VARCHAR(8),
	sk_code		VARCHAR(8),
	PRIMARY KEY (per_id, sk_code),
	FOREIGN KEY (per_id) REFERENCES person
		ON DELETE CASCADE,
	FOREIGN KEY (sk_code) REFERENCES skill
		ON DELETE CASCADE
);
