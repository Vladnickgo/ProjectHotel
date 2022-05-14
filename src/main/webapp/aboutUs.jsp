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
        <div class="col-3"></div>
        <div class="col-6 t">
            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ad architecto atque blanditiis commodi consequatur
            dolores eveniet ex exercitationem explicabo harum hic ipsam ipsum itaque maiores natus nesciunt nisi non
            nostrum obcaecati, odit placeat provident quia quibusdam quo reiciendis repellat reprehenderit suscipit
            tempore veritatis voluptas. Accusantium beatae consectetur corporis delectus error, explicabo ipsam itaque
            maiores perspiciatis. Doloremque enim pariatur quaerat recusandae sit. Accusantium, deserunt distinctio
            ducimus, facere facilis fuga hic ipsa laborum nesciunt obcaecati odit officiis perspiciatis possimus
            quibusdam, repudiandae sed totam veritatis. Consectetur corporis deserunt distinctio esse ex hic magnam
            magni provident quo repellat. A adipisci at commodi corporis doloribus ea est excepturi, facilis id maxime
            mollitia ratione soluta temporibus ullam voluptates. Doloribus ducimus enim fugiat iusto, nobis praesentium
            sunt. Eos illum porro praesentium similique voluptas. Asperiores assumenda, cupiditate debitis doloremque
            doloribus minima nam nulla porro quasi quis quisquam quo quos recusandae reiciendis reprehenderit ut
            voluptas voluptate. Accusamus assumenda blanditiis hic maiores neque nihil odit officiis sapiente velit?
            Accusamus accusantium aperiam asperiores corporis culpa dolorum earum esse ex fugiat illo iste iure iusto
            laboriosam magnam nesciunt nihil, nisi officiis recusandae reiciendis saepe sit, suscipit tempore unde
            veniam voluptate? Blanditiis doloribus harum rem sequi tenetur. Dicta explicabo in iure molestias saepe
            tempore. Dicta dignissimos earum, expedita illum, ipsum iste itaque laudantium libero magnam modi nesciunt
            nisi nulla officia officiis quam quidem, reprehenderit sapiente sint sunt ullam. Accusantium autem, esse
            omnis quia reiciendis similique tenetur. Ea eligendi incidunt laboriosam quisquam vero! Accusamus ad aliquam
            aliquid aspernatur deleniti harum modi nobis numquam quod repudiandae. Aliquam amet aperiam consequuntur
            corporis debitis ea, et fuga harum hic illum laudantium magni modi neque nihil non officia quidem, quis
            quisquam quo quod repellat reprehenderit repudiandae rerum sequi sint voluptates voluptatibus. Aperiam
            commodi impedit nesciunt nobis omnis rem repellat sed veritatis vero. Eius eum inventore odio qui soluta?
            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ad architecto atque blanditiis commodi consequatur
            dolores eveniet ex exercitationem explicabo harum hic ipsam ipsum itaque maiores natus nesciunt nisi non
            nostrum obcaecati, odit placeat provident quia quibusdam quo reiciendis repellat reprehenderit suscipit
            tempore veritatis voluptas. Accusantium beatae consectetur corporis delectus error, explicabo ipsam itaque
            maiores perspiciatis. Doloremque enim pariatur quaerat recusandae sit. Accusantium, deserunt distinctio
            ducimus, facere facilis fuga hic ipsa laborum nesciunt obcaecati odit officiis perspiciatis possimus
            quibusdam, repudiandae sed totam veritatis. Consectetur corporis deserunt distinctio esse ex hic magnam
            magni provident quo repellat. A adipisci at commodi corporis doloribus ea est excepturi, facilis id maxime
            mollitia ratione soluta temporibus ullam voluptates. Doloribus ducimus enim fugiat iusto, nobis praesentium
            sunt. Eos illum porro praesentium similique voluptas. Asperiores assumenda, cupiditate debitis doloremque
            doloribus minima nam nulla porro quasi quis quisquam quo quos recusandae reiciendis reprehenderit ut
            voluptas voluptate. Accusamus assumenda blanditiis hic maiores neque nihil odit officiis sapiente velit?
            Accusamus accusantium aperiam asperiores corporis culpa dolorum earum esse ex fugiat illo iste iure iusto
            laboriosam magnam nesciunt nihil, nisi officiis recusandae reiciendis saepe sit, suscipit tempore unde
            veniam voluptate? Blanditiis doloribus harum rem sequi tenetur. Dicta explicabo in iure molestias saepe
            tempore. Dicta dignissimos earum, expedita illum, ipsum iste itaque laudantium libero magnam modi nesciunt
            nisi nulla officia officiis quam quidem, reprehenderit sapiente sint sunt ullam. Accusantium autem, esse
            omnis quia reiciendis similique tenetur. Ea eligendi incidunt laboriosam quisquam vero! Accusamus ad aliquam
            aliquid aspernatur deleniti harum modi nobis numquam quod repudiandae. Aliquam amet aperiam consequuntur
            corporis debitis ea, et fuga harum hic illum laudantium magni modi neque nihil non officia quidem, quis
            quisquam quo quod repellat reprehenderit repudiandae rerum sequi sint voluptates voluptatibus. Aperiam
            commodi impedit nesciunt nobis omnis rem repellat sed veritatis vero. Eius eum inventore odio qui soluta?
        </div>
        <div class="col-3">

        </div>
    </div>
</div>
<c:import url="views/footer.jsp"/>
</body>
</html>
