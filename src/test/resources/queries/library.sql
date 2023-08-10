select count(id)
from users;-- actual


select count(distinct id)
from users; -- expected


-- us01-2
select *
from users;

-- Us02

select count(*)
from book_borrow
where is_returned = 0;

-- Us03
select name
from book_categories;

select *
from books
where name = 'Jenkins With Grid';

select book_category_id
from books
where name = 'Jenkins With Grid';

select bc.name, count(bc.name) as numberOfSlectedGenre
from books b
         join book_categories bc on bc.id = b.book_category_id
         join book_borrow bb on b.id = bb.book_id
group by bc.name
order by count(bc.name) desc;


select bc.name, count(bc.name) as numberOfSlectedGenre
from books b
         join book_categories bc on bc.id = b.book_category_id
         join book_borrow bb on b.id = bb.book_id
group by bc.name

order by count(bc.name) desc;

select *
from book_borrow;
select *
from books;
select *
from book_categories;

select * from books
where name in ('Head First Java ','The Scrum Field Guide');

