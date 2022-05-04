Random Users
=====

<p align="center">
  <img src="./app/src/main/res/mipmap-xhdpi/ic_launcher_round.png" alt="Random Users" />
</p>

Features
------------

This projects has 4 modules to separate each layer, using a Clean Architecture approach. I have used 
MVI as design system with Jetpack Compose. All modules all fully tested using Unit tests, UI tests 
and MockWebServer for server calls.

#### Data
This module holds all network calls, using an implementation of a repository pattern. In this 
implementation we could add a local data source to store data into local storage. The remote data 
source has an API service to make server calls.

We can also find all data entities that are converted to domain business models.

#### Domain
This module holds business logic, defined by use cases that calls repository interfaces (implemented 
in data module), or another use cases. Here also are all the models for this logic, with operation 
functions needed by use cases.

#### Presentation
In this module we can find the stores, that using a MVI design pattern provides a clean way to 
connect UI app and domain modules, using states and actions. Navigation actions are modeled using 
navigator interfaces, implemented in app module.

Store file holds this MVI logic. In this class we have a state handler, which initially launch an 
empty state. All states defined can have a side effect, for example launching a use case, track an
event or navigating to another screen. Any state change triggers a view render.

Here we also have an AsynchronyManager to hold all threading logic.

#### App
This module has all Android app related code. All screens are built using Jetpack Compose, and they 
communicate to presentation module using stores.
We have Loading and Error screens to show depending on screen status.

We also have an UI package to hold the design system and custom views or screens that can be reused 
across the app.

Possible improvements
------------
- Add pagination in dashboard, allowing user to load more users when reaching bottom of screen.
- Add offline support, using local storage to always show results to the user.
- Phone, email and location operations can be used in separate managers for better maintenance.
- Update CI/CD to make automatic processes of deployment.
- Prepare app to be released, adding ProGuard rules and signing info.

Libraries used
------------

- Retrofit, OkHttp and Moshi to make server calls, logging and to model server responses.
- Coroutines for threading.
- Dagger2 and Javax Inject for dependency injection.
- Arrow, to have some functional programming utilities.
- JUnit, MockK, CoroutinesTest, ComposeJUnit and MockWebServer for testing.
- Jetpack Compose and Material for UI.