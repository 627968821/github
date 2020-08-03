## 简介
MySQL 是最流行的关系型数据库管理系统

## 特点

1. 数据以表格的形式出现
2. 每行为各种记录名称
3. 每列为记录名称所对应的数据域
4. 许多的行和列组成一张表
5. 若干的表组成数据库

## 术语

+ 数据库: 数据库是一些关联表的集合。
+ 数据表: 表是数据的矩阵。在一个数据库中的表看起来像一个简单的电子表格。
+ 列: 一列(数据元素) 包含了相同类型的数据, 例如邮政编码的数据。
+ 行：一行（=元组，或记录）是一组相关的数据，例如一条用户订阅的数据。
+ 主键：主键是唯一的。一个数据表中只能包含一个主键。你可以使用主键来查询数据。

## 语法
### 创建数据库
```
CREATE DATABASE <数据库名>;
```

### 删除数据库
```
DROP DATABASE <数据库名>;
```

### 数据类型
#### 数值类型
| 类型 | 大小 (bytes)| 用途 |
| --- | --- | --- |
| **`TINYINT`** | 1 | 小整数值 |
| SMALLINT | 2 | 大整数值 |
| MEDIUMINT | 3 | 大整数值 |
| **`INT`** | 4 | 大整数值 |
| **`BIGINT`** | 8 | 极大整数值 |
| FLOAT | 4 | 单精度浮点数值 |
| DOUBLE | 8 | 双精度浮点数值 |
| DECIMAL | 对DECIMAL(M,D) ，如果M>D，为M+2否则为D+2 | 小数值 |

#### 日期和时间类型
| 类型 | 大小 (bytes)| 格式 | 用途 |
| --- | --- | --- | --- |
| DATE | 3 | `YYYY-MM-DD` | 日期值 |
| TIME | 3 | `HH:MM:SS` | 时间值或持续时间 |
| YEAR | 1 | `YYYY` | 年份值 |
| **`DATETIME`** | 8 | `YYYY-MM-DD HH:MM:SS` | 混合日期和时间值 |
| TIMESTAMP | 4 | `YYYYMMDD HHMMSS` | 混合日期和时间值，时间戳 |

#### 字符串类型
| 类型 | 用途 |
| --- | --- |
| CHAR | 定长字符串 |
| **`VARCHAR`** | 变长字符串 |
| TINYBLOB | 不超过 255 个字符的二进制字符串 |
| TINYTEXT | 短文本字符串 |
| BLOB | 二进制形式的长文本数据 |
| **`TEXT`** | 长文本数据 |
| MEDIUMBLOB | 二进制形式的中等长度文本数据 |
| MEDIUMTEXT | 中等长度文本数据 |
| LONGBLOB | 二进制形式的极大文本数据 |
| LONGTEXT | 极大文本数据 |

### 创建数据表
```
CREATE TABLE table_name (column_name column_type);
```

### 删除数据表
```
DROP TABLE table_name;
```

### 插入数据
```
INSERT INTO table_name ( field1, field2,...fieldN ) VALUES ( value1, value2,...valueN );
```

### 查询数据
```
SELECT column_name, column_name
FROM table_name
[WHERE Clause]
[LIMIT N][ OFFSET M]
```

### 修改数据
```
UPDATE table_name SET field1=new-value1, field2=new-value2
[WHERE Clause]
```

### 删除数据
```
DELETE FROM table_name [WHERE Clause]
```

### WHERE 子句
```
SELECT field1, field2,...fieldN FROM table_name1, table_name2...
[WHERE condition1 [AND [OR]] condition2.....
```

#### 操作符
| 操作符 | 描述 |
| --- | --- |
| = | 等号，检测两个值是否相等 |
| <>, != | 不等于，检测两个值是否相等 |
| > | 大于号，检测左边的值是否大于右边的值 |
| < | 小于号，检测左边的值是否小于右边的值 |
| >= | 大于等于号，检测左边的值是否大于或等于右边的值 |
| <= | 小于等于号，检测左边的值是否小于或等于右边的值 |

### NULL 值处理
+ column_name IS NULL
+ column_name IS NOT NULL

### LIKE 子句
```
SELECT field1, field2,...fieldN 
FROM table_name
WHERE field1 LIKE condition1 [AND [OR]] filed2 = 'somevalue'
```

### 排序
```
SELECT field1, field2,...fieldN FROM table_name1, table_name2...
ORDER BY field1 [ASC [DESC][默认 ASC]], [field2...] [ASC [DESC][默认 ASC]]
```

### 分组
```
SELECT column_name, function(column_name)
FROM table_name
WHERE column_name operator value
GROUP BY column_name;
```

### 表连接的使用
+ INNER JOIN（内连接,或等值连接）：获取两个表中字段匹配关系的记录。
+ LEFT JOIN（左连接）：获取左表所有记录，即使右表没有对应匹配的记录。
+ RIGHT JOIN（右连接）： 与 LEFT JOIN 相反，用于获取右表所有记录，即使左表没有对应匹配的记录。
