package hangman.frameController;

import hangman.util.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class GameFrame extends JFrame {
    private static GameFrame instance = null;
    private JPanel defaultPanel;
    private JButton aButton;
    private JButton iButton;
    private JButton pButton;
    private JButton shButton;
    private JButton bButton;
    private JButton vButton;
    private JButton gButton;
    private JButton dButton;
    private JButton eButton;
    private JButton zhButton;
    private JButton zButton;
    private JButton ikButton;
    private JButton kButton;
    private JButton lButton;
    private JButton mButton;
    private JButton nButton;
    private JButton oButton;
    private JButton rButton;
    private JButton sButton;
    private JButton tButton;
    private JButton uButton;
    private JButton fButton;
    private JButton hButton;
    private JButton chButton;
    private JButton tzhButton;
    private JButton shtButton;
    private JButton erGolyamButton;
    private JButton erMalakButton;
    private JButton yuButton;
    private JButton yaButton;
    private JLabel hangedImage;
    private JButton exitButton;
    private JButton newGameButton;
    private JButton menuButton;

    Dimension WINDOW_SIZE = new Dimension(850, 600);

    private GameFrame() {
        super("Hangman");
        initializeFrame();
        initializeButtons();
    }

    public static GameFrame getInstance() {
        if (instance == null) {
            instance = new GameFrame();
        }
        return instance;
    }

    protected void showFrame() {
        if (!this.isVisible()) {
            this.setVisible(true);
        }
    }

    private void initializeFrame() {
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(defaultPanel);
        this.pack();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(Util.getWindowIconPath()));
        this.setSize(WINDOW_SIZE);
        Util.setCentered(this);
    }

    private void initializeButtons() {
        aButton.addActionListener(e -> {
            new ErrorDialog("A");
        });

        bButton.addActionListener(e -> {
            ErrorDialog ed = new ErrorDialog("Б");
        });

        vButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ErrorDialog ed = new ErrorDialog("В");
            }
        });

        gButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ErrorDialog ed = new ErrorDialog("Г");
            }
        });

        dButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ErrorDialog ed = new ErrorDialog("Д");
            }
        });

        eButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ErrorDialog ed = new ErrorDialog("Е");
            }
        });

        zhButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ErrorDialog ed = new ErrorDialog("Ж");
            }
        });

        zButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ErrorDialog ed = new ErrorDialog("З");
            }
        });

        iButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ErrorDialog ed = new ErrorDialog("И");
            }
        });

        ikButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ErrorDialog ed = new ErrorDialog("Й");
            }
        });

        kButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ErrorDialog ed = new ErrorDialog("К");
            }
        });

        lButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ErrorDialog ed = new ErrorDialog("Л");
            }
        });

        mButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ErrorDialog ed = new ErrorDialog("М");
            }
        });

        nButton.addActionListener(e -> {
            ErrorDialog ed = new ErrorDialog("Н");
        });

        oButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ErrorDialog ed = new ErrorDialog("О");
            }
        });

        pButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ErrorDialog ed = new ErrorDialog("П");
            }
        });

        rButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ErrorDialog ed = new ErrorDialog("Р");
            }
        });

        sButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ErrorDialog ed = new ErrorDialog("С");
            }
        });

        tButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ErrorDialog ed = new ErrorDialog("Т");
            }
        });

        uButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ErrorDialog ed = new ErrorDialog("У");
            }
        });

        fButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ErrorDialog ed = new ErrorDialog("Ф");
            }
        });

        hButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ErrorDialog ed = new ErrorDialog("Х");
            }
        });

        tzhButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ErrorDialog ed = new ErrorDialog("Ц");
            }
        });

        chButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ErrorDialog ed = new ErrorDialog("Ч");
            }
        });

        shButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ErrorDialog ed = new ErrorDialog("Ш");
            }
        });

        shtButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ErrorDialog ed = new ErrorDialog("Щ");
            }
        });

        erGolyamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ErrorDialog ed = new ErrorDialog("Ъ");
            }
        });

        erMalakButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ErrorDialog ed = new ErrorDialog("Ь");
            }
        });

        yuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ErrorDialog ed = new ErrorDialog("Ю");
            }
        });

        yaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ErrorDialog ed = new ErrorDialog("Я");
            }
        });
    }

    protected void setMockImage(BufferedImage image) {

        image = Util.resize(image, 300, 500);
        this.hangedImage.setText("");
        this.hangedImage.setIcon(new ImageIcon(image));
    }

}
