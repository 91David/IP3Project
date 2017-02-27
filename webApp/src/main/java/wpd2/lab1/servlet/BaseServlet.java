
package wpd2.lab1.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;


public class BaseServlet extends HttpServlet {
    @SuppressWarnings("unused")
    static final Logger LOG = LoggerFactory.getLogger(BaseServlet.class);

    public static final  String PLAIN_TEXT_UTF_8 = "text/plain; charset=UTF-8";
    public static final Charset CHARSET_UTF8 = Charset.forName("UTF-8");

    protected BaseServlet() {
    }

    protected void issue(String mimeType, int returnCode, byte[] output, HttpServletResponse response) throws IOException {
        response.setContentType(mimeType);
        response.setStatus(returnCode);
        response.getOutputStream().write(output);
    }

    protected void cache(HttpServletResponse response, int seconds) {
        if (seconds > 0) {
            response.setHeader("Pragma", "Public");
            response.setHeader("Cache-Control", "public, no-transform, max-age=" + seconds);
        }
    }
}
