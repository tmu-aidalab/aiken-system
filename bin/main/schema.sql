CREATE TABLE IF NOT EXISTS user (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(60),
  mail_address varchar(100),
  password varchar(100),
  user_icon varchar(10),
  register_date timestamp default CURRENT_TIMESTAMP,
  graduate smallint,
  delete_flag boolean DEFAULT FALSE,
  PRIMARY KEY (id),
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS user_last_login (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  login_count bigint(20) default 0,
  last_login timestamp DEFAULT CURRENT_TIMESTAMP,
  delete_flag boolean DEFAULT FALSE,
  PRIMARY KEY (id),
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS bibpaper (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  category bigint(20),
  title varchar(600),
  authors varchar(200),
  abst varchar(900),
  descript varchar(600),
  hash_tag varchar(200),
  hyperlink varchar(300),
  bibtex_source varchar(2000),
  bibitem_source varchar(1000),
  remark varchar(600),
  delete_flag boolean DEFAULT FALSE,
  PRIMARY KEY (id),
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS category (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  category varchar(60),
  delete_flag boolean DEFAULT FALSE,
  PRIMARY KEY (id),
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS author (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  author varchar(60),
  delete_flag boolean DEFAULT FALSE,
  PRIMARY KEY (id),
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS hash_tag (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  hash_tag varchar(300),
  delete_flag boolean DEFAULT FALSE,
  PRIMARY KEY (id),
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS bibpaper_register_log (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  user_id bigint(20),
  time_stamp timestamp,
  bibpaper_id bigint(20),
  delete_flag boolean DEFAULT FALSE,
  PRIMARY KEY (id),
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS bibpaper_update_log (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  user_id bigint(20),
  time_stamp timestamp,
  bibpaper_id bigint(20),
  delete_flag boolean DEFAULT FALSE,
  PRIMARY KEY (id),
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS view_log (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  user_id bigint(20),
  time_stamp timestamp,
  bibpaper_id bigint(20),
  search_id bigint(20),
  view_from tinyint,
  delete_flag boolean DEFAULT FALSE,
  PRIMARY KEY (id),
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS search_log (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  user_id bigint(20),
  time_stamp timestamp,
  query varchar(300),
  search_result varchar(300),
  delete_flag boolean DEFAULT FALSE,
  PRIMARY KEY (id),
) ENGINE=InnoDB DEFAULT CHARSET=utf8;