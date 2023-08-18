package com.top.calc;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "findingTheSide", value = "/calc")
public class Calc extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        CosineTheorem cosTheorem=new CosineTheorem();
        resp.setContentType("text/html");
        PrintWriter out=resp.getWriter();

        double sideB,sideC,alfa;
        double res; //Результат

        String units=req.getParameter("units");
        String error="Вы не ввели один из параметров";
        try {
            //Проверка на не заполненые поля
            if(!req.getParameter("sideB").isEmpty() && !req.getParameter("sideC").isEmpty() && !req.getParameter("alfa").isEmpty()) {
                sideB = Double.parseDouble(req.getParameter("sideB"));//Получаем сторону В
                sideC = Double.parseDouble(req.getParameter("sideC")); //Получаем сторону С
                alfa = Double.parseDouble(req.getParameter("alfa"));   //Получаем угол alfa
                System.out.println("Полученны параметры: "+sideC+" "+sideB+" "+alfa);

                switch (units) {
                    //1. Если пользователь выбрал ед. градусы
                    case "degree":
                        if (alfa >= 0 && alfa <= 360) {
                            alfa = Math.toRadians(alfa);
                            res = cosTheorem.foundingASide(sideC, sideB, alfa);
                            req.setAttribute("res", res);
                            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
                        } else {
                            req.setAttribute("res", "Не верное значение угла");
                            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
                            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        }
                        break;
                    //2. Если пользователь выбрал ед. радианы
                    case "radians":
                        if (alfa >= 0 && alfa <= 6.3) {
                            res = cosTheorem.foundingASide(sideC, sideB, alfa);
                            req.setAttribute("res", res);
                            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
                        } else {
                            req.setAttribute("res", "Не верное значение угла");
                            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
                            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        }
                        break;
                    default:
                        break;
                }
            }else {
                System.out.println(error);
                out.print("<p>Ошибка:" + error + "</p>");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }catch (Exception e){
            error=("Введены неверные параметры "+e.getMessage());
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print(error+HttpServletResponse.SC_BAD_REQUEST);
            System.out.println(error);
        }finally {
            out.close();
        }
    }
}

