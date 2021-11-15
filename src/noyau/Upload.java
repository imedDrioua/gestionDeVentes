package noyau;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.Properties;

public class Upload implements Runnable {
    public Upload(String to, String filename, boolean generate, ObservableList<Piece> piecesManquantes, Label l) throws FileNotFoundException, DocumentException {
        this.to = "driouaimed@gmail.com";
        this.filename = filename;
        this.l=l;
        this.generate=generate;
        if(generate){
            Font bfBold12 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0, 0, 0));
            Font bfBold13 = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLDITALIC, new BaseColor(0, 0, 0));
            Font bf12 = new Font(Font.FontFamily.TIMES_ROMAN, 12);
            Rectangle pagesize = new Rectangle(700, 800);
            Document document = new Document(pagesize, 36f, 72f, 80f, 180f);
            Paragraph titre =  new Paragraph("Les Pièces Manquantes",bfBold13);

            Paragraph paragraph = new Paragraph("");
            Paragraph gap = new Paragraph("                                                         " +
                    "                                                                                              " +
                    "                            " +
                    "                  " +
                    "               " +
                    "             " +
                    "                                                                                              " +
                    "                                                                                               " +
                    "                                                                                         " +
                    "");

            DecimalFormat df = new DecimalFormat("0.00");
            //specify column widths
            float[] columnWidths = {1.5f, 2f, 5f, 2f};
            //create PDF table with the given widths
            PdfPTable table = new PdfPTable(columnWidths);
            PdfWriter.getInstance(document, new FileOutputStream("pièce.pdf"));
            document.open();
            titre.setAlignment(Element.ALIGN_CENTER);
            table.setWidthPercentage(90f);
            //insert column headings
            insertCell(table, "Réference", 1, bfBold12);
            insertCell(table, "Stock disponible", 1, bfBold12);
            insertCell(table, "Désigniation", 1, bfBold12);
            insertCell(table, "Prix", 1, bfBold12);
            table.setHeaderRows(1);

            //insert an empty row
            //create section heading by cell merging
            //    insertCell(table, "New York Orders ...", Element.ALIGN_LEFT, 4, bfBold12);
            double  total = 0;

            //just some random data to fill
            for(Piece piece : piecesManquantes){
                insertCell(table, piece.getReference(), 1, bf12);
                insertCell(table, String.valueOf(piece.getStock_disponible()), 1, bf12);
                insertCell(table, piece.getDesigniation(), 1, bf12);
                insertCell(table, df.format(piece.getPrix_de_achat()), 1, bf12);
                total = total + piece.getPrix_de_achat();
            }
            //merge the cells to create a footer for that section
            insertCell(table, "Facture totale", 3, bfBold12);
            insertCell(table, df.format(total), 1, bfBold12);
            //add the PDF table to the paragraph
            paragraph.add(table);
            // add the paragraph to the document
            document.add(titre);
            document.add(gap);
            document.add(paragraph);
            // Fermer le document, à ne pas oublier
            document.close();

        }
    }

    String to="";
    String filename="";
    Label l;
    boolean generate;
    @Override
    public void run() {
        String from = "driouaimed@gmail.com";
        String host = "smtp.gmail.com";
        Properties properties = System.getProperties();
        SmtpAuthenticator auth= new SmtpAuthenticator();
        properties.setProperty("mail.smtp.host",host);
        properties.put("mail.smtp.starttls.enable","true");
        properties.setProperty("mail.smtp.auth","true");
        Session session = Session.getDefaultInstance(properties,auth);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(this.to));
            message.setSubject("Gestion de Stock");
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Drioua imed , 0549318426");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(this.filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart );
            Transport.send(message);
           if(generate) Platform.runLater(()->l.setText("Envoyé"))  ;
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
    private static void insertCell(PdfPTable table, String text, int colspan, Font font){

        //create a new cell with the specified Text and Font
        PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
        //set the cell alignment
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        //set the cell column span in case you want to merge two or more cells
        cell.setColspan(colspan);
        cell.setMinimumHeight(20f);
        //in case there is no text and you wan to create an empty row
        if(text.trim().equalsIgnoreCase("")){
            cell.setMinimumHeight(20f);
        }
        //add the call to the table
        table.addCell(cell);

    }


}
