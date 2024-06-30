
## Project Structure 

/app<br>
&emsp;|-- /src<br>
&emsp;&emsp;|-- /main<br>
&emsp;&emsp;&emsp;|-- /java<br>
&emsp;&emsp;&emsp;&emsp;|-- /com.blazewheeler.statellus<br>
&emsp;&emsp;&emsp;&emsp;&emsp;|-- /java<br>
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;|-- /View<br>
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;|--/java views<br>
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;|--/ViewModel<br>
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;|--/java ViewModels<br>
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;|--/Model<br>
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;|--/Model(Java Calculations Logic)<br>
&emsp;&emsp;&emsp;&emsp;&emsp;|-- /Kotlin<br>
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;|-- /View<br>
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;|--/Kotlin views<br>
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;|--/ViewModel<br>
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;|--/Kotlin ViewModels<br>
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;|--/Model<br>
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;|--/Model(Koglin Calculations Logic)<br>
&emsp;&emsp;&emsp;&emsp;&emsp;|-- /python<br>
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;|-- python modules<br>
|-- /res<br>
&emsp;|-- /values<br>
&emsp;&emsp;|-- strings.xml<br>
|-- /layout<br>
&emsp;|-- activity_main.xml<br>
|-- /drawable<br>
|-- /mipmap<br>
|-- AndroidManifest.xml<br>
|-- build.gradle<br>

#
The project uses a MVVM approach. MVVM - MODEL VIEW VIEWMODEL ensures each ViewModel, in the scope of this project is responsible for handling the UI-related logic for its associated View, *(i am referring to android activities)* and the associated Model contains the business logic and calculations, *(in the scope of this project the model will have calculations, and methods to format data to specfic API input requirements)* Python modules handle logic needed to use python. libraries.
#

# API LEVEL 
This application targets the andorid API LEVEl 34 with a MIN of 30. 

* The application is supported on 74.2% of all android devices. 
	* See <a href ="https://apilevels.com/"> Android API Levels</a> for more information.
#


## Mockup

![App Preview](img/TopLevelMockup.png)