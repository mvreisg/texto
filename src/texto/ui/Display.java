package texto.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import texto.model.Challenge;

public class Display {

    private JFrame frame;
    
    private JPanel mainPanel;
    private LayoutManager mainPanelLayout;
    
    private JPanel leftPanel;
    private LayoutManager leftPanelLayout;    
    private JTextArea leftPanelTextArea;
    private JScrollPane leftPanelScrollPane;
    
    private JPanel rightPanel;
    private LayoutManager rightPanelLayout;
    private JTextArea rightPanelTextArea;
    private JScrollPane rightPanelScrollPane;
    private JButton rightPanelButton;
    
    private List<Challenge> challenges;
    private Challenge challenge;
    private int index = 0;
    private boolean started;
    
    public Display(List<Challenge> challenges){
        super();
        this.challenges = new ArrayList<>(challenges);
        frame = new JFrame("texto");
        mainPanel = new JPanel();
        leftPanel = new JPanel();
        rightPanel = new JPanel();
        mainPanelLayout = new BoxLayout(mainPanel, BoxLayout.X_AXIS);
        leftPanelLayout = new BoxLayout(leftPanel, BoxLayout.Y_AXIS);
        rightPanelLayout = new BoxLayout(rightPanel, BoxLayout.Y_AXIS);
        leftPanelTextArea = new JTextArea();
        rightPanelTextArea = new JTextArea();
        leftPanelScrollPane = new JScrollPane(leftPanelTextArea);
        rightPanelScrollPane = new JScrollPane(rightPanelTextArea);
        rightPanelButton = new JButton(getAction());
        
    }
    
    private AbstractAction getAction(){
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!started){
                    started = true;
                }
                else{
                    if (index >= challenges.size()){
                        index = 0;
                    }
                }
                if (challenge == null){
                    challenge = challenges.get(index);
                    leftPanelTextArea.setText(challenge.getOriginalData());
                    rightPanelTextArea.setText(challenge.getWrongData());    
                }
                if (challenge.compare(rightPanelTextArea.getText())){
                    index++;
                    challenge = null;
                    rightPanelTextArea.setBackground(Color.WHITE);
                    challenge = challenges.get(index);
                    leftPanelTextArea.setText(challenge.getOriginalData());
                    rightPanelTextArea.setText(challenge.getWrongData()); 
                }
                else{
                    rightPanelTextArea.setBackground(Color.RED);
                }
            }
        };
    }

    public void start(){
        Font font = new Font("Arial", Font.PLAIN, 48);
        
        leftPanelTextArea.setFont(font);
        leftPanelTextArea.setEditable(false);
        rightPanelTextArea.setFont(font);
        rightPanelButton.setText("Avançar");
        rightPanelButton.setSize(60, 30);
        rightPanelButton.setMinimumSize(rightPanelButton.getSize());        
        
        fit(leftPanelScrollPane);
        fit(rightPanelScrollPane);                
                
        leftPanel.add(leftPanelScrollPane);
        rightPanel.add(rightPanelScrollPane);
        rightPanel.add(rightPanelButton);
        
        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }
    
    private void fit(java.awt.Component c){        
        Dimension d = new Dimension(600, 600);
        c.setMinimumSize(d);        
        c.setMaximumSize(d);     
        c.setPreferredSize(d);
    }
    
}