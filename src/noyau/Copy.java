package noyau;

import java.io.*;

public class Copy implements Runnable {
    File source = new File("magasin");
    File destination = new File("D:/magasin");
    @Override
    public void run() {
        try {
            this.copyFileUsingStream(source,destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private  void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }
}
