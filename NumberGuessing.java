import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class NumberGuessing extends JFrame implements ActionListener {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 100;
    private static final int MAX_GUESSES = 10;

    private Random random;
    private int randomNumber;
    private int guessCount;
    private int score;

    private JPanel panel;
    private JLabel titleLabel;
    private JLabel instructionLabel;
    private JLabel guessCountLabel;
    private JLabel scoreLabel;
    private JTextField guessField;
    private JButton guessButton;
    public NumberGuessing() {
        super("Number Guessing Game");

        // generate a random number
        random = new Random();
        randomNumber = random.nextInt(MAX_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER;

        // initialize guess count and score
        guessCount = 0;
        score = 0;

        // create user interface components
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);

        titleLabel = new JLabel("Guess the Number!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        panel.add(titleLabel, c);

        instructionLabel = new JLabel("I'm thinking of a number between " + MIN_NUMBER + " and " + MAX_NUMBER + ".");
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        panel.add(instructionLabel, c);

        guessCountLabel = new JLabel("Guesses: " + guessCount + "/" + MAX_GUESSES);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        panel.add(guessCountLabel, c);

        scoreLabel = new JLabel("Score: " + score);
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        panel.add(scoreLabel, c);

        guessField = new JTextField(10);
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        panel.add(guessField, c);

        guessButton = new JButton("Guess");
        guessButton.addActionListener(this);
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 1;
        panel.add(guessButton, c);

        // add panel to frame and set frame properties
        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String guessString = guessField.getText();
        if (!guessString.isEmpty()) {
            try {
                int guess = Integer.parseInt(guessString);
                if (guess >= MIN_NUMBER && guess <= MAX_NUMBER) {
                    guessCount++;
                    guessCountLabel.setText("Guesses: " + guessCount + "/" + MAX_GUESSES);
                    if (guess == randomNumber) {
                        // correct guess
                        score += MAX_GUESSES - guessCount + 1;
                        JOptionPane.showMessageDialog(this, "Congratulations, you guessed the number!\nYour score is " + score + ".");
                        scoreLabel.setText("Score: " + score);
                        int choice = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "Play again?", JOptionPane.YES_NO_OPTION);
                        if (choice == JOptionPane.YES_OPTION) {
                                                    // generate a new random number and reset guess count and score
                        randomNumber = random.nextInt(MAX_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER;
                        guessCount = 0;
                        score = 0;
                        guessCountLabel.setText("Guesses: " + guessCount + "/" + MAX_GUESSES);
                        scoreLabel.setText("Score: " + score);
                    } else {
                        // user chose not to play again, exit the game
                        System.exit(0);
                    }
                } else {
                    // incorrect guess
                    String message;
                    if (guess < randomNumber) {
                        message = "Too low!";
                    } else {
                        message = "Too high!";
                    }
                    JOptionPane.showMessageDialog(this, message);
                    if (guessCount == MAX_GUESSES) {
                        // out of guesses, game over
                        JOptionPane.showMessageDialog(this, "Sorry, you are out of guesses.\nThe number was " + randomNumber + ".");
                        int choice = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "Play again?", JOptionPane.YES_NO_OPTION);
                        if (choice == JOptionPane.YES_OPTION) {
                            // generate a new random number and reset guess count and score
                            randomNumber = random.nextInt(MAX_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER;
                            guessCount = 0;
                            score = 0;
                            guessCountLabel.setText("Guesses: " + guessCount + "/" + MAX_GUESSES);
                            scoreLabel.setText("Score: " + score);
                        } else {
                            // user chose not to play again, exit the game
                            System.exit(0);
                        }
                    }
                }
                guessField.setText("");
                guessField.requestFocus();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid guess.\nPlease enter a number between " + MIN_NUMBER + " and " + MAX_NUMBER + ".");
                guessField.setText("");
                guessField.requestFocus();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid guess.\nPlease enter a number.");
            guessField.setText("");
            guessField.requestFocus();
        }
    }
}

public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
        public void run() {
            new NumberGuessing();
        }
    });
}
}

                        
