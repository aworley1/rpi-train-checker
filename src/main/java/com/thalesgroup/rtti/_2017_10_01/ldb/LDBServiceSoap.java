
package com.thalesgroup.rtti._2017_10_01.ldb;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import com.thalesgroup.rtti._2013_11_28.token.types.AccessToken;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "LDBServiceSoap", targetNamespace = "http://thalesgroup.com/RTTI/2017-10-01/ldb/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    com.thalesgroup.rtti._2017_10_01.ldb.ObjectFactory.class,
    com.thalesgroup.rtti._2012_01_13.ldb.types.ObjectFactory.class,
    com.thalesgroup.rtti._2013_11_28.token.types.ObjectFactory.class,
    com.thalesgroup.rtti._2015_11_27.ldb.types.ObjectFactory.class,
    com.thalesgroup.rtti._2016_02_16.ldb.types.ObjectFactory.class,
    com.thalesgroup.rtti._2017_10_01.ldb.commontypes.ObjectFactory.class,
    com.thalesgroup.rtti._2017_10_01.ldb.types.ObjectFactory.class,
    com.thalesgroup.rtti._2007_10_10.ldb.commontypes.ObjectFactory.class
})
public interface LDBServiceSoap {


    /**
     * 
     * @param accessToken
     * @param parameters
     * @return
     *     returns com.thalesgroup.rtti._2017_10_01.ldb.StationBoardResponseType
     */
    @WebMethod(operationName = "GetDepartureBoard", action = "http://thalesgroup.com/RTTI/2012-01-13/ldb/GetDepartureBoard")
    @WebResult(name = "GetDepartureBoardResponse", targetNamespace = "http://thalesgroup.com/RTTI/2017-10-01/ldb/", partName = "parameters")
    public StationBoardResponseType getDepartureBoard(
        @WebParam(name = "GetDepartureBoardRequest", targetNamespace = "http://thalesgroup.com/RTTI/2017-10-01/ldb/", partName = "parameters")
        GetBoardRequestParams parameters,
        @WebParam(name = "AccessToken", targetNamespace = "http://thalesgroup.com/RTTI/2013-11-28/Token/types", header = true, partName = "AccessToken")
        AccessToken accessToken);

    /**
     * 
     * @param accessToken
     * @param parameters
     * @return
     *     returns com.thalesgroup.rtti._2017_10_01.ldb.StationBoardResponseType
     */
    @WebMethod(operationName = "GetArrivalBoard", action = "http://thalesgroup.com/RTTI/2012-01-13/ldb/GetArrivalBoard")
    @WebResult(name = "GetArrivalBoardResponse", targetNamespace = "http://thalesgroup.com/RTTI/2017-10-01/ldb/", partName = "parameters")
    public StationBoardResponseType getArrivalBoard(
        @WebParam(name = "GetArrivalBoardRequest", targetNamespace = "http://thalesgroup.com/RTTI/2017-10-01/ldb/", partName = "parameters")
        GetBoardRequestParams parameters,
        @WebParam(name = "AccessToken", targetNamespace = "http://thalesgroup.com/RTTI/2013-11-28/Token/types", header = true, partName = "AccessToken")
        AccessToken accessToken);

    /**
     * 
     * @param accessToken
     * @param parameters
     * @return
     *     returns com.thalesgroup.rtti._2017_10_01.ldb.StationBoardResponseType
     */
    @WebMethod(operationName = "GetArrivalDepartureBoard", action = "http://thalesgroup.com/RTTI/2012-01-13/ldb/GetArrivalDepartureBoard")
    @WebResult(name = "GetArrivalDepartureBoardResponse", targetNamespace = "http://thalesgroup.com/RTTI/2017-10-01/ldb/", partName = "parameters")
    public StationBoardResponseType getArrivalDepartureBoard(
        @WebParam(name = "GetArrivalDepartureBoardRequest", targetNamespace = "http://thalesgroup.com/RTTI/2017-10-01/ldb/", partName = "parameters")
        GetBoardRequestParams parameters,
        @WebParam(name = "AccessToken", targetNamespace = "http://thalesgroup.com/RTTI/2013-11-28/Token/types", header = true, partName = "AccessToken")
        AccessToken accessToken);

    /**
     * 
     * @param accessToken
     * @param parameters
     * @return
     *     returns com.thalesgroup.rtti._2017_10_01.ldb.ServiceDetailsResponseType
     */
    @WebMethod(operationName = "GetServiceDetails", action = "http://thalesgroup.com/RTTI/2012-01-13/ldb/GetServiceDetails")
    @WebResult(name = "GetServiceDetailsResponse", targetNamespace = "http://thalesgroup.com/RTTI/2017-10-01/ldb/", partName = "parameters")
    public ServiceDetailsResponseType getServiceDetails(
        @WebParam(name = "GetServiceDetailsRequest", targetNamespace = "http://thalesgroup.com/RTTI/2017-10-01/ldb/", partName = "parameters")
        GetServiceDetailsRequestParams parameters,
        @WebParam(name = "AccessToken", targetNamespace = "http://thalesgroup.com/RTTI/2013-11-28/Token/types", header = true, partName = "AccessToken")
        AccessToken accessToken);

    /**
     * 
     * @param accessToken
     * @param parameters
     * @return
     *     returns com.thalesgroup.rtti._2017_10_01.ldb.StationBoardWithDetailsResponseType
     */
    @WebMethod(operationName = "GetDepBoardWithDetails", action = "http://thalesgroup.com/RTTI/2015-05-14/ldb/GetDepBoardWithDetails")
    @WebResult(name = "GetDepBoardWithDetailsResponse", targetNamespace = "http://thalesgroup.com/RTTI/2017-10-01/ldb/", partName = "parameters")
    public StationBoardWithDetailsResponseType getDepBoardWithDetails(
        @WebParam(name = "GetDepBoardWithDetailsRequest", targetNamespace = "http://thalesgroup.com/RTTI/2017-10-01/ldb/", partName = "parameters")
        GetBoardRequestParams parameters,
        @WebParam(name = "AccessToken", targetNamespace = "http://thalesgroup.com/RTTI/2013-11-28/Token/types", header = true, partName = "AccessToken")
        AccessToken accessToken);

    /**
     * 
     * @param accessToken
     * @param parameters
     * @return
     *     returns com.thalesgroup.rtti._2017_10_01.ldb.StationBoardWithDetailsResponseType
     */
    @WebMethod(operationName = "GetArrBoardWithDetails", action = "http://thalesgroup.com/RTTI/2015-05-14/ldb/GetArrBoardWithDetails")
    @WebResult(name = "GetArrBoardWithDetailsResponse", targetNamespace = "http://thalesgroup.com/RTTI/2017-10-01/ldb/", partName = "parameters")
    public StationBoardWithDetailsResponseType getArrBoardWithDetails(
        @WebParam(name = "GetArrBoardWithDetailsRequest", targetNamespace = "http://thalesgroup.com/RTTI/2017-10-01/ldb/", partName = "parameters")
        GetBoardRequestParams parameters,
        @WebParam(name = "AccessToken", targetNamespace = "http://thalesgroup.com/RTTI/2013-11-28/Token/types", header = true, partName = "AccessToken")
        AccessToken accessToken);

    /**
     * 
     * @param accessToken
     * @param parameters
     * @return
     *     returns com.thalesgroup.rtti._2017_10_01.ldb.StationBoardWithDetailsResponseType
     */
    @WebMethod(operationName = "GetArrDepBoardWithDetails", action = "http://thalesgroup.com/RTTI/2015-05-14/ldb/GetArrDepBoardWithDetails")
    @WebResult(name = "GetArrDepBoardWithDetailsResponse", targetNamespace = "http://thalesgroup.com/RTTI/2017-10-01/ldb/", partName = "parameters")
    public StationBoardWithDetailsResponseType getArrDepBoardWithDetails(
        @WebParam(name = "GetArrDepBoardWithDetailsRequest", targetNamespace = "http://thalesgroup.com/RTTI/2017-10-01/ldb/", partName = "parameters")
        GetBoardRequestParams parameters,
        @WebParam(name = "AccessToken", targetNamespace = "http://thalesgroup.com/RTTI/2013-11-28/Token/types", header = true, partName = "AccessToken")
        AccessToken accessToken);

    /**
     * 
     * @param accessToken
     * @param parameters
     * @return
     *     returns com.thalesgroup.rtti._2017_10_01.ldb.DeparturesBoardResponseType
     */
    @WebMethod(operationName = "GetNextDepartures", action = "http://thalesgroup.com/RTTI/2015-05-14/ldb/GetNextDepartures")
    @WebResult(name = "GetNextDeparturesResponse", targetNamespace = "http://thalesgroup.com/RTTI/2017-10-01/ldb/", partName = "parameters")
    public DeparturesBoardResponseType getNextDepartures(
        @WebParam(name = "GetNextDeparturesRequest", targetNamespace = "http://thalesgroup.com/RTTI/2017-10-01/ldb/", partName = "parameters")
        GetDeparturesRequestParams parameters,
        @WebParam(name = "AccessToken", targetNamespace = "http://thalesgroup.com/RTTI/2013-11-28/Token/types", header = true, partName = "AccessToken")
        AccessToken accessToken);

    /**
     * 
     * @param accessToken
     * @param parameters
     * @return
     *     returns com.thalesgroup.rtti._2017_10_01.ldb.DeparturesBoardWithDetailsResponseType
     */
    @WebMethod(operationName = "GetNextDeparturesWithDetails", action = "http://thalesgroup.com/RTTI/2015-05-14/ldb/GetNextDeparturesWithDetails")
    @WebResult(name = "GetNextDeparturesWithDetailsResponse", targetNamespace = "http://thalesgroup.com/RTTI/2017-10-01/ldb/", partName = "parameters")
    public DeparturesBoardWithDetailsResponseType getNextDeparturesWithDetails(
        @WebParam(name = "GetNextDeparturesWithDetailsRequest", targetNamespace = "http://thalesgroup.com/RTTI/2017-10-01/ldb/", partName = "parameters")
        GetDeparturesRequestParams parameters,
        @WebParam(name = "AccessToken", targetNamespace = "http://thalesgroup.com/RTTI/2013-11-28/Token/types", header = true, partName = "AccessToken")
        AccessToken accessToken);

    /**
     * 
     * @param accessToken
     * @param parameters
     * @return
     *     returns com.thalesgroup.rtti._2017_10_01.ldb.DeparturesBoardResponseType
     */
    @WebMethod(operationName = "GetFastestDepartures", action = "http://thalesgroup.com/RTTI/2015-05-14/ldb/GetFastestDepartures")
    @WebResult(name = "GetFastestDeparturesResponse", targetNamespace = "http://thalesgroup.com/RTTI/2017-10-01/ldb/", partName = "parameters")
    public DeparturesBoardResponseType getFastestDepartures(
        @WebParam(name = "GetFastestDeparturesRequest", targetNamespace = "http://thalesgroup.com/RTTI/2017-10-01/ldb/", partName = "parameters")
        GetDeparturesRequestParams parameters,
        @WebParam(name = "AccessToken", targetNamespace = "http://thalesgroup.com/RTTI/2013-11-28/Token/types", header = true, partName = "AccessToken")
        AccessToken accessToken);

    /**
     * 
     * @param accessToken
     * @param parameters
     * @return
     *     returns com.thalesgroup.rtti._2017_10_01.ldb.DeparturesBoardWithDetailsResponseType
     */
    @WebMethod(operationName = "GetFastestDeparturesWithDetails", action = "http://thalesgroup.com/RTTI/2015-05-14/ldb/GetFastestDeparturesWithDetails")
    @WebResult(name = "GetFastestDeparturesWithDetailsResponse", targetNamespace = "http://thalesgroup.com/RTTI/2017-10-01/ldb/", partName = "parameters")
    public DeparturesBoardWithDetailsResponseType getFastestDeparturesWithDetails(
        @WebParam(name = "GetFastestDeparturesWithDetailsRequest", targetNamespace = "http://thalesgroup.com/RTTI/2017-10-01/ldb/", partName = "parameters")
        GetDeparturesRequestParams parameters,
        @WebParam(name = "AccessToken", targetNamespace = "http://thalesgroup.com/RTTI/2013-11-28/Token/types", header = true, partName = "AccessToken")
        AccessToken accessToken);

}
