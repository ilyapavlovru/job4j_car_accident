<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link type="text/css" rel="stylesheet" href="../css/styles.css"/>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

    <title>Сайт Автонарушители</title>
</head>

<body>

<div class="container pt-3">
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                Новое нарушение
            </div>

            <div class="card-body">

                <form action="<c:url value='/save'/>" method="post">

                    <div class="form-group">
                        <label>Заголовок нарушения:</label>
                        <input type="text" class="form-control" id="name" name="name">
                    </div>

                    <div class="form-group">
                        <label>Адрес нарушения:</label>
                        <input type="text" class="form-control" id="address" name="address">
                    </div>

                    <div class="form-group">
                        <label>Номер машины:</label>
                        <input type="text" class="form-control" id="carNumber" name="carNumber">
                    </div>

                    <div class="form-group">
                        <label for="descriptionTextArea">Описание:</label>
                        <textarea class="form-control" id="descriptionTextArea" name = "description" rows="3"></textarea>
                    </div>

                    <div class="form-group">
                        <label for="accidentStatusSelector">Статус заявки:</label>
                        <select class="form-control" id="accidentStatusSelector" name = "status">
                            <option selected value="">Выберите статус заявки</option>
                            <option value="Принята">Принята</option>
                            <option value="Отклонена">Отклонена</option>
                            <option value="Завершена">Завершена</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="accidentTypeSelector">Тип нарушения:</label>
                        <select class="form-control" id="accidentTypeSelector" name="type.id">
                            <c:forEach var="type" items="${types}" >
                                <option value="${type.id}">${type.name}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="ruleTypeSelector">Статьи:</label>
                        <select class="form-control" id="ruleTypeSelector" name="rIds" multiple>
                            <c:forEach var="rule" items="${rules}" >
                                <option value="${rule.id}">${rule.name}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <input type="hidden" name="action" value="update"/>
                    <button type="submit" value="UPDATE" class="btn btn-primary" onclick="return validate()">Сохранить</button>
                </form>
            </div>

        </div>
    </div>
</div>

</body>
</html>
