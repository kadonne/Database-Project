CREATE TABLE inventory
(	
	item_num	NUMERIC(8,0),
	title		VARCHAR(22),
 	description	VARCHAR(38),
 	quantity	NUMERIC(8,0),
 	unit		VARCHAR(9),
 	avg_cost	NUMERIC(8,0),
 	old_date	VARCHAR(8),
 	min_level	VARCHAR(8),
 	shelf_code	VARCHAR(5),
 	sh_title	VARCHAR(8),
 	PRIMARY KEY (item_num)
);

CREATE TABLE supplier
(
	sup_id		VARCHAR(8),
	address		VARCHAR(20),
	zip_code	VARCHAR(5),
	phone	 	VARCHAR(10),
	PRIMARY KEY (sup_id)
);

CREATE TABLE account_payable
(
	sup_id		VARCHAR(8),
 	balance		NUMERIC(8,0),
 	PRIMARY KEY (sup_id),
	FOREIGN KEY (sup_id) REFERENCES supplier
		ON DELETE CASCADE
);
	
CREATE TABLE purchase
(
	pur_num		NUMERIC(8,0),
	sup_id		VARCHAR(8),
 	complete_date	VARCHAR(8),
 	item_num	NUMERIC(8,0),
 	quantity	NUMERIC(8,0),
 	unit_cost	NUMERIC(8,0),
 	note	 	VARCHAR(20),
 	PRIMARY KEY (pur_num, complete_date),
	FOREIGN KEY (sup_id) REFERENCES account_payable
		ON DELETE SET NULL
);

CREATE TABLE store
(
	store_id	VARCHAR(8),
	address		VARCHAR(34),
	zip_code	VARCHAR(5),
	phone		VARCHAR(12),
	PRIMARY KEY (store_id)
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
    	store_id	VARCHAR(8),
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
	FOREIGN KEY (store_id) REFERENCES store
		ON DELETE SET NULL
);

CREATE TABLE purchase_payment_record
(
	pur_num		NUMERIC(8,0),
	complete_date	VARCHAR(8),
	sup_id		VARCHAR(8),
	amount		NUMERIC(8,0),
	trans_type	VARCHAR(6)
		check (trans_type in ('credit','debit')),
	PRIMARY KEY (pur_num),
	FOREIGN KEY (pur_num, complete_date) REFERENCES purchase 
		ON DELETE CASCADE,
	FOREIGN KEY (sup_id)  REFERENCES account_payable 
		ON DELETE SET NULL
); 

CREATE TABLE stocking
(
	item_num	NUMERIC(8,0),
	pur_num		NUMERIC(8,0),
	complete_date	VARCHAR(8),
	PRIMARY KEY (item_num, pur_num),
	FOREIGN KEY (item_num) REFERENCES inventory 
		ON DELETE CASCADE,
	FOREIGN KEY (pur_num, complete_date) REFERENCES purchase 
		ON DELETE CASCADE
);

CREATE TABLE sales
(
	item_num 	NUMERIC (8,0),
	store_id	VARCHAR(8),
	shelf_code	VARCHAR(5),
	invoice_num	VARCHAR(8),
	s_year		NUMERIC (4,0),
	s_month		NUMERIC (2,0),
	s_day		NUMERIC (2,0),
	quantity	NUMERIC (8,0),
	price		NUMERIC(8,0),
	avg_cost	NUMERIC (8,0),
	note		VARCHAR(20),
	PRIMARY KEY (item_num, store_id),
	FOREIGN KEY (store_id) REFERENCES store
		ON DELETE CASCADE
	/*FOREIGN KEY (item_num) REFERENCES inventory
		ON DELETE CASCADE*/
);

CREATE TABLE addi_requires
(
	sk_code		VARCHAR(8),
	job_code	VARCHAR(8),
	PRIMARY KEY (job_code, sk_code),
	FOREIGN KEY (sk_code) REFERENCES skill
		ON DELETE CASCADE,
	FOREIGN KEY (job_code) REFERENCES job
		ON DELETE CASCADE
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
