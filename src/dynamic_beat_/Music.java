package dynamic_beat_;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Music extends Thread{

	private Player player;
	private boolean isLoop;
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;

	public Music(String name, boolean isLoop) {
		try {
			this.isLoop = isLoop;
			file = new File(Main.class.getResource("../music/"+name).toURI());
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			player = new Player(bis);
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}


	public int getTime() {
	if(player == null)
		return 0;
	return player.getPosition();
}

	public void close() {
	isLoop = false;
	player.close(); // 곡 종료시키는서
	this.interrupt();  //해당 트레드 중지시키는 것(곡을 실행하는 것을 종료시키는것) 
}
	
	@Override
	public void run() {
		try {
			do {
				player.play();
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
			}while(isLoop);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}


