#dependencies {
    def media3_version = "1.2.0"

    // For media playback using ExoPlayer
    implementation "androidx.media3:media3-exoplayer:$media3_version"

    // For DASH playback support with ExoPlayer
    implementation "androidx.media3:media3-exoplayer-dash:$media3_version"
    // For HLS playback support with ExoPlayer
    implementation "androidx.media3:media3-exoplayer-hls:$media3_version"
    // For RTSP playback support with ExoPlayer
    implementation "androidx.media3:media3-exoplayer-rtsp:$media3_version"
    // For ad insertion using the Interactive Media Ads SDK with ExoPlayer
    implementation "androidx.media3:media3-exoplayer-ima:$media3_version"

    // For loading data using the Cronet network stack
    implementation "androidx.media3:media3-datasource-cronet:$media3_version"
    // For loading data using the OkHttp network stack
    implementation "androidx.media3:media3-datasource-okhttp:$media3_version"
    // For loading data using librtmp
    implementation "androidx.media3:media3-datasource-rtmp:$media3_version"

    // For building media playback UIs
    implementation "androidx.media3:media3-ui:$media3_version"
    // For building media playback UIs for Android TV using the Jetpack Leanback library
    implementation "androidx.media3:media3-ui-leanback:$media3_version"

    // For exposing and controlling media sessions
    implementation "androidx.media3:media3-session:$media3_version"

    // For extracting data from media containers
    implementation "androidx.media3:media3-extractor:$media3_version"

    // For integrating with Cast
    implementation "androidx.media3:media3-cast:$media3_version"

    // For scheduling background operations using Jetpack Work's WorkManager with ExoPlayer
    implementation "androidx.media3:media3-exoplayer-workmanager:$media3_version"

    // For transforming media files
    implementation "androidx.media3:media3-transformer:$media3_version"

    // Utilities for testing media components (including ExoPlayer components)
    implementation "androidx.media3:media3-test-utils:$media3_version"
    // Utilities for testing media components (including ExoPlayer components) via Robolectric
    implementation "androidx.media3:media3-test-utils-robolectric:$media3_version"

    // Common functionality for media database components
    implementation "androidx.media3:media3-database:$media3_version"
    // Common functionality for media decoders
    implementation "androidx.media3:media3-decoder:$media3_version"
    // Common functionality for loading data
    implementation "androidx.media3:media3-datasource:$media3_version"
    // Common functionality used across multiple media libraries
    implementation "androidx.media3:media3-common:$media3_version"
} AndroidX Media

AndroidX Media is a collection of libraries for implementing media use cases on
Android, including local playback (via ExoPlayer), video editing (via Transformer) and media sessions.

## Documentation

*   The [developer guide][] provides a wealth of information.
*   The [class reference][] documents the classes and methods.
*   The [release notes][] document the major changes in each release.
*   The [media dev center][] provides samples and guidelines.
*   Follow our [developer blog][] to keep up to date with the latest
    developments!

[developer guide]: https://developer.android.com/guide/topics/media/media3
[class reference]: https://developer.android.com/reference/androidx/media3/common/package-summary
[release notes]: RELEASENOTES.md
[media dev center]: https://developer.android.com/media
[developer blog]: https://medium.com/google-exoplayer

## Migration for existing ExoPlayer and MediaSession projects

You'll find a [migration guide for existing ExoPlayer and MediaSession users][]
on developer.android.com.

[migration guide for existing ExoPlayer and MediaSession users]: https://developer.android.com/guide/topics/media/media3/getting-started/migration-guide

## API stability

AndroidX Media releases provide API stability guarantees, ensuring that the API
surface remains backwards compatible for the most commonly used APIs. APIs
intended for more advanced use cases are marked as unstable. To use an unstable
method or class without lint warnings, you’ll need to add the OptIn annotation
before using it. For more information see the [UnstableApi][] documentation.

[UnstableApi]: https://github.com/androidx/media/blob/main/libraries/common/src/main/java/androidx/media3/common/util/UnstableApi.java

## Using the libraries

You can get the libraries from [the Google Maven repository][]. It's
also possible to clone this GitHub repository and depend on the modules locally.

[the Google Maven repository]: https://developer.android.com/studio/build/dependencies#google-maven

### From the Google Maven repository

#### 1. Add module dependencies

The easiest way to get started using AndroidX Media is to add gradle
dependencies on the libraries you need in the `build.gradle.kts` file of your
app module.

For example, to depend on ExoPlayer with DASH playback support and UI components
you can add dependencies on the modules like this:

```kotlin
implementation("androidx.media3:media3-exoplayer:1.X.X")
implementation("androidx.media3:media3-exoplayer-dash:1.X.X")
implementation("androidx.media3:media3-ui:1.X.X")
```

Or in Gradle Groovy DSL `build.gradle`:

```groovy
implementation 'androidx.media3:media3-exoplayer:1.X.X'
implementation 'androidx.media3:media3-exoplayer-dash:1.X.X'
implementation 'androidx.media3:media3-ui:1.X.X'
```

where `1.X.X` is your preferred version. All modules must be the same version.

Please see the [AndroidX Media3 developer.android.com page][] for more
information, including a full list of library modules.

This repository includes some modules that depend on external libraries that
need to be built manually, and are not available from the Maven repository.
Please see the individual READMEs under the [libraries directory][] for more
details.

[AndroidX Media3 developer.android.com page]: https://developer.android.com/jetpack/androidx/releases/media3#declaring_dependencies
[libraries directory]: libraries

#### 2. Turn on Java 8 support

If not enabled already, you also need to turn on Java 8 support in all
`build.gradle.kts` files depending on AndroidX Media, by adding the following to
the `android` section:

```kotlin
compileOptions {
  targetCompatibility = JavaVersion.VERSION_1_8
}
```

Or in Gradle Groovy DSL `build.gradle`:

```groovy
compileOptions {
  targetCompatibility JavaVersion.VERSION_1_8
}
```

#### 3. Enable multidex

If your Gradle `minSdkVersion` is 20 or lower, you should
[enable multidex](https://developer.android.com/studio/build/multidex) in order
to prevent build errors.

### Locally

Cloning the repository and depending on the modules locally is required when
using some libraries. It's also a suitable approach if you want to make local
changes, or if you want to use the `main` branch.

First, clone the repository into a local directory:

```sh
git clone https://github.com/androidx/media.git
cd media
```

Next, add the following to your project's `settings.gradle.kts` file, replacing
`path/to/media` with the path to your local copy:

```kotlin
gradle.extra.apply {
  set("androidxMediaModulePrefix", "media-")
}
apply(from = file("path/to/media/core_settings.gradle"))
```

Or in Gradle Groovy DSL `settings.gradle`:

```groovy
gradle.ext.androidxMediaModulePrefix = 'media-'
apply from: file("path/to/media/core_settings.gradle")
```

You should now see the AndroidX Media modules appear as part of your project.
You can depend on them from `build.gradle.kts` as you would on any other local
module, for example:

```kotlin
implementation(project(":media-lib-exoplayer"))
implementation(project(":media-lib-exoplayer-dash"))
implementation(project(":media-lib-ui"))
```

Or in Gradle Groovy DSL `build.gradle`:

```groovy
implementation project(':media-lib-exoplayer')
implementation project(':media-lib-exoplayer-dash')
implementation project(':media-lib-ui')
```

## Developing AndroidX Media

#### Project branches

Development work happens on the `main` branch. Pull requests should normally be
made to this branch.

The `release` branch holds the most recent stable release.

#### Using Android Studio

To develop AndroidX Media using Android Studio, simply open the project in the
root directory of this repository.
