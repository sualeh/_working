package us.fatehi._working;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class RedirectSystemOut {

  // https://stackoverflow.com/questions/5339499/resetting-standard-output-stream
  public static void main(String[] args) {
    // Print sample
    System.out.println("Test - Before");

    // Redirect System.out
    final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    System.out.print("Test - Output to stream");
    String printed = outContent.toString();
    if (!"Test - Output to stream".equals(printed)) {
      throw new RuntimeException("\"" + printed + "\"");
    }

    // Reset System.out
    System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
    System.out.println("Test - After");
  }
}
