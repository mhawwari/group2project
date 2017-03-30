package Servlet;
import HandleData.Member;
import HandleData.Gender;
import UNannyDB.DataBaseMember;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Controller extends HttpServlet {
    private HashMap<String, Dispatcher> dispatchTable;
    private HttpSession session;
    
    public Controller(){
        System.out.println("i am Controller");
        dispatchTable = new HashMap<String, Dispatcher>();
        dispatchTable.put(ServletCommands.param_CNTRL_Login, new Handler_Login());
        dispatchTable.put(ServletCommands.param_CNTRL_Logout, new Handler_Logout());
        dispatchTable.put(ServletCommands.param_CNTRL_Register, new Handler_Register());
        
    }
protected void handle(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		session = request.getSession(true);
		boolean flag = false;

		Enumeration<String> allParamNames = request.getParameterNames();
		while(allParamNames.hasMoreElements()){
			String tmp = allParamNames.nextElement();
			if(dispatchTable.containsKey(tmp)){
				flag = true;
				dispatchTable.get(tmp).execute(request, response);
			}
		}
		if(!flag){

			dispatchTable.get(ServletCommands.attrError).execute(request, response);
		}
	}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        handle(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        handle(request, response);
    }
    
    private void forward(String addr, HttpServletRequest request, HttpServletResponse response)
    {
        RequestDispatcher rd = getServletContext().getRequestDispatcher(addr);
        System.out.println("i am at forward");
        try {
            rd.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private interface Dispatcher{
        public void execute(HttpServletRequest request, HttpServletResponse response);
    }
    
     private class Handler_Login implements Dispatcher {
        @Override
        public void execute(HttpServletRequest request, HttpServletResponse response){
           String userName = request.getParameter(ServletCommands.paramUserName);
           String passWord = request.getParameter(ServletCommands.paramPassWord);
           System.out.println("ksekhnaw login");
           DataBaseMember connector = new DataBaseMember();
           System.out.println("connected");
           Boolean loginState = connector.checkPassword(userName, passWord);
           System.out.println("password ok");
           if(loginState){
               session.setAttribute(ServletCommands.attrUserName, userName);  
               forward("/index.html",request,response);
           }
           else{
               //   request.setAttribute(ServletCommands.attrError, connector.getConnStatus());
               forward("/login_register.html",request,response);
           }
           
        }
    }
    
    private class Handler_Logout implements Dispatcher {
        @Override
        public void execute(HttpServletRequest request, HttpServletResponse response){
            session.removeAttribute(ServletCommands.attrUserName);
            request.setAttribute(ServletCommands.attrUserName, null);
            forward("/login_registration.html",request,response);
        }
    }
    private class Handler_Register implements Dispatcher {
        @Override
        public void execute(HttpServletRequest request, HttpServletResponse response){
            System.out.println("i am at register");
             String userName = request.getParameter(ServletCommands.paramUserName);
           String passWord = request.getParameter(ServletCommands.paramPassWord);
           String firstName = request.getParameter(ServletCommands.paramFirstName);
           String lastName = request.getParameter(ServletCommands.paramLastName);
           String age1 =request.getParameter(ServletCommands.paramAge);
           Integer age= Integer.parseInt(age1);
           String mail = request.getParameter(ServletCommands.paramMail);
           String telephone = request.getParameter(ServletCommands.paramTelephone);
           String city= request.getParameter(ServletCommands.paramCity);
           String address= request.getParameter(ServletCommands.paramAddress);
           String zip1= request.getParameter(ServletCommands.paramZip);
           Integer zip=Integer.parseInt(zip1);
           String institute = request.getParameter(ServletCommands.paramInstitute);
           String department = request.getParameter(ServletCommands.paramDepartment);
           String gender = request.getParameter(ServletCommands.paramGender);
           System.out.println(gender);
           Member student = new Member(userName, passWord);
           student.setFirstName(firstName);
           student.setLastName(lastName);
           student.setAge(age);
           student.setMail(mail);
           student.setPhone(telephone);
           student.setCity(city);
           student.setadress(address);
           student.setZip(zip);
           student.setUniversity(institute);
           student.setDepartment(department);
           student.setGender(gender);
           DataBaseMember connector = new DataBaseMember();
           Boolean loginState = connector.insertStudentIntoBase(student);

           if(loginState){
               session.setAttribute(ServletCommands.attrUserName, userName);
               
               forward("/index.html",request,response);
           }
           else{
               //   request.setAttribute(ServletCommands.attrError, connector.getConnStatus());
               forward("/registration_nanny.jsp",request,response);
           }
        }
           
           
        }
    
    
    
   
    
   
}
