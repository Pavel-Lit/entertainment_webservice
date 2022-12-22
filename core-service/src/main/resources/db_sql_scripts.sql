SET search_path TO myschema, public;

drop table if exists likes;
drop table if exists contents;
drop table if exists categories;


create table categories
(
    id    bigserial
        constraint categories_pk
            primary key,
    title varchar(255)
);



create table contents
(
    id          bigserial
        constraint contents_pk primary key,
    content     varchar(4096),
    moderate    boolean,
    like_count  INTEGER   DEFAULT 0 CHECK ( like_count >= 0 ),
    category_id bigserial
        constraint contents_categories_id_fk
            references categories
            on update cascade on delete cascade,
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

create table likes
(
    id         bigserial
        constraint likes_pk
            primary key,
    content_id bigserial
        constraint likes_contents_id_fk
            references contents,
    username   varchar(255)
);

insert into categories (title)
values ('Мем');
insert into categories (title)
values ('Анекдот');

insert into contents (category_id, content, moderate)
values (2, 'Бабушка, а ты пришла сама? - Сама внученька, сама! - А папа сказал, что тебя черти принесли!', true);
insert into contents (category_id, content, moderate)
values (2, 'Папа научил маленького Вовочку считать, теперь папе приходится делить пельмени поровну.', true);
insert into contents (category_id, content, moderate)
values (2, 'Чем дольше звонят в дверь, тем сильнее меня нет дома', true);
insert into contents (category_id, content, moderate)
values (2, 'В переполненный автобус протискивается бабка с объемистой сумкой и кричит:
— Мужики, осторожно! Не поколите яйца!
— Что, бабка, яйца везешь?
— Нет, гвозди!', true);
insert into contents (category_id, content, moderate)
values (2,
        '- Я хочу цветы, но не хочу говорить тебе, что хочу цветы. Потому что ты просто подаришь мне цветы, потому что я их хочу. А я хочу, чтобы ты подарил мне цветы, потому что тебе хочется подарить мне цветы, понимаешь?',
        true);
insert into contents (category_id, content, moderate)
values (2, '- Скажите, как узнать, что перед вами настоящий морж?
- Легко! Настоящие моржи купаются голыми.
- То есть, если кто-то купается в проруби в одежде, то это точно не морж?
- Нет. Это рыбак.', true);
insert into contents (category_id, content, moderate)
values (2, 'Сделайте разгрузочный день - не грузите себя!', true);
insert into contents (category_id, content, moderate)
values (2, 'Сидит художник-абстракционист, грустит, и к нему приходит друг.
- Что случилось? Почему ты такой грустный?
- Да вот, пришел клиент и попросил, чтобы я на картине поменял ему цвет глаз.
- Ну так в чем проблема? Поменяй!
- Да я забыл, где нарисовал глаза!', true);
insert into contents (category_id, content, moderate)
values (2,
        'Как читать сайты в 2022-м: открываешь сайт, закрываешь уведомление "присылать вам новости?", закрываешь баннер "мы используем куки", закрываешь "мы обновили нашу политику конфиденциальности", пару всплывающих реклам тоже – бах, а статья которую ты хотел прочитать доступна только после платной подписки.',
        true);
insert into contents (category_id, content, moderate)
values (2, 'Стоят рядом три магазина:
Хозяин левого повесил вывеску "у нас самые низкие цены".
Хозяин правого повесил вывеску "у нас самые качественные товары".
А хозяин среднего, подумав, повесил вывеску "главный вход".', true);
insert into contents (category_id, content, moderate)
values (2, 'В картинной галерее.
- Посмотрите, какая интересная работа. Какая чёткость и лаконичность линий. Ничего лишнего, но, вместе с тем, каждая деталь тщательно прорисована, чтобы дать возможность рассмотреть её красоту. Чувствуется определенный посыл к зрителям, словно художник зовёт публику за собой в вихрь неистовых переживаний, владевших им в миг творчества. Глядя на картину, хочется отринуть всё земное и суетное и следовать за мастером.
- Извините, что вмешиваюсь, но это - план эвакуации. Вход на экспозицию дальше по коридору.', true);
insert into contents (category_id, content, moderate)
values (2, 'Некоторые ошибки слишком классные, чтобы совершать их всего лишь раз.', true);
insert into contents (category_id, content, moderate)
values (2, 'Скажите, после поклейки обоев остаются куски.
Обычно многие сохраняют их на случай если...
Кто-нибудь хоть раз доклеивал эти куски!?', true);
insert into contents (category_id, content, moderate)
values (2, '- Прости, я не занимаюсь сексом без чувств.
- А как же жалость?', true);
insert into contents (category_id, content, moderate)
values (2, 'Прочитала статус 14-тилетней девочки:
«Сижу, пью кофе, курю и думаю о нем…»
… пипец!!! Мне 30… Я валяюсь на диване, ем апельсин и смотрю Алешу Поповича!!!)))', true);
insert into contents (category_id, content, moderate)
values (2, 'Мужика посадили в тюрьму на 10 лет.
Ну, миллиционер говорит ему:
- Говори всем, что у тебя статья 152-я, часть 2-я.
Приводят мужика в камеру. Зэки подходят, все на понтах:
- Ну чо, какая статья?
- 152-я.
Бац - зэки ему лучшую койку выделили, лучшую еду принесли.
- А часть какая?
- 2-я.
Бац - все по койкам забились, бояться.
Так и прошло 10 лет.
Вышел мужик, подходит к тому менту:
- Слыш, а че за статья такая, 152-я?
- А, да это изнасилование крупного рогатого скота.
- А часть вторая?
- Со смертельным исходом.', true);


insert into contents (category_id, content, moderate)
values (2, 'Из новогоднего настроения у меня щас только глаз дергается в такт гирляндам', false);
insert into contents (category_id, content, moderate)
values (2, 'Табличка в кабинете врача:
ЦВЕТЫ И КОНФЕТЫ НЕ ПЬЮ', false);
insert into contents (category_id, content, moderate)
values (2, 'Разговор по скайпу около 3 часов ночи.
- Привет! Чё делаешь?
- За колбасой пошла.
- Не поздновато ли?
- А у меня холодильник круглосуточно работает!', false);
insert into contents (category_id, content, moderate)
values (2, 'Вчера был у друга на ДР, запускали воздушного змея.
Аж до драки скандалили, всем хотелось за нитку подержаться и побегать с ним, а ведь в этой компании я был самый младший, мне 34 годика...',
        false);
insert into contents (category_id, content, moderate)
values (1, 'Могу в постели,
на столе,
В машине,
в поезде- в купе.
Под пледом в самолёте было,
Но укачало, аж мутило.
На стуле,
в кресле,
гамаке-
На даче было по весне.
И стоя запросто могу,
Упёршись в стеночку, в углу.
Во время отдыха- на пляже
И в душевой кабинке даже...
Жизнь научила с малых лет:
Повтора в нашей жизни нет.
Неважно "где"- везде люблю,
Возможность есть - Я СРАЗУ СПЛЮ!...', false);
insert into contents (category_id, content, moderate)
values (2, '- Блин, руку порезал, больно...
- Расслабься, до свадьбы заживет...
- До свадьбы ДВА ЧАСА!!!', false);
insert into contents (category_id, content, moderate)
values (2, '- А давай без презерватива?!
- Просто графин воды в окно выльем?', false);
insert into contents (category_id, content, moderate)
values (2, '- М-м-м... Что это вы такое приготовили? Прямо тает во рту!
- Это лед из морозилки', false);
insert into contents (category_id, content, moderate)
values (2, '- Как прошел твой день?
- Такое ощущение, что прошел по мне...', false);
insert into contents (category_id, content, moderate)
values (2, 'Когда синоптики говорят "небольшой снег", они описывают размеры снежинок, а не их количество.', false);
insert into contents (category_id, content, moderate)
values (2, 'Хочешь денег в 2 раза больше? Положи их перед зеркалом.', false);
insert into contents (category_id, content, moderate)
values (2,
        'У нас крепкий брак уже 20 лет. А всё потому, что каждые выходные мы выезжаем с женой на природу - я в субботу, а она в воскресенье.',
        false);
insert into contents (category_id, content, moderate)
values (2, '- Дорогой, как получилось, что ты в меня влюбился?
- Вот видишь, теперь это и тебя удивляет...', false);
insert into contents (category_id, content, moderate)
values (1, '- Как ты стал космонавтом?
- Ткнул куда-то не туда на Госуслугах', false);
insert into contents (category_id, content, moderate)
values (2, 'Сижу за рюмочкой кофе, за соседним столиком мужики играют в Преферанс. Один, убеждая другого в правильности своей стратегии:
- Вот ты, Федя, в Деда Мороза веришь?
Тот, подозрительно:
- Ну, нет...
- Потому он тебе подарки и не приносит.', false);
insert into contents (category_id, content, moderate)
values (2,
        'Стоит на остановке девушка с длинной тонкой белой сигаретой во рту и по карманам шарит, я как истинный джентльмен, поднёс ей зажигалку. Девушка круглыми глазами секунды три смотрела на идиота, который поджёг ей палочку от чупа-чупса.',
        false);