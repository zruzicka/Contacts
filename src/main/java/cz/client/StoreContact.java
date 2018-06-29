package cz.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import cz.zr.contacts.Consts;
import cz.zr.contacts.ExceptionHandler;
import cz.zr.contacts.Utils;
import cz.zr.contacts.model.Contact;
import cz.zr.contacts.model.ContactField;
import cz.zr.contacts.service.ContactService;

/**
 * Servlet for contact details processing.
 *
 * @author zruzicka
 */
public class StoreContact extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // Single ContactService instance MUST be used.
    private static ContactService contactService = new ContactService();

    @Override
    public void init() throws ServletException {
        // Intentionally left empty.
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rawQuery = StringUtils.defaultString(request.getQueryString(), "");
        try {
            // QueryString must be decoded before usage.
            String queryString = URLDecoder.decode(rawQuery, Consts.URL_CHARSET);
            Map<String, String[]> parameters = loadParameters(queryString);

            if (!parameters.isEmpty()) {
                String name = getFirstParameter(parameters, ContactField.NAME);
                String lastName = getFirstParameter(parameters, ContactField.LAST_NAME);
                String email = getFirstParameter(parameters, ContactField.EMAIL);
                contactService.add(Contact.create(name, lastName, email));
            }

            provideResponse(response);
        } catch (IOException e) {
            ExceptionHandler.handleExc(e);
        }
    }

    /**
     * @param response
     * @throws IOException
     */
    private void provideResponse(HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        PrintWriter out = response.getWriter();
        out.println("<h1>No content available.</h1>");
    }

    /**
     * This method <fill in description>
     *
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     */
    private Map<String, String[]> loadParameters(String queryString) throws UnsupportedEncodingException {
        Map<String, String[]> queryParameters = new HashMap<>();
        if (StringUtils.isNotEmpty(queryString)) {
            String[] parameters = queryString.split("&");
            for (String parameter : parameters) {
                String[] keyValuePair = parameter.split("=");
                // Even older values are loaded from the map for a case if key is repeated in query.
                String[] values = queryParameters.get(keyValuePair[0]);
                // Load keyValuePair's value at index 1.
                values = ArrayUtils.add(values, Utils.valueAt(keyValuePair, 1));
                queryParameters.put(keyValuePair[0], values);
            }
        }
        return queryParameters;
    }

    /**
     * @return The first recorded value for given key.
     */
    private String getFirstParameter(Map<String, String[]> params, ContactField key) {
        String[] values = params.get(key.getValue());
        return Utils.notNullValueAt(values, 0);
    }

}
