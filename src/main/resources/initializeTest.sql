TRUNCATE TABLE references;

INSERT INTO references ( citationKey, author, title, journal, volume, pages, class, year )
    VALUES ( 'testiArtikkeli1', 'Matti Meikäläinen', 'Tärkeä artikkeli', 'Suurien ajatusten lehti',
     '99', '131-232', 'Article', '2017' ), ( 'testiArtikkeli2', 'Sari Suorittaja', 'Paljon asiaa tikusta', 'Hirsimökkien filosofiaa',
     '456', '45-56', 'Article', '1999');

INSERT INTO references ( citationKey, title, year, publisher, month, author, class )
    VALUES ('testiKirja1', 'Suurteos', '1998', 'Otava', 'Syyskuu', 'Ahdistunut Kirjailija', 'Book');

INSERT INTO references ( citationKey, title,  year, publisher, month, editor , edition, class )
    VALUES ('testiKirja2', 'Kootut kertomukset', '2005', 'Tammi', 'Lokakuu', 'Kari Kustannustoimittaja', '2. painos', 'Book');
