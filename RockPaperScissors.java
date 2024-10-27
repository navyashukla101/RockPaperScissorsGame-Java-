import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RockPaperScissors extends JFrame implements ActionListener {
    private JButton rockButton, paperButton, scissorsButton;
    private JLabel resultLabel, scoreLabel;
    private int userScore = 0, computerScore = 0;

    public RockPaperScissors() {
        setTitle("Rock, Paper, Scissors");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        rockButton = new JButton("Rock");
        paperButton = new JButton("Paper");
        scissorsButton = new JButton("Scissors");

        rockButton.addActionListener(this);
        paperButton.addActionListener(this);
        scissorsButton.addActionListener(this);

        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);

        resultLabel = new JLabel("Make your choice!", JLabel.CENTER);
        scoreLabel = new JLabel("User: 0 | Computer: 0", JLabel.CENTER);

        add(resultLabel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(scoreLabel, BorderLayout.NORTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String userChoice = e.getActionCommand();
        String computerChoice = getComputerChoice();
        String result = determineWinner(userChoice, computerChoice);
        updateScore(result);
        resultLabel.setText("You chose " + userChoice + ", Computer chose " + computerChoice + ". " + result);
        scoreLabel.setText("User: " + userScore + " | Computer: " + computerScore);
    }

    private String getComputerChoice() {
        String[] choices = { "Rock", "Paper", "Scissors" };
        Random random = new Random();
        return choices[random.nextInt(choices.length)];
    }

    private String determineWinner(String userChoice, String computerChoice) {
        if (userChoice.equals(computerChoice)) {
            return "It's a tie!";
        } else if ((userChoice.equals("Rock") && computerChoice.equals("Scissors")) ||
                (userChoice.equals("Paper") && computerChoice.equals("Rock")) ||
                (userChoice.equals("Scissors") && computerChoice.equals("Paper"))) {
            return "You win!";
        } else {
            return "Computer wins!";
        }
    }

    private void updateScore(String result) {
        if (result.equals("You win!")) {
            userScore++;
        } else if (result.equals("Computer wins!")) {
            computerScore++;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RockPaperScissors game = new RockPaperScissors();
            game.setVisible(true);
        });
    }
}
