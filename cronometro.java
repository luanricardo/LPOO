import javax.swing.JFrame; 
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.util.Timer;
import java.util.TimerTask;

class cronometro{
													
  JLabel contagemTempo;	
  boolean ligdesl = false;									
  Timer tm;
  Integer contador = 600;
  final int tFonte = 100;
  final int seg = 1000;
  
  public void init() {
    JFrame.setDefaultLookAndFeelDecorated(true);		
    JFrame janela = new JFrame("Tempo de banho");							
    janela.setSize(300,200);
    janela.setAlwaysOnTop(true);
    janela.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    janela.setLayout(new BorderLayout());
    
    contagemTempo = new JLabel("Iniciar banho");
    contagemTempo.setFont(new Font(contagemTempo.getName(), Font.PLAIN, tFonte));
    janela.add(contagemTempo, BorderLayout.CENTER);    
    
    JPanel painel = new JPanel();
    painel.setLayout(new GridLayout(2, 1));
        
    JButton btIniciar = new JButton("Ligar");								
    btIniciar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (!ligdesl) {
          contagemTempo.setText("00:10:00");                                                          
          tm = new Timer();
          tm.scheduleAtFixedRate(new TimerTask() {
            
            @Override
            public void run() {
              contador--;
              int seg = contador % 60;
              int min = contador / 60;
              int hora = min / 60;
              min %= 60;
              contagemTempo.setText(String.format("%02d:%02d:%02d", hora,min,seg));                                          
              if (contador == 0) {								
            	  contagemTempo.setText("Desligando");
            	  contagemTempo = new JLabel();
              }
              
            }
          }, seg, seg);
          ligdesl = true;
          
        }
      }
    });
    
    JButton btDesligar = new JButton("Desligar");								
    btDesligar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (ligdesl) {
          tm.cancel();
          ligdesl = false;
          contador = 600;
          contagemTempo.setText("Desligando");          
        }
      }
    });
    
    painel.add(btIniciar);                                                                              
    painel.add(btDesligar);                                                                                  
    janela.add(painel,BorderLayout.EAST);                                                                
    janela.pack();
    janela.setVisible(true);                                                                        
  }
  
public static void main(String[] args) {
    cronometro c = new cronometro();
    EventQueue.invokeLater(new Runnable() {
      @Override 										
      public void run() {
        c.init();
      }
    });
  }
  
}

//Nosso código foi pensado no uso consciente da água, a idéia é implementar um timer na estrutura do chuveiro utilizando mecanismos de Boolean ao qual ele “desliga” o chuveiro quando o tempo termina.

