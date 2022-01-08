package com.frames;

import com.enums.FrameType;
import com.interfaces.HangmanFrame;
import com.logicController.LogicController;
import com.strings.GameFrameStrings;
import com.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static com.strings.GameFrameStrings.*;
import static com.strings.ErrorMessages.*;

public class GameFrame extends JFrame implements HangmanFrame {
    private JPanel defaultPanel;

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

    private GameFrame() {
        initializeFrame();
        attachListeners();
        initializeLetterButtons();
        initializeControlButtons();
        initializeText();
    }

    private void initializeFrame() {
        this.setTitle(GAMEFRAME_TITLE);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setContentPane(defaultPanel);
        this.pack();
//        this.setIconImage(Toolkit.getDefaultToolkit().getImage(Utils.getWindowIconPath())); //todo fix me
        this.setSize(WINDOW_SIZE);
        Utils.centerFrame(this);
    }

    private void attachListeners() {
        // call onCancel() when cross is clicked
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                closeWindow();
            }
        });

        // call onCancel() on ESCAPE
        this.defaultPanel.registerKeyboardAction(e -> closeWindow(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    }

    private void initializeText() {
        this.labelUsedLetters.setText(GAMEFRAME_LETTERS_USED);
        this.wordLetters.setText(GAMEFRAME_WORD);
        this.wordDescription.setText(GAMEFRAME_WORD_DESCRIPTION);

        this.exitButton.setText(GAMEFRAME_EXIT);
        this.menuButton.setText(GAMEFRAME_MAIN_MENU);
        this.newGameButton.setText(GAMEFRAME_NEW_GAME);
    }

    private void initializeLetterButtons() {
        letters = new ArrayList<>();

        letters.add(aButton);
        letters.add(bButton);
        letters.add(vButton);
        letters.add(gButton);
        letters.add(dButton);
        letters.add(eButton);
        letters.add(zhButton);
        letters.add(zButton);
        letters.add(iButton);
        letters.add(ikButton);
        letters.add(kButton);
        letters.add(lButton);
        letters.add(mButton);
        letters.add(nButton);
        letters.add(oButton);
        letters.add(pButton);
        letters.add(rButton);
        letters.add(sButton);
        letters.add(tButton);
        letters.add(uButton);
        letters.add(fButton);
        letters.add(hButton);
        letters.add(chButton);
        letters.add(tzhButton);
        letters.add(shButton);
        letters.add(shtButton);
        letters.add(erGolyamButton);
        letters.add(erMalakButton);
        letters.add(yuButton);
        letters.add(yaButton);

        for (JButton letterButton : letters) {
            String letterString;
            try {
                letterString = GameFrameStrings.class.getDeclaredField(getCorrespondingString(letterButton)).toString(); //this is some bullshit :<
            } catch (NoSuchFieldException e) {
                letterString = GAMEFRAME_LETTER_NOT_FOUND_IN_STRINGS;
            }

            letterButton.setText(letterString);
            letterButton.addActionListener(e -> {
                LogicController.getInstance().letterButtonPress(letterButton.getText());
            });
        }

    }

    private String getCorrespondingString(JButton button) {
        return GAMEFRAME_LETTER_PREFIX + button.getName().replace(GAMEFRAME_LETTER_BUTTON_SUFFIX, GAMEFRAME_EMPTY_STRING).toUpperCase();
    }

    private void initializeControlButtons() {


        this.exitButton.addActionListener(e -> closeWindow());

        this.menuButton.addActionListener(e -> LogicController.getInstance().gameFrameButtonClickMenu());

        this.newGameButton.addActionListener(e -> LogicController.getInstance().gameFrameButtonClickNewGame());
    }

    private void closeWindow() {
        LogicController.getInstance().gameFrameButtonClickExit();
    }

    protected void setMockImage(BufferedImage image) {
        image = Utils.resize(image, 300, 300);
        this.hangedImage.setText("");
        this.hangedImage.setIcon(new ImageIcon(image));
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

}
