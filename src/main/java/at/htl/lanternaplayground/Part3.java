package at.htl.lanternaplayground;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Part3 {

    public static void main(String[] args) throws IOException, InterruptedException {
        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        Screen screen = new TerminalScreen(terminal);

        TextGraphics tg = screen.newTextGraphics();

        screen.startScreen();
        screen.clear();


        // Einen Text schreiben
        tg.putString(5, 5, "Hello World!");


        screen.refresh();

        StringBuilder sb = new StringBuilder();
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
                        tg.setForegroundColor(TextColor.ANSI.BLUE);
                        tg.setBackgroundColor(TextColor.ANSI.GREEN);
                        tg.putString(0, 0, "width of th window: "
                                + screen.getTerminalSize().getColumns()
                                + "x" + screen.getTerminalSize().getRows());
                        screen.refresh();
                        tg.setForegroundColor(TextColor.ANSI.WHITE);
                        tg.setBackgroundColor(TextColor.ANSI.DEFAULT);
                        break;
                    case Character:
                        sb.append(keyPressed.getCharacter());
                        System.out.println(keyPressed);
                        break;
                    case Enter:
                        screen.clear();
                        tg.putString(10, 10, sb.toString(), SGR.CROSSED_OUT);
                        System.out.println(sb.toString());
                        screen.refresh();
                        sb = new StringBuilder();
                        break;
                    case ArrowUp:
                        tg.setForegroundColor(TextColor.ANSI.MAGENTA);
                        tg.setBackgroundColor(TextColor.ANSI.MAGENTA);
                        for (int i = 20; i < 80; i++) {
                            tg.putString(i, 20, "X");
                            Thread.sleep(100);
                            screen.refresh();
                        }
                        tg.setForegroundColor(TextColor.ANSI.WHITE);
                        tg.setBackgroundColor(TextColor.ANSI.DEFAULT);
                        break;
                    case ArrowLeft:
                        int row = 5, col = 60;
                        for (SGR sgr : SGR.values()) {
                            tg.putString(col, row++, sgr.name(), sgr);
                            System.out.println(sgr.name());
                            screen.refresh();
                        }
                        break;
                    default:
                        System.out.println(keyPressed);
                }
            }
        }

        // screen.readInput();  // readInput is blocking
        screen.stopScreen();
        System.out.println("exit");
    }

}
