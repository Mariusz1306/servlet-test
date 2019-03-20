import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class MyServlet extends HttpServlet {

    String msg;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        Cookie[] cookies = request.getCookies();
        Cookie licznik = null;

        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++){
                if (cookies[i].getName().equals("licznik")) {
                    licznik = cookies[i];
                    break;
                }
            }
        }

        if (licznik == null) {
            licznik = new Cookie("licznik", "0");
        } else {
            Integer v = Integer.parseInt(licznik.getValue());
            v++;
            licznik.setValue(v+"");
        }

        licznik.setMaxAge(-1);
        response.addCookie(licznik);

        out.println(licznik.getValue());

        try {
            HttpSession session = request.getSession(true);
            String action = request.getParameter("akcja");

            if (action != null) {
                if (action.equals("wyloguj")) {
                    Boolean wartosc = false;
                    session.setAttribute("zalogowany", wartosc);
                }
            }
            Boolean loggedIn = (Boolean)session.getAttribute("zalogowany");

            if (loggedIn == null)
                loggedIn = false;
            if (loggedIn == true) {
                out.println("<h1>ZALOGOWANY</h1>");
                out.println("<form method = \"get\">");
                out.println("<input type = \"hidden\" name = \"akcja\" value = \"wyloguj\" \\>");
                out.println("<input type = \"submit\" value = \"Wyloguj\" \\>");
                out.println("</form>");
            } else {
                out.println("<form method=\"get\">");
                out.println("<input type=\"text\" name=\"user\"/>");
                out.println("<input type=\"password\" name=\"pass\"/>");
                out.println("<input type=\"submit\" value=\"zaloguj\"/>");
                out.println("</form>");

                String user;
                String pass;
                user = request.getParameter("user");
                pass = request.getParameter("pass");
                if (user != null && pass != null) {
                    if (user.equals("radek") && pass.equals("haslo")) {
                        loggedIn = true;
                        session.setAttribute("zalogowany", loggedIn);
                        response.setIntHeader("Refresh", 0);
                    }
                }

            }


        } finally {
            out.close();
        }




        /*response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<h2>Welcome to my first servlet application in IntelliJ</h2>");
            out.println("Wynik getMethod: " + request.getMethod() + "<BR>");
            out.println("Wynik getRemoteAddr: " + request.getRemoteAddr() + "<BR>");
            out.println("Wynik getServerName: " + request.getServerName() + "<BR>");
            out.println("Wynik getHeader: " + request.getHeader("Accept") + "<BR>");
            out.println("Wynik getHeader: " + request.getHeader("Accept-Language") + "<BR>");
            out.println("Wynik getHeader: " + request.getHeader("Accept-Encoding") + "<BR>");
            out.println("Wynik getHeader: " + request.getHeader("User-Agent") + "<BR>");
            out.println("<BR>");
            try {
                if (request.getParameter("x") != null && request.getParameter("y") != null) {
                    out.println("Wynik getParameter(\"x\"): : " + request.getParameter("x") + "<BR>");
                    out.println("Wynik getParameter(\"y\"): : " + request.getParameter("y") + "<BR>");
                    int x, y;
                    x = Integer.parseInt(request.getParameter("x"));
                    y = Integer.parseInt(request.getParameter("y"));
                    out.println("Wynik dodawania: " + (x + y) + "<BR>");
                }
            } catch (NumberFormatException e){
                out.println("Incorrect input. " + e.getMessage() + "<BR>");
            }
        } finally {
            out.close();
        }*/
    }

    public void init(ServletConfig config) {
        msg = config.getInitParameter("name");
    }

}
