package com.frames;

import com.enums.FrameType;
import com.gameController.GameState;
import com.gameController.Letter;
import com.interfaces.HangmanFrame;
import com.logicController.LogicController;
import com.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static com.strings.GameFrameStrings.*;

public class GameFrame extends JFrame implements HangmanFrame {
    private JPanel gameFrame;

    private List<JButton> letters;

    private JButton aButton;
    private JButton bButton;
    private JButton vButton;
    private JButton gButton;
    private JButton dButton;
    private JButton eButton;
    private JButton zhButton;
    private JButton zButton;
    private JButton iButton;
    private JButton ikButton;
    private JButton kButton;
    private JButton lButton;
    private JButton mButton;
    private JButton nButton;
    private JButton oButton;
    private JButton pButton;
    private JButton rButton;
    private JButton sButton;
    private JButton tButton;
    private JButton uButton;
    private JButton fButton;
    private JButton hButton;
    private JButton chButton;
    private JButton tzhButton;
    private JButton shButton;
    private JButton shtButton;
    private JButton erGolyamButton;
    private JButton erMalakButton;
    private JButton yuButton;
    private JButton yaButton;

    private JButton exitButton;
    private JButton newGameButton;
    private JButton menuButton;

    private JLabel hangedImage;

    private JLabel labelUsedLetters;
    private JLabel wordLetters;

    private JLabel wordDescription;

    Dimension WINDOW_SIZE = new Dimension(850, 600);

    public GameFrame() {
        initializeFrame();
        attachListeners();
        initializeLetterButtons();
        initializeControlButtons();
        initializeText();
    }

    private void initializeFrame() {

        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setContentPane(gameFrame);
        this.pack();
        this.setSize(WINDOW_SIZE);
        Utils.centerFrame(this);
    }

    private void attachListeners() {
        LogicController lc = LogicController.getInstance();

        // when cross is clicked
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                lc.gameFrameButtonClickExit();
            }
        });

        // on escape key press
        this.gameFrame.registerKeyboardAction(e -> lc.gameFrameButtonClickExit(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_FOCUSED);

    }

    private void initializeText() {
        this.setTitle(GAME_FRAME_TITLE);

        this.labelUsedLetters.setText(GAME_FRAME_LETTERS_USED);
        this.wordLetters.setText(GAME_FRAME_WORD);
        this.wordDescription.setText(GAME_FRAME_WORD_DESCRIPTION);

        this.exitButton.setText(GAME_FRAME_EXIT);
        this.menuButton.setText(GAME_FRAME_MAIN_MENU);
        this.newGameButton.setText(GAME_FRAME_NEW_GAME);
    }

    private void initializeLetterButtons() {
        letters = new ArrayList<>();

        aButton.setText(GAME_FRAME_LETTER_A);
        letters.add(aButton);

        bButton.setText(GAME_FRAME_LETTER_B);
        letters.add(bButton);

        vButton.setText(GAME_FRAME_LETTER_V);
        letters.add(vButton);

        gButton.setText(GAME_FRAME_LETTER_G);
        letters.add(gButton);

        dButton.setText(GAME_FRAME_LETTER_D);
        letters.add(dButton);

        eButton.setText(GAME_FRAME_LETTER_E);
        letters.add(eButton);

        zhButton.setText(GAME_FRAME_LETTER_ZH);
        letters.add(zhButton);

        zButton.setText(GAME_FRAME_LETTER_Z);
        letters.add(zButton);

        iButton.setText(GAME_FRAME_LETTER_I);
        letters.add(iButton);

        ikButton.setText(GAME_FRAME_LETTER_IK);
        letters.add(ikButton);

        kButton.setText(GAME_FRAME_LETTER_K);
        letters.add(kButton);

        lButton.setText(GAME_FRAME_LETTER_L);
        letters.add(lButton);

        mButton.setText(GAME_FRAME_LETTER_M);
        letters.add(mButton);

        nButton.setText(GAME_FRAME_LETTER_N);
        letters.add(nButton);

        oButton.setText(GAME_FRAME_LETTER_O);
        letters.add(oButton);

        pButton.setText(GAME_FRAME_LETTER_P);
        letters.add(pButton);

        rButton.setText(GAME_FRAME_LETTER_R);
        letters.add(rButton);

        sButton.setText(GAME_FRAME_LETTER_S);
        letters.add(sButton);

        tButton.setText(GAME_FRAME_LETTER_T);
        letters.add(tButton);

        uButton.setText(GAME_FRAME_LETTER_U);
        letters.add(uButton);

        fButton.setText(GAME_FRAME_LETTER_F);
        letters.add(fButton);

        hButton.setText(GAME_FRAME_LETTER_H);
        letters.add(hButton);

        chButton.setText(GAME_FRAME_LETTER_CH);
        letters.add(chButton);

        tzhButton.setText(GAME_FRAME_LETTER_TZH);
        letters.add(tzhButton);

        shButton.setText(GAME_FRAME_LETTER_SH);
        letters.add(shButton);

        shtButton.setText(GAME_FRAME_LETTER_SHT);
        letters.add(shtButton);

        erGolyamButton.setText(GAME_FRAME_LETTER_ERGOLYAM);
        letters.add(erGolyamButton);

        erMalakButton.setText(GAME_FRAME_LETTER_ERMALAK);
        letters.add(erMalakButton);

        yuButton.setText(GAME_FRAME_LETTER_YU);
        letters.add(yuButton);

        yaButton.setText(GAME_FRAME_LETTER_YA);
        letters.add(yaButton);

        for (JButton letterButton : letters) {
            letterButton.addActionListener(e -> {
                LogicController.getInstance().gameFrameLetterButtonClick(new Letter(letterButton.getText()));
            });
        }

    }

    private void initializeControlButtons() {
        LogicController lc = LogicController.getInstance();

        this.exitButton.addActionListener(e -> lc.gameFrameButtonClickExit());

        this.menuButton.addActionListener(e -> lc.gameFrameButtonClickMenu());

        this.newGameButton.addActionListener(e -> lc.gameFrameButtonClickNewGame());
    }

    @Override
    public void showFrame() {
        this.setVisible(true);
    }

    @Override
    public void hideFrame() {
        this.dispose();
    }

    @Override
    public FrameType getFrameType() {
        return FrameType.GAME_FRAME;
    }

    public void setGameState(GameState gameState) {
        this.wordLetters.setText(gameState.getWordState());
        this.wordDescription.setText("<html><center>" + gameState.getDescription() + "</center></html>");

        this.hangedImage.setText("");
        BufferedImage currentStep = Utils.resizeImage(gameState.getCurrentStep(), 300, 300);
        this.hangedImage.setIcon(new ImageIcon(currentStep));

        this.labelUsedLetters.setText(gameState.getUsedLetters());
    }

    public GameState getGameState() {
        Icon icon = this.hangedImage.getIcon();

        BufferedImage currentHangingStep = new BufferedImage(icon.getIconWidth(),
                icon.getIconHeight(),
                BufferedImage.TYPE_INT_ARGB);

        Graphics graphics = currentHangingStep.createGraphics();
        graphics.drawImage(currentHangingStep, 0, 0, null);
        icon.paintIcon(null, graphics, 0, 0);
        graphics.dispose();

        return new GameState(this.wordLetters.getText(), this.wordDescription.getText(), currentHangingStep, this.labelUsedLetters.getText());
    }

    public void disableLetters() {
        for (JButton letter : letters) {
            letter.setEnabled(false);
        }
    }

    public void enableLetters() {
        for (JButton letter : letters) {
            letter.setEnabled(true);
        }
    }
}
