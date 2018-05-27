package pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfLayer;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import ppeclientlourd.Client;
//  PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:/Users/mlaml/Desktop/PPE/test.pdf"));

public class MailSender {

	private Client client;

	
	static final String FROM = "cashcash@rushy.fr";
	static final String FROMNAME = "CashCash";


	static final String SMTP_USERNAME = "cashcash@rushy.fr";

	// SES SMTP password.
	static final String SMTP_PASSWORD = "CashCash1";

	// The name of the Configuration Set to use for this message.
	// If you comment out or remove this variable, you will also need to
	// comment out or remove the header below.

	// Amazon SES SMTP host name. This example uses the US West (Oregon) region.
	// See http://docs.aws.amazon.com/ses/latest/DeveloperGuide/regions.html#region-endpoints
	// for more information.
	static final String HOST = "mail.firstheberg.net";

	// The port you will connect to on the Amazon SES SMTP endpoint. 
	static final int PORT = 587;

	static final String SUBJECT = "CashCash renouvellement de votre contrat";

	static final String BODY = String.join(
			System.getProperty("line.separator"),
			"<h1>Relance de votre contrat de maintenanc</h1>",
			"<p>Bonjour, votre contrat de maintenance qui assure vos caisses enregistreusse a pris fin</p>"

			);


	public MailSender(Client client) {
		this.client = client;


	}
	//retourne vrai si l'envoie est un succes
	
	public boolean sendMail() throws UnsupportedEncodingException, MessagingException {
		boolean succes = false;
		// Create a Properties object to contain connection configuration information.
		Properties props = System.getProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.port", PORT); 
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");

		// Create a Session object to represent a mail session with the specified properties. 
		Session session = Session.getDefaultInstance(props);

		// Create a message with the specified information. 
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(FROM,FROMNAME));
		msg.setRecipient(Message.RecipientType.TO, new InternetAddress(client.getEmail()));
		msg.setSubject(SUBJECT);


		// Contenue du message
		MimeMultipart multipart = new MimeMultipart();
		BodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(BODY,"text/html");
		multipart.addBodyPart(messageBodyPart);

		// Partie de la pièce jointe
		MimeBodyPart pdfBodyPart = new MimeBodyPart();
		FileDataSource source = new FileDataSource(getPDF());
		pdfBodyPart.setDataHandler(new DataHandler(source));
		pdfBodyPart.setFileName("renouvellementCasCash.pdf");
		multipart.addBodyPart(pdfBodyPart);

		msg.setContent(multipart);

		// Create a transport.
		Transport transport = session.getTransport();

		// Send the message.
		try
		{
			System.out.println("Envoie email à " + client.getEmail());
			System.out.println("Sending...");

			// Connect to Amazon SES using the SMTP username and password you specified above.
			transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);

			// Send the email.
			transport.sendMessage(msg, msg.getAllRecipients());
			System.out.println("Email envoyé avec succès !");
			succes = true;
			
			
		}
		catch (Exception ex) {
			succes = false;
			System.out.println("Echec d'envoie d'email.");
			System.out.println("Error message: " + ex.getMessage());
		}
		finally
		{
			// Close and terminate the connection.
			transport.close();
		}
		return succes;
	}

	public File getPDF(){
		Document document = new Document();
		// step 2
		File pdfFile = null;

		try {
			pdfFile = File.createTempFile("renouvellement", ".pdf");


			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
			writer.setViewerPreferences(PdfWriter.PageModeUseOC);
			writer.setPdfVersion(PdfWriter.VERSION_1_5);
			// step 3
			document.open();
			// step 4
			Image image = Image.getInstance("C:/Users/mlaml/Desktop/PPE/logoCash.png");
			image.scalePercent(50f);
			document.add(image);

			PdfLayer layer = new PdfLayer("Header", writer);
			BaseFont bf = BaseFont.createFont();
			PdfContentByte cb = writer.getDirectContent();
			cb.beginText();
			cb.setFontAndSize(bf, 15);
			cb.showTextAligned(Element.ALIGN_LEFT, client.getRaisonSociale() + "", 350, 790, 0);
			cb.beginLayer(layer);
			cb.showTextAligned(Element.ALIGN_LEFT, client.getEmail() +" ", 350, 766, 0);
			cb.showTextAligned(Element.ALIGN_LEFT, client.getSiren() +" ", 350, 740, 0);
			cb.endLayer();
			cb.endText();


			Font chapterFont = FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLDITALIC);
			Font paragraphFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL);
			Paragraph paragraph1 = new Paragraph("Relance de votre contrat de maintenance",chapterFont);
			paragraph1.setSpacingBefore(150f);
			Paragraph paragraph2 = new Paragraph("Bonjour, votre contrat de maintenance qui assure vos caisses enregistreusse à pris fin le " + client.getLeContrat().dateEcheance + ".",paragraphFont);
			Paragraph paragraph3 = new Paragraph("Nous vous invitons donc à renouveler votre contrat qui déclare les machines ci présente", paragraphFont);

			document.add(paragraph1);
			document.add(paragraph2);
			document.add(paragraph3);



			String[] headers = new String[]{
					"NumSerie", "Type", "Famille", "DateVente"
			};



			String[][] data = client.getLeContrat().getInfoTableau();

			Font bold = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
			Font normal = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);

			PdfPTable table = new PdfPTable(headers.length);
			for (String header : headers) {
				PdfPCell cell = new PdfPCell();
				cell.setGrayFill(0.9f);
				cell.setPhrase(new Phrase(header, bold));
				table.addCell(cell);
			}
			table.completeRow();

			for (String[] row : data) {
				for (String column : row) {
					PdfPCell cell = new PdfPCell();
					cell.setPhrase(new Phrase(column, normal));
					table.addCell(cell);
				}
				table.completeRow();
			}

			table.setSpacingBefore(50F);
			document.addTitle("RelancePDF");

			document.add(table);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// step 5
		document.close();
		return pdfFile;
	}
}

