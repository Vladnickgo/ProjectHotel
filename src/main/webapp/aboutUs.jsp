<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<f:setLocale value="${sessionScope.language}"/>

<f:setBundle var="bunCont" basename="resources"/>

<c:set var="Title" scope="request" value="AboutUs"/>

<c:import url="views/head.jsp"/>

<body>

<c:import url="views/header.jsp"/>

<div class="container mt-5">
    <div class="row text-center">
        <div class="col-4"></div>
        <div class="col-4">
            <h1 class="mb-5">
                <f:message key="about" bundle="${bunCont}"></f:message>
            </h1>
        </div>
        <div class="col-4"></div>
    </div>
</div>
<div class="container mt-1">
    <div class="row text-center">
        <div class="col-2"></div>
        <div class="col-8">
            <div><img width="100%"
                      src="https://setupmyhotel.com/images/Room-Types-in-hotels.png?ezimgfmt=ng%3Awebp%2Fngcb337%2Frs%3Adevice%2Frscb337-1"
                      alt=""></div>
            <p class="h1">Different Room Types in hotels</p>
            <div class="ip">In hotels the rooms are categorised and priced according to the type of bed,
                number of occupants, number of bed, decor, specific furnishings or features and nowadays
                special even the special theme available in the room.
                Later when assigning the guest room before the arrival of the guest the front desk agent
                must be aware of guest room characteristics for each room type available in the hotel. Also
                not to forget any guest specific request or room specific request requested by the guest for
                eg:- room away from the elevator, King bedded room, twin bedroom, non-smoking room etc.
            </div>

            <p class="h3">Following room type definitions are common in the hotel industry:</p>

            <div class="ip"><b>1) Single:</b> A room assigned to one person. May have one or more beds.

                The room size or area of Single Rooms are generally between 37 m² to 45 m².

                Room Type In hotel - Single Room
                <p>
                    <img src="https://setupmyhotel.com/images/Room-Type-Single-Room.jpg?ezimgfmt=rs:300x250/rscb337/ng:webp/ngcb337"
                         alt=""></p>
            </div>

            <div class="ip"><b>2) Double:</b> A room assigned to two people. May have one or more beds.

                The room size or area of Double Rooms are generally between 40 m² to 45 m².
                Room Type In hotel - Double Room
                <p>
                    <img src="https://setupmyhotel.com/images/Room-Type-Double-Room.jpg?ezimgfmt=rs:300x250/rscb337/ng:webp/ngcb337"
                         alt=""></p></div>

            <div class="ip"><b>3) Triple:</b> A room that can accommodate three persons and has been fitted
                with three twin beds, one double bed and one twin bed or two double beds.

                The room size or area of Triple Rooms are generally between 45 m² to 65 m².

                Room Type In hotel - Triple Room
                <p>
                    <img src="https://setupmyhotel.com/images/Room-Type-Triple-Room.jpg?ezimgfmt=rs:300x250/rscb337/ng:webp/ngcb337"
                         alt="">
                </p>
            </div>

            <div class="ip"><b>4) Quad:</b> A room assigned to four people. May have two or more beds.

                The room size or area of Quad Rooms are generally between 70 m² to 85 m².
                Room Type In hotel - Quad Room<p><img
                        src="https://setupmyhotel.com/images/Room-Type-quad-Room.jpg?ezimgfmt=rs:300x250/rscb337/ng:webp/ngcb337"
                        alt=""></p></div>

            <div class="ip"><b>5) Queen:</b> A room with a queen-sized bed. May be occupied by one or more
                people.

                The room size or area of Queen Rooms are generally between 32 m² to 50 m².

                Room Type In hotel - Queen Room | Queen Bedded Room in hotel<p><img
                        src="https://setupmyhotel.com/images/Room-Type-Queen-Room.jpg?ezimgfmt=rs:300x250/rscb337/ng:webp/ngcb337"
                        alt=""></p></div>

            <div class="ip"><b>6) King:</b> A room with a king-sized bed. May be occupied by one or more
                people.

                The room size or area of King Rooms are generally between 32 m² to 50 m².
                Room Type In hotel - King Room | King Room Type in hotel<p><img
                        src="https://setupmyhotel.com/images/Room-Type-King-Room.jpg?ezimgfmt=rs:300x250/rscb337/ng:webp/ngcb337"
                        alt=""></p></div>

            <div class="ip"><b>7) Twin:</b> A room with two twin beds. May be occupied by one or more
                people.

                The room size or area of Twin Rooms are generally between 32 m² to 40 m².

                Room Type In hotel - Twin Room | Twin Bedded room in hotel Sampel<p><img
                        src="https://setupmyhotel.com/images/Room-Type-Twin-Room.jpg?ezimgfmt=rs:300x250/rscb337/ng:webp/ngcb337"
                        alt=""></p></div>

            <div class="ip"><b>8) Hollywood Twin Room: </b> A room that can accommodate two persons with two
                twin beds joined together by a common headboard. Most of the budget hotels tend to provide
                many of these room settings which cater both couples and parties in two.

                The room size or area of Hollywood Twin Rooms are generally between 32 m² to 40 m².
                Room Type In hotel - Hollywood Twin Room | Twin bed with common head board<p><img
                        src="https://setupmyhotel.com/images/Room-Type-Hollywood-Twin-Room.jpg?ezimgfmt=rs:300x250/rscb337/ng:webp/ngcb337"
                        alt=""></p></div>

            <div class="ip"><b>9) Double-double:</b> A Room with two double ( or perhaps queen) beds. And
                can accommodate two to four persons with two twin, double or queen-size beds.

                The room size or area of Double-double / Double Twin rooms are generally between 50 m² to 70
                m².

                Room Type In hotel - Double Double Room | Two Double bed Room In hotel sample <p><img
                        src="https://setupmyhotel.com/images/Room-Type-double-double-Room.jpg?ezimgfmt=rs:300x250/rscb337/ng:webp/ngcb337"
                        alt=""></p></div>

            <div class="ip"><b>10) Studio:</b> A room with a studio bed- a couch which can be converted into
                a bed. May also have an additional bed.

                The room size or area of Studio room types are generally between 25 m² to 40 m².
                Room Type In hotel - Studio Room | Bed a Couch Room in hotel Sample<p><img
                        src="https://setupmyhotel.com/images/Room-Type-studio-Room.jpg?ezimgfmt=rs:300x250/rscb337/ng:webp/ngcb337"
                        alt=""></p></div>

            <div class="ip"><b>11) Suite / Executive Suite: </b> A parlour or living room connected with to
                one or more bedrooms. (A room with one or more bedrooms and a separate living space.)

                The room size or area of Suite rooms are generally between 70 m² to 100 m².

                Room Type In hotel - Suite Room | Room with a separate living room in hotel<p><img
                        src="https://setupmyhotel.com/images/Room-Type-suite-Room.jpg?ezimgfmt=rs:300x250/rscb337/ng:webp/ngcb337"
                        alt=""></p></div>

            <div class="ip"><b>12) Mini Suite or Junior Suite:</b> A single room with a bed and sitting
                area. Sometimes the sleeping area is in a bedroom separate from the parlour or living room.

                The room size or area of Junior Suites are generally between 60 m² to 80 m².
                Room Type In hotel - Junior Suite Room | Kids Suite Room | Mini Suite Room<p><img
                        src="https://setupmyhotel.com/images/Room-Type-Junior-suite-Room.jpg?ezimgfmt=rs:300x250/rscb337/ng:webp/ngcb337"
                        alt=""></p></div>

            <div class="ip">
                <b>13) President Suite | Presidential Suite:</b> The most expensive room provided by a
                hotel. Usually, only one president suite is available in one single hotel property. Similar
                to the normal suites, a president suite always has one or more bedrooms and a living space
                with a strong emphasis on grand in-room decoration, high-quality amenities and supplies, and
                tailor-made services (e.g. personal butler during the stay).

                The room size or area of Presidential Suites are generally between 80 m² to 350 m².

                Room Type In hotel - President Room | Presidential Suite | Luxury Suite | Pent house Suite
                <p>
                    <img src="https://setupmyhotel.com/images/Room-Type-presidential-suite-bedroom.jpg?ezimgfmt=rs:300x250/rscb337/ng:webp/ngcb337"
                         alt=""></p>
            </div>

            <div class="ip"><b>14) Apartments / Room for Extended Stay:</b> This room type can be found in
                service apartments and hotels which target for long stay guests. Open kitchens, cooking
                equipment, dryer, washer etc. are usually available in the room. Housekeeping services are
                only provided once in a week or two times in a week.
                The room size or area of Serviced Apartments are generally between 96 m² to 250 m².
                Room Type In hotel - Serviced Apartment | Apart hotel Room | Apartment with Kitchen in hotel
                <p>
                    <img src="https://setupmyhotel.com/images/Room-Type-serviced-apartment.jpg?ezimgfmt=rs:300x250/rscb337/ng:webp/ngcb337"
                         alt=""></p></div>

            <div class="ip"><b>15) Connecting rooms:</b> Rooms with individual entrance doors from the
                outside and a connecting door between. Guests can move between rooms without going through
                the hallway.
                The room size or area of Connecting rooms are generally between 30 m² to 50 m².
                Room Type In hotel - Connecting Room | Inter Connecting Room | Joining Room <p><img
                        src="https://setupmyhotel.com/images/Room-Type-connecting-rooms.jpg?ezimgfmt=rs:300x250/rscb337/ng:webp/ngcb337"
                        alt=""></p></div>

            <div class="ip"><b>16) Murphy Room:</b> A room that is fitted with a sofa bed or a Murphy bed
                (i.e. a bed that folds out of a wall or closet) which can be transformed from a bedroom in
                the night time to a living room in daytime.
                The room size or area of Murphy Room Types are generally between 20 m² to 40 m².
                Room Type In hotel - Murphy Room | Studio Room | Folding cum bed Room<p><img
                        src="https://setupmyhotel.com/images/Room-Type-murphy-bed-rooms.jpg?ezimgfmt=rs:300x250/rscb337/ng:webp/ngcb337"
                        alt=""></p></div>

        </div>
        <div class="col-2"></div>
    </div>
</div>
<c:import url="views/footer.jsp"/>
</body>
</html>
