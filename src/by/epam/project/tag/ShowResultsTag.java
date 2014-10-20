package by.epam.project.tag;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

public class ShowResultsTag extends TagSupport {
	
	private static final long serialVersionUID = 1L;
	public static Logger log = Logger.getLogger(ShowResultsTag.class);
	private String language;
	private String personId;

	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public void setLanguage(String language) {
		this.language = language;
	}

	@Override
	public int doStartTag() throws JspException {

		try {
			
			Locale locale = new Locale(language);
			ResourceBundle rs = ResourceBundle.getBundle(
					"resources.messages_bundle", locale);
			StringBuilder htmlContent = new StringBuilder(
					"<form name=\"show_results_form\" method=\"POST\" action=\"controller\">");
			htmlContent
					.append("<input type=\"hidden\" name=\"command\" value=\"show_Results\" />");
			htmlContent
					.append("<input type=\"hidden\" name=\"personId\" value=\""
							+ personId + "\" />");
			htmlContent.append("<input type=\"submit\" value=\"");

			htmlContent.append(rs.getString("root.button.show.results")
					+ "\"/></form>");
			JspWriter out = pageContext.getOut();
			out.write(htmlContent.toString());
		} catch (IOException e) {
			log.error("Technical exception", e);
		}

		return SKIP_BODY;
	}

}
