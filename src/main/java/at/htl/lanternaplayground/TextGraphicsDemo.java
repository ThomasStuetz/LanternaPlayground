package at.htl.lanternaplayground;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.Random;

// https://nikolaydimov83.wordpress.com/2014/05/30/create-console-game-in-java/
public class TextGraphicsDemo {

    private static Random rnd = new Random();

    public static void main(String[] args) throws IOException, InterruptedException {

        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        Screen screen = new TerminalScreen(terminal);

        TextGraphics tg = screen.newTextGraphics();

        screen.startScreen();

        tg.putString(0, 0, "TextGraphicsDemo");
        screen.refresh();

//        tg.putString(0, 0, "TextGraphicsDemo - change background colors                        ");
//        for (TextColor.ANSI backgroundColor : TextColor.ANSI.values()) {
//            tg.setBackgroundColor(backgroundColor);
//            tg.fill(' ');
//            screen.refresh();
//            Thread.sleep(500);
//        }
//
//        // http://mabe02.github.io/lanterna/apidocs/3.0/com/googlecode/lanterna/Symbols.html
//        tg.putString(0, 4, "TextGraphicsDemo - draw figures                        ");
//        //tg.setBackgroundColor(TextColor.ANSI.GREEN);
//        tg.drawRectangle(new TerminalPosition(1, 5), new TerminalSize(4, 4), Symbols.BLOCK_SPARSE);
//        tg.drawLine(new TerminalPosition(7, 5), new TerminalPosition(10, 5), Symbols.DOUBLE_LINE_HORIZONTAL);
//        tg.drawTriangle(
//                new TerminalPosition(12, 5),
//                new TerminalPosition(20, 5),
//                new TerminalPosition(16, 9),
//                Symbols.SOLID_SQUARE_SMALL);
//        screen.refresh();
//        Thread.sleep(2000);
//        tg.fillRectangle(new TerminalPosition(1, 5), new TerminalSize(4, 4), Symbols.BLOCK_SPARSE);
//        //tg.setBackgroundColor(TextColor.ANSI.DEFAULT);
//        tg.fillTriangle(
//                new TerminalPosition(12, 5),
//                new TerminalPosition(20, 5),
//                new TerminalPosition(16, 9),
//                Symbols.SOLID_SQUARE_SMALL);
//        screen.refresh();
//        Thread.sleep(2000);

        tg.putString(0, 10, "TextGraphicsDemo - move character                        ");
        TerminalPosition formerPos = null, currentPos;
        //for (int i = 0; i < screen.getTerminalSize().getColumns(); i++) {
        for (int i = 0; i < 10; i++) {
//            if (i > 0) {
//                tg.setCharacter(i-1, 11, ' ');
//            }
            if (formerPos != null) {
                tg.setCharacter(formerPos, ' ');
            }
            currentPos = new TerminalPosition(i, 11);
            tg.setCharacter(currentPos, Symbols.FACE_WHITE);
            formerPos = currentPos;
            screen.refresh(Screen.RefreshType.DELTA);
            Thread.sleep(100);
        }

        int line = 13;
        tg.putString(0, line, "TextGraphicsDemo - Balkendiagramm                        ");
        tg.putString(0, line + 1, "Wert 1");
        tg.putString(0, line + 2, "Wert 2");
        tg.putString(0, line + 3, "Wert 3");
        tg.putString(0, line + 4, "Wert 4");
        tg.putString(0, line + 5, "Wert 5");

        tg.setForegroundColor(TextColor.ANSI.BLUE);
        drawVerticalBar(tg, screen, 7, 5 + rnd.nextInt(60), line + 1);
        drawVerticalBar(tg, screen, 7, 5 + rnd.nextInt(60), line + 2);
        drawVerticalBar(tg, screen, 7, 5 + rnd.nextInt(60), line + 3);
        drawVerticalBar(tg, screen, 7, 5 + rnd.nextInt(60), line + 4);
        drawVerticalBar(tg, screen, 7, 5 + rnd.nextInt(60), line + 5);

        //terminal.bell();


        //Thread.sleep(500);
        screen.readInput();
        screen.stopScreen();
    }

    private static void drawVerticalBar(TextGraphics tg, Screen s, int fromX, int toX, int y) {

        for (int i = fromX; i < toX; i++) {
            if (i > 0) {
                tg.setCharacter(i, y, ' ');
            }
            tg.setCharacter(i, y, Symbols.BLOCK_SOLID);
            try {
                s.refresh();
                Thread.sleep(10);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }

        }

    }

}
