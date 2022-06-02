use movies;

DROP VIEW if exists view_general_info;
create or replace view view_general_info as
select director_name as "director name", title as "movie", actor_name as "actor", actor_age as "age"
FROM movies
JOIN actor ON movies.movie_id = actor.actor_movie_id
JOIN director ON movies.movie_id = director.director_id;