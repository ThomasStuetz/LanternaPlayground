package at.htl.lanternaplayground;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

// https://github.com/mabe02/lanterna/tree/master/src/examples/java/com/googlecode/lanterna/examples
// https://www.javatips.net/api/lanterna-master/src/main/java/com/googlecode/lanterna/screen/Screen.java
public class Main {

    public static void main(String[] args) throws IOException {

        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        Screen screen = new TerminalScreen(terminal);

        TextGraphics textGraphics = screen.newTextGraphics();

        screen.startScreen();
        screen.clear();

        // Einen Text schreiben
        textGraphics.putString(10, 10, "Hello World!");

        // Ein Rechteck malen
        textGraphics.drawRectangle(
                new TerminalPosition(3,3), new TerminalSize(20,3), '*'
        );

        // Ein Zeichen schreiben
        screen.setCharacter(24, 1, new TextCharacter('X'));

        screen.refresh();

        screen.readInput();
        screen.stopScreen();
    }



}
