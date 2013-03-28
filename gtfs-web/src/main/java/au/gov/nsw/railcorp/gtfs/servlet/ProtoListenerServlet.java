// RailCorp 2012
package au.gov.nsw.railcorp.gtfs.servlet;

import au.gov.nsw.railcorp.gtfs.converter.GeneralProtocolBufferConverter;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestHandler;


/**
 * Servlet implementation class ProtoListenerServlet. This class implements a
 * generic listener that receives protocol buffer input and passes it to its.
 */
public class ProtoListenerServlet implements HttpRequestHandler {

    private static final long serialVersionUID = 1L;

    private GeneralProtocolBufferConverter converter;
    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * Constructor.
     */
    public ProtoListenerServlet() {

        super();
    }

    /**
     * getter for serialversionuid.
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {

        return serialVersionUID;
    }

    /**
     * getter for converter.
     * @return the converter
     */
    public GeneralProtocolBufferConverter getConverter() {

        return converter;
    }

    /**
     * setter for converter.
     * @param val
     *            the converter to set
     */
    public void setConverter(GeneralProtocolBufferConverter val) {

        this.converter = val;
    }


    /**
     * Handles all requests for the Protocol Buffer Listener servlet from the PI Database.
     * Expects the binary protocol buffer content as the request contents
     * {@inheritDoc}
     * @see HttpRequestHandler#handleRequest(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final PrintWriter writer = response.getWriter();
        final InputStream in = request.getInputStream();
        log.info("Protocol Buffer Process {} request received of {} bytes",
                request.getServletContext().getServletContextName(),
                request.getContentLength());
        if (converter.storeProtocolBuffer(in)) {
            writer.append("Successful Update");
        } else {
            writer.append("Failed Protocol Buffer validation processing");
        }

    }

}
