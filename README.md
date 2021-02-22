# WeatherApp
                                                        \

Flow:
•	User open “Weather App”
•	User will enter city name and press lookup button
•	Retrofit hit API Call  “https://api.openweathermap.org/ ”  with end point “data/2.5/forcast?q=cityname&appid”
•	After getting data from api application store data in Room Database and populate on RecyclerView with the help of Live Data Binding , ViewModel and Kotlin Coroutines.
•	If there is no internet connection or  API failed data will  load from room database
•	When user click on any list item the detail fragment will open and show all the detail of weather

Architecture:
•	MVVM
•	Architecture Components

Tools:
•	Android Studio 4.1.2
•	Source Tree 3.4.2


Language:
•	Kotlin

Screens:
•	Enter City
•	Weather List
•	Weather Detail


Libraries:
•	Retrofit (Network/Api Calling)
•	Room Database( Cache Data)
•	Kotlin Coroutines(Threading and Async Task)
•	SDP (All Screen Size)
•	Constraint Layout (Screen Designing)
•	Live Data Binding
•	LifeCycle View Model
•	Navigation Component(Fragment Navigation)
•	Junit4 (Unit Testing)
•	Truth Library(Test Assertion)



