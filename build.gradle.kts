plugins {
//    val agpVersion = "7.3.0"// AGP 7.3.0 gives warning
    val agpVersion = "8.0.0-alpha03" // AGP 8.0.0-alpha03 gives error
    id ("com.android.application") version agpVersion apply false
    id ("com.android.library") version agpVersion apply false
    id ("org.jetbrains.kotlin.android") version "1.7.20" apply false
    id ("adeum") apply false
}



//task clean(type: Delete) {
//    delete rootProject.buildDir
//}