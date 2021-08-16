import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    protected JPanel panel;
    private JButton closeButton;

    public MainFrame()  {
        init();
    }

    private void init(){
        JFrame frame = new JFrame("Hangman");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                main.closeApp();
            }
        });
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
