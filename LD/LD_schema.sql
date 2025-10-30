CREATE TABLE company
(
	comp_id		VARCHAR(8),
	industry_group	VARCHAR(8),
	address		VARCHAR(34),
	zip_code	VARCHAR(5),
	website		VARCHAR(4),
	PRIMARY KEY (comp_id)
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
	last_name	VARCHAR(12),
	first_name	VARCHAR(12),
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
    	comp_id 	VARCHAR(8),
	pos_code	VARCHAR(8),
	emp_mode	VARCHAR(9),
	pay_rate	NUMERIC(8,0),
	pay_type	VARCHAR(9),
	cate_code	VARCHAR(8),
	PRIMARY KEY (job_code),
	FOREIGN KEY (pos_code) REFERENCES position
		ON DELETE SET NULL,
	FOREIGN KEY (comp_id) REFERENCES company
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

CREATE TABLE GICS
(
	ind_id		VARCHAR(8),
	ind_title	VARCHAR(8),
	ind_level	VARCHAR(8),
	ind_description	VARCHAR(30),
	parent_id	VARCHAR(8),
	PRIMARY KEY (ind_id)
);
CREATE TABLE sub_ind
(
	comp_id		VARCHAR(8),
	ind_id		VARCHAR(8),
	PRIMARY KEY (comp_id, ind_id),
	FOREIGN KEY (comp_id) REFERENCES company
		ON DELETE CASCADE,
	FOREIGN KEY (ind_id) REFERENCES GICS
		ON DELETE CASCADE
);

CREATE TABLE course
(
	c_code		VARCHAR(8), 
	c_title		VARCHAR(28),
	c_level		VARCHAR(9),
	description	VARCHAR(96),
	status		VARCHAR(12),
	retail_price	NUMERIC(8,2),
	PRIMARY KEY (c_code)
);

CREATE TABLE teaches
(
	c_code 		VARCHAR(8),
	sk_code		VARCHAR(8),
	PRIMARY KEY (c_code, sk_code),
	FOREIGN KEY (c_code) REFERENCES course
		ON DELETE CASCADE,
	FOREIGN KEY (sk_code) REFERENCES skill
		ON DELETE CASCADE
);


CREATE TABLE works
(
	per_id		VARCHAR(8),
	job_code	VARCHAR(8),
	s_y	VARCHAR(4),
	s_m	VARCHAR(3),
	s_d	VARCHAR(3),
	e_y	VARCHAR(4),
	e_m	VARCHAR(3),
	e_d 	VARCHAR(3),
	PRIMARY KEY (per_id, job_code),
	FOREIGN KEY (per_id) REFERENCES person 
		ON DELETE CASCADE,
	FOREIGN KEY (job_code) REFERENCES job 
		ON DELETE CASCADE
);
