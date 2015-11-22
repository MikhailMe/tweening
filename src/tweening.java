import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class tweening{

    private JFrame mainFrame = new JFrame("Tweening");
    private MyPanel mainPanel = new MyPanel();
    private JButton button = new JButton("Let's tween");
    private JTextArea textArea = new JTextArea("Amount of ticks: 0");
    private Font myFont = new Font("Arial",0,20);
    final static int amount = 8;
    public static Point []start = new Point[amount];
    public static Point []finish = new Point[amount];
    public static Point []current = new Point[amount];

    public tweening(){
        start[0] = new Point(50.0*5,30.0*5);    finish[0] = new Point(50.0*5,40.0*5);   current[0] = new Point(0.0,0.0);
        start[1] = new Point(40.0*5,50.0*5);    finish[1] = new Point(40.0*5,20.0*5);   current[1] = new Point(0.0,0.0);
        start[2] = new Point(20.0*5,60.0*5);    finish[2] = new Point(30.0*5,50.0*5);   current[2] = new Point(0.0,0.0);
        start[3] = new Point(40.0*5,70.0*5);    finish[3] = new Point(40.0*5,80.0*5);   current[3] = new Point(0.0,0.0);
        start[4] = new Point(50.0*5,90.0*5);    finish[4] = new Point(50.0*5,60.0*5);   current[4] = new Point(0.0,0.0);
        start[5] = new Point(60.0*5,70.0*5);    finish[5] = new Point(60.0*5,80.0*5);   current[5] = new Point(0.0,0.0);
        start[6] = new Point(80.0*5,60.0*5);    finish[6] = new Point(70.0*5,50.0*5);   current[6] = new Point(0.0,0.0);
        start[7] = new Point(60.0*5,50.0*5);    finish[7] = new Point(60.0*5,20.0*5);   current[7] = new Point(0.0,0.0);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ActionListener task = new ActionListener() {
                    int k = 0;
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (k < 10){
                            ++k;
                            for (int i = 0; i < amount; ++i){
                                current[i].init(start[i].getX()*(1-k*0.1)+finish[i].getX()*k*0.1,
                                                start[i].getY()*(1-k*0.1)+finish[i].getY()*k*0.1);
                            }
                            textArea.setText("Amount of ticks: " + k);
                            mainPanel.repaint();
                        }
                    }
                };
                Timer timer = new Timer(600,task);
                timer.start();
            }
        });
    }

    // создание основного окна
    public void create(){
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setBounds(400,50,600,650);
        mainFrame.setVisible(true);
        mainFrame.add(mainPanel);
        mainFrame.setResizable(false);
        mainFrame.add(mainPanel);
        mainPanel.setBackground(new Color(202,218,150));
        mainPanel.setLayout(null);
        mainPanel.add(button);
        button.setBounds(350,400,200,100);
        button.setBackground(new Color(251,236,93));
        mainPanel.add(textArea);
        textArea.setBounds(350,550,200,50);
        textArea.setEditable(false);
        textArea.setBackground(new Color(202,218,150));
        textArea.setFont(myFont);
    }
}

class MyPanel extends JPanel{

    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.setColor(Color.BLACK);
        // отрисовка начальной фигуры
        draw(g,tweening.start,-90,-140);
        // отрисовка конечной фигуры
        draw(g,tweening.finish,200,-90);
        // отрисовка текущей фигуры
        draw(g,tweening.current,-90,180);
    }

    // отрисовка фигуры заданной массивом точек mas
    public void draw(Graphics g,Point[] mas,int kX,int kY){
        for (int i = 0; i < tweening.amount-1; ++i)
            g.drawLine((int) mas[i].getX()+kX, (int) mas[i].getY()+kY,
                    (int) mas[i + 1].getX()+kX, (int) mas[i + 1].getY()+kY);
        g.drawLine((int) mas[tweening.amount-1].getX()+kX,(int) mas[tweening.amount-1].getY()+kY,
                (int) mas[0].getX()+kX,(int) mas[0].getY()+kY);
    }
}

// класс точка
class Point{
    private double x;
    public double getX() {return x;}
    private double y;
    public double getY() {return y;}
    public Point(double x1, double y1){
        x =  x1;
        y =  y1;
    }
    public void init(double x1, double y1){
        x = x1;
        y = y1;
    }
}