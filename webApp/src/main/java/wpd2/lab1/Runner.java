
package wpd2.lab1;

import wpd2.lab1.servlet.DemoServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Runner {
    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(Runner.class);

    private static final int PORT = 9000;
    private final String shopName;

    private Runner(String shopName) {
        this.shopName = shopName;
    }

    private void start() throws Exception {
        Server server = new Server(PORT);

        ServletContextHandler handler = new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS);
        handler.setContextPath("/");
        handler.setInitParameter("org.eclipse.jetty.servlet.Default." + "resourceBase", "src/main/resources/webapp");

        DemoServlet demoServlet = new DemoServlet(shopName);
        handler.addServlet(new ServletHolder(demoServlet), "/shop/*");

        DefaultServlet ds = new DefaultServlet();
        handler.addServlet(new ServletHolder(ds), "/");

        server.start();
        LOG.info("Server started, will run until terminated");
        server.join();

    }

    public static void main(String[] args) {
        try {
            LOG.info("starting");
            new Runner("Demo Shop").start();
        } catch (Exception e) {
            LOG.error("Unexpected error running shop: " + e.getMessage());
        }
    }
}
