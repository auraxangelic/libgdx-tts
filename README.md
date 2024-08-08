# libgdx-tts
Library for a text-to-speech synthesizer for LibGDX. Available for desktop and android apps.

## Releases

For the latest release, download the jar here:

https://github.com/auraxangelic/libgdx-tts/releases

## Usage

1. Download the jar from the releases.
2. Put the jar into your libGDX project.
3. Include the jar in your `core` module's `build.gradle` with something like the following: 
```
dependencies {
    ...
    
    api fileTree(dir: "../libs", include: ["*.jar"])
}
```
4. Make sure you refresh gradle.
5. At any point after your Game class has been initialized, you my use the `TextSpeech` class to read any string.
```
import com.reikaxubia.libgdxtts.TextSpeech
...

    TextSpeech.speak("testing 1 2 3")
    // TextSpeech.INSTANCE.speak("testing 1 2 3") // in java
```
6. The text to speech will run asynchronously. If you want to block until it's finished speaking, you can run: `TextSpeech.waitUntilFinished()`/`TextSpeech.INSTANCE.waitUntilFinished()`
7. The `TextSpeech` class allocates and runs on its own thread. Make sure to deallocate the thread when your game exits by running `TextSpeech.deallocate()`/`TextSpeech.INSTANCE.deallocate()`, or run `exitProcess(0)`/`System.exit(0)`.

## Known Issues
Currently doesn't work for iOS robovm on libGDX version 1.12.1, but should be working once a new libGDX version is released. The PR to make it work for iOS is here: https://github.com/libgdx/libgdx/pull/7371
You can have it work for iOS now by building the latest main branch by following steps here: https://libgdx.com/dev/from-source/

This library isn't uploaded to maven central yet, so you must download the binaries manualy or clone the source yourself to use it.

On desktop, when a new `speak` call is made, the audio will play a tiny bit of the end of the previous `speak` call. This is due to how the AudioDevice is currently implemented for desktop.

## Information
Created using FreeTTS source: https://freetts.sourceforge.io/ by replacing its default audio player with the libGDX one `Gdx.audio#newAudioDevice`.
