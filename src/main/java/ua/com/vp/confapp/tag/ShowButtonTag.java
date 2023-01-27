package ua.com.vp.confapp.tag;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.TagSupport;

import java.time.LocalDateTime;


public class ShowButtonTag extends TagSupport {
    private LocalDateTime dateTime;


    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int doStartTag() throws JspException {
        return dateTime.isBefore(LocalDateTime.now()) ? SKIP_BODY : EVAL_BODY_INCLUDE;
    }
}
