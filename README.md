# WallUp ⛰

Wallpaper finder and downloader app Demonstrate the Jetpack Compose UI
using [Unsplash](https://unsplash.com/developers) API *Made with ❤️
by [Enes](https://github.com/Enes-Kayiklik)*

## UI Design 🎨

***Thanks to [Rian Hamidjoyo](https://dribbble.com/rseth)
for [Wallpaper App UI Design](https://dribbble.com/shots/14808564-Wallpaper-app)***

## Screens 🖼

Home | Detail | Bookmark | Collection --- | --- | --- |
![](https://github.com/Enes-Kayiklik/Wall-Up/blob/master/screenshots/home_screen.jpg)
| ![](https://github.com/Enes-Kayiklik/Wall-Up/blob/master/screenshots/detail_screen.jpg)
| ![](https://github.com/Enes-Kayiklik/Wall-Up/blob/master/screenshots/bookmark_screen.jpg)
| ![](https://github.com/Enes-Kayiklik/Wall-Up/blob/master/screenshots/collection_screen.jpg)

## Prerequisites

- #### API Key

To run the application, an API key from [Unsplash](https://unsplash.com/developers) should be
supplied.

inside local.properties file add this line and Rebuild project.
`` apiKey="Your API Key Here" ``

How to store API key? - [Stackoverflow](https://stackoverflow.com/a/70244128/13447094)

## Architecture 🧰

- Single Activity No Fragment
- MVVM Pattern

**View:** Renders UI and delegates user actions to ViewModel

**ViewModel:** Can have simple UI logic but most of the time just gets the data from UseCase.

**UseCase:** Contains all business rules and they written in the manner of single responsibility
principle.

**Repository:** Single source of data. Responsible to get data from one or more data sources.

**For more information you can
check [Guide to app architecture](https://developer.android.com/jetpack/guide?gclid=CjwKCAiA_omPBhBBEiwAcg7smXcfbEYneoLKFD_4Tyw0OgVQkpZL_XIr5TPXT0mncuQhgDIBBvLhbBoCEx0QAvD_BwE&gclsrc=aw.ds#mobile-app-ux)**

<img src="/screenshots/architecture_diagram.png" width="500">

## Libraries 🛠

- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android
  development.
- [Ktor Client](https://ktor.io/docs/client.html) - Ktor includes a multiplatform asynchronous HTTP
  client, which allows you to make requests and handle responses, extend its functionality with
  plugins (formerly known as features), such as authentication, JSON serialization, and so on. In
  this topic, we'll take an overview of the client - from setting it up to making requests and
  installing plugins.
- [Jetpack Compose](https://developer.android.com/jetpack/compose) - Jetpack Compose is Android’s
  modern toolkit for building native UI.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For asynchronous
  and more..
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) -
  Collection of libraries that help you design robust, testable, and maintainable apps.
    - [Flows](https://developer.android.com/kotlin/flow) - Data objects that notify views when the
      underlying database changes.
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores
      UI-related data that isn't destroyed on UI changes.
    - [Room](https://developer.android.com/topic/libraries/architecture/room) - Database Library
- [Compose Destinations](https://github.com/raamcosta/compose-destinations) - A KSP library that
  processes annotations and generates code that uses Official Jetpack Compose Navigation under the
  hood. It hides from you the non-type-safe and boilerplate code you would otherwise have to write.
- [Material Components for Android](https://github.com/material-components/material-components-android)
  - Modular and customizable Material Design UI components for Android.
- [Dagger - Hilt](https://dagger.dev/hilt/) - Dependency Injection Framework
- [Coil](https://coil-kt.github.io/coil/compose/) - Image loader library.

## Package Structure 🗂

    .
    .
    .
    ├── di                    # Hilt Dependency Injection
    ├── feature_bookmark
    ├── feature_collection
    ├── feature_detail
    ├── feature_home
    ├── feature_splash
    |    ├── data             # DTOs and repositories implementation
    |    |
    |    ├── domain           # Models, repositories and use cases
    |    | 
    |    └── presentation     # UI Components
    ├── ui                    
    |    ├── theme            # Compose Theme
    |    |               
    |    └── animation        # Animation Utils
    |
    ├── utils                 # Useful classes
    |
    └── WallUpApp.kt          # @HiltAndroidApp

## Contribute 🤝

If you want to contribute to this app, you're always welcome!

## License 📄

```
Copyright 2022 Enes-Kayiklik

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```