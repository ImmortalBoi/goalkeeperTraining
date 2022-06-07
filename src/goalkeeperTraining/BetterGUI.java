package goalkeeperTraining;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BetterGUI {
    public static final int width = 450;
    public static final int height = width / 12 * 9;
    public static final int scale = 2;
    static JFrame frame = new JFrame("FootBallGame");

    Toolkit tk = Toolkit.getDefaultToolkit();
    Image spriteGoalKeeperStanding = tk.getImage("src/goalkeeperTraining/SpriteFiles/GoalKeeperSprites/goalkeeperstanding.png");
    Image backgroundImage = tk.getImage("src/goalkeeperTraining/SpriteFiles/BackgroundImages/footballGoalFull.png");
    Image ballImage = tk.getImage("src/goalkeeperTraining/SpriteFiles/BallSprites/BallImage2.png");

    public Rectangle autoPlayBtn = new Rectangle(width / 2 - 50, height / 2 + 350, 150, 50);
    public Rectangle freePlayBtn = new Rectangle(width / 2 + 150, height / 2 + 350, 150, 50);
    public Rectangle quitBtn = new Rectangle(width / 2 + 350, height / 2 + 350, 150, 50);

    JLabel playerView = new JLabel("");
    JLabel player1Score = new JLabel("Player 1 Score: 0");
    JLabel player2Score = new JLabel("Player 2 Score: 0");
    JLabel player3Score = new JLabel("Player 3 Score: 0");
    JLabel player4Score = new JLabel("Player 4 Score: 0");

    Goalkeeper goalkeeper;
    FootballBall ballInstance;

    BetterGUI(Goalkeeper gk, FootballBall fb) {
        goalkeeper = gk;
        ballInstance = fb;
    }


    public enum STATE {
        MENU,
        FREEPLAY,
        AUTOPLAY,
        Quit
    }

    public static STATE state = STATE.MENU;

    public void runGUI() {
        frame.setPreferredSize(new Dimension(width * scale, height * scale));
        frame.setMinimumSize(new Dimension(width * scale, height * scale));
        frame.setMaximumSize(new Dimension(width * scale, height * scale));

        frame.pack();
        frame.add(new Animation(), BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.addMouseListener(new mouseListener());
    }

    public void runAutoPlay() {
        frame.add(playerView);
        frame.add(new Animation(goalkeeper, ballInstance), BorderLayout.CENTER);
        frame.repaint();
    }

    public void freePlayGui() {
        frame.add(new Animation(goalkeeper), BorderLayout.CENTER);
        frame.repaint();
    }

    class Animation extends JPanel {
        Goalkeeper goalkeeper;
        FootballBall ballInstance;
        Timer timer;
        PlayersMovement playersMovement;

        Animation(Goalkeeper goalkeeper, FootballBall ballInstance) {
            frame.add(playerView);
            this.goalkeeper = goalkeeper;
            this.ballInstance = ballInstance;
            playersMovement = new PlayersMovement(goalkeeper, ballInstance);
            timer = new Timer(10, new BetterGUI.Animation.moveListener());
            super.setLayout(new GridLayout(0, 1));
            timer.start();
            frame.repaint();
        }

        Animation() {
            this.goalkeeper = BetterGUI.this.goalkeeper;
            this.ballInstance = BetterGUI.this.ballInstance;
            playersMovement = new PlayersMovement(goalkeeper, ballInstance);
            super.setLayout(new GridLayout(0, 1));
            frame.repaint();
        }

        Animation(Goalkeeper goalkeeper) {
            this.goalkeeper = goalkeeper;
            this.ballInstance = BetterGUI.this.ballInstance;
            timer = new Timer(30, new BetterGUI.Animation.moveListener());
            playersMovement = new PlayersMovement(goalkeeper, ballInstance);
            super.setLayout(new GridLayout(0, 1));
            timer.start();
            frame.repaint();
        }

        public void paintComponent(Graphics g) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            if (state == STATE.FREEPLAY || state == STATE.AUTOPLAY) {
                frame.addKeyListener(new keyInputs(playersMovement));
                g.drawImage(spriteGoalKeeperStanding, goalkeeper.position[0], goalkeeper.position[1], this);
                g.drawImage(ballImage, ballInstance.position[0], ballInstance.position[1], this);
                playerView.setFont(new Font("Arial", Font.BOLD, 36));
                player1Score.setFont(new Font("Arial", Font.BOLD, 36));
                player2Score.setFont(new Font("Arial", Font.BOLD, 36));
                player3Score.setFont(new Font("Arial", Font.BOLD, 36));
                player4Score.setFont(new Font("Arial", Font.BOLD, 36));
                playerView.setForeground(Color.white);
                Dimension playerSize = playerView.getPreferredSize();
                player1Score.setBounds(0, 100, playerSize.width, playerSize.height);
                player2Score.setBounds(0, 200, playerSize.width, playerSize.height);
                player3Score.setBounds(0, 300, playerSize.width, playerSize.height);
                player4Score.setBounds(0, 400, playerSize.width, playerSize.height);
                playerView.setSize(playerSize);
                frame.add(playerView);
                frame.repaint();
            }
            if (state == STATE.MENU) {
                Graphics2D g2d = (Graphics2D) g;
                Font ft_title = new Font("arial", Font.BOLD, 50);
                g.setFont(ft_title);
                g.setColor(Color.white);
                g.drawString("FOOTBALL GAME", width / 2, 100);

                Font ft_btn = new Font("arial", Font.BOLD, 30);
                g.setFont(ft_btn);
                g.drawString("Auto Play", autoPlayBtn.x + 6, autoPlayBtn.y + 37);
                g.drawString("Free Play", freePlayBtn.x + 8, freePlayBtn.y + 37);
                g.drawString("Quit", quitBtn.x + 40, quitBtn.y + 37);

                g2d.setColor(Color.BLACK);
                g2d.draw(autoPlayBtn);
                g2d.draw(freePlayBtn);
                g2d.draw(quitBtn);
                frame.repaint();
            }
            return;
        }

        private class moveListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                if (state == STATE.AUTOPLAY) {
                    playersMovement.movement(playersMovement.goalkeeper, playersMovement.ballInstance, timer);
                    frame.repaint();
                }
                if (state == STATE.FREEPLAY) {
                    playersMovement.singleMovement();
                    frame.repaint();
                }
            }
        }

        public class keyInputs extends KeyAdapter {
            PlayersMovement playersMovement;

            public keyInputs(PlayersMovement playersMovement) {
                this.playersMovement = Animation.this.playersMovement;
            }

            public void keyPressed(KeyEvent e) {
                    playersMovement.keyPressed(e);
                    frame.repaint();
            }

        }

    }

    private class mouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            int mouseX = e.getX();
            int mouseY = e.getY();
//        public Rectangle autoPlayBtn = new Rectangle(width/2 - 50,height/2 + 350 ,150,50);
//        public Rectangle freePlayBtn = new Rectangle(width/2 + 150,height/2 + 350,150,50);
//        public Rectangle quitBtn = new Rectangle(width/2 + 350,height/2 + 350,150,50);
            //play btn pressed
            if (mouseX >= BetterGUI.width / 2 - 50 && mouseX <= BetterGUI.width / 2 + 100) {
                if (mouseY >= BetterGUI.height / 2 + 350 && mouseY <= BetterGUI.height / 2 + 400) {
                    System.out.println("AutoPlay CLicked");
                    BetterGUI.state = BetterGUI.STATE.AUTOPLAY;
                    frame.removeMouseListener(this);
                }
            }
            if (mouseX >= BetterGUI.width / 2 + 150 && mouseX <= BetterGUI.width / 2 + 300) {
                if (mouseY >= BetterGUI.height / 2 + 350 && mouseY <= BetterGUI.height / 2 + 400) {
                    System.out.println("FreePlay Clicked");
                    BetterGUI.state = STATE.FREEPLAY;
                    frame.removeMouseListener(this);
                }
            }
            if (mouseX >= BetterGUI.width/2 +350 && mouseX <= BetterGUI.width / 2 + 500 ){
                if (mouseY >= BetterGUI.height / 2 + 350 && mouseY <= BetterGUI.height / 2 + 400){
                    System.out.println("Quit Clicked");
                    BetterGUI.state = STATE.Quit;
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}

