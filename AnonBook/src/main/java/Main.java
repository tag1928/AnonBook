import Servlets.CommentServlet;
import Servlets.IndexServlet;
import Servlets.PostServlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class Main
{
	public static void main(String[] args)
	{
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(8080);
		tomcat.setBaseDir("Tomcat");

		final String contextPath = "";
		final String docBase =
			"C:\\Users\\wwwta\\Desktop\\Java\\MziuriJava2\\Project4\\AnonBook\\src\\main\\DocBase";

		Context context = tomcat.addContext(contextPath, docBase);

		Tomcat.addServlet(context, "PostServlet", PostServlet.getInstance());
		Tomcat.addServlet(context, "CommentServlet", CommentServlet.getInstance());
		Tomcat.addServlet(context, "IndexServlet", IndexServlet.getInstance());
		Tomcat.addServlet(context, "MainPage", "org.apache.catalina.servlets.DefaultServlet");

		context.addServletMappingDecoded("/post/*", "PostServlet");
		context.addServletMappingDecoded("/comment", "CommentServlet");
		context.addServletMappingDecoded("", "IndexServlet");
		context.addServletMappingDecoded("/", "MainPage");

		try
		{
			tomcat.start();
			tomcat.getConnector();
			tomcat.getServer().await();
		}
		catch (LifecycleException e)
		{
			System.err.println("LifeCycle exception at Main::main()");
		}
	}
}