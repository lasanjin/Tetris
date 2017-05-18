package Controller;

import Model.exceptions.DropException;
import Model.exceptions.GameOverException;
import Model.exceptions.NextLevelException;
import View.*;
import Model.*;
import View.buttons.IController;
import View.sound.IControlSound;
import View.sound.Sound;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Controller class, handles input from user.
 */
public class Controller implements Runnable, IController {
    private IControlSound controlSound;
    private IControlModel controlModel;
    private IControlView controlView;
    private Keys keys;
    private Thread thread;
    private boolean menu;
    private volatile boolean isRunning;
    private boolean gameOver;
    private volatile boolean paus;
    private int updateInterval;
    private int level;
    private int countDown;


    public Controller(MainView view, Sound sound) {
        this.addIControlView(view);
        this.addIControlSound(sound);
        keys = new Keys();
        controlView.addMouse(new Mouse());
        view.addIControl(this);
        controlSound.muteSound(false);
        controlSound.muteMusic(false);
        paus = false;
    }

    @Override
    public void run() {
        while (isRunning) {
            checkIfPaus();
            try {
                controlModel.updateModel();
                Thread.sleep(updateInterval);

            } catch (DropException d) {
                updateInterval = level;

            } catch (NextLevelException next) {
                controlView.removeKeys(keys);
                controlSound.stopAllSound(true);
                controlSound.playSound("levelup");
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.getCause();
                }
                controlView.addKeys(keys);
                level = next.getNewUpdateInterval();
                updateInterval = level;
                controlSound.loopSound("main");

            } catch (GameOverException g) {
                isRunning = false;
                gameOver = true;
                controlSound.stopAllSound(true);
                controlSound.playSound("gameover");

            } catch (InterruptedException e) {
                e.getCause();

            }
        }
    }

    @Override
    public void stopTetris() {
        if (isRunning) {
            isRunning = false;
            controlView.removeKeys(keys);
            controlSound.stopAllSound(true);
            controlView.paintBoard(false);
            controlModel.stopScoreTask();
        }
        if (paus) {
            resumeTetris();
        }
        if (thread != null) {
            while (thread.isAlive()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    @Override
    public void startTetris() {
        controlView.updateNextTetrimino(null);
        controlView.updateHold(null);
        controlView.updateScore(0);
        controlView.updateLevel(1);
        controlView.updateLines(0);
        Model model = new Model();
        model.addIControlView(controlView);
        model.addIControlSound(controlSound);
        menu = false;
        paus = false;
        this.addIControlModel(model);
        thread = new Thread(this);
        isRunning = true;
        gameOver = false;
        level = 350;
        updateInterval = level;
        if (controlSound.getMuteSound()) {
            controlView.paintBoard(true);
            controlSound.playSound("main");
            controlSound.loopSound("main");
            controlView.addKeys(keys);
            thread.start();
        } else {
            countDown = 3;
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    countDown--;
                    if (playCountDown(countDown)) {
                        controlView.paintBoard(true);
                        controlSound.playSound("main");
                        controlSound.loopSound("main");
                        controlView.addKeys(keys);
                        thread.start();
                        this.cancel();
                    }
                }
            };
            timer.scheduleAtFixedRate(task, 200, 1200);
        }
    }

    private boolean playCountDown(int i) {
        if (i == 2) {
            controlSound.playSound("three");
            return false;
        }
        if (i == 1) {
            controlSound.playSound("two");
            return false;
        } else {
            controlSound.playSound("one");
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.getCause();
            }
            return true;
        }
    }

    private synchronized void checkIfPaus() {
        while (paus) {
            try {
                wait();
            } catch (InterruptedException e) {
                notifyAll();
                e.getCause();
            }
        }
    }

    private void pauseTetris() {
        paus = true;
    }

    private synchronized void resumeTetris() {
        paus = false;
        notifyAll();
    }

    private class Keys implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public synchronized void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (isRunning) {
                if (!paus) {
                    try {
                        if (key == KeyEvent.VK_LEFT) {
                            controlModel.actOnKeys(key);
                        }
                        if (key == KeyEvent.VK_RIGHT) {
                            controlModel.actOnKeys(key);
                        }
                        if (key == KeyEvent.VK_UP  || key == KeyEvent.VK_X) {
                            controlModel.actOnKeys(key);
                        }
                        if (key == KeyEvent.VK_Z) {
                            controlModel.actOnKeys(key);
                        }
                        if (key == KeyEvent.VK_C) {
                            controlModel.actOnKeys(key);
                        }
                        if (key == KeyEvent.VK_DOWN) {
                            updateInterval = 40;
                            controlModel.addScore(true);
                        }
                        if (key == KeyEvent.VK_SPACE) {
                            controlModel.actOnKeys(true);
                            updateInterval = 1;
                        }
                    } catch (DropException d) {
                        updateInterval = level;
                    }
                }
                if (key == KeyEvent.VK_ESCAPE) {
                    if (!paus) {
                        pauseTetris();
                        controlSound.stopSound("main");
                    } else {
                        resumeTetris();
                        controlSound.loopSound("main");
                    }
                }
            }
            if (isRunning || gameOver) {
                if (key == KeyEvent.VK_ENTER) {
                    stopTetris();
                    startTetris();
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            if (isRunning) {
                if (key == KeyEvent.VK_DOWN) {
                    updateInterval = level;
                    controlModel.addScore(false);
                }
            }
        }
    }

    private class Mouse implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (isRunning && countDown == 0) {
                if (!paus) {
                    pauseTetris();
                }
                menu = !menu;
                controlView.showMenu(menu);
                if (menu) {
                    controlView.removeKeys(keys);
                    controlSound.stopSound("main");
                } else {
                    controlView.addKeys(keys);
                }
            }
            if (gameOver) {
                menu = !menu;
                controlView.showMenu(menu);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

    }

    private void addIControlModel(IControlModel model) {
        controlModel = model;
    }

    private void addIControlView(IControlView view) {
        controlView = view;
    }

    private void addIControlSound(IControlSound sound) {
        controlSound = sound;
    }

    @Override
    public boolean getIsRunning() {
        return isRunning;
    }

    @Override
    public boolean getIsMenu() {
        return menu;
    }

}
