CREATE VIEW passes_select AS
SELECT
	passes.id,
	tickets.card_number,
    types_of_tickets.type_ticket,
    metro_stations.name_station,
    passes.data_passe
FROM
	passes INNER JOIN tickets ON passes.card_number = tickets.card_number
    INNER JOIN types_of_tickets ON tickets.type_ticket = types_of_tickets.id
    INNER JOIN metro_stations ON passes.metro_station = metro_stations.id;

#Популярность различных типов проездных в заданный период времени
SELECT type_ticket, count(type_ticket) 
FROM passes_select 
WHERE data_passe > '2017-06-02 00:00:00' AND data_passe > '2017-06-02 10:00:00'
GROUP BY type_ticket 
ORDER BY count(type_ticket) DESC;

#Анализ загрузки метрополитена с учётом часов-пик
SELECT data_passe, count(data_passe) 
FROM passes_select 
GROUP BY data_passe 
ORDER BY count(data_passe) DESC;

#Анализ популярности станций (рейтинг по загруженности) за заданный период времени
SELECT name_station, count(name_station) 
FROM passes_select 
WHERE data_passe > '2017-06-02 00:00:00' AND data_passe > '2017-06-02 10:00:00'
GROUP BY name_station 
ORDER BY count(name_station) DESC;

#Вывод списка активных билетов с оставшимся количеством поездок
SELECT card_number, balance FROM tickets WHERE NOT balance = 0 ORDER BY balance DESC;