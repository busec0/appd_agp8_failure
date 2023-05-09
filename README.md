# Context

Android Gradle Plugin 8+ requires AppDynamic Android (`adeum` plugin) version 23.4.1 (and over). The Gradle Transformation API has been removed.

The version **23.4.1** of AppDynamic fails when combined with Android `kapt`.
Apart from that, the new plugin seems to generate excessive warning logs.


## Problems

### 1. Failure to build when `disabledForBuildTypes` is used in combination with `kapt`

`app/build.gradle.kts`:
```kotlin
plugins {
    // (...)
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
    id("adeum")
}

adeum {
    disabledForBuildTypes = listOf("debug")
}
dependencies {
    implementation("com.google.dagger:hilt-android:2.44.2")
    kapt("com.google.dagger:hilt-android-compiler:2.44.2")
//    (...)
}
```

Causes:
```text
(...)
Caused by: com.android.tools.r8.utils.b: Type com.test.appdagp8.MainApplication$Companion is defined multiple times: <projectpath>/AppDAgp8/app/build/intermediates/classes/debug/transformDebugClassesWithAsm/dirs/transformDebugClassesWithAsm/dirs/com/test/appdagp8/MainApplication$Companion.class, <projectpath>/AppDAgp8/app/build/intermediates/classes/debug/transformDebugClassesWithAsm/dirs/com/test/appdagp8/MainApplication$Companion.class
        at com.android.tools.r8.utils.E2.a(R8_8.0.40_1caf5950b946297b5c46a21a695cd28795208d72fd17f5129543b31a15a067c2:21)
        at com.android.tools.r8.utils.E2.a(R8_8.0.40_1caf5950b946297b5c46a21a695cd28795208d72fd17f5129543b31a15a067c2:26)
        at com.android.tools.r8.utils.r2.a(R8_8.0.40_1caf5950b946297b5c46a21a695cd28795208d72fd17f5129543b31a15a067c2:44)
        at com.android.tools.r8.utils.r2.a(R8_8.0.40_1caf5950b946297b5c46a21a695cd28795208d72fd17f5129543b31a15a067c2:10)
        at java.base/java.util.concurrent.ConcurrentHashMap.merge(Unknown Source)
        at com.android.tools.r8.utils.r2.a(R8_8.0.40_1caf5950b946297b5c46a21a695cd28795208d72fd17f5129543b31a15a067c2:6)
        at com.android.tools.r8.graph.k4$a.e(R8_8.0.40_1caf5950b946297b5c46a21a695cd28795208d72fd17f5129543b31a15a067c2:7)
        at com.android.tools.r8.dex.c.a(R8_8.0.40_1caf5950b946297b5c46a21a695cd28795208d72fd17f5129543b31a15a067c2:58)
        at com.android.tools.r8.dex.c.a(R8_8.0.40_1caf5950b946297b5c46a21a695cd28795208d72fd17f5129543b31a15a067c2:9)
        at com.android.tools.r8.dex.c.a(R8_8.0.40_1caf5950b946297b5c46a21a695cd28795208d72fd17f5129543b31a15a067c2:8)
        at com.android.tools.r8.D8.a(R8_8.0.40_1caf5950b946297b5c46a21a695cd28795208d72fd17f5129543b31a15a067c2:29)
        at com.android.tools.r8.D8.d(R8_8.0.40_1caf5950b946297b5c46a21a695cd28795208d72fd17f5129543b31a15a067c2:17)
        at com.android.tools.r8.D8.b(R8_8.0.40_1caf5950b946297b5c46a21a695cd28795208d72fd17f5129543b31a15a067c2:1)
        at com.android.tools.r8.utils.N0.a(R8_8.0.40_1caf5950b946297b5c46a21a695cd28795208d72fd17f5129543b31a15a067c2:23)
        ... 43 more


Execution failed for task ':app:dexBuilderDebug'.
> There was a failure while executing work items
   > A failure occurred while executing com.android.build.gradle.internal.dexing.DexWorkAction
      > Failed to process: <projectpath>/AppDAgp8/app/build/intermediates/classes/debug/transformDebugClassesWithAsm/dirs

(...)

```


### 2. Excessive warnings
Hundreds of entries on the format:
```
WARNING: <projectpath>/AppDAgp8/app/build/intermediates/classes/release/ALL/classes.jar: D8: Expected stack map table for method with non-linear control flow.
WARNING: <projectpath>/AppDAgp8/app/build/intermediates/classes/release/ALL/classes.jar: D8: Expected stack map table for method with non-linear control flow.
WARNING: <projectpath>/AppDAgp8/app/build/intermediates/classes/release/ALL/classes.jar: D8: Expected stack map table for method with non-linear control flow.
(...)
```

## How to see the error

Be sure that the `disabledForBuildTypes` is set to exclude `debug` builds.
E.g.:

In file `app/build.gradle.kts`

```kotlin
adeum {
    disabledForBuildTypes = listOf("debug")
}
```

Run `./gradlew clean build`

If the `disabledForBuildTypes` line is commented out, or if `kapt`/`hilt` is removed, the problem doesn't happen.