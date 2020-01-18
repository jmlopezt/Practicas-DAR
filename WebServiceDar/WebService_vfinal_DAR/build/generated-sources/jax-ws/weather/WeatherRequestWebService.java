
package weather;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "WeatherRequestWebService", targetNamespace = "http://Weather/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface WeatherRequestWebService {


    /**
     * 
     * @param cityName
     * @param countryName
     */
    @WebMethod
    @RequestWrapper(localName = "getParameters", targetNamespace = "http://Weather/", className = "weather.GetParameters")
    @ResponseWrapper(localName = "getParametersResponse", targetNamespace = "http://Weather/", className = "weather.GetParametersResponse")
    @Action(input = "http://Weather/WeatherRequestWebService/getParametersRequest", output = "http://Weather/WeatherRequestWebService/getParametersResponse")
    public void getParameters(
        @WebParam(name = "cityName", targetNamespace = "")
        String cityName,
        @WebParam(name = "countryName", targetNamespace = "")
        String countryName);

    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getHumidity", targetNamespace = "http://Weather/", className = "weather.GetHumidity")
    @ResponseWrapper(localName = "getHumidityResponse", targetNamespace = "http://Weather/", className = "weather.GetHumidityResponse")
    @Action(input = "http://Weather/WeatherRequestWebService/getHumidityRequest", output = "http://Weather/WeatherRequestWebService/getHumidityResponse")
    public String getHumidity();

    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getPressure", targetNamespace = "http://Weather/", className = "weather.GetPressure")
    @ResponseWrapper(localName = "getPressureResponse", targetNamespace = "http://Weather/", className = "weather.GetPressureResponse")
    @Action(input = "http://Weather/WeatherRequestWebService/getPressureRequest", output = "http://Weather/WeatherRequestWebService/getPressureResponse")
    public String getPressure();

    /**
     * 
     * @param country
     * @param city
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "hello", targetNamespace = "http://Weather/", className = "weather.Hello")
    @ResponseWrapper(localName = "helloResponse", targetNamespace = "http://Weather/", className = "weather.HelloResponse")
    @Action(input = "http://Weather/WeatherRequestWebService/helloRequest", output = "http://Weather/WeatherRequestWebService/helloResponse")
    public String hello(
        @WebParam(name = "City", targetNamespace = "")
        String city,
        @WebParam(name = "Country", targetNamespace = "")
        String country);

    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getVisibility", targetNamespace = "http://Weather/", className = "weather.GetVisibility")
    @ResponseWrapper(localName = "getVisibilityResponse", targetNamespace = "http://Weather/", className = "weather.GetVisibilityResponse")
    @Action(input = "http://Weather/WeatherRequestWebService/getVisibilityRequest", output = "http://Weather/WeatherRequestWebService/getVisibilityResponse")
    public String getVisibility();

    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getDateTime", targetNamespace = "http://Weather/", className = "weather.GetDateTime")
    @ResponseWrapper(localName = "getDateTimeResponse", targetNamespace = "http://Weather/", className = "weather.GetDateTimeResponse")
    @Action(input = "http://Weather/WeatherRequestWebService/getDateTimeRequest", output = "http://Weather/WeatherRequestWebService/getDateTimeResponse")
    public String getDateTime();

    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getTemperature", targetNamespace = "http://Weather/", className = "weather.GetTemperature")
    @ResponseWrapper(localName = "getTemperatureResponse", targetNamespace = "http://Weather/", className = "weather.GetTemperatureResponse")
    @Action(input = "http://Weather/WeatherRequestWebService/getTemperatureRequest", output = "http://Weather/WeatherRequestWebService/getTemperatureResponse")
    public String getTemperature();

    /**
     * 
     * @param etiqueta
     * @param message
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "ObtainXmlData")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "ObtainXmlData", targetNamespace = "http://Weather/", className = "weather.ObtainXmlData")
    @ResponseWrapper(localName = "ObtainXmlDataResponse", targetNamespace = "http://Weather/", className = "weather.ObtainXmlDataResponse")
    @Action(input = "http://Weather/WeatherRequestWebService/ObtainXmlDataRequest", output = "http://Weather/WeatherRequestWebService/ObtainXmlDataResponse")
    public String obtainXmlData(
        @WebParam(name = "message", targetNamespace = "")
        String message,
        @WebParam(name = "etiqueta", targetNamespace = "")
        String etiqueta);

}