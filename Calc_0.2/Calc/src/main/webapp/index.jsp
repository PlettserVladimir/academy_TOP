<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Калькулятор "Теорема косинусов"</title>
</head>
<body>
<form action="calc" method="get">
    <label> Сторона В
        <input type="text" name="sideB">
    </label>
    <br>
    <label>Сторона С
        <input type="text" name="sideC">
    </label>
    <br>
    <label>Угол
        <input type="text" name="alfa">
    </label>
    <br>
    Единицы измерения угла:<label> Градусы
    <input type="radio" name="units" value="degree" checked>
</label>
    <label>Радианы <input type="radio" name="units" value="radians"></label>
    <br>
    <button>Расчетать</button>
    <br>
    <p>Ответ: ${res}</p>

</form>

</body>
</html>