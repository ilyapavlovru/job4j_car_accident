<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">

    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <title>Сайт Автонарушители</title>
</head>


<body>

<div class="row">
    <ul class="nav">
        <li class="nav-item">
            <a class="nav-link" href="<c:url value='/create'/>">Добавить инцидент</a>
        </li>
    </ul>
</div>

<div class="container">
    <h2>Все заявления</h2>
    <div class="row">
        <div class="col-12">
            <table class="table table-bordered" id='table'>

                <thead>
                <tr>
                    <th scope="col">Наименование нарушения</th>
                    <th scope="col">Адрес нарушения</th>
                    <th scope="col">Номер машины</th>
                    <th scope="col">Описание нарушения</th>
                    <th scope="col">Фотография нарушения</th>
                    <th scope="col">Статус нарушения</th>
                </tr>
                </thead>

                <tbody id="allUsersTable">
                <c:forEach items="${accidents}" var="accident">
                <tr>
                    <td>
                        <c:out value="${accident.name}"/>
                    </td>
                    <td>
                        <c:out value="${accident.address}"/>
                    </td>
                    <td>
                        <c:out value="${accident.carNumber}"/>
                    </td>
                    <td>
                        <c:out value="${accident.description}"/>
                    </td>
                    <td>
                        <c:out value="${accident.image}"/>
                    </td>
                    <td>
                        <c:out value="${accident.status}"/>
                    </td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<!-- Bootstrap 5 Scripts -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>

</body>
</html>
