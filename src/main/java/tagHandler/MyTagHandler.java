package tagHandler;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MyTagHandler extends TagSupport {
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();//returns the instance of JspWriter
        try {
            out.print(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        } catch (Exception e) {
            throw new JspException(e);
        }
        return SKIP_BODY;//will not evaluate the body content of the tag
    }
}
