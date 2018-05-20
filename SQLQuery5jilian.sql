alter table bookBorrow
add constraint 管理员编号
foreign key(管理员编号) references 
bookOperator(管理员编号)
on delete cascade