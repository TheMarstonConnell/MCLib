package xyz.marstonconnell.util;

import java.awt.Color;
import java.awt.Container;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import xyz.marstonconnell.graphics.components.InfoBox;
import xyz.marstonconnell.graphics.components.Palette;

/**
 * A set of basic tools for Java projects.<br>
 * <br>
 * Contains file choosers and easy to use info/input boxes to name a few.
 * 
 * @author Marston Connell
 * @since 4/16/2018
 */
public class Tools {

	public static final Palette DEFAULT_PALETTE = new Palette(new Color(238, 238, 238), Color.DARK_GRAY, Color.white,
			Color.BLACK);
	public static final Palette DARK_PALETTE = new Palette(Color.DARK_GRAY, Color.gray, Color.LIGHT_GRAY, Color.WHITE);
	public static final Palette SUNSHINE_PALETTE = new Palette(new Color(255, 253, 219), new Color(186, 213, 224),
			Color.WHITE, new Color(224, 181, 132));

	static Synthesizer midiSynth;
	static MidiChannel[] mChannels;
	static Instrument[] instr;

	public static final JPanel SPACER = new JPanel();

	public Tools() {
		try {
			midiSynth = MidiSystem.getSynthesizer();
			mChannels = midiSynth.getChannels();
			midiSynth.open();
			instr = midiSynth.getDefaultSoundbank().getInstruments();
			midiSynth.loadInstrument(instr[13]);// load an instrument

		} catch (MidiUnavailableException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * shows user an info message.
	 * 
	 * @author Marston Connell
	 * @param message
	 * @param title
	 * @param parent
	 * @since 3/28/2018
	 */
	public static void infoBox(String message, String title, Container parent) {
		JOptionPane.showMessageDialog(parent, message, title, JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Shows user info along with an icon.
	 * 
	 * @author Marston Connell
	 * @param message
	 * @param title
	 * @param iconName
	 * @param parent
	 * @since 3/28/2018
	 */
	public static void infoBox(String message, String title, String iconName, Container parent) {
		JOptionPane.showMessageDialog(parent, message, title, JOptionPane.INFORMATION_MESSAGE,
				new ImageIcon("assets/images/" + iconName + ".png"));
	}

	/**
	 * Gets input as a string from user.
	 * 
	 * @author Marston Connell
	 * @param message
	 * @param title
	 * @param parent
	 * @return Entered Message
	 * @since 3/28/2018
	 */
	public static String inputBox(String message, String title, Container parent) {
		return JOptionPane.showInputDialog(parent, message, title, JOptionPane.DEFAULT_OPTION);
	}

	/**
	 * Generates a random Integer with the highest possible value given
	 * 
	 * @author Marston Connell
	 * @param high
	 * @since 4/2/2018
	 * @return Random Integer
	 */
	public static int RandomInteger(int high) {
		return (int) (Randomize() * (high + 1));
	}

	/**
	 * Generates a random Integer within the highest and lowest values given
	 * 
	 * @author Marston Connell
	 * @param high
	 * @param low
	 * @since 4/2/2018
	 * @return Random Integer
	 */
	public static int RandomInteger(int high, int low) {
		return (int) (Randomize() * (high + 1 - low) + low);
	}

	/**
	 * Generates a random float that can be used to multiply against other<br>
	 * numbers to achieve a random number inside specific parameters
	 * 
	 * @author Marston Connell
	 * @since 4/2/2018
	 * @return Two decimal places
	 */
	public static float Randomize() {
		String num = String.valueOf(System.currentTimeMillis()).substring(11);
		return (float) (Integer.parseInt(num) * 0.01);
	}

	/**
	 * Generates a random upper case character
	 * 
	 * @author Marston Connell
	 * @since 4/2/2018
	 * @return Upper case Char
	 */
	public static char RandomChar() {
		return (char) RandomInteger(64, 90);
	}

	/**
	 * Returns a string of random characters.
	 * 
	 * @author Marston Connell
	 * @return random string of random length
	 * @since 4/17/2018
	 */
	public static String randomString() {
		String s = "";
		for (int x = RandomInteger(100); x > 0; x--) {
			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
			}
			s = s + RandomChar();
		}
		return s;
	}

	/**
	 * Returns a String of random characters.
	 * 
	 * @author Marston Connell
	 * @param maxChars
	 * @param minChars
	 * @return random string
	 * @since 4/17/2018
	 */
	public static String randomString(int maxChars, int minChars) {
		String s = "";
		for (int x = maxChars; x > minChars; x--) {
			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
			}
			s = s + RandomChar();
		}
		return s;
	}

	/**
	 * Plays sound file from file name. <br>
	 * <br>
	 * 
	 * .wav format is suggested.
	 * 
	 * @author Marston Connell
	 * @param audioName
	 * @since 4/17/2018
	 */
	public void playSound(String audioName) {
		Clip soundClip = null;
		AudioInputStream soundAudioInputStream = null;
		try {
			soundAudioInputStream = AudioSystem.getAudioInputStream(new File(audioName).getAbsoluteFile());
		} catch (UnsupportedAudioFileException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		// create clip reference
		try {
			soundClip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		try {
			soundClip.open(soundAudioInputStream);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		soundClip.loop(0);
	}

	/**
	 * Reads in every line from a file and adds it to a list.
	 * 
	 * @author Marston Connell
	 * @param file
	 * @return
	 */
	@SuppressWarnings("resource")
	public static ArrayList<String> readLines(String file) {
		ArrayList<String> words = new ArrayList<String>();
		try {

			BufferedReader br = new BufferedReader(new FileReader(new File(file)));
			String line;
			while ((line = br.readLine()) != null) {

				words.add(line);
			}
		} catch (IOException e) {
		}
		return words;
	}

	/**
	 * Opens a file chooser in the center of the screen and allows user to pick
	 * file.
	 * 
	 * @author Marston Connell
	 * @return file chosen
	 */
	public static File chooseFile() {
		JFileChooser j = new JFileChooser();
		j.showOpenDialog(null);
		return j.getSelectedFile();
	}

	/**
	 * Opens a file chooser in the center of the screen and allows user to pick
	 * file.
	 * 
	 * @author Marston Connell
	 * @param startPath
	 * @return file chosen
	 */
	public static File chooseFile(String startPath) {
		JFileChooser j = new JFileChooser();
		j.setCurrentDirectory(new File(startPath));
		j.showOpenDialog(null);
		return j.getSelectedFile();
	}

	/**
	 * Capitalizes word.
	 * 
	 * @author Marston Connell
	 * @param word
	 * @return
	 */
	public static String capitalize(String word) {
		String first = word.substring(0, 1).toUpperCase();
		word = word.substring(1);
		word = first + word;
		return word;
	}

	/**
	 * Returns last line in a file. https://stackoverflow.com/a/7322581/9005519
	 * 
	 * @author Eric Leschinski
	 * @param file
	 * @return
	 */
	public static String tail(File file) {
		RandomAccessFile fileHandler = null;
		try {
			fileHandler = new RandomAccessFile(file, "r");
			long fileLength = fileHandler.length() - 1;
			StringBuilder sb = new StringBuilder();

			for (long filePointer = fileLength; filePointer != -1; filePointer--) {
				fileHandler.seek(filePointer);
				int readByte = fileHandler.readByte();

				if (readByte == 0xA) {
					if (filePointer == fileLength) {
						continue;
					}
					break;

				} else if (readByte == 0xD) {
					if (filePointer == fileLength - 1) {
						continue;
					}
					break;
				}

				sb.append((char) readByte);
			}

			String lastLine = sb.reverse().toString();
			return lastLine;
		} catch (java.io.FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (java.io.IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (fileHandler != null)
				try {
					fileHandler.close();
				} catch (IOException e) {
					/* ignore */
				}
		}
	}

	/**
	 * Converts a string to title case. <br>
	 * ex: hello sir -> Hello Sir
	 * 
	 * @author Neelam Singh
	 * @param givenString
	 * @return
	 */
	public static String toTitleCase(String givenString) {
		String[] arr = givenString.split(" ");
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < arr.length; i++) {
			sb.append(Character.toUpperCase(arr[i].charAt(0))).append(arr[i].substring(1)).append(" ");
		}
		return sb.toString().trim();
	}

	public static void infoBox(String infoMessage, String titleBar) {
		// JOptionPane.showMessageDialog(this.content, infoMessage, titleBar,
		// JOptionPane.INFORMATION_MESSAGE);
		new InfoBox(infoMessage, titleBar);
	}

	public static void infoBox(String infoMessage, String titleBar, String iconName) {
		// JOptionPane.showMessageDialog(this.content, "<html>" + infoMessage +
		// "</html>", titleBar,
		// JOptionPane.INFORMATION_MESSAGE, new ImageIcon("assets/images/" + iconName +
		// ".png"));

		new InfoBox(titleBar, infoMessage, "Ok", iconName, Tools.DEFAULT_PALETTE);
	}

	public static void playMidiNote(int tone) {

		mChannels[0].noteOn(tone, 100);// On channel 0, play note number 60 with velocity
		mChannels[0].noteOff(tone);// turn of the note

	}

}
