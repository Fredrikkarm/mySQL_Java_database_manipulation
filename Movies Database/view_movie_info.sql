use movies;

DROP VIEW if exists view_movie_info;
create or replace view view_movie_info as
select title, genre
from movies
join moviegenres on movies.movie_id = moviegenres.movie_id
join genre on moviegenres.genre_id = genre.genre_id
ORDER BY movies.movie_id;