## 示例

### 查询数据

#### 查询 student 表中 sname, ssex, classNo 列的所有数据
```sql
select sname, ssex, classNo
from student;
```
```
sname	ssex	classNo
李华	男	90301
孙蓉	女	90301
赵明	男	90301
李鹏	男	90302
吴天	男	90302
沈月	女	90302
```

#### 查询老师表所有学院名称，使用 distinct 去重
```sql
select distinct depart
from teacher;
```
```
depart
计算机
经济学
机械
```

#### 查询 teacher 表所有记录
```sql
select * from teacher;
```
```
tno	tname	tsex	depart
9011	钱程	男	计算机
9012	周录	男	计算机
9013	王欢	女	经济学
9014	张铁	男	机械
```

#### 查询 score 表中成绩在 80 到 90 之间的数据，使用 between and
```sql
select * from score
where degree between 80 and 90;
```
```
sno	cno	degree
101	3102	89
102	3102	90
102	3103	88
105	4101	85
106	4101	89
```

#### 查询 score 表中成绩为 88 90 92 的数据，使用 in
```sql
select * from score
where degree in(88, 90, 92);
```
```
sno	cno	degree
102	3102	90
102	3103	88
103	3103	92
```

#### 查询 student 表中班级为 90301 或者性别为 女 的数据
```sql
select * from student
where classNo = 90301 or ssex='女';
```
```
sno	sname	ssex	classNo
101	李华	男	90301
102	孙蓉	女	90301
103	赵明	男	90301
106	沈月	女	90302
```

#### 按照班级序号降序查询 student 表的所有记录，使用 order by desc
```sql
select * from student
order by classNo desc;
```
```
sno	sname	ssex	classNo
104	李鹏	男	90302
105	吴天	男	90302
106	沈月	女	90302
101	李华	男	90301
102	孙蓉	女	90301
103	赵明	男	90301
```

#### 按照 sno 升序 degree 降序 查询 score 表的所有记录
```sql
select * from score
order by sno asc, degree desc;
```
```
sno	cno	degree
101	3102	89
101	3103	79
102	3102	90
102	3103	88
103	3102	95
103	3103	92
104	4101	78
105	4101	85
106	4101	89
```

#### 查询 90301 班级学生人数
```sql
select count(1) as num
from student
where classNo = 90301;
```
```
num
3
```

#### 统计学生表男女比例
```sql
select ssex, count(1) as num
from student
group by ssex;
```
```
ssex	num
男	4
女	2
```

#### 查询 score 表最高分的学生序号和课程序号及成绩
```sql
select sno, cno, degree
from score
order by degree desc limit 1;
```
```
sno	cno	degree
103	3102	95
```

#### 查询课程序号 4101 的平均分
```sql
select avg(degree)
from score
where cno = 4101;
```
```
avg(degree)
84.0000
```

#### 查询 score 表最低分大于 80 最高分小于 90 的学生序号
```sql
select sno
from score
group by sno
having max(degree) < 90 and min(degree) > 80;
```
```
sno
105
106
```

#### 查询有课程的老师姓名，所在学院名称及所上课程名称
```sql
select t.tname, t.depart, c.cname
from teacher t
    inner join course c on t.tno = c.tno
```
```
tname	depart	cname
钱程	计算机	计算机导论
周录	计算机	数据库
王欢	经济学	经济法
```

#### 查询学生序号，学生姓名，课程名称，课程成绩
```sql
select s.sno, s.sname, c.cname, sc.degree 
from student s
    inner join score sc on s.sno = sc.sno
    inner join course c on sc.cno = c.cno;
```
```
sno	sname	cname	degree
101	李华	计算机导论	89
101	李华	数据库	79
102	孙蓉	计算机导论	90
102	孙蓉	数据库	88
103	赵明	计算机导论	95
103	赵明	数据库	92
104	李鹏	经济法	78
105	吴天	经济法	85
106	沈月	经济法	89
```

#### 查询 90302 班级学生所选课程的平均分
```sql
select sc.cno, avg( degree ) 
from student s
    inner join score sc on ( s.sno = sc.sno ) 
where classNo = '90301' 
group by sc.cno;
```
```
cno	avg( degree )
3102	91.3333
3103	86.3333
```
