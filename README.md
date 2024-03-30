# libgdx-tts
Text to Speech Synthesizer for LibGDX. Working on desktop and android.

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
import com.auraxangelic.libgdxtts.TextSpeech
...

    TextSpeech.speak("testing 1 2 3")
```
6. The text to speech will run asynchronously. If you want to block until it's finished speaking, you can run: `TextSpeech.waitUntilFinished()`
7. The `TextSpeech` class allocates and runs on its own thread. Make sure to deallocate the thread when your game exits by running `TextSpeech.deallocate()`, or run `exitProcess(0)`/`System.exit(0)`.

## Issues
Currently doesn't work for iOS robovm since AudioDevice interface isn't implemented yet for iOS.

## Information
Created using FreeTTS source: https://freetts.sourceforge.io/ by replacing its default audio player with the libGDX one `Gdx.audio#newAudioDevice`.
