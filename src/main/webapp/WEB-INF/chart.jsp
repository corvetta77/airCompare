<!-- chart.jsp-->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
   <script type="text/javascript">
      window.onload = function() {
         var dps = [[], []];
         var chart = new CanvasJS.Chart("chartContainer", {
            theme: "light2", // "light1", "dark1", "dark2"
            animationEnabled: true,
            title: {
               text: "Airly CAQI comparison"
            },
            axisX: {
               valueFormatString: "MMM"
            },
            axisY: {
               title: "Airly Index",
               suffix: " "
            },
            legend: {
               cursor: "pointer",
               itemclick: toggleDataSeries
            },
            data: [{
               type: "line",
               name: "Siercza",
               xValueType: "dateTime",
               xValueFormatString: "MMM",
               yValueFormatString: "## ",
               toolTipContent: "<span style=\"color:#51CDA0\">{name}</span>",
               dataPoints: dps[0]
            },{
                  type: "line",
                  name: "Hoffmanowej",
                  markerType: "triangle",
                  xValueType: "dateTime",
                  xValueFormatString: "MMM",
                  yValueFormatString: "## ",
               toolTipContent: "<span style=\"color:#6D78AD\">{name}</span>",
               dataPoints: dps[1]
               }]
         });

         function toggleDataSeries(e) {
            if (typeof (e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
               e.dataSeries.visible = false;
            }
            else {
               e.dataSeries.visible = true;
            }
            chart.render();
         }

         var xValue;
         var yValue;


         <c:forEach items="${dataPointsList}" var="dataPoints" varStatus="loop">
         <c:forEach items="${dataPoints}" var="dataPoint">
         xValue = parseInt("${dataPoint.x}");
         yValue = parseFloat("${dataPoint.y}");
         dps[parseInt("${loop.index}")].push({
            x: xValue,
            y: yValue
         });
         </c:forEach>
         </c:forEach>

         chart.render();

      }
   </script>
</head>
<body>
<div id="chartContainer" style="height: 370px; width: 100%;"></div>
<script src="http://canvasjs.com/assets/script/canvasjs.min.js"></script>
</body>
</html>