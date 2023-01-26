package ua.com.vp.confapp.tag;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import static jakarta.servlet.jsp.tagext.Tag.SKIP_BODY;

public class WelcomeTag extends SimpleTagSupport {
    private String role;

    public void setRole(String role) {
        this.role = role;
    }

    public int doStartTag() throws JspException {
        try {
            String to = null;
            if ("admin".equalsIgnoreCase(role)) {
                to = "Hello, " + role;
            } else if ("moderator".equalsIgnoreCase(role)) {
                to = "Let's work, " + role;
            } else {
                to = "Welcome, " + role;
            }

            JspWriter out = getJspContext().getOut();
            out.print(to);

        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
}
