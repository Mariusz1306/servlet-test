import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
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
            if (request.getParameter("imie") != null && request.getParameter("wiek") !=null) {
                out.println("Wynik getParameter(\"imie\"): : " + request.getParameter("imie") + "<BR>");
                out.println("Wynik getParameter(\"wiek\"): : " + request.getParameter("wiek") + "<BR>");
            }
        } finally {
            out.close();
        }
    }
}
