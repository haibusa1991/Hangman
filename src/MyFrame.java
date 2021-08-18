import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame {
    private static MyFrame instance = null;
    private JPanel defaultPanel;
    private JButton button_close;

    public String getLabel_modeText() {
        return label_mode.getText();
    }

    public void setLabel_modeText(String text) {
        this.label_mode.setText(text);
    }

    private JLabel label_mode;
    Dimension WINDOW_SIZE = new Dimension(640, 480);

    private MyFrame() {
        super("Hangman");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(defaultPanel);
        this.pack();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(Helpers.getIconPath()));
        this.setSize(WINDOW_SIZE);
        Helpers.setCentered(this);
        this.setVisible(true);

        button_close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameController.closeWindow(instance);
            }
        });
    }

    public static MyFrame getInstance() {
        if (instance == null) {
            instance = new MyFrame();
        }
        return instance;
    }



}
