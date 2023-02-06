package ua.com.vp.confapp.tag;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;

import java.io.IOException;
import java.time.LocalDateTime;

public class DisableButtonTag extends SimpleTagSupport {
    private LocalDateTime dateTime;
    private boolean disableForPast;


    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDisableForPast(boolean disableForPast) {
        this.disableForPast = disableForPast;
    }


    @Override
    public void doTag() throws IOException {
        String out;
        if (disableForPast) {
            out = dateTime.isAfter(LocalDateTime.now()) ? "" : "disabled";
        } else {
            out = dateTime.isAfter(LocalDateTime.now()) ? "disabled" : "";
        }

        JspWriter writer = getJspContext().getOut();
        writer.print(out);
    }
}
