plugins {
    val agpVersion = "8.0.1"
    id("com.android.application") version agpVersion apply false
    id("com.android.library") version agpVersion apply false
    id("org.jetbrains.kotlin.android") version "1.8.21" apply false
    id("com.google.dagger.hilt.android") version "2.44.2" apply false
    id("adeum") version "23.4.1" apply false
}


//task clean(type: Delete) {
//    delete rootProject.buildDir
//}