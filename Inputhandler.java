import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.KeyEvent;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.awt.Cursor;

class Inputhandler implements KeyListener, MouseListener, MouseWheelListener, MouseMotionListener {
    String text;

    public Inputhandler() {
        this.text = "";
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            if(Main.state == "Paused") {
                Main.state = "Running";
            } else if(Main.state == "Running") {
                Main.state = "Paused";
            }
        } else if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            Main.grid.reset();
        }







        /*if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            Main.showText(this.text);
            this.text = "";
        } else if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            this.text = "";
        } else if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            if(this.text.length() > 0) {
                this.text = this.text.substring(0, this.text.length() - 1);
            }
        } else if(e.getKeyCode() == KeyEvent.VK_SHIFT || e.getKeyCode() == KeyEvent.VK_CONTROL || e.getKeyCode() == KeyEvent.VK_NUM_LOCK || e.getKeyCode() == KeyEvent.VK_CAPS_LOCK || e.getKeyCode() == KeyEvent.VK_ALT) {
            ;
        } else {
            this.text += e.getKeyChar();
        }*/
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == 1) {
            Main.undieCell(e.getPoint());
        } else if (e.getButton() == 3) {
            Main.killCell(e.getPoint());
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if(e.getWheelRotation() == 1) {
            if(Main.tps > 1) {
                Main.tps -= 1;
            }
        } else {
            Main.tps += 1;
        }
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        if(Main.state == "Paused") {
            if(e.getX() >= 0 && e.getY() >= 0 && e.getX() < Main.frame.getWidth() && e.getY() < Main.frame.getHeight()){
                if(e.getModifiersEx() == 1024) {
                    Main.undieCell(e.getPoint());
                } else if(e.getModifiersEx() == 4096) {
                    Main.killCell(e.getPoint());
                }
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {}
 
    @Override
    public void mouseEntered(MouseEvent e) {}
 
    @Override
    public void mouseExited(MouseEvent e) {}
}