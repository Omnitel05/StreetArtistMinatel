package StreetArtistPack;

import java.util.Random;

public class StreetArtistMain {

	private static final int NUM_SEATS = 4; // Numero di sedie disponibili
    private static final int MAX_WAITING_TIME = 1000; // Tempo massimo di attesa di un cliente in millisecondi
    private static final int MAX_PORTRAIT_TIME = 5000;

    private static Semaforo Seats = new Semaforo(NUM_SEATS); // Semaforo per tenere traccia delle sedie disponibili
    private static Semaforo Artist = new Semaforo(1);

    public static void main(String[] args) {
        Random random = new Random();
        // Genera nuovi clienti ad intervalli casuali
        for (int i = 1; i <= 20; i++) {
            Thread customer = new Thread(new Customer(i,Seats, Artist, random.nextInt(MAX_WAITING_TIME), random.nextInt(MAX_PORTRAIT_TIME)));
            customer.start();
            // Attende un po' di tempo prima di generare il cliente successivo
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


