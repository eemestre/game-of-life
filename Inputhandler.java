import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.KeyEvent;
import java.awt.Point;
import java.awt.RenderingHints.Key;
import java.util.ArrayList;
import java.util.List;
import java.awt.Cursor;


import javax.swing.text.Style;

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
        } else if(e.getKeyCode() == KeyEvent.VK_UP) {
            Main.current.mass += 100;
        } else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            if(Main.current.mass >= 200) {
                Main.current.mass -= 100;
            }
        } else if(e.getKeyCode() == KeyEvent.VK_DELETE) {
            Main.particles.remove(Main.current);
            Main.current = null;
        } else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            int i = Main.particles.indexOf(Main.current);
            if(i == Main.particles.size() - 1) {
                Main.current = Main.particles.get(0);
            } else {
                Main.current = Main.particles.get(i+1);
            }
        } else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            int i = Main.particles.indexOf(Main.current);
            if(i == 0) {
                Main.current = Main.particles.get(Main.particles.size() - 1);
            } else {
                Main.current = Main.particles.get(i-1);
            }
        }



        /*if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            this.values.add(this.text);
            Main.createParticle(this.text);
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
            Particle p = Main.mouseIn(e.getPoint());
            if(p == null) {
                Main.createParticle(e.getPoint());
            } else {
                Main.current = p;
                Main.current.dragged = true;
                System.out.println("dragged cliquei em cima");
            }
        } else if(e.getButton() == 3) {
            Particle p = Main.mouseIn(e.getPoint());
            if(p != null) {
                Main.current = p;
                Main.current.vx = 0;
                Main.current.vy = 0;
                Main.current.ax = 0;
                Main.current.ay = 0;
            }
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if(Main.current != null) {
            if(e.getWheelRotation() == 1) {
                if(Main.current.radius >= 10) {
                    Main.current.radius -= 5;
                }
            } else {
                Main.current.radius += 5;
            }
        }
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        if(Main.current.dragged) {
            Main.moveCurrent(e.getPoint());
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Particle p = Main.mouseIn(e.getPoint());
        if(p == null) {
            Main.canvas.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        } else {
            Main.canvas.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(Main.current != null) {
            Main.current.dragged = false;
            System.out.println("n ta mais dragged");
        }
    }

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