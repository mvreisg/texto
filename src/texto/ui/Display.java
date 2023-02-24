package texto.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
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
    
    private void getNewChallenge(){
        if (index < 0){
            index = 0;
        }
        if (index >= challenges.size()){
            index = 0;
        }
        challenge = challenges.get(index);
        index++;
    }
    
    private void prepareComponents(){
        if (challenge == null){
            throw new RuntimeException("challenge == null");
        }
        leftPanelTextArea.setText(challenge.getOriginalData());
        rightPanelTextArea.setText(challenge.getWrongData());
        rightPanelTextArea.setBackground(Color.WHITE);
    }
    
    private boolean canProgress(){
        return challenge.compare(rightPanelTextArea.getText());
    }
    
    private void colorTextArea(){
        if (canProgress()){
            rightPanelTextArea.setBackground(Color.GREEN);
        }
        else{
            rightPanelTextArea.setBackground(Color.RED);        
        }
    }
    
    private AbstractAction getAction(){
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (canProgress()){
                    getNewChallenge();
                    prepareComponents();                    
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
        
        rightPanelTextArea.getDocument().addDocumentListener(getDocumentListener());
        
        getNewChallenge();
        prepareComponents();
    }
    
    private void fit(java.awt.Component c){        
        Dimension d = new Dimension(600, 600);
        c.setMinimumSize(d);        
        c.setMaximumSize(d);     
        c.setPreferredSize(d);
    }

    private DocumentListener getDocumentListener() {
        return new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                colorTextArea();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                colorTextArea();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                colorTextArea();  
            }
        };
    }
    
}