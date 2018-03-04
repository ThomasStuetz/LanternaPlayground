package at.htl.lanternaplayground;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class BackgroundColor {

    // https://github.com/mabe02/lanterna/blob/master/src/test/java/com/googlecode/lanterna/screen/ScreenResizeTest.java
    public static void main(String[] args) throws IOException {
        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        Screen screen = new TerminalScreen(terminal);

        TextGraphics textGraphics = screen.newTextGraphics();

        screen.startScreen();
        textGraphics.setForegroundColor(TextColor.ANSI.BLUE);
        textGraphics.setBackgroundColor(TextColor.ANSI.GREEN);
        textGraphics.fill(' ');
        textGraphics.putString(0, 0, "Size: "
                + screen.getTerminalSize().getColumns()
                + "x" + screen.getTerminalSize().getRows());
        textGraphics.putString(10, 1, "Hello World");


        textGraphics.setForegroundColor(TextColor.ANSI.WHITE);
        textGraphics.setBackgroundColor(TextColor.ANSI.BLUE);
        textGraphics.putString(10, 2, "Hello World");
        textGraphics.putString(10, 3, "Hello World", SGR.BOLD);


        screen.refresh();

        screen.readInput();
        screen.stopScreen();
    }
}
