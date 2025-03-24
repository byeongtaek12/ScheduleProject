CREATE TABLE schedule
(
    id               BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '일정 식별자',
    name             VARCHAR(50) COMMENT '이름',
    password         VARCHAR(100) COMMENT '비밀번호',
    todo             VARCHAR(200) COMMENT '할 일',
    creationdate     DATETIME COMMENT '생성일',
    modificationdate DATETIME COMMENT '수정일'
);