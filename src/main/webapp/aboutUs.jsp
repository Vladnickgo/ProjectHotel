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

            <div class="ip"><b>8) Double-double:</b> A Room with two double ( or perhaps queen) beds. And
                can accommodate two to four persons with two twin, double or queen-size beds.

                The room size or area of Double-double / Double Twin rooms are generally between 50 m² to 70
                m².

                Room Type In hotel - Double Double Room | Two Double bed Room In hotel sample <p><img
                        src="https://setupmyhotel.com/images/Room-Type-double-double-Room.jpg?ezimgfmt=rs:300x250/rscb337/ng:webp/ngcb337"
                        alt=""></p></div>

            <div class="ip"><b>9) Suite / Executive Suite: </b> A parlour or living room connected with to
                one or more bedrooms. (A room with one or more bedrooms and a separate living space.)

                The room size or area of Suite rooms are generally between 70 m² to 100 m².

                Room Type In hotel - Suite Room | Room with a separate living room in hotel<p><img
                        src="https://setupmyhotel.com/images/Room-Type-suite-Room.jpg?ezimgfmt=rs:300x250/rscb337/ng:webp/ngcb337"
                        alt=""></p></div>

            <div class="ip">
                <b>10) President Suite | Presidential Suite:</b> The most expensive room provided by a
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


        </div>
        <div class="col-2"></div>
    </div>
</div>
<c:import url="views/footer.jsp"/>
</body>
</html>
