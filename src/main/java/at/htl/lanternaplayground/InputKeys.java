package at.htl.lanternaplayground;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

// https://github.com/mabe02/lanterna/blob/master/src/test/java/com/googlecode/lanterna/terminal/TelnetTerminalTest.java
public class InputKeys {

    public static void main(String[] args) throws IOException {
        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        Screen screen = new TerminalScreen(terminal);

        TextGraphics textGraphics = screen.newTextGraphics();

        screen.startScreen();
        screen.clear();

        KeyStroke keyPressed; // = terminal.readInput();


        while (true) { //keyPressed != null && keyPressed.getKeyType() != KeyType.Escape) {
            keyPressed = terminal.pollInput();
            if (keyPressed != null) {
                System.out.println("keypressed: " + keyPressed);
                if (keyPressed.getKeyType() == KeyType.Escape) {
                    terminal.exitPrivateMode();
                    return;
                }
            }
        }
//        screen.refresh();
//        screen.readInput();
//        screen.stopScreen();

    }

}
