/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Weather;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Juanma
 */
@WebService(serviceName = "WeatherRequestWebService")
public class WeatherRequestWebService {
    String city;
    String country;
    String temperature;
    String pressure;
    String dateTime;
    String visibility;
    String humidity;

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "City") String city, @WebParam(name = "Country") String country) {
        return "Hello " + city + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "ObtainXmlData")
    public String ObtainXmlData(@WebParam(name = "message") String mensajeRegistro, @WebParam(name = "etiqueta") String etiqueta) {
        //TODO write your implementation code here:
                    String nombre="";
            String etiquetaInicio="<"+etiqueta+">";
            String etiquetaCierre="</"+etiqueta+">";

            int posInicio=0;
            int posFinal=0;

            for(int i=0;i<mensajeRegistro.length();i++) {

                if (mensajeRegistro.substring(i,i+etiquetaInicio.length()).equals(etiquetaInicio)) {
                    posInicio=i+etiquetaInicio.length();
                }
                if(mensajeRegistro.substring(i,i+etiquetaCierre.length()).equals(etiquetaCierre)){
                    posFinal=i;
                    break;
                }
            }
            nombre=mensajeRegistro.substring(posInicio,posFinal);

            return nombre;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getParameters")
    public void getParameters(@WebParam(name = "cityName") String cityName, @WebParam(name = "countryName") String countryName) {
        //TODO write your implementation code here:
                String mensaje;
        mensaje=getWeather(cityName, countryName);
        this.dateTime=ObtainXmlData(mensaje, "Time");
        this.temperature=ObtainXmlData(mensaje, "Temperature");
        this.pressure=ObtainXmlData(mensaje, "Pressure");
        this.visibility=ObtainXmlData(mensaje, "Visibility");
        this.humidity=ObtainXmlData(mensaje, "RelativeHumidity");
 
    }

    private static String getWeather(java.lang.String cityName, java.lang.String countryName) {
        net.webservicex.GlobalWeather service = new net.webservicex.GlobalWeather();
        net.webservicex.GlobalWeatherSoap port = service.getGlobalWeatherSoap();
        return port.getWeather(cityName, countryName);
    }
    
    @WebMethod(operationName = "getTemperature")
    public String getTemperature() {
        //TODO write your implementation code here:
        return this.temperature;
    }
    
    @WebMethod(operationName = "getPressure")
    public String getPressure() {
        //TODO write your implementation code here:
        return this.pressure;
    }
    
    @WebMethod(operationName = "getHumidity")
    public String getHumidity() {
        //TODO write your implementation code here:
        return this.humidity;
    }
    
    @WebMethod(operationName = "getVisibility")
    public String getVisibility() {
        //TODO write your implementation code here:
        return this.visibility;
    }    
    
    @WebMethod(operationName = "getDateTime")
    public String getDateTime() {
        //TODO write your implementation code here:
        return this.dateTime;
    }
}
