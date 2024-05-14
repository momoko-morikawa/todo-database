DROP TABLE IF EXISTS todos;

CREATE TABLE todos (
id int PRIMARY KEY AUTO_INCREMENT,
title VARCHAR(255),
status VARCHAR(255),
detail VARCHAR(255),
create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO todos (title, status, detail)
VALUES ("買い物","未着手","米を買う");
INSERT INTO todos (title, status, detail)
VALUES ("買い物","未着手","肉を買う");
INSERT INTO todos (title, status, detail)
VALUES ("家事","進行中","トイレ掃除");
INSERT INTO todos (title, status, detail)
VALUES ("家事","未着手","夕飯を作る");
INSERT INTO todos (title, status, detail)
VALUES ("仕事","完了","会議室の予約");
INSERT INTO todos (title, status, detail)
VALUES ("買い物","完了","ケーキを買う");