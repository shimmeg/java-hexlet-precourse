package io.hexlet.code;

import java.util.Scanner;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;

public class App {

  final static int[][] NOTES = {
      {60, 1, 1}, // C
      {62, 1, 1}, // D
      {64, 1, 1}, // E
      {65, 1, 1}, // F
      {67, 2, 2}, // G,G
      {69, 1, 4}, // A,A,A,A
      {67, 4, 1}, // G
      {69, 1, 4}, // A,A,A,A
      {67, 4, 1}, // G
      {65, 1, 4}, // F,F,F,F
      {64, 2, 2}, // E,E
      {62, 1, 4}, // D,D,D,D
      {60, 4, 1}, // C
  };

  private static final byte C = 60; // do
  private static final byte D = 62; // re
  private static final byte E = 64; // mi
  private static final byte F = 65; // fa
  private static final byte G = 67; // sol
  private static final byte A = 69; // la
  private static final byte B = 70; // si


  public static void main(String[] args)
      throws MidiUnavailableException, InvalidMidiDataException, InterruptedException {
//    System.out.println("Please enter notes: ");
//    Scanner scanner = new Scanner(System.in);

    Synthesizer synthesizer = MidiSystem.getSynthesizer();
    synthesizer.open();
    Receiver receiver = synthesizer.getReceiver();

    ShortMessage msg = new ShortMessage();
//    msg.setMessage(ShortMessage.PROGRAM_CHANGE, 27, -1);
//    receiver.send(msg, -1);

    for (int[] note : NOTES) {
      int count = note[2];
      int duration = note[1];
      for (int i = 0; i < count; i++) {
        msg.setMessage(ShortMessage.NOTE_ON, note[0], 100);
        receiver.send(msg, -1);

        Thread.sleep(500L * duration);

        msg.setMessage(ShortMessage.NOTE_OFF, note[0], 100);
        receiver.send(msg, -1);
      }

    }

    // A B C E D E C
    //   a b c a
//    String text = scanner.nextLine().trim();

//     [A, B, C, E ...]
//    while (!text.equalsIgnoreCase("exit")) {
//      String[] notes = text.toUpperCase().split(" ");
//      playNotes(receiver, notes);
//      text = scanner.nextLine().trim();
//    }
  }

  private static void playNotes(Receiver receiver, String[] notes)
      throws InvalidMidiDataException, InterruptedException {
    for (String note : notes) {
      ShortMessage msg = new ShortMessage();
      msg.setMessage(ShortMessage.NOTE_ON, convertToId(note), 100);
      receiver.send(msg, -1);

      Thread.sleep(500);

      msg.setMessage(ShortMessage.NOTE_OFF, convertToId(note), 100);
      receiver.send(msg, -1);
    }
  }

  private static int convertToId(String note) {
    switch (note) {
      case "A":
        return A;
      case "B":
        return B;
      case "C":
        return C;
      case "D":
        return D;
      case "E":
        return E;
      case "F":
        return F;
      case "G":
        return G;
      default:
        System.out.println("Entered incorrect note: " + note);
        return A;
    }
  }

}
