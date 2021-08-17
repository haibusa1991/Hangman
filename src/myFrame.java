import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class myFrame extends JFrame {
    private JPanel defaultPanel;
    private JButton button_close;
    Dimension WINDOW_SIZE = new Dimension(640, 480);

    public myFrame() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        super("Hangman");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(defaultPanel);
        this.pack();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(helpers.getIconPath()));
        this.setSize(WINDOW_SIZE);
        helpers.setCentered(this);
        this.setVisible(true);

        button_close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeWindow();
            }
        });
    }

    private void closeWindow() {
        this.dispose();
    }
}
