import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WordCounter extends JFrame implements ActionListener {
    JTextArea textArea;
    JButton countButton;
    JLabel resultLabel;

    public WordCounter() {
        setTitle("Word Counter");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // center the window

        // Create components
        textArea = new JTextArea(8, 30);
        countButton = new JButton("Count Words");
        resultLabel = new JLabel("Words: 0");

        // Add action listener
        countButton.addActionListener(this);

        // Layout setup
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(countButton, BorderLayout.CENTER);
        bottomPanel.add(resultLabel, BorderLayout.SOUTH);

        panel.add(bottomPanel, BorderLayout.SOUTH);

        // Add panel to frame
        add(panel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String text = textArea.getText().trim();
        if (text.isEmpty()) {
            resultLabel.setText("Words: 0");
        } else {
            String[] words = text.split("\\s+"); // split by whitespace
            resultLabel.setText("Words: " + words.length);
        }
    }

    public static void main(String[] args) {
        new WordCounter();
    }
}
