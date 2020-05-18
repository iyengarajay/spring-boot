 CREATE TABLE social_media_site(
	id INTEGER  PRIMARY KEY,
	name VARCHAR (255) UNIQUE NOT NULL,
	description VARCHAR (255) NOT NULL
);

CREATE TABLE social_media_user(
	id INTEGER  PRIMARY KEY,
	firstname VARCHAR (255) UNIQUE NOT NULL,
	lastname VARCHAR (255) NOT NULL,
	email VARCHAR(255) NOT NULL,
	social_media_site_id INTEGER,
	constraint fk_social_media_site_user foreign key (social_media_site_id) 
    REFERENCES social_media_site (id)
	
);

create sequence hibernate_sequence START 1;
create sequence social_media_site_id_seq start 1 increment  1;
create sequence user_id_seq start 1 increment 1;