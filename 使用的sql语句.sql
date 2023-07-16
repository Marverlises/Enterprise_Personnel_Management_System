DROP DATABASE personnel_management;

CREATE DATABASE personnel_management;

USE personnel_management;




SELECT employee_id, count(*) '数量' FROM attendance GROUP BY employee_id 

SELECT * FROM  attendance, employee WHERE employee.employee_id = 5
-- 用户登录信息表(user_info)
CREATE TABLE user_info (
  user_id INT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(50) NOT NULL UNIQUE,
  user_password VARCHAR(255) NOT NULL,
  login_time DATETIME NOT NULL,
  -- 0为普通员工，1为管理员
  permission_level TINYINT,  
  employee_id INT,
  FOREIGN KEY (employee_id) REFERENCES employee(employee_id)
);
UPDATE user_info
SET username = 'new_username',
    user_password = MD5('new_password'),
    login_time = '2023-06-25 12:34:56',
    permission_level = 1,
    employee_id = 7
WHERE user_id = 2;

DELETE FROM user_info WHERE user_id = "4";
SELECT * FROM user_info


-- 员工档案信息表(employee)

CREATE TABLE employee (
  employee_id INT PRIMARY KEY,
  employee_name VARCHAR(50) NOT NULL,
  gender ENUM('male', 'female') NOT NULL,
  birthdate DATE NOT NULL,
  phone_number VARCHAR(20),
  address VARCHAR(100),
  email VARCHAR(50),
  hire_date DATE,
  position_id INT,
  department_id INT,
	FOREIGN KEY (position_id) REFERENCES job(position_id),
	FOREIGN KEY (department_id) REFERENCES department(department_id)
);
ALTER TABLE employee
ADD COLUMN qualification VARCHAR(50);

SELECT * FROM employee

SELECT * FROM employee
WHERE gender = 'male' OR gender = 'female'
  AND qualification = '本科文凭'
  AND TIMESTAMPDIFF(YEAR, birthdate, CURDATE()) >= 20;


UPDATE employee
SET employee_name = 'New Name',
    gender = 'female',
    birthdate = '1990-01-01',
    phone_number = '18721789123',
    address = 'New Address',
    email = 'new@example.com',
    hire_date = '2022-01-01',
    position_id = 2,
    department_id = 1
WHERE employee_id = 10;


SELECT * from department

UPDATE employee SET
-- 岗位信息表(job)
DROP TABLE job;
CREATE TABLE job (
  position_id INT PRIMARY KEY,
  position_name VARCHAR(50),
  responsibilities VARCHAR(512)
);

-- 考勤状况表(attendance)
DROP TABLE attendance;
CREATE TABLE attendance (
  attendance_id INT AUTO_INCREMENT, 
  employee_id INT,
	-- 打卡时间
  log_time DATETIME,
  -- 打卡类型（上班/下班...）
  log_type TINYINT,
	late_type TINYINT,
	PRIMARY KEY(attendance_id, employee_id),
	FOREIGN KEY (employee_id) REFERENCES employee(employee_id)
);
-- 查找外键约束名
SELECT CONSTRAINT_NAME
FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE
WHERE TABLE_NAME = 'attendance' AND REFERENCED_TABLE_NAME = 'employee' AND COLUMN_NAME = 'employee_id';

-- 修改外键约束级联删除
ALTER TABLE attendance
DROP FOREIGN KEY attendance_ibfk_1,
ADD CONSTRAINT fk_attendance_employee FOREIGN KEY (employee_id) REFERENCES employee(employee_id) ON DELETE CASCADE;


SELECT * FROM attendance
-- 为考勤表设置触发器
-- 使用了 TIME('09:00:00') 和 TIME('18:00:00') 来表示时间部分，以确保正确的比较。同时，添加了 DELIMITER 语句来指定自定义分隔符，以避免语法冲突。
-- 1上班迟到  2下班早退  0正常出勤
DROP TRIGGER IF EXISTS set_late_type_trigger;

DELIMITER //
CREATE TRIGGER set_late_type_trigger BEFORE INSERT ON attendance
FOR EACH ROW
BEGIN
    SET NEW.late_type = CASE
        WHEN NEW.log_type = 0 AND TIME(NEW.log_time) > TIME('09:00:00') THEN 1
        WHEN NEW.log_type = 1 AND TIME(NEW.log_time) < TIME('18:00:00') THEN 2
        ELSE 0
    END;
END //
DELIMITER ;


-- 设置在记录被删除时重新从1开始的功能
-- DELIMITER //
-- CREATE TRIGGER reset_auto_increment
-- AFTER DELETE ON attendance
-- FOR EACH ROW
-- BEGIN
--   SET @max_id = (SELECT MAX(attendance_id) FROM attendance);
--   IF @max_id IS NULL THEN
--     SET @max_id = 0;
--   END IF;
--   SET @sql = CONCAT('ALTER TABLE attendance AUTO_INCREMENT = ', @max_id + 1);
--   PREPARE stmt FROM @sql;
--   EXECUTE stmt;
--   DEALLOCATE PREPARE stmt;
-- END //
-- DELIMITER ;
-- 
-- 显示该表的触发器
SHOW TRIGGERS LIKE 'attendance';



-- 部门信息表(department)
CREATE TABLE department (
  department_id INT PRIMARY KEY,
  department_name VARCHAR(50),
  department_manager VARCHAR(50),
	employee_id INT
);
-- 添加外键约束
ALTER TABLE department
ADD CONSTRAINT fk_department_employee
FOREIGN KEY (employee_id) REFERENCES employee(employee_id);
-- 添加级联删除外键约束
ALTER TABLE department
DROP FOREIGN KEY fk_department_employee,
ADD CONSTRAINT fk_depart_empl FOREIGN KEY (employee_id) REFERENCES employee(employee_id) ON DELETE CASCADE;

SELECT * FROM employee
DELETE FROM employee WHERE employee_id = 3
SELECT * FROM department
SELECT * FROM job
-- 薪资状况表(salary)
SHOW CREATE TABLE salary;

DROP TABLE salary;
CREATE TABLE salary (
  salary_id CHAR(18) PRIMARY KEY,
  employee_id INT,
  salary_amount NUMERIC(18, 2),
  salary_category VARCHAR(50),
  payment_date DATE,
	FOREIGN KEY (employee_id) REFERENCES employee(employee_id)
);
-- 查找外键约束名
SELECT CONSTRAINT_NAME
FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE
WHERE TABLE_NAME = 'salary' AND REFERENCED_TABLE_NAME = 'employee' AND COLUMN_NAME = 'employee_id';

-- 修改外键约束级联删除
ALTER TABLE salary
DROP FOREIGN KEY salary_ibfk_1,
ADD CONSTRAINT fk_salary_employee FOREIGN KEY (employee_id) REFERENCES employee(employee_id) ON DELETE CASCADE;



-- 培训信息表(training)
DROP TABLE training;
CREATE TABLE training (
  training_id char(8) PRIMARY KEY,
  training_name VARCHAR(50),
  training_description VARCHAR(512),
  start_date DATE,
  end_date DATE,
  total_participants INT
);
-- 系统维护表(maintenance)
CREATE TABLE maintenance (
  maintenance_id INT PRIMARY KEY,
  employee_id INT REFERENCES employee(employee_id),
  maintenance_description TEXT,
  maintenance_time DATETIME
);
SELECT * FROM user_info
-- 参加培训关系表(employee_training)
DROP TABLE employee_training
CREATE TABLE employee_training (
  employee_id INT,
  training_id char(8),
  -- 0表示未完成， 1表示完成
  done_training TINYINT,
  PRIMARY KEY (employee_id, training_id),
	FOREIGN KEY (employee_id) REFERENCES employee(employee_id),
	FOREIGN KEY (training_id) REFERENCES training(training_id)
);

-- 查找外键约束名
SELECT CONSTRAINT_NAME
FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE
WHERE TABLE_NAME = 'employee_training' AND REFERENCED_TABLE_NAME = 'training' AND COLUMN_NAME = 'training_id';

-- 修改外键约束级联删除
ALTER TABLE employee_training
DROP FOREIGN KEY employee_training_ibfk_1,
DROP FOREIGN KEY employee_training_ibfk_2,
ADD CONSTRAINT fk_employee_training_employee FOREIGN KEY (employee_id) REFERENCES employee(employee_id) ON DELETE CASCADE,
ADD CONSTRAINT fk_employee_training_training FOREIGN KEY (training_id) REFERENCES training(training_id) ON DELETE CASCADE;

-- 当employee_training插入的数据中training_id字段对应的training表中end_date字段(也就是这个training的结束时间)时间小于现在的时间，就将employee_training中的done_training字段置为1
DROP TRIGGER IF EXISTS trg_employee_training_insert;

-- 插入前的触发器
DELIMITER //
CREATE TRIGGER trg_employee_training_insert_before
BEFORE INSERT ON employee_training
FOR EACH ROW
BEGIN

  DECLARE training_end_date DATE;
	DECLARE current_date_val DATE;
	
  -- 获取对应training_id的结束日期
  SELECT end_date INTO training_end_date
  FROM training
  WHERE training_id = NEW.training_id;
	
	SET current_date_val = CURDATE();
	
		SET NEW.done_training = CASE
        WHEN training_end_date < current_date_val THEN 1
        WHEN training_end_date >= current_date_val THEN 0
        ELSE 0
    END;

END //
DELIMITER ;

-- 插入后的触发器
DELIMITER //
CREATE TRIGGER trg_employee_training_insert_after
AFTER INSERT ON employee_training
FOR EACH ROW
BEGIN
  -- 更新training表中对应training_id的total_participants字段加一
  UPDATE training
  SET total_participants = total_participants + 1
  WHERE training_id = NEW.training_id;
END //
DELIMITER ;

SHOW TRIGGERS LIKE 'employee_training';

-------------------------------------------------------------------
-- 插入数据
-- 用户登录信息表(user_info)
SELECT * FROM user_info
INSERT INTO user_info (username, user_password, login_time, permission_level, employee_id) 
VALUES ('user1', MD5('password1'), '2023-01-01 09:00:00', 0, NULL);
INSERT INTO user_info (username, user_password, login_time, permission_level, employee_id) 
VALUES ('user2', MD5('password2'), '2023-01-02 10:00:00', 0, NULL);
INSERT INTO user_info (username, user_password, login_time, permission_level, employee_id) 
VALUES ('user3', 'password3', '2023-01-03 08:30:00', 0, 20230003);
INSERT INTO user_info (username, user_password, login_time, permission_level, employee_id) 
VALUES ('user4', 'password4', '2023-01-04 09:30:00', 0, 20230004);
INSERT INTO user_info (username, user_password, login_time, permission_level, employee_id) 
VALUES ('user5', 'password5', '2023-01-05 11:00:00', 0, 20230005);
INSERT INTO user_info (username, user_password, login_time, permission_level, employee_id) 
VALUES ('user6', 'password6', '2023-01-06 08:00:00', 0, 20230006);
INSERT INTO user_info (username, user_password, login_time, permission_level, employee_id) 
VALUES ('user7', 'password7', '2023-01-07 10:30:00', 0, 20230007);
INSERT INTO user_info (username, user_password, login_time, permission_level, employee_id) 
VALUES ('user8', 'password8', '2023-01-08 09:30:00', 0, 20230008);
INSERT INTO user_info (username, user_password, login_time, permission_level, employee_id) 
VALUES ('admin1', MD5('adminpassword1'), '2023-01-01 08:00:00', 1, NULL);
INSERT INTO user_info (username, user_password, login_time, permission_level, employee_id) 
VALUES ('admin2', 'adminpassword2', '2023-01-05 09:20:00', 1, 20232000);

SELECT * FROM user_info WHERE permission_level = 0;

-- 员工档案信息表(employee)
INSERT INTO employee (employee_id, employee_name, gender, birthdate, phone_number, address, email, hire_date, position_id, department_id)
VALUES (1, '张三', 'male', '1990-01-01', '12345678129', '北京市', 'zhangsan@example.com', '2020-01-01', NULL, NULL);

INSERT INTO employee (employee_id, employee_name, gender, birthdate, phone_number, address, email, hire_date, position_id, department_id)
VALUES (2, '李四', 'female', '1992-05-15', '98765432121', '上海市', 'lisi@example.com', '2020-02-01', NULL, NULL);

INSERT INTO employee (employee_id, employee_name, gender, birthdate, phone_number, address, email, hire_date, position_id, department_id)
VALUES (3, '王五', 'male', '1988-09-20', '45612372189', '广州市', 'wangwu@example.com', '2020-03-01', NULL, NULL);

INSERT INTO employee (employee_id, employee_name, gender, birthdate, phone_number, address, email, hire_date, position_id, department_id)
VALUES (4, '赵六', 'female', '1995-07-10', '78945621123', '深圳市', 'zhaoliu@example.com', '2020-04-01', NULL, NULL);

INSERT INTO employee (employee_id, employee_name, gender, birthdate, phone_number, address, email, hire_date, position_id, department_id)
VALUES (5, '刘七', 'male', '1991-03-25', '32165421987', '成都市', 'liuqi@example.com', '2020-05-01', NULL, NULL);

INSERT INTO employee (employee_id, employee_name, gender, birthdate, phone_number, address, email, hire_date, position_id, department_id)
VALUES (6, '陈八', 'female', '1989-11-11', '65432211987', '重庆市', 'chenba@example.com', '2020-06-01', NULL, NULL);

INSERT INTO employee (employee_id, employee_name, gender, birthdate, phone_number, address, email, hire_date, position_id, department_id)
VALUES (7, '许九', 'male', '1993-08-08', '98712321654', '武汉市', 'xujiu@example.com', '2020-07-01', NULL, NULL);

INSERT INTO employee (employee_id, employee_name, gender, birthdate, phone_number, address, email, hire_date, position_id, department_id)
VALUES (8, '郭十', 'female', '1994-02-18', '32192187654', '南京市', 'guoshi@example.com', '2020-08-01', NULL, NULL);

INSERT INTO employee (employee_id, employee_name, gender, birthdate, phone_number, address, email, hire_date, position_id, department_id)
VALUES (9, '周十一', 'male', '1990-12-05', '78912213456', '杭州市', 'zhouyiyi@example.com', '2020-09-01', NULL, NULL);

INSERT INTO employee (employee_id, employee_name, gender, birthdate, phone_number, address, email, hire_date, position_id, department_id)
VALUES (10, '钱十二', 'female', '1987-04-30', '98721789123', '天津市', 'qianshier@example.com', '2020-10-01', NULL, NULL);
SELECT * FROM employee;

-- 岗位信息表(job)
INSERT INTO job (position_id, position_name, responsibilities)
VALUES (1, '经理', '负责部门的管理和协调工作');

INSERT INTO job (position_id, position_name, responsibilities)
VALUES (2, '工程师', '进行系统开发和编程工作');

INSERT INTO job (position_id, position_name, responsibilities)
VALUES (3, '销售代表', '负责产品销售和客户关系维护');

INSERT INTO job (position_id, position_name, responsibilities)
VALUES (4, '人力资源专员', '负责招聘、培训和员工福利管理');

INSERT INTO job (position_id, position_name, responsibilities)
VALUES (5, '财务主管', '负责财务报表和资金管理');

INSERT INTO job (position_id, position_name, responsibilities)
VALUES (6, '市场营销经理', '制定市场营销策略和推广计划');

INSERT INTO job (position_id, position_name, responsibilities)
VALUES (7, '生产主管', '负责生产计划和生产线管理');

INSERT INTO job (position_id, position_name, responsibilities)
VALUES (8, '客户服务代表', '处理客户投诉和解决问题');

INSERT INTO job (position_id, position_name, responsibilities)
VALUES (9, '研发工程师', '进行产品研发和创新设计');

INSERT INTO job (position_id, position_name, responsibilities)
VALUES (10, '行政助理', '提供行政支持和文件管理');

-- UPDATE job SET position_id = 1, position_name = "大经理", responsibilities = "负责部门的管理和协调工作" WHERE position_id = 1;
DELETE FROM job WHERE position_id = 7;
SELECT * FROM job

-- 部门信息表插入数据(department)
-- UPDATE department SET department_id = 1, department_name ="销售部", department_manager = "王五", employee_id = NULL WHERE department_id = 1;


SELECT * FROM department;
UPDATE department SET employee_id = 123 WHERE department_id = 1;
DELETE FROM department WHERE department_id = 3;
INSERT INTO department (department_id, department_name, department_manager, employee_id)
VALUES (1, '销售部', '张三', NULL);

INSERT INTO department (department_id, department_name, department_manager, employee_id)
VALUES (2, '市场部', '李四', NULL);

INSERT INTO department (department_id, department_name, department_manager, employee_id)
VALUES (3, '财务部', '王五', NULL);

INSERT INTO department (department_id, department_name, department_manager, employee_id)
VALUES (4, '人力资源部', '赵六', 6);

INSERT INTO department (department_id, department_name, department_manager, employee_id)
VALUES (5, '研发部', '刘七', 15);

INSERT INTO department (department_id, department_name, department_manager, employees_num)
VALUES (6, '信息技术部', '陈八', 20);

INSERT INTO department (department_id, department_name, department_manager, employee_id)
VALUES (7, '运营部', '许九', 18);

INSERT INTO department (department_id, department_name, department_manager, employee_id)
VALUES (8, '客户服务部', '郭十', 9);

INSERT INTO department (department_id, department_name, department_manager, employee_id)
VALUES (9, '质量保证部', '周十一', 7);

INSERT INTO department (department_id, department_name, department_manager, employee_id)
VALUES (10, '供应链部', '钱十二', 11);

SELECT * FROM department




-- 向考勤信息表中插入数据
SELECT * FROM attendance;

INSERT INTO attendance (employee_id, log_time, log_type, late_type) VALUES (1, '2023-06-10 09:00:00', 0, NULL);
INSERT INTO attendance (employee_id, log_time, log_type, late_type) VALUES (2, '2023-06-11 08:30:00', 0, NULL);
INSERT INTO attendance (employee_id, log_time, log_type, late_type) VALUES (3, '2023-06-12 10:15:00', 0, NULL);
INSERT INTO attendance (employee_id, log_time, log_type, late_type) VALUES (4, '2023-06-13 09:45:00', 0, NULL);
INSERT INTO attendance (employee_id, log_time, log_type, late_type) VALUES (5, '2023-06-14 08:00:00', 0, NULL);
INSERT INTO attendance (employee_id, log_time, log_type, late_type) VALUES (6, '2023-06-15 10:30:00', 0, NULL);
INSERT INTO attendance (employee_id, log_time, log_type, late_type) VALUES (7, '2023-06-16 09:15:00', 0, NULL);
INSERT INTO attendance (employee_id, log_time, log_type, late_type) VALUES (8, '2023-06-17 08:45:00', 0, NULL);
INSERT INTO attendance (employee_id, log_time, log_type, late_type) VALUES (9, '2023-06-18 09:30:00', 0, NULL);
INSERT INTO attendance (employee_id, log_time, log_type, late_type) VALUES (10, '2023-06-19 08:00:00', 0, NULL);
INSERT INTO attendance (employee_id, log_time, log_type, late_type) VALUES (1, '2023-06-10 18:00:00', 1, NULL);
INSERT INTO attendance (employee_id, log_time, log_type, late_type) VALUES (2, '2023-06-11 17:30:00', 1, NULL);
INSERT INTO attendance (employee_id, log_time, log_type, late_type) VALUES (3, '2023-06-12 19:15:00', 1, NULL);
INSERT INTO attendance (employee_id, log_time, log_type, late_type) VALUES (4, '2023-06-13 18:45:00', 1, NULL);
INSERT INTO attendance (employee_id, log_time, log_type, late_type) VALUES (5, '2023-06-14 17:00:00', 1, NULL);
INSERT INTO attendance (employee_id, log_time, log_type, late_type) VALUES (6, '2023-06-15 19:30:00', 1, NULL);
INSERT INTO attendance (employee_id, log_time, log_type, late_type) VALUES (7, '2023-06-16 18:15:00', 1, NULL);
INSERT INTO attendance (employee_id, log_time, log_type, late_type) VALUES (8, '2023-06-17 17:45:00', 1, NULL);
INSERT INTO attendance (employee_id, log_time, log_type, late_type) VALUES (9, '2023-06-18 18:30:00', 1, NULL);
INSERT INTO attendance (employee_id, log_time, log_type, late_type) VALUES (10, '2023-06-19 17:00:00', 1, NULL);
INSERT INTO attendance (employee_id, log_time, log_type, late_type)
VALUES
    (1, '2023-06-21 09:30:00', 0, NULL),
    (2, '2023-06-22 10:15:00', 0, NULL),
    (3, '2023-06-21 11:00:00', 0, NULL),
    (4, '2023-06-22 11:45:00', 0, NULL),
    (5, '2023-06-21 12:30:00', 0, NULL),
    (6, '2023-06-22 14:00:00', 1, NULL),
    (7, '2023-06-23 14:45:00', 1, NULL),
    (8, '2023-06-23 15:30:00', 1, NULL),
    (9, '2023-06-23 16:15:00', 1, NULL),
    (10, '2023-06-23 17:00:00', 1, NULL),
    (1, '2023-06-23 09:15:00', 0, NULL),
    (2, '2023-06-21 10:00:00', 0, NULL),
    (3, '2023-06-22 10:45:00', 0, NULL),
    (4, '2023-06-21 11:30:00', 0, NULL),
    (5, '2023-06-22 12:15:00', 0, NULL),
    (6, '2023-06-23 14:30:00', 1, NULL),
    (7, '2023-06-23 15:15:00', 1, NULL),
    (8, '2023-06-21 16:00:00', 1, NULL),
    (9, '2023-06-22 16:45:00', 1, NULL),
    (10, '2023-06-22 17:30:00', 1, NULL);


SELECT * FROM attendance;
-- 取出最新的日期
SELECT MAX(log_time) AS latest_date FROM attendance;
SELECT DATE_SUB("2023-06-19 08:00:00", INTERVAL 7 DAY) AS one_week_before ;

-- 删除一周之前的所有数据
DELETE FROM attendance 
WHERE attendance_id IN (
  SELECT a.attendance_id 
  FROM (
    SELECT attendance_id 
    FROM attendance 
    WHERE log_time < (
      SELECT DATE_SUB((SELECT MAX(log_time) FROM attendance), 			INTERVAL 7 DAY) AS one_week_before
    )
  ) a
);

-- 取出距离今天10天以内的数据
SELECT *
FROM attendance
WHERE log_time >= CURDATE() - INTERVAL 10 DAY;

-- 按照天分组
SELECT DATE(log_time), COUNT(*) AS count
FROM attendance
WHERE log_time >= CURDATE() - INTERVAL 10 DAY
GROUP BY DATE(log_time);


-- 进行薪资的插入
INSERT INTO salary (salary_id, employee_id, salary_amount, salary_category, payment_date)
VALUES ('2143c88b-50b8-4772', 1, 5000.00, 'Monthly', '2023-06-01');

INSERT INTO salary (salary_id, employee_id, salary_amount, salary_category, payment_date)
VALUES ('2143c88b-50b8-4773', 2, 4500.00, 'Monthly', '2023-06-02');

INSERT INTO salary (salary_id, employee_id, salary_amount, salary_category, payment_date)
VALUES ('2143c88b-50b8-4774', 3, 4800.00, 'Monthly', '2023-06-03');

INSERT INTO salary (salary_id, employee_id, salary_amount, salary_category, payment_date)
VALUES ('2143c88b-50b8-4775', 4, 5200.00, 'Monthly', '2023-06-04');

INSERT INTO salary (salary_id, employee_id, salary_amount, salary_category, payment_date)
VALUES ('2143c88b-50b8-4776', 5, 5100.00, 'Monthly', '2023-06-05');

INSERT INTO salary (salary_id, employee_id, salary_amount, salary_category, payment_date)
VALUES ('2143c88b-50b8-4777', 6, 4800.00, 'Monthly', '2023-06-06');

INSERT INTO salary (salary_id, employee_id, salary_amount, salary_category, payment_date)
VALUES ('2143c88b-50b8-4778', 7, 4700.00, 'Monthly', '2023-06-07');

INSERT INTO salary (salary_id, employee_id, salary_amount, salary_category, payment_date)
VALUES ('2143c88b-50b8-4770', 8, 4900.00, 'Monthly', '2023-06-08');

INSERT INTO salary (salary_id, employee_id, salary_amount, salary_category, payment_date)
VALUES ('2143c88b-50b8-4771', 9, 5200.00, 'Monthly', '2023-06-09');

INSERT INTO salary (salary_id, employee_id, salary_amount, salary_category, payment_date)
VALUES ('2143c88b-50b8-4779', 10, 5000.00, 'Monthly', '2023-06-10');


SELECT * FROM salary
DELETE FROM salary WHERE salary_id = 20234 and employee_id = 4
UPDATE salary SET employee_id = 5, salary_amount = 1000, salary_category = "测试", payment_date = "2023-06-01" WHERE salary_id = "2143c88b-50b8-4775"



-------------------------------------------------------------------
-- 向培训信息表插入数据
-- 插入第一条数据
INSERT INTO training (training_id, training_name, training_description, start_date, end_date, total_participants)
VALUES ('asdqwq12', 'Training 1', 'Description for Training 1', '2023-06-01', '2023-06-10', 20);

-- 插入第二条数据
INSERT INTO training (training_id, training_name, training_description, start_date, end_date, total_participants)
VALUES ('asdqwq13', 'Training 2', 'Description for Training 2', '2023-06-05', '2023-06-15', 15);

-- 插入第三条数据
INSERT INTO training (training_id, training_name, training_description, start_date, end_date, total_participants)
VALUES ('asdqwq15', 'Training 3', 'Description for Training 3', '2023-06-10', '2023-06-20', 25);

-- 插入第四条数据
INSERT INTO training (training_id, training_name, training_description, start_date, end_date, total_participants)
VALUES ('asdqwq14', 'Training 4', 'Description for Training 4', '2023-06-15', '2023-06-25', 18);

-- 插入第五条数据
INSERT INTO training (training_id, training_name, training_description, start_date, end_date, total_participants)
VALUES ('avdqwe12', 'Training 5', 'Description for Training 5', '2023-06-20', '2023-06-30', 30);

-- 插入第六条数据
INSERT INTO training (training_id, training_name, training_description, start_date, end_date, total_participants)
VALUES ("agqqwe11", "Training 6", "Description for Training 6", 
"2023-06-25", "2023-07-05", 22);

UPDATE training SET training_name = "测试2", training_description="测试一下", start_date="2023-06-21", end_date="2023-06-22", total_participants = 0 WHERE training_id = "avdqwe12";

DELETE FROM training WHERE training_id = "8e076e26";
SELECT * FROM training;


SELECT * FROM employee_view WHERE employee_id = 3;
-------------------------------------------------------------------
-- Emptraining表的处理
SELECT * FROM employee;
SELECT * FROM training;
SELECT * FROM employee_training;
INSERT INTO employee_training (employee_id, training_id, done_training)
VALUES (1, 'avdqwe12', NULL);

INSERT INTO employee_training (employee_id, training_id, done_training)
VALUES (4, 'avdqwe12', NULL);

INSERT INTO employee_training (employee_id, training_id, done_training)
VALUES (3, 'avdqwe12', 1);

DELETE FROM employee_training WHERE employee_id = 4 AND training_id = 'c260bdaa'

SELECT * FROM employee WHERE employee_id IN (SELECT employee_id 
FROM employee_training WHERE training_id = 'avdqwe12')

-------------------------------------------------------------------
-- 创建普通员工视图
drop view employee_view;
CREATE VIEW employee_view AS
SELECT t1.employee_id, t1.employee_name, t1.gender, t1.birthdate, t1.phone_number, t1.address, t1.email, t1.hire_date, t1.position_id, t1.department_id, t2.salary_amount
FROM employee t1
JOIN salary t2 ON t1.employee_id = t2.employee_id and t2.salary_category = "Monthly";

SELECT * FROM employee_view WHERE employee_id = 2;
SELECT * FROM user_info
-------------------------------------------------------------------
-- 系统维护
SELECT user, host FROM mysql.user;

SELECT CURRENT_USER();

SELECT * FROM salary



-------------------------------------------------------------------
-- 进行用户权限管理
CREATE USER 'client'@'localhost' IDENTIFIED BY '123456';

GRANT USAGE ON `personnel_management`.* TO 'client'@'localhost';

GRANT SELECT ON `personnel_management`.`employee` TO 'client'@'localhost';
GRANT SELECT ON `personnel_management`.`salary` TO 'client'@'localhost';
GRANT SELECT ON `personnel_management`.`user_info` TO 'client'@'localhost';
GRANT SELECT ON `personnel_management`.`employee_view` TO 'client'@'localhost';
GRANT INSERT ON `personnel_management`.`attendance` TO 'client'@'localhost';

FLUSH PRIVILEGES;

SELECT user, host FROM mysql.user;

SHOW GRANTS FOR 'client'@'localhost';
