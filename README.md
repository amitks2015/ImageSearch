#Image Search App - powered by Flickr API

This is an Android app which loads images from the Flickr API and display in a grid of size 3. It also
 implements pagination to support infinite scrolling. It implements MVVM architecture and also
 conforms with the principle of clean architecture.

 This app consists of following modules: -
 - Data - Networking
 - Domain - Models, Repository, Mapper & Paging DataSource
 - UI - Activity, View Model & Themes

 For simplicity, I've merged not used UseCase in domain module. Repository does that work for the
  app.

It uses following libraries to accomplish the above task:

Jetpack Compose - UI
Hilt - Dependency injection
Coil - Image loading
Paging3 - Pagination
Coroutine - Asynchronous operations
Retrofit - Networking
Mockk - Unit testing