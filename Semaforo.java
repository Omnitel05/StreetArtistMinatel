package StreetArtistPack;

public class Semaforo {
	private int s;     
    public Semaforo(int n) { 
        s=n;
    }
    synchronized public void acquire() { 
        s--;
        if (s<0) {
            try{wait();} 
   catch(InterruptedException e) {};
        }        
    }
    synchronized public void release(){ 
       s++;
       notify();
    }

}
