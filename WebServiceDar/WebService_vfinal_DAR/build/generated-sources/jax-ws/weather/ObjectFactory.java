
package weather;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the weather package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetDateTimeResponse_QNAME = new QName("http://Weather/", "getDateTimeResponse");
    private final static QName _GetDateTime_QNAME = new QName("http://Weather/", "getDateTime");
    private final static QName _GetTemperatureResponse_QNAME = new QName("http://Weather/", "getTemperatureResponse");
    private final static QName _GetParametersResponse_QNAME = new QName("http://Weather/", "getParametersResponse");
    private final static QName _ObtainXmlDataResponse_QNAME = new QName("http://Weather/", "ObtainXmlDataResponse");
    private final static QName _GetPressureResponse_QNAME = new QName("http://Weather/", "getPressureResponse");
    private final static QName _GetHumidity_QNAME = new QName("http://Weather/", "getHumidity");
    private final static QName _GetVisibilityResponse_QNAME = new QName("http://Weather/", "getVisibilityResponse");
    private final static QName _HelloResponse_QNAME = new QName("http://Weather/", "helloResponse");
    private final static QName _ObtainXmlData_QNAME = new QName("http://Weather/", "ObtainXmlData");
    private final static QName _GetPressure_QNAME = new QName("http://Weather/", "getPressure");
    private final static QName _GetVisibility_QNAME = new QName("http://Weather/", "getVisibility");
    private final static QName _GetTemperature_QNAME = new QName("http://Weather/", "getTemperature");
    private final static QName _GetHumidityResponse_QNAME = new QName("http://Weather/", "getHumidityResponse");
    private final static QName _Hello_QNAME = new QName("http://Weather/", "hello");
    private final static QName _GetParameters_QNAME = new QName("http://Weather/", "getParameters");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: weather
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ObtainXmlDataResponse }
     * 
     */
    public ObtainXmlDataResponse createObtainXmlDataResponse() {
        return new ObtainXmlDataResponse();
    }

    /**
     * Create an instance of {@link GetParametersResponse }
     * 
     */
    public GetParametersResponse createGetParametersResponse() {
        return new GetParametersResponse();
    }

    /**
     * Create an instance of {@link GetDateTime }
     * 
     */
    public GetDateTime createGetDateTime() {
        return new GetDateTime();
    }

    /**
     * Create an instance of {@link GetTemperatureResponse }
     * 
     */
    public GetTemperatureResponse createGetTemperatureResponse() {
        return new GetTemperatureResponse();
    }

    /**
     * Create an instance of {@link GetDateTimeResponse }
     * 
     */
    public GetDateTimeResponse createGetDateTimeResponse() {
        return new GetDateTimeResponse();
    }

    /**
     * Create an instance of {@link GetVisibilityResponse }
     * 
     */
    public GetVisibilityResponse createGetVisibilityResponse() {
        return new GetVisibilityResponse();
    }

    /**
     * Create an instance of {@link GetHumidity }
     * 
     */
    public GetHumidity createGetHumidity() {
        return new GetHumidity();
    }

    /**
     * Create an instance of {@link GetPressureResponse }
     * 
     */
    public GetPressureResponse createGetPressureResponse() {
        return new GetPressureResponse();
    }

    /**
     * Create an instance of {@link ObtainXmlData }
     * 
     */
    public ObtainXmlData createObtainXmlData() {
        return new ObtainXmlData();
    }

    /**
     * Create an instance of {@link GetPressure }
     * 
     */
    public GetPressure createGetPressure() {
        return new GetPressure();
    }

    /**
     * Create an instance of {@link HelloResponse }
     * 
     */
    public HelloResponse createHelloResponse() {
        return new HelloResponse();
    }

    /**
     * Create an instance of {@link GetParameters }
     * 
     */
    public GetParameters createGetParameters() {
        return new GetParameters();
    }

    /**
     * Create an instance of {@link GetHumidityResponse }
     * 
     */
    public GetHumidityResponse createGetHumidityResponse() {
        return new GetHumidityResponse();
    }

    /**
     * Create an instance of {@link Hello }
     * 
     */
    public Hello createHello() {
        return new Hello();
    }

    /**
     * Create an instance of {@link GetTemperature }
     * 
     */
    public GetTemperature createGetTemperature() {
        return new GetTemperature();
    }

    /**
     * Create an instance of {@link GetVisibility }
     * 
     */
    public GetVisibility createGetVisibility() {
        return new GetVisibility();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDateTimeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Weather/", name = "getDateTimeResponse")
    public JAXBElement<GetDateTimeResponse> createGetDateTimeResponse(GetDateTimeResponse value) {
        return new JAXBElement<GetDateTimeResponse>(_GetDateTimeResponse_QNAME, GetDateTimeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDateTime }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Weather/", name = "getDateTime")
    public JAXBElement<GetDateTime> createGetDateTime(GetDateTime value) {
        return new JAXBElement<GetDateTime>(_GetDateTime_QNAME, GetDateTime.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTemperatureResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Weather/", name = "getTemperatureResponse")
    public JAXBElement<GetTemperatureResponse> createGetTemperatureResponse(GetTemperatureResponse value) {
        return new JAXBElement<GetTemperatureResponse>(_GetTemperatureResponse_QNAME, GetTemperatureResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetParametersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Weather/", name = "getParametersResponse")
    public JAXBElement<GetParametersResponse> createGetParametersResponse(GetParametersResponse value) {
        return new JAXBElement<GetParametersResponse>(_GetParametersResponse_QNAME, GetParametersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtainXmlDataResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Weather/", name = "ObtainXmlDataResponse")
    public JAXBElement<ObtainXmlDataResponse> createObtainXmlDataResponse(ObtainXmlDataResponse value) {
        return new JAXBElement<ObtainXmlDataResponse>(_ObtainXmlDataResponse_QNAME, ObtainXmlDataResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPressureResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Weather/", name = "getPressureResponse")
    public JAXBElement<GetPressureResponse> createGetPressureResponse(GetPressureResponse value) {
        return new JAXBElement<GetPressureResponse>(_GetPressureResponse_QNAME, GetPressureResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetHumidity }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Weather/", name = "getHumidity")
    public JAXBElement<GetHumidity> createGetHumidity(GetHumidity value) {
        return new JAXBElement<GetHumidity>(_GetHumidity_QNAME, GetHumidity.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetVisibilityResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Weather/", name = "getVisibilityResponse")
    public JAXBElement<GetVisibilityResponse> createGetVisibilityResponse(GetVisibilityResponse value) {
        return new JAXBElement<GetVisibilityResponse>(_GetVisibilityResponse_QNAME, GetVisibilityResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Weather/", name = "helloResponse")
    public JAXBElement<HelloResponse> createHelloResponse(HelloResponse value) {
        return new JAXBElement<HelloResponse>(_HelloResponse_QNAME, HelloResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtainXmlData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Weather/", name = "ObtainXmlData")
    public JAXBElement<ObtainXmlData> createObtainXmlData(ObtainXmlData value) {
        return new JAXBElement<ObtainXmlData>(_ObtainXmlData_QNAME, ObtainXmlData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPressure }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Weather/", name = "getPressure")
    public JAXBElement<GetPressure> createGetPressure(GetPressure value) {
        return new JAXBElement<GetPressure>(_GetPressure_QNAME, GetPressure.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetVisibility }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Weather/", name = "getVisibility")
    public JAXBElement<GetVisibility> createGetVisibility(GetVisibility value) {
        return new JAXBElement<GetVisibility>(_GetVisibility_QNAME, GetVisibility.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTemperature }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Weather/", name = "getTemperature")
    public JAXBElement<GetTemperature> createGetTemperature(GetTemperature value) {
        return new JAXBElement<GetTemperature>(_GetTemperature_QNAME, GetTemperature.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetHumidityResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Weather/", name = "getHumidityResponse")
    public JAXBElement<GetHumidityResponse> createGetHumidityResponse(GetHumidityResponse value) {
        return new JAXBElement<GetHumidityResponse>(_GetHumidityResponse_QNAME, GetHumidityResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Hello }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Weather/", name = "hello")
    public JAXBElement<Hello> createHello(Hello value) {
        return new JAXBElement<Hello>(_Hello_QNAME, Hello.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetParameters }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Weather/", name = "getParameters")
    public JAXBElement<GetParameters> createGetParameters(GetParameters value) {
        return new JAXBElement<GetParameters>(_GetParameters_QNAME, GetParameters.class, null, value);
    }

}
