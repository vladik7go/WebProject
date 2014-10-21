package by.epam.project.tag;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import by.epam.project.resource.MessageManager;

public class SuccessfullyPerformedActionTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	public static Logger log = Logger
			.getLogger(SuccessfullyPerformedActionTag.class);
	private String language;

	public void setLanguage(String language) {
		this.language = language;
	}

	@Override
	public int doStartTag() throws JspException {

		try {
			Locale loc = new Locale(language);
//			 Locale loc = new Locale("en");
			log.debug("attribute 'language' =  " + language);

			ResourceBundle rb = ResourceBundle.getBundle(
					"resources.messages_bundle", loc);

			log.debug("locale of resource bundle: " + rb.getLocale()
					+ "  ---Locale loc = " + loc);
			StringBuilder htmlContent = new StringBuilder(
					rb.getString("edit.label.successfullyPerformedAction"));

			JspWriter out = pageContext.getOut();
			out.write(htmlContent.toString());
		} catch (IOException e) {
			log.error("Technical exception", e);
		}

		return SKIP_BODY;
	}

}
