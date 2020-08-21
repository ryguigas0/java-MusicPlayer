package com.guiga.musica.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class PlayerMusica {

	public void Rodar(String[] args) {
		
		boolean play = false;
		boolean playlist = false;
		String path = "";
		
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("play")) {
				play = true;
				path = args[i + 1];
				i = args.length;
			} else if (args[i].equals("playlist")) {
				playlist = true;
				path = args[i + 1];
				i = args.length;
			} else {
				System.out.println("play [caminho pro arquivo mp3 entre aspas] -> tocar uma única múscia");
				System.out.println("playlist [caminho para pasta com arquivos mp3 entre aspas] -> tocar uma série de músicas");
				i = args.length;
			}
			

			if (play && playlist) {
				System.out.println("ERRO: Apenas pode tocar uma música ou uma playlist de músicas");
				break;
			} else if (play) {
				PlayMusica(path);
			} else if (playlist) {
				PlayPlaylist(path);
			}
		}

	}

	private void PlayMusica(String path) {
		File f = new File(path);
		try {
			FileInputStream fis = new FileInputStream(f);
			Player p = new Player(fis);
			System.out.println("Tocando [" + f.getName() + "]");
			System.out.println("Localizada em: " + f.getAbsolutePath());
			p.play();
		} catch (FileNotFoundException e) {
			System.out.println("ERRO: Arquivo não encontrado ou contém espaços na linha do camniho descrito");
		} catch (JavaLayerException e) {
			System.out.println("ERRO INTERNO");
		}
	}

	private void PlayPlaylist(String path) {
		File pasta = new File(path);
		if(!pasta.isDirectory()) {
			System.out.println("ERRO: Arquivo não é uma pasta");
		} else {
			System.out.println("Playlist [" + pasta.getName() + "]");
			System.out.println("Playlist localizada em: " + pasta.getPath());
			for(File f : pasta.listFiles()) {
				if(f.getName().contains(".mp3")) {
					PlayMusica(f.getPath());
				}
			}
		}
	}

}