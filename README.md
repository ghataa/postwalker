# postwalker

Postwalker is a dummy application for experimenting with different software architectures on Android. The application loads posts either from a remote source - the [JSONPlaceholder](http://jsonplaceholder.typicode.com/) - or from the local database if server is not available for any reason.

There are two gradle flavors available in the project. One for working and/or testing with mock data (MOCK) and the other for the production environment (PROD).

## MVP - Java

On this branch you can find a version which uses the MVP ([Model–View–Presenter](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter)) concept.

Project uses the following technologies and libraries:
- Java
- [RxJava 2](https://github.com/ReactiveX/RxJava/tree/2.x)
- [Dagger 2](https://dagger.dev/)
- [Retrofit 2](https://square.github.io/retrofit/)
- [Butterknife](http://jakewharton.github.io/butterknife/)
- [Room](https://developer.android.com/training/data-storage/room/index.html)

## What's coming next?

- More screens
- More advanced UI (design and animations)
- UI and unit tests
