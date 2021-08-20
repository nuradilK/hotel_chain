# hotel_chain

In this project, for a database systems course we were required to design a database of hotel chain management systems that enables effective querying in a complex domain problem.

First, an ER Model was designed for our database (Fig. 1).

![Fig. 1](https://github.com/nuradilK/hotel_chain/blob/main/Final ER Model.png?raw=true)

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

