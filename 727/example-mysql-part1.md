## 示例

### 创建数据库
```sql
create database db_demo;
```

### 创建数据表
| 字段 | 类型 | 描述 |
| --- | --- | --- |
| sno | int | 学生序号 |
| sname | varchar | 学生姓名 |
| ssex | varchar | 性别 |
| classNo | int | 班级序号 |

```sql
-- 学生表
create table student (
    sno int not null AUTO_INCREMENT,
    sname varchar(10) not null,
    ssex varchar(2) not null,
    classNo int,
    PRIMARY KEY (`sno`)
);
```

| 字段 | 类型 | 描述 |
| --- | --- | --- |
| tno | int | 老师序号 |
| tname | varchar | 老师姓名 |
| tsex | varchar | 性别 |
| depart | varchar | 所在学院 |

```sql
-- 老师表
create table teacher (
    tno int not null AUTO_INCREMENT,
    tname varchar(10) not null,
    tsex varchar(2) not null,
    depart varchar(10) not null,
    PRIMARY KEY (`tno`)
);
```

| 字段 | 类型 | 描述 |
| --- | --- | --- |
| cno | int | 课程序号 |
| cname | varchar | 课程名称 |
| tno | int | 老师序号 |

```sql
-- 课程表
create table course (
    cno int not null,
    cname varchar(20) not null,
    tno int not null,
    PRIMARY KEY (`cno`,`tno`)
);
```

| 字段 | 类型 | 描述 |
| --- | --- | --- |
| sno | int | 学生序号 |
| cno | int | 课程序号 |
| degree | int | 成绩 |

```sql
-- 成绩表
create table score (
    sno int not null,
    cno int not null,
    degree int not null,
    PRIMARY KEY (`sno`,`cno`)
);
```

### 插入数据
```sql
insert into student(sno, sname, ssex, classNo) values (101, '李华', '男', 90301);
insert into student(sno, sname, ssex, classNo) values (102, '孙蓉', '女', 90301);
insert into student(sno, sname, ssex, classNo) values (103, '赵明', '男', 90301);
insert into student(sno, sname, ssex, classNo) values (104, '李鹏', '男', 90302);
insert into student(sno, sname, ssex, classNo) values (105, '吴天', '男', 90302);
insert into student(sno, sname, ssex, classNo) values (106, '沈月', '女', 90302);

insert into teacher(tno, tname, tsex, depart) values(9011, '钱程', '男', '计算机');
insert into teacher(tno, tname, tsex, depart) values(9012, '周录', '男', '计算机');
insert into teacher(tno, tname, tsex, depart) values(9013, '王欢', '女', '经济学');
insert into teacher(tno, tname, tsex, depart) values(9014, '张铁', '男', '机械');

insert into course(cno, cname, tno) values(3102, '计算机导论', 9011);
insert into course(cno, cname, tno) values(3103, '数据库', 9012);
insert into course(cno, cname, tno) values(4101, '经济法', 9013);


insert into score(sno, cno, degree) values(101, 3102, 89);
insert into score(sno, cno, degree) values(102, 3102, 90);
insert into score(sno, cno, degree) values(103, 3102, 95);
insert into score(sno, cno, degree) values(101, 3103, 79);
insert into score(sno, cno, degree) values(102, 3103, 88);
insert into score(sno, cno, degree) values(103, 3103, 92);
insert into score(sno, cno, degree) values(104, 4101, 78);
insert into score(sno, cno, degree) values(105, 4101, 85);
insert into score(sno, cno, degree) values(106, 4101, 89);
```

