package Utils;

import java.awt.*;

/**
 * This class holds dimensions of different panelns, buttons etc.
 */
public class Constants {
    private static final Dimension FRAME_SIZE = new Dimension(458, 667);
    private static final Dimension BOARD_SIZE = new Dimension(270, 648);
    private static final Dimension PANEL_SIZE = new Dimension(167, 669);
    private static final Dimension PLAY_BUTTON_POS = new Dimension(135, 314);
    private static final Dimension SETTINGS_BUTTON_POS = new Dimension(135, 382);
    private static final Dimension INFO_BUTTON_POS = new Dimension(135, 449);
    private static final Dimension BUTTON_SIZE = new Dimension(189, 42);
    private static final Dimension ONOFF_BUTTON_SIZE = new Dimension(73,37);
    private static final Dimension SOUND_BUTTON_POS = new Dimension(273,411);
    private static final Dimension MUSIC_BUTTON_POS = new Dimension(273,348);
    private static final Dimension TETRIMINO_SIZE = new Dimension(27, 27);
    private static final Dimension BOARD_SCALE = new Dimension(10, 24);

    public static Dimension getFrameSize() {
        return new Dimension(FRAME_SIZE);
    }

    public static Dimension getBoardSize() {
        return new Dimension(BOARD_SIZE);
    }

    public static Dimension getPanelSize() {
        return new Dimension(PANEL_SIZE);
    }

    public static Dimension getPlayButtonPos() {
        return new Dimension(PLAY_BUTTON_POS);
    }

    public static Dimension getSettingsButtonPos() {
        return new Dimension(SETTINGS_BUTTON_POS);
    }

    public static Dimension getInfoButtonPos() {
        return new Dimension(INFO_BUTTON_POS);
    }

    public static Dimension getButtonSize() {
        return new Dimension(BUTTON_SIZE);
    }

    public static Dimension getOnoffButtonSize() {
        return new Dimension(ONOFF_BUTTON_SIZE);
    }

    public static Dimension getSoundButtonPos() {
        return new Dimension(SOUND_BUTTON_POS);
    }

    public static Dimension getMusicButtonPos() {
        return new Dimension(MUSIC_BUTTON_POS);
    }

    public static Dimension getTetriminoSize() {
        return new Dimension(TETRIMINO_SIZE);
    }

    public static Dimension getBoardScale() {
        return new Dimension(BOARD_SCALE);
    }
}