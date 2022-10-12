# Context

Gradle 8.0 (still in Alpha) will drop the support to Gradle Transformation API, which seems to be heavily used by the "adeum" AppDynamics Gradle plugin.
It is essential that AppDynamics migrates the plugin to the new standards as soon as possible, otherwise it will block Android builds to migrate to the new version of Gradle within the next few months.

*Official announcement can be found here:* https://android-developers.googleblog.com/2022/10/prepare-your-android-project-for-agp8-changes.html

## How to see the error

Just run `./gradlew build`, it should show an error similar to:

```
FAILURE: Build failed with an exception.

* Where:
Build file 'AppDAgp8/app/build.gradle' line: 4

* What went wrong:
An exception occurred applying plugin request [id: 'adeum', artifact: 'com.appdynamics:appdynamics-gradle-plugin:21.6.0']
> Failed to apply plugin 'adeum'.
   > API 'android.registerTransform' is removed.
     
     For more information, see https://developer.android.com/studio/releases/gradle-plugin-api-updates#transform-api.
     To determine what is calling android.registerTransform, use -Pandroid.debug.obsoleteApi=true on the command line to display more information.
```


## Additional information

- As of the time writing (Oct/2022) the official version of AGP is 7.3.0. This version will give a warning, similar to:
```
> Configure project :app
WARNING:API 'android.registerTransform' is obsolete.
It will be removed in version 8.0 of the Android Gradle plugin.
The Transform API is removed to improve build performance. Projects that use the
Transform API force the Android Gradle plugin to use a less optimized flow for the
build that can result in large regressions in build times. It’s also difficult to
use the Transform API and combine it with other Gradle features; the replacement
APIs aim to make it easier to extend the build without introducing performance or
correctness issues.

There is no single replacement for the Transform API—there are new, targeted
APIs for each use case. All the replacement APIs are in the
`androidComponents {}` block.

```

- You can see that by replacing the version `8.0.0-alpha03` with `7.3.0` in `./build.gradle.kts`
- This project won't open in stable release of Android Studio, given it is not yet compatible, but it can run via terminal
- The current version of AGP (7.3.0) already support the new methodologies, so the plugin can be migrated before AGP 8 becomes production ready