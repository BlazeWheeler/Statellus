

# Instructions 
- Install Android Studio

## Universal 
- Install python3 <a href="https://www.python.org/downloads/">Here <a/><br> 
#
### For MAC: 
- **Install with Hombrew**

 * Enter Command 
  ```sh
  brew install python3
  ```
# 

- Open *Statellus* **(Project Folder)** in Android Studio 
- Create Emulator 
	* Open **Device Manager** in Android Studio 
	* Create New Device (press the plus in the top left corner) 
	* Select device from the phone category 
	* Select API Level of 30 or above 
	* Install SDK 
	* Select Finish
- Build & Run 

# Generate Documentation Using <a href="https://github.com/Kotlin/dokka">Dokka <a/><br> 
 * Open Terminal in Android Studio 
 	* **For Windows:** use gradlew.bat
	* **For Linux or Mac:** use ./gradlew
	* Enter command 
* 	```console
	 ./gradlew dokkaHtml	
 	```
	* You can find the generated docs here: Statellus/app/build/dokka
	##



	|Mac Command |Windows Command|EXPECTED RESULTS| Destination|
	|---| ----------------|-------|----|
	| ./gradlew dokkaHtml|gradlew.bat dokkaHtml | HTML Docs| app\build\dokka
	| ./gradlew dokkaGfm|gradlew.bat dokkaGfm | GFM Docs.|app\build\dokka
	| ./gradlew dokkaJavadoc|gradlew.bat Javadoc | Generate Javadoc.|app\build\dokka




# Unit Testing 
* Testing files are located at: 
	* Statellus/app/src/test/java/com/blazewheeler/statellus
* To Run Tests you can either 
	* 1.) Right click the test file inside the project manger in androd studio. 
	* 2.) Select run with coverage.	


# Dependencies Information

- <a href="https://github.com/Kotlin/dokka">Dokka <a/><br> 
	* Dokka is an API documentation engine for Kotlin.

	* Just like Kotlin itself, Dokka supports mixed-language projects. It understands Kotlin's KDoc comments and Java's Javadoc comments.

	* Dokka can generate documentation in multiple formats, including its own modern HTML format, multiple flavors of Markdown, and Java's Javadoc HTML.
#

- <a href="https://github.com/google/guava/tree/master">Guava <a/><br> 
	* By replacing the existing library classes with those from guava, you can reduce the amount of code you need to maintain
	* It provides Optimized, thoroughly tested math utilities not provided by the JDK
	* It provides powerful collection utilities, for common operations not provided in java.util.Collections
#

- <a href="https://github.com/google/guava/tree/master">Parserng-android <a/><br>
	* Andorid Fork of <a href="https://github.com/google/guava/tree/master">Parserng<a/><br>

	*  <a href="https://github.com/google/guava/tree/master">Parserng<a/><br>
		* a powerful open-source math tool that parses and evaluates algebraic expressions and also knows how to handle a lot of mathematical expressions. Including: Arithmetic, statistical, Calculus Operations etc.  
#

-  <a href="https://github.com/noties/jlatexmath-android">jlatexmath-android<a/><br>
	* Andorid Fork of <a href="https://github.com/google/guava/tree/master">jlatexmath<a/><br>
	*  <a href="https://github.com/google/guava/tree/master">jlatexmath<a/><br>
		* JLaTeXMath is a Java library. Its main purpose is to display mathematical formulas written in LaTeX. JLaTeXMath is the best Java library to display LaTeX code.
#
- <a href="https://github.com/PhilJay/MPAndroidChart">MPchart <a/><br> 
	* MP chart is the best **free** chart library for data visualization in android 
#

- <a href="https://github.com/puskal-khadka/OnionDiagram">Onion Diagram <a/><br> 
    * Android library for onion diagram i.e also refered as stacked vann diagram
#



- <a href="https://github.com/google/guava/tree/master">Chaquopy <a/><br> 
	* Chaquopy provides everything you need to include Python components in an Android app, including: Full integration with Android Studio’s standard Gradle build system, Simple APIs for calling Python code from Java/Kotlin, and vice versa.
	* **Use chaquopy when MPchart is not sufficent**
### Python Libraries 
- <a href="https://matplotlib.org/">Mathplotlib <a/><br> 
	* Matplotlib is a comprehensive library for creating static, animated, and interactive visualizations in Python. Matplotlib makes easy things easy and hard things possible.
#
- <a href="http://stemgraphic.org/doc/intro.html">Stemgraphic <a/><br> 
	* Python package for making graphical stem-and-leaf plots. Within the jupyter notebook or ipython environment, it can also be used with other programming languages. Beyond pure visualization, stemgraphic also does some amount of automated data wrangling. It supports any size of data and can generate ready for print plots.
#
