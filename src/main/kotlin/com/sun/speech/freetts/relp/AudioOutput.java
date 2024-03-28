/**
 * Portions Copyright 2001 Sun Microsystems, Inc.
 * Portions Copyright 1999-2001 Language Technologies Institute, 
 * Carnegie Mellon University.
 * All Rights Reserved.  Use is subject to license terms.
 * 
 * See the file "license.terms" for information on usage and
 * redistribution of this file, and for a DISCLAIMER OF ALL 
 * WARRANTIES.
 */
package com.sun.speech.freetts.relp;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.badlogic.gdx.audio.AudioDevice;
import com.sun.speech.freetts.ProcessException;
import com.sun.speech.freetts.Utterance;
import com.sun.speech.freetts.UtteranceProcessor;

/**
 * Supports generating audio output from an utterance. This is an
 * utterance processor. The primary method, <code> procesUtterance </code> 
 * takes an utterance and hands it off to the LPCResult to be sent to the
 * proper audio player.
 *
 * @see LPCResult
 */
public class AudioOutput implements UtteranceProcessor {
    /** Logger instance. */
    private static final Logger LOGGER =
        Logger.getLogger(AudioOutput.class.getName());
    
    /**
     * Generates audio waves for the given Utterance. The audio data
     * is decoded using the Linear Predictive Decoder
     *
     * @param  utterance  the utterance to generate waves
     *
     * @see LPCResult
     *
     * @throws ProcessException if an IOException is thrown during the
     *         processing of the utterance
     */
    public void processUtterance(Utterance utterance) throws ProcessException {
	LPCResult lpcResult = (LPCResult) utterance.getObject("target_lpcres");
	SampleInfo sampleInfo = 
	    (SampleInfo) utterance.getObject(SampleInfo.UTT_NAME);
	AudioDevice audioPlayer = utterance.getVoice().getAudioPlayer();

	audioPlayer.setVolume(utterance.getVoice().getVolume());

	if (LOGGER.isLoggable(Level.FINE)) {
	    LOGGER.fine("=== " +
		utterance.getString("input_text"));
	}
	if (!lpcResult.playWave(audioPlayer, utterance)) {
	    throw new ProcessException("Output Cancelled");
	}
    }
    
    /**
     * 
     * Returns the string form of this object
     * 
     * @return the string form of this object
     */
    public String toString() {
	return "AudioOutput";
    }
}




