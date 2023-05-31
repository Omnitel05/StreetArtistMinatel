package StreetArtistPack;

public class Customer implements Runnable {
	private int customerId;
	private int waitingTime;
	private int portraitTime;
	private Semaforo p;
	private Semaforo a;
	private boolean PortraitDone=false;
	private long START_WAITING;
	private long END_WAITING;

	public Customer(int customerId, Semaforo posti, Semaforo artista, int waitingTime, int portraitTime) {
		this.customerId = customerId;
		this.waitingTime = waitingTime;
		this.portraitTime = portraitTime;
		p=posti;
		a=artista;
	}	
	public void run() {
		System.out.println("Cliente " + customerId + " giunge incuriosito alla vista di un prodigo artista da strada.");        
		START_WAITING=System.currentTimeMillis();
		p.acquire();
		END_WAITING=System.currentTimeMillis();
		if(END_WAITING - START_WAITING <= waitingTime) {
			PortraitDone=true;
			a.acquire();
			try {
				Thread.sleep(portraitTime); // Tempo di attesa del cliente
			}	 catch (InterruptedException e) {
				e.printStackTrace();
			}
			a.release();
		}
		p.release();
		if(PortraitDone) {
			System.out.println("Cliente " + customerId + " se ne va soddisfatto con un bellissimo ritratto :)");
		}else {
			System.out.println("Cliente " + customerId + " va via insoddisfatto, sta aspettando da troppo tempo :(");
		}
	}
}
