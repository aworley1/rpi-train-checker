package io.github.aworley1.rpi_train_checker

fun getDepBoardWithDetailsRequestGenerator(accessToken: String, departureStation: String, destinationStation: String) =
        """<soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:typ="http://thalesgroup.com/RTTI/2013-11-28/Token/types" xmlns:ldb="http://thalesgroup.com/RTTI/2017-10-01/ldb/">
   <soap:Header>
      <typ:AccessToken>
         <typ:TokenValue>${accessToken}</typ:TokenValue>
      </typ:AccessToken>
   </soap:Header>
   <soap:Body>
      <ldb:GetDepBoardWithDetailsRequest>
         <ldb:numRows>9</ldb:numRows>
         <ldb:crs>${departureStation}</ldb:crs>
         <ldb:filterCrs>${destinationStation}</ldb:filterCrs>
         <ldb:filterType>to</ldb:filterType>
         <ldb:timeOffset>0</ldb:timeOffset>
         <ldb:timeWindow>120</ldb:timeWindow>
      </ldb:GetDepBoardWithDetailsRequest>
   </soap:Body>
</soap:Envelope>"""