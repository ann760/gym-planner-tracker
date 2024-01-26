DROP TABLE IF EXISTS skill_drill;
DROP TABLE IF EXISTS drill;
DROP TABLE IF EXISTS skill;
DROP TABLE IF EXISTS event;
DROP TABLE IF EXISTS achievement;
DROP TABLE IF EXISTS client;


CREATE TABLE event(
	event_id int NOT NULL AUTO_INCREMENT,
	event_name varchar(128) NOT NULL,
	event_description varchar(256) NOT NULL,
	PRIMARY KEY (event_id)
);

CREATE TABLE skill(
	skill_id int NOT NULL AUTO_INCREMENT,
	event_id int NOT NULL,
	skill_name varchar(128) NOT NULL,
	skill_description varchar(256) NOT NULL,
	skill_level int,
	PRIMARY KEY (skill_id),
	FOREIGN KEY (event_id) REFERENCES event (event_id) ON DELETE CASCADE
);

CREATE TABLE drill(
	drill_id int NOT NULL AUTO_INCREMENT,
	drill_name varchar(128) NOT NULL,
	drill_description varchar(256) NOT NULL,
	PRIMARY KEY (drill_id)
);

CREATE TABLE skill_drill (
	skill_id int NOT NULL,
	drill_id int NOT NULL,
	FOREIGN KEY (skill_id) REFERENCES skill (skill_id) ON DELETE CASCADE,
	FOREIGN KEY (drill_id) REFERENCES drill (drill_id) ON DELETE CASCADE
);