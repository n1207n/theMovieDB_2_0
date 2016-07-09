# theMovieDB_2_0
Starter project boilerplate inspired by https://github.com/RoRoche/AndroidStarter and many others. This project is a bare-minimum skeleton project for bootstrapping your codebase.

## Thanks to
https://github.com/RoRoche/AndroidStarter

https://medium.com/@cesarmcferreira/gradle-tips-tricks-to-survive-the-zombie-apocalypse-3dd996604341#.psceqbtzk

https://plus.google.com/+AndroidDevelopers/posts/Z1Wwainpjhd?linkId=17769888 (For Brand launch screen)


## What's in here?
This project includes the following:
* Useful Gradle dependencies
* Pre-made Dagger 2 Auto components
* Application class bootstrapped
* Gradle build script goodies from versioning variables to testLogging
* MainActivity with Fragment as first activity

This project does not include:
* Database setup
* Pre-made Retrofit services
* No eventbus or RxJava setup
* No base activity or fragment
* No keystore

### So this is just pre-Gradled project?
Pretty much! The reason for not pre-configuring dependencies and creating Base Java classes is purely flexibility. 
Some apps don't need to use Database at all and/or REST APIs. From this skeleton, you can freely start bootstrapping Java POJOs or Database models or Retrofit services.

I also didn't want to set up eventbus or RxJava out of the box. Again, this is for flexibility. Some people prefer Eventbus over the RxJava/RxAndroid; vice versa. Even for Eventbus, people have different opinions on how Event structure or dispatching should be. You can build Roroche's Eventbus + Priority job queue pattern or full RxJava/RxAndroid pattern from here. 

We are also living in the world where people constantly debate on MVP vs Clean vs SOLID vs MVVM vs RxAndroid-bindings. Mosby is included as the Gradle but there is no base setup or anything else. So, choose what you prefer for UI architecture. ;)

In order to make the base project very lean, this project is purely codebase. There is no Gradle keystore configuration or building tasks.

Overall, this project gives you a great starting point to polish the Gradle dependencies as you fit and set up your building blocks for the architecture choices.

### Secret info!
Any secret keys or information should go to gradle.properties file. In the project's build.gradle file, some buildConfigField entries are commented out for RSET API helper information later on.

If you are using GCM, Firebase, Fabric, or any cloud services, make sure those secret files are in the .gitignore files. I have made my best effort to hide them ;)

### Gradle build goodies
* Variables for
  * Application ID
  * Versioning
  * SDK & Build tool versioning
* Git SHA hash & Build timestamp as BuildConfigField
* Runtime Environment BuildConfigField (DEV or PROD)
* Data binding enabled
* VectorDrawable support from Support library
* Color style XML set
* Brand splash screen support
* Product flavors for different target minSdkVersion
* Suffixes for debug build
* Gradle-driven app_name
* testCoverage enabled
* REST API BuildConfigField from gradle.properties
* Unit test result as Gradle output

### Gradle dependencies

* apt 'com.squareup:javapoet:1.7.0' is included as first apt statement to resolve version conflicts in square dependencies

1. Testing
  * 'com.android.support:support-annotations'
  * 'com.android.support.test:runner'
  * 'com.android.support.test:rules'
  * 'com.android.support.test.espresso:espresso-core'
  * 'com.android.support.test.uiautomator:uiautomator-v18'
  * 'org.mockito:mockito-core'
  * 'junit:junit'
  * 'com.squareup.okhttp3:mockwebserver'

2. Support libraries
  * 'com.android.support:design'
  * 'com.android.support:support-annotations'
  * 'com.android.support:support-vector-drawable'
  * 'com.android.support:percent'
  * 'com.android.support.constraint:constraint-layout'

3. Auto Value
  * 'com.google.auto.value:auto-value'
  * 'com.ryanharter.auto.value:auto-value-parcel'
  * 'com.ryanharter.auto.value:auto-value-parcel-adapter'

4. Square libraries
  * 'com.squareup.okhttp3:okhttp' with logging-interceptor
  * 'com.squareup.picasso:picasso'
  * 'com.squareup.retrofit2:retrofit'
  * 'com.squareup.retrofit2:converter-moshi'
  * 'com.squareup.retrofit:adapter-rxjava'
  * 'com.squareup.moshi:moshi'
  * 'com.jakewharton:butterknife'

5. Dagger 2
  * 'com.google.dagger:dagger'
  * 'javax.annotation:jsr250-api'
  * 'org.glassfish:javax.annotation'
  * 'com.github.lukaspili.autodagger2:autodagger2'

6. A bunch of APTs!
  * 'se.emilsjolander:intentbuilder-api'
  * 'frankiesardo:icepick'
  * 'com.vanniktech:onactivityresult'

7. RxJava/RxAndroid
  * 'io.reactivex:rxjava'
  * 'io.reactivex:rxandroid'
  * 'com.trello:rxlifecycle'
  * 'com.trello:rxlifecycle-components'

8. Mosby
  * 'com.hannesdorfmann.mosby:mvp'
  * 'com.hannesdorfmann.mosby:viewstate'

9. Some UI goodies
  * 'io.nlopez.smartadapters:library'
  * 'me.relex:circleindicator'

10. Utilities
  * 'com.birbit:android-priority-jobqueue'
  * 'uk.co.chrisjenx:calligraphy'
  * 'com.novoda:merlin'
  * 'joda-time:joda-time'

11. Animation
  * 'com.facebook.rebound:rebound'

12. Database
  * "com.github.Raizlabs.DBFlow:dbflow"

13. Debugging
  * 'com.facebook.stetho:stetho'
  * 'com.jakewharton.scalpel:scalpel'
  * 'io.palaima.debugdrawer:debugdrawer'
  * 'com.squareup.leakcanary:leakcanary-android'
