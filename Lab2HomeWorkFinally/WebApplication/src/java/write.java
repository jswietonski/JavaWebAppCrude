
import Trwalosc.MotorQuery;
import Trwalosc.Motorcycles;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.PersistenceException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "write", urlPatterns = {"/Write", "/insert", "/delete", "/update"})
public class write extends HttpServlet {
    boolean name = false;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String sort = request.getParameter("Sort");
        orderByBrand(sort);
        String inst = request.getParameter("motorcycles");
        try (PrintWriter out = response.getWriter()) {
              out.println("<!DOCTYPE html>");         
              out.println("<html>");
              out.println("<head><meta><link rel='stylesheet' href='Style/css/components.css'>");
              out.println("<link rel='stylesheet' href='Style/css/icons.css'>");
              out.println("<link rel='stylesheet' href='Style/css/responsee.css'>");
              out.println("<body>" + "List of motorcycles " + inst + "<br />");
              out.println(new MotorQuery().getMotorList(name));
              out.println("<a class=\'button rounded-full-btn reload-btn s-2 margin-bottom\' href=");
              out.println(request.getHeader("referer"));
              out.println("><i class='icon-sli-arrow-left'>Back</i></a>");
              out.println("</body></html>");
        }catch (PersistenceException ex) {
            Logger.getLogger(write.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String action = request.getServletPath();
        try {
            switch (action) {
 
                case "/insert":
                    insertMotor(request, response);
                    break;
                    
                case "/delete":
                    deleteMotor(request, response);
                    break;
                
                case "/update":
                    updateMotor(request, response);
                    break;
                default:
                    listMotor(request, response);
                    break;
           }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    
    
    private boolean orderByBrand(String sort) {
        if(sort != null) {
            this.name = true;
        }
        return name;
    }
    
    
    
    private void insertMotor(HttpServletRequest request, HttpServletResponse response) 
         throws SQLException, IOException {
      
       String modelId = request.getParameter("modelidw");
       String name = request.getParameter("namew");
       String brand = request.getParameter("brandw");
       String EngineCapacity = request.getParameter("enginecapacityw");
       String power = request.getParameter("powerw");
       String noTraces = request.getParameter("notracesw");
       String RequireDrivingLicense = request.getParameter("requiredrivinglicensew");
       Motorcycles motor = new Motorcycles(Integer.parseInt(modelId), name, brand, EngineCapacity, power, Integer.parseInt(noTraces), RequireDrivingLicense);
       new MotorQuery().insertMotor(motor);
       response.sendRedirect("Write");
        
    }
    
  
    
    
    
    private void deleteMotor(HttpServletRequest request, HttpServletResponse response)
          throws SQLException, IOException {
            int id = Integer.parseInt(request.getParameter("modelidw"));
            new MotorQuery().deleteUser(id);
            response.sendRedirect("Write");
        }
    
    


    private void updateMotor(HttpServletRequest request, HttpServletResponse response)
           throws SQLException, IOException {
           String modelId = request.getParameter("modelidw");
           String name = request.getParameter("namew");
           String brand = request.getParameter("brandw");
           String EngineCapacity = request.getParameter("enginecapacityw");
           String power = request.getParameter("powerw");
           String noTraces = request.getParameter("notracesw");
           String RequireDrivingLicense = request.getParameter("requiredrivinglicensew");
           Motorcycles motor = new Motorcycles(Integer.parseInt(modelId), name, brand, EngineCapacity, power, Integer.parseInt(noTraces), RequireDrivingLicense);
           new MotorQuery().updateMotor(motor);
           response.sendRedirect("Write");
        }
   


        
    
    private void listMotor(HttpServletRequest request, HttpServletResponse response) 
         throws ServletException, IOException {
        processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");
        String sort = request.getParameter("Sort");
        orderByBrand(sort);
        String inst = request.getParameter("motorcycles");
        try (PrintWriter out = response.getWriter()) {
              out.println("<!DOCTYPE html>");         
              out.println("<html>");
              out.println("<head><meta><link rel='stylesheet' href='Style/css/components.css'>");
              out.println("<link rel='stylesheet' href='Style/css/icons.css'>");
              out.println("<link rel='stylesheet' href='Style/css/responsee.css'>");
              out.println("<body>" + "List of motorcycles " + inst + "<br />");
              out.println(new MotorQuery().getMotorList(name));
              out.println("<a class=\'button rounded-full-btn reload-btn s-2 margin-bottom\' href=");
              out.println(request.getHeader("referer"));
              out.println("><i class='icon-sli-arrow-left'>Back</i></a>");
              out.println("</body></html>");
              response.sendRedirect("Write");
      }
    }
    


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
    
    
    private boolean polaczenie = false;
    private String data;
    
    private String getDataFromDb() {
        try {
            polaczenie = ManagerDb.DbManager.Connect();
            if (polaczenie) {
                data = ManagerDb.DbManager.getData();
                polaczenie = ManagerDb.DbManager.Disconnect();
            }
           } catch (ClassNotFoundException | SQLException ex) {
               Logger.getLogger(write.class.getName()).log(Level.SEVERE, null, ex);
            }
       return data;
}
    
}