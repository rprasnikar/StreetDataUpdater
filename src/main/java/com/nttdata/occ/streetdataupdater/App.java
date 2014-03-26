package com.nttdata.occ.streetdataupdater;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipEntry;

public class App 
{
    public static void main( String[] args )
    {
        String s = "http://www.statistik.at/verzeichnis/strassenliste/gemplzstr.zip";
        URL u;
        InputStream is = null;
        ZipInputStream zis;
        FileOutputStream os;
        File f;
        try
        {
                u = new URL(s);
                is = u.openStream();
                zis = new ZipInputStream(is);
                
                ZipEntry entry;
                while ((entry = zis.getNextEntry()) != null)
                {
                    System.out.println("entry: " + entry.getName() + ", " + entry.getSize());
                        
                    if(entry.getName().equalsIgnoreCase("gemplzstr.xml"))
                    {
                        System.out.println("entry: " + entry.getName() + ", " + entry.getSize());
                        f = new File(entry.getName());
                        os = new FileOutputStream(f);
                        while(zis.available() > 0)
                        {
//                          System.out.println("Avail: " + zis.available());
                            os.write(zis.read());
                        }
                        os.close();
                    }
        }
                zis.close();

        } 
        catch (MalformedURLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
