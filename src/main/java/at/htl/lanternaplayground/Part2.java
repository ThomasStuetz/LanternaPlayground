package at.htl.lanternaplayground;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

import static com.googlecode.lanterna.input.KeyType.*;

// https://github.com/mabe02/lanterna/blob/master/src/test/java/com/googlecode/lanterna/screen/ScreenResizeTest.java
public class Part2 {

    public static void main(String[] args) throws IOException {
        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        Screen screen = new TerminalScreen(terminal);

        TextGraphics textGraphics = screen.newTextGraphics();

        screen.startScreen();
        screen.clear();


        // Einen Text schreiben
        textGraphics.putString(5, 5, "Hello World!");


        screen.refresh();

        boolean keepRunning = true;
        while (keepRunning) {
            KeyStroke keyPressed = terminal.pollInput();  // pollInput is non-blocking

            if (keyPressed != null) {
                System.out.println(keyPressed);
                switch (keyPressed.getKeyType()) {
                    case Escape:
                        keepRunning = false;
                        break;
                    case ArrowRight:
                        System.out.println("--> Arrow Right");
                        textGraphics.setForegroundColor(TextColor.ANSI.BLUE);
                        textGraphics.setBackgroundColor(TextColor.ANSI.GREEN);
                        textGraphics.putString(0, 0, "width of th window: "
                                + screen.getTerminalSize().getColumns()
                                + "x" + screen.getTerminalSize().getRows());
                        screen.refresh();
                        break;
                    default:
                        keepRunning = false;
                }
            }
        }

        // screen.readInput();  // readInput is blocking
        screen.stopScreen();
        System.out.println("exit");
    }

}
