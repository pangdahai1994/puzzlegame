package UI;

import java.io.File;
import java.io.FileInputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line;
import javax.sound.sampled.SourceDataLine;

public class MediaPlayer extends Thread implements Runnable{
    String path;
    File file;
    boolean isStop,hasStop=true;
    SourceDataLine sourceDataLine;
    AudioInputStream audioInputStream;
    MediaPlayer(String path){
    	setpath(path);
    	start();
    }
    public void setpath(String path){
    	this.path=path;
    	file=new File(path);
    }
    public void stopmedia(){
        isStop = true;
    }
    
    public void changemusic(String path){
    	this.path=path;
    }
    public void run(){
            try {
                isStop = true;// ֹͣ�����߳�
                File file = new File(path);
                // ȡ���ļ�������
                audioInputStream = AudioSystem.getAudioInputStream(file);
                AudioFormat audioFormat = audioInputStream.getFormat();
                // ת��mp3�ļ�����
                if (audioFormat.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
                    audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                            audioFormat.getSampleRate(), 16, audioFormat
                                    .getChannels(), audioFormat.getChannels() * 2,
                            audioFormat.getSampleRate(), false);
                    audioInputStream = AudioSystem.getAudioInputStream(audioFormat,
                            audioInputStream);
                }
     
                while (!hasStop) {
                    System.out.print(".");
                    try {
                        Thread.sleep(10);
                    } catch (Exception e) {
                    }
                }
                // ������豸
                DataLine.Info dataLineInfo = new DataLine.Info(
                        SourceDataLine.class, audioFormat,
                        AudioSystem.NOT_SPECIFIED);
                sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
                sourceDataLine.open(audioFormat);
                sourceDataLine.start();
     
                // ���������߳̽��в���
                isStop = false;
                Thread playThread = new Thread(new PlayThread());
                playThread.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    
    class PlayThread extends Thread {
        byte tempBuffer[] = new byte[320];
     
        public void run() {
            try {
                int cnt;
                hasStop = false;
                // ��ȡ���ݵ���������
                while ((cnt = audioInputStream.read(tempBuffer, 0,
                        tempBuffer.length)) != -1) {
                    if (isStop)
                        break;
                    if (cnt > 0) {
                        // д�뻺������
                        sourceDataLine.write(tempBuffer, 0, cnt);
                    }
                }
                // Block�ȴ���ʱ���ݱ����Ϊ��
                sourceDataLine.drain();
                sourceDataLine.close();
                hasStop = true;
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
    }
}
