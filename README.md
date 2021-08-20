# hotel_chain

In this project, for a database systems course we were required to design a database of hotel chain management systems that enables effective querying in a complex domain problem.

First, an ER Model was designed for our database (Fig. 1).

![Fig. 1](https://github.com/nuradilK/hotel_chain/blob/main/Final%20ER%20Model.png?raw=true)

There are many hotels in the chain. Hotels have seasons. Some of them may share seasons (for example, same season in one country), while others may have unique ones. Many-to-many relationships were used to identify this relationship.
Hotels have employees at different positions, some of them supervise others.

Each hotel has its own set of room types, and there are sets of rooms that belong to a certain room type. The “Users” entity is used to identify all users of the system: guests, desk clerks, managers and administrators. Hence each user has a role. Guests have one user category.
The “Book” stores information about past, current and future bookings of each guest and each room. Each book has a derived attribute called bill, which is a total charge for the booking (including services). Multiple services can be assigned to a booking. Services do not have relationships with hotels, since it is a hotel chain, the assumption is that all hotels have more or less the same services. For security reasons, a guest entity was added that will store all occupants’ information of certain bookings.
The Relational Model was created from this ER Model (Fig. 2).

Guests can register and log in to the system, they get the “user” role and “regular” user category by default. All their information and encoded passwords are stored in Users table. The guests can book rooms of any room types they want. Guests are provided with the list of empty rooms, so they actually book one room at a time. They are able to change their booking or cancel it.
When they arrive at the hotel, desk clerks can also cancel or modify the booking information by changing the date, if possible and the room, if there are any available. The desk clerks are able to change the guest category. They also add information about all guests occupying a room.

The managers can create, assign and cancel seasonal rates for hotels. They are able to change employee’s working hours and salaries. The administrators are able to create new services and new accounts for desk clerks and managers.

![Fig. 2](https://raw.githubusercontent.com/nuradilK/hotel_chain/main/Final%20Relational%20Model.png)

To test our model’s working capacity the provided queries were considered:

**Question-1.**

> ● Query to return all the different room types are available in the hotel for 2 nights, along with be the average cost per night for that room type:
>> ➔ First we find all available rooms for that period. Rooms are selected from join of “room” and “roomtypes” tables, where room must be available for the period given by query.

```
select
      R.id, RT.type
from
  roomtypes RT
  join 
    rooms R
    on RT.roomtypeid = R.roomtypeid
    and RT.hotelid = 1 
where
  R.id not in 
  (
    select 
      B.roomid
    from
      books B
    where
      R.id = B.roomid
      and B.from_date between '2020-12-15' and '2020-12-17' 
      or B.to_date between '2020-12-15' and '2020-12-17' or B.from_date <= '2020-12-15'
      and B.to_date >= '2020-12-17'
);
```

>> ➔ Then, coefficient of season, if any, is retrieved. The start date of the reservation should be within the period of season in our system.

```
select distinct 
  S.coefficient
from
  seasons S
  join
    hotels_have_seasons HHS 
    on HHS.hotelid = 1
where
  '2020-12-15' between s.start_date and s.end_date;
```

>> ➔ Subsequently, coefficient of user_category is retrieved from user_category table

```
select
      
coefficient
from
      user_categories
where
name = 'Gold';
```

>> ➔ After that, the get_bill method will calculate the bill for each room-type considering different prices for different days of the week. The get_avg_bill method will return the average cost per night

```
def​​get_bill​(​room_type, from_date, to_date, season_coef, category_coef​): List<Integer> days_count = new ArrayList<>(7)
Int bill = 0
          while(from_date <= to_date):
                 // java calendar methods are used here (SWE Part)
                 Int week_day = get_week_day(from_date)
                 days_count[week_day] += 1
                 from_date.next_day()
          bill += room_type.get_price_M() * days_count[0]
          bill += room_type.get_price_T() * days_count[1]
          bill += room_type.get_price_R() * days_count[2]
          bill += room_type.get_price_W() * days_count[3]
          bill += room_type.get_price_F() * days_count[4]
          bill += room_type.get_price_St() * days_count[5]
          bill += room_type.get_price_Sn() * days_count[6]
return​ ​bill​;
def​​get_avg_bill​(​user_id, from_date, to_date, season_coef, category_coef​):
          List<room> rooms = get_valid_room_ids(from_date, to_date)
          Double sum = 0, cnt = 0
for room in rooms:
sum += get_bill(room.get_room_type, from_date, to_date) cnt += 1
return​​(sum / cnt) * season_coef * user_category_coef
```

> ● In our system one room, which is of the type that guests want, is reserved for one booking. Insert statements needed to make reservations are following:

```
insert​ ​into
​`hotel_chain`​.​`books`​(​`bill`​, ​`from_date`​, ​`to_date`​, ​`roomid`​, ​`userid`​,
`num_of_guests`​) values
(​'42500'​, ​'2021-12-26'​, ​'2021-12-28'​, ​'2'​, ​'1'​, ​'3'​);
insert​ ​into
​`hotel_chain`​.​`books`​(​`bill`​, ​`from_date`​, ​`to_date`​, ​`roomid`​, ​`userid`​,
`num_of_guests`​) values
(​'42500'​, ​'2021-12-26'​, ​'2021-12-28'​, ​'6'​, ​'1'​, ​'4'​);
insert​ ​into
​`hotel_chain`​.​`books`​(​`bill`​, ​`from_date`​, ​`to_date`​, ​`roomid`​, ​`userid`​,
`num_of_guests`​) values
(​'42500'​, ​'2021-12-26'​, ​'2021-12-28'​, ​'1'​, ​'1'​, ​'2'​);
```

> ● The following pseudocode is used to calculate the entire cost of this reservation:

```
def ​get_total_bill​(​book_ids, season_coef, category_coef​):
       total_bill = 0
       Double season_coef = get_season_coef(book_ids[0].user_id)
       Double user_category_coef = get_user_category_coef(book_ids[0].user_id)
       for book_id in book_ids:
             total_bill += get_bill(book_id.room_type_id, book_id.from, book_id.to)
return​​total_bill * season_coef * category_coef
```

**Question-2.**

> ● Guest Jon Smith is already registered in the system, so he was able to reserve a room of type ‘double’ at hotel B beforehand. In our system the user selects a certain room based on his preferences, so the room is already marked to be booked. However, if the guest won’t like the room on the day of arrival, the desk clerk will offer the available rooms of the same type both unoccupied and clean instead. We can sort the rooms out by checking the values of ‘cleaned’ and ‘guested’ fields.

```
select
R.id
            
from
roomtypes RT
​join rooms R
​on​RT.roomtypeid = R.roomtypeid
​and​RT.hotelid = ​2 where
RT.type = ​'double' ​and​R.guested = ​0 ​and​R.cleaned = ​1 ​and​R.id ​not in
(
​select B.roomid
​from
books B
​where
R.id = B.roomid ​and
(
B.from_date ​between​​'2020-12-26'​​and​​'2020-12-28' ​or​B.to_date ​between​​'2020-12-26'​​and​​'2020-12-28' ​or​(B.from_date <= ​'2020-12-26'​​and​B.to_date >= ​'2020-12-28'​)
        ) );
```

> ● The Jon Smith booking insert statement:

```
insert​ ​into
​`hotel_chain`​.​`books`​(​`bill`​, ​`from_date`​, ​`to_date`​, ​`roomid`​, ​`userid`​,
​`num_of_guests`​) values
(​'42500'​, ​'2020-12-26'​, ​'2020-12-28'​, ​'10'​, ​'2'​, ​'2'​);
The information about the guests to be filled by desk clerk:
insert into
`hotel_chain`​.​`guest`​(​`id`​,​`first_name`​, ​`last_name`​, ​`address`​, ​`id_type`​,
`id_number`​, ​`mobile_phohe_number`​, ​`home_phone_number`​, ​`books_id`​) values
(​'2'​, ​'Jon'​, ​'Smith'​, ​'Nur-Sultan'​, ​'passport'​, ​'111111111'​, ​'+7 777 7777'​, ​'999999'​, ​'4'​);
    
insert into
`hotel_chain`​.​`guest`​(​`id`​,​`first_name`​, ​`last_name`​, ​`address`​, ​`id_type`​,
`id_number`​, ​`mobile_phohe_number`​, ​`home_phone_number`​, ​`books_id`​) values
(​'3'​, ​'Joanna'​, ​'Smith'​, ​'Nur-Sultan'​, ​'passport'​, ​'222222222'​, ​'+7 777 1122'​, ​'999999'​, ​'4'​);
```

**Question-3.**

> ● Firstly, we select required fields which are First name, Last name, address and phone number. All of this information is stored in the “guest” table. Since we cannot directly take the room number and booking dates we made inner joins with “books” and “room” tables. After completing the join we filtered out from date, to date and room number and get access to guest information we needed.

```
select
   guest.first_name, guest.last_name, guest.address, guest.mobile_phohe_number
from
​guest ​inner​ ​join
books
​on​books.id = guest.books_id ​inner​ ​join
rooms
​on​books.roomid = rooms.id ​and​books.from_date <= ​'2020-10-15' ​and​books.to_date >= ​'2020-10-15' ​and​rooms.number = ​311​;
```

**Question-4.**

> ● All hotels from our hotel chain have the same services because every service is a special feature of the hotel chain. So, every hotel must have the same services. Every guest after booking can register services he/she need. For example, after booking room 12 in hotel Astana, the guest can also register such services as SPA, gym, pool to his/her booking (The price of services will be added to the bill). The services are stored as an independent table in the database, since every hotel must have all services available. The service is connected to the booking by “books_has_services” table.
   
> ● As was written before, after booking every guest can register any service he/she want. This is done with the help of “books_has_services” table. The “books_has_services” table will store the booking ID and service ID to connect them. After the quest registered the service to his booking, the price of the service will be automatically added to the bill of booking. Regarding queries, there are two simple insert queries where in the “bookid” and “serviceid” attributes of “books_has_services” table we insert the ID of the booking
to which we want to register the service and service ID respectively.

```
insert​ ​into
hotel_chain.books_has_services (bookid, serviceid)
values
(​1​, ​1​);
insert​ ​into
hotel_chain.books_has_services (bookid, serviceid)
values
(​1​, ​2​);
```

After each insert statement bill is updated:

`book.​setBill​(book.​getBill()​+ service.​getPrice()​); ​// pseudocode`

> ● Whenever service is used, the bill of the booking is updated, so selecting bill of the booking is enough

```
select
bill
from
books
where
books.id = ​1
```

**Question-5.**

> ● “Find all guests who have spent over $1000 (at the hotel chain) since January 1, 2020, and if
their current guest category is ‘Bronze”, change it to ‘Silver’.”:

```
update ​users
   
 set
   user_categoryId =
   (
​select user_categoryid
​from
user_categories U
​where
U.name = ​'silver'
)
where users.id ​in
(
​select T.id
​from (
​select U.id,
​sum​(bill) ​as​total_bill ​from
(
​`hotel_chain`​.​`books`​B ​join
​`hotel_chain`​.​`users`​U
​on​B.userid = U.id )
​join
​`hotel_chain`​.​`user_categories`​UC ​on​U.user_categoryid = UC.user_categoryid
​where
UC.name = ​'bronze' ​and​B.from_date > ​'2020-01-01'
​group​ ​by U.id
)
​as​T ​where
         T.total_bill > 1000
   )
;
```
  
> ● In this query, we first of all find out every ‘bronze’ users’ total sum of bookings by joining tables, books, users, and user_categories. To filter in ‘bronze’ users, we make a where statement to get only ‘bronze’ users, and group by their user id in order to get their total sum of bookings. So, in the end the joint table will have only 2 columns which are user_id, and total_bill. We have to name the joint table in order to be able to work with it afterwards, thus we name it as T. Having the table T, it is easy to find user ids who’s total bill is greater than 1000. So, after filtering out the users whose total bill is less than 1000, we have in the table T users who have to be upgraded to ‘silver’. Thereby, we update users who are only in the table T.
