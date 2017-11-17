# Climate Predictor

Climate predictor is a java component that will predict the weather factors (temperature, pressure, humidity and weather condition) corresponds to a location, based on historical data.

## Table of contents
 
| Serial No | Title |
| ------ | ------ |
| 1 | Prerequisites |
| 2 | Configuring enviroment |
| 3 | Workflow |
| 4 | Running the component |
| 5 | Ouput |
| 6 | Major classes |
| 7 | Author |
| 8 | Version |


### 1.Prerequisites

 - JDK 1.7 or higher (JAVA_HOME and PATH set) for compile and execution
 - Apache Maven 3.3 or higher (MVN_HOME and PATH set) for build
 - Git installed
 - Internet Connection

### 2.Configuring enviroment
 - ###### Install java 

```sh
Install java 1.7 or higher in machine.
Ensure Java is installed by using command.
$ 'java -version'
If installed correctly, this command will display the flavour and version of installed java.
```

 - ###### Install Maven

```sh
Download maven http://maven.apache.org/ (latest version)
$ 'unzip apache-maven-3.x.x-bin.zip'
Add apache-maven-3.x.x/bin to PATH variable 
Ensure maven is properly installed by using command.
$ 'mvn -v' or $ 'mvn --version'
```
 - ###### Install Git

```sh
Install git in your machine.
Ensure git is installed by using command.
$ 'git --version'
```
### 3.Workflow

The component reads a date which must be entered by user. Then it starts to read the configured cities in inputData.csv. After reading the input data, component calls 3 google API for getting location based information

* [Geocoding API](https://developers.google.com/maps/documentation/geocoding/intro)
* [Elevation API](https://developers.google.com/maps/documentation/elevation/start)
* [Time Zone API](https://developers.google.com/maps/documentation/timezone/intro)

After fetching those location data, it calls  [Bureau of Meteorology](http://www.bom.gov.au/climate/dwo/) for historic data (temperature, pressure & relative humidity).

With those data Prediction of weather parameters is performed based on ARIMA Model. The result from that  data will be a group of prediction data. From that list, the component will select nearest prediction value compared to last year's value.

##### Supported locations 
```sh
Sydney
Melbourne
Perth
GoldCoast
Adelaide
Canberra
Tuggeranong
Penrith
Newcastle
Brisbane
```


### 4.Running the component

*Clone the repository*

```sh
$ git clone https://github.com/SujithMohanan/climate-Predictor
```
*Build the project*
Navigate to the root directory of the project and build the project using the below command:

```sh
$ mvn clean install
```

*Run the application*
Move to target folder and execute the command 

```sh
$ java -jar target/Climatepredictor.jar
```

### 4.Output

```sh
Sydney|-33.87,151.21,24.54|2018-06-18T06:00:00Z|Rain|+10.76|62.34|1024.09
Melbourne|-37.82,144.98,7.5|2018-06-18T06:00:00Z|Snow|+8.4|79.19|1029.11
Perth|-31.95,115.86,16.38|2018-06-18T06:00:00Z|Rain|+12.34|22.64|1018.02
GoldCoast|-28.02,153.4,1.23|2018-06-18T06:00:00Z|Snow|+17.84|88.39|1022.39
Adelaide|-34.92,138.62,40.47|2018-06-18T06:00:00Z|Snow|+9.33|91.09|1032.45
Canberra|-35.28,149.13,566.76|2018-06-18T06:00:00Z|Snow|+1.49|90.86|1026.98
Tuggeranong|-35.42,149.09,586.03|2018-06-18T06:00:00Z|Snow|+2.92|135.86|1026.74
Penrith|-33.75,150.7,27.25|2018-06-18T06:00:00Z|Snow|+8.55|96.02|0.0
Newcastle|-32.89,151.7,29.44|2018-06-18T06:00:00Z|Snow|+10.56|84.93|0.0
Brisbane|-27.47,153.03,26.54|2018-06-18T06:00:00Z|Snow|+16.97|78.65|1022.79
```



### 6.Major classes

##### App.java
>The Class App is entry point of the application. This class will prompt user to enter a future date in the form of "yyyy-MM-dd HH". Then two >months historic (previous years) data will collected and predictionsare done with the help of Algorithm.

##### HistoryLoader.java
>The Class for getting history data about weather. Data is collected from Bureau of Meteorology "http://www.bom.gov.au/climate/dwo/"

##### LocationLoader.java
>The Class to collect location specific informations using google's various API's.

##### TimeSeriesAlgorithm.java
>The Class that describes TimeSeriesAlgorithm which is based on ARIMA modelling. This class has 2 methods 
> - predictValue- which predicts the weather parameter value using ARIMA.
> - getcondition- identifies the weather condition.

### 7.Author
Sujith M


### 8.Version
Initial version
0.0.1
