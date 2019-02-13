package TP3;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class RepeatNetwork implements Runnable{
    private SocketChannel socketChannel;
    private ByteBuffer byteBuffer;
    private Client client;

    public RepeatNetwork(SocketChannel socketChannel, Client client) {
        this.socketChannel = socketChannel;
        this.client = client;
        this.byteBuffer = ByteBuffer.allocate(512);
    }

    @Override
    public void run() {
        while (client.isConnected()){
            try {
                this.socketChannel.read(this.byteBuffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.byteBuffer.flip();
            Charset charset = Charset.forName("UTF-8");
            CharBuffer charBuffer = charset.decode(byteBuffer);
            System.out.println(charBuffer.toString());
        }
    }
}
