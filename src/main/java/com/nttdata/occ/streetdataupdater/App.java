package com.nttdata.occ.streetdataupdater;

import java.io.*;
import java.net.*;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipEntry;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import org.xml.sax.SAXException;

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
//                        f = new File(entry.getName());
//                        os = new FileOutputStream(f);
//                        while(zis.available() > 0)
//                        {
////                          System.out.println("Avail: " + zis.available());
//                            os.write(zis.read());
//                        }
//                        os.close();

//                        XMLInputFactory factory = XMLInputFactory.newInstance();
//                        XMLStreamReader parser = factory.createXMLStreamReader(zis);
//                        while (parser.hasNext())
//                        {
//                            int event = parser.next();
//                            System.out.println( "Event: " + event );
//                            switch (event) {
//                                case XMLStreamConstants.START_DOCUMENT:
//                                    System.out.println( "START_DOCUMENT" );
//                                    break;
//                                case XMLStreamConstants.END_DOCUMENT:
//                                    System.out.println("END_DOCUMENT");
//                                    break;
//                                case XMLStreamConstants.START_ELEMENT:
//                                    System.out.println( "START_ELEMENT " + parser.getLocalName() + " " + parser.getAttributeCount());
//                                    break;
//                                case XMLStreamConstants.END_ELEMENT:
//                                    System.out.println( "END_ELEMENT" );
//                                    break;
//                                case XMLStreamConstants.ATTRIBUTE:
//                                    System.out.println( "ATTRIBUTE" );
//                                    break;
//                                case XMLStreamConstants.CHARACTERS:
//                                    System.out.println( "CHAR" );
//                                    break;                                    
//                                default:
//                                    System.out.println( "OTHER");
//                            }
//                            System.in.read();
//                            
//                        }
                        SAXParserFactory factory = SAXParserFactory.newInstance();
                        SAXParser saxParser = factory.newSAXParser();
                        Document doc = new SAXBuilder().build(zis);
//                        XMLOutputter out = new XMLOutputter();
//                        out.output(doc, System.out);
                        Element daten = doc.getRootElement();
                        List datenInfo = daten.getContent();
                        Iterator datenIterator = datenInfo.iterator();
                        while (datenIterator.hasNext())
                            System.out.println(datenIterator.next());
//                        System.out.println(datenInfo.size());
//                        break;
                    }
        }
//                zis.close();

        } 
        catch (MalformedURLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JDOMException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
