package ua.com.vp.confapp.tag;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;

import java.io.IOException;
import java.time.LocalDateTime;

import static jakarta.servlet.jsp.tagext.Tag.SKIP_BODY;

public class DateHandler extends SimpleTagSupport {
    private LocalDateTime dateTime;

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int doStartTag() {
        JspWriter out = getJspContext().getOut();
        try {
            out.print(dateTime);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return SKIP_BODY;
    }
}
