import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

/**
 * Servlet que muestra el número de veces que se ha 
 * accedido a la página desde el navegador.
 * 
 * @author Juan Luis Reyes Pérez
 *
 */
@WebServlet("/contador")
public class Contador extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String valor = null;
         int n=0;

         Cookie[] cs = request.getCookies();
         if (cs!=null) 
             for (int i=0; i<cs.length; i++) {
                 if ((cs[i].getName()).equals("contador")) {
                     valor = cs[i].getValue(); 
                     break;
                 }
             }
         if (valor != null){
        	 n = Integer.parseInt (valor);
         }
         n++;

         Cookie c = new Cookie ("contador", Integer.toString(n));
         c.setMaxAge (60*60*24*365);
         response.addCookie (c);
         String titulo = "Eres el visitante número: " + n + " por este navegador.";
         
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String docType = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " + "Transitional//EN\">\n";
		out.println(docType + "<HTML>\n" + "<HEAD><TITLE>" + "Servlet Contador" + "</TITLE></HEAD>\n"
				+ "<BODY BGCOLOR=\"#FDF5E6\">\n" + "<H1 ALIGN=\"CENTER\">" + "Servlet Contador de Acceso" + "</H1>\n" 
				+ "<H2 ALIGN=\"CENTER\">" + titulo +  "</H2>" + "</BODY></HTML>");
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,
	IOException {
	doGet(request, response);
	}
}