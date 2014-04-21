/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nttdata.occ.streetdataupdater;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 *
 * @author robert
 */
public class datenHandler extends DefaultHandler {
    private Integer isDatensatz = 0;
    private Integer isElement = 0;
    private Integer elementIndex = 0;
    
  @Override
  public void startDocument()
  {
    System.out.println( "Document starts." );
  }

  @Override
  public void endDocument()
  {
    System.out.println( "Document ends." );
  }
  
    @Override
  public void startElement( String namespaceURI, String localName,
                            String qName, Attributes atts )
  {
//    System.out.println( "namespaceURI: " + namespaceURI );
//    System.out.println( "localName: " + localName );
    System.out.println( "qName: " + qName );
    for ( int i = 0; i < atts.getLength(); i++ )
      System.out.printf( "Attribut no. %d: %s = %s%n", i,
                         atts.getQName( i ), atts.getValue( i ) );
    if ("datensatz".equals(qName)) {
        System.out.println("Neuer Datensatz...");
//        System.out.println(atts.getLength());
        elementIndex = 1;
    }
    
        if ("element".equals(qName)) {
        System.out.println("Element " + elementIndex++);
    }
  }
  
    public void endElement( String namespaceURI, String localName,
                            String qName )
  {
//    System.out.println( "namespaceURI: " + namespaceURI );
//    System.out.println( "localName: " + localName );
//    System.out.println( "qName: " + qName );
    
    if ("datensatz".equals(qName)) {
        System.out.println("Ende neuer Datensatz...");
        elementIndex = 0;
    }
  }
  
  @Override
  public void characters( char[] ch, int start, int length )
  {
    
// To get idea which element the reader is in a home made stack would be needed 
// or simple impl. with isElementXY = true in startElement and isElementXY = false in endElement     

      if (elementIndex > 1)
          System.out.println( "String: " + new String(ch, start, length).trim());

//      System.out.println( "Characters:" );
//
//    for ( int i = start; i < (start + length); i++ )
//      System.out.printf( "%1$c (%1$x) ", (int) ch[i] );

//    System.out.println("String...");
  }
public void error( SAXParseException e ) throws SAXException
{
  throw new SAXException( saxMsg(e) );
}
private String saxMsg( SAXParseException e )
{
  return   "Line: " + e.getLineNumber() + ", Column: "
         + e.getColumnNumber() + ", Error: " + e.getMessage();
}

}

