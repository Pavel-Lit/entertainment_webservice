
-- Входные данные для добавления лайка
insert into likes (username, content_id)
values ('admin', 1);

CREATE RULE rule_increment_likes_upd AS ON INSERT
    TO likes
    WHERE (SELECT COUNT(content_id)
           FROM likes
           GROUP BY content_id
           HAVING COUNT(username) > 1
           ORDER BY COUNT(username)) is null
    DO ALSO
    NOTHING ;

-- Входные данные для удаления лайка
delete
from likes
where username = 'qwe'
  and content_id = 2;

Сохранено для будущего!


CREATE RULE rule_decrement_likes_upd AS ON DELETE TO likes
    DO ALSO
    (UPDATE contents
     SET like_count = contents.like_count - 1
     FROM likes as l
     WHERE contents.id = l.content_id;
    );
--
CREATE OR REPLACE FUNCTION update_likes_increment_trigger()
    RETURNS trigger AS
$$
BEGIN
    if ((SELECT COUNT(content_id)
         FROM likes
         GROUP BY content_id
         HAVING COUNT(username) > 1
         ORDER BY COUNT(username)) < 1) then
        update contents set like_count = like_count + 1 from likes l where contents.id = l.content_id;
    END IF;
    RETURN new;
END;
$$
    LANGUAGE plpgsql;
--
CREATE TRIGGER bfr_insert_update_trigger
    BEFORE INSERT
    ON likes
    FOR EACH ROW
EXECUTE PROCEDURE update_likes_increment_trigger();


select username from likes where username = 'admin' and content_id = 1;

SELECT (username = 'admin', content_id = 1)::text AS status FROM likes;