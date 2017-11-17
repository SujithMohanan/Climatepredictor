# Climate Predictor

Climate predictor is a java component that will predict the weather factors (temperature, pressure, humidity and weather condition) corresponds to location based on historic data.

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
Sydney|-33.87,151.21,24.54|2017-11-17T22:00:00Z|Normal|+22.43|1013.94|62.62
Melbourne|-37.82,144.98,7.5|2017-11-17T22:00:00Z|Show|+14.71|1011.38|77.79
Perth|-31.95,115.86,16.38|2017-11-17T22:00:00Z|Normal|+24.79|1017.05|51.64
GoldCoast|-28.02,153.4,1.23|2017-11-17T22:00:00Z|Normal|+25.89|1013.77|60.17
Adelaide|-34.92,138.62,40.47|2017-11-17T22:00:00Z|Show|+19.32|1013.22|49.07
Canberra|-35.28,149.13,566.76|2017-11-17T22:00:00Z|Show|+18.18|1012.45|64.25
Tuggeranong|-35.42,149.09,586.03|2017-11-17T22:00:00Z|Show|+17.51|1012.26|64.66
Penrith|-33.75,150.7,27.25|2017-11-17T22:00:00Z|Normal|+22.18|0.0|67.98
Newcastle|-32.89,151.7,29.44|2017-11-17T22:00:00Z|Normal|+22.27|0.0|76.31
Brisbane|-27.47,153.03,26.54|2017-11-17T22:00:00Z|Normal|+27.19|1013.12|50.23
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
