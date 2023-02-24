package texto;

import java.util.ArrayList;
import java.util.List;
import texto.model.Challenge;
import texto.ui.Display;

public class Main {
    
    private Display display;
    private List<Challenge> challenges;
    
    public static void main(String[] args) {
        new Main().start();
    }
    
    private Main(){
        challenges = new ArrayList<>();
        challenges.add(
            new Challenge(
                String.format("Esse%ntexto%npossui%nquebra%nde%nlinhas."), 
                "Esse texto é apenas de UMA linha."
            )
        );
        challenges.add(
            new Challenge(
                "Teste.", 
                "herikgber"
            )
        );
        challenges.add(
            new Challenge(
                "Zero, um, dois, três, quatro, cinco, seis, sete, oito, nove.", 
                "0,1,2,3,4,cinco, seis,    sete"
            )
        );
        challenges.add(
            new Challenge(
                "Não esqueça do til nem da cedilha.", 
                "Naum eskeça do til nem da sedilia"
            )
        );
        challenges.add(
            new Challenge(
                "Cânticos. Leviatã. Pároco. Cinqüenta e sete.", 
                "nada"
            )
        );
        challenges.add(
            new Challenge(
                "(Texto entre parênteses).", 
                "("
            )
        );
        challenges.add(
            new Challenge(
                "{Texto entre chaves}", 
                "{"
            )
        );
        challenges.add(
            new Challenge(
                "[Texto entre colchetes]", 
                "["
            )
        );
        challenges.add(
            new Challenge(
                "Ponto e vírgula; Vírgula, Ponto.", 
                "...")
        );
        challenges.add(
            new Challenge(
                "5 * 3 = 15.", 
                "="
            )
        );
        challenges.add(
            new Challenge(
                "10 / 2 = 5", 
                "/"
            )
        );

        display = new Display(challenges);        
    }
    
    private void start(){
        display.start();
    }
    
}