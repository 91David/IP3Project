package wpd2.lab1.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DemoServlet extends BaseServlet {
    @SuppressWarnings("unused")
    static final Logger LOG = LoggerFactory.getLogger(DemoServlet.class);
    private static final long serialVersionUID = -7461821901454655091L;

    private final String shopName;

    public DemoServlet(String shopName) {
        this.shopName = shopName;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String toPrint = "Welcome, your shop is called " + shopName + " and your path is " + request.getRequestURI();
        issue(PLAIN_TEXT_UTF_8, HttpServletResponse.SC_OK, toPrint.getBytes(CHARSET_UTF8), response);
    }


}
