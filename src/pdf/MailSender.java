package pdf;

import java.io.FileOutputStream;
import java.io.IOException;

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
	
	public MailSender(Client client) {
		this.client = client;
	}
	
	
	
	public void pdf() throws IOException, DocumentException{
		  Document document = new Document();
	        // step 2
		  PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:/Users/mlaml/Desktop/PPE/test.pdf"));
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
	        
	        
	        /*  Font chapterFont = FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLDITALIC);
	        Font paragraphFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL);
	        Chunk chunk = new Chunk("Relance de votre contrat de maintenance", chapterFont);
	        Chapter chapter = new Chapter(new Paragraph(chunk), 0);
	        chapter.setNumberDepth(0);
	        chapter.add(new Paragraph("Bonjour, votre contrat de maintenance qui assure vos caisse enregistreuse à pris fin le " + client.getLeContrat().dateEcheance + ".", paragraphFont));
	        chapter.add(new Paragraph("Nous vous invitons donc à renouveler votre contrat qui déclare les machines ci présente", paragraphFont));
		     
	        document.add(chapter);
	         */ 
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
	        
	        
	        // step 5
	        document.close();
	    }
    }


// creation of the document with a certain size and certain margins
// (you can use PageSize.Letter instead of PageSize.A4)
/*

String[] headers = new String[]{
        "No", "Username", "First Name", "Last Name"
};


String[][] data = new String[][]{
        {"1", "jdow", "John", "Dow"},
        {"2", "stiger", "Scott", "Tiger"},
        {"3", "fbar", "Foo", "Bar"}
};

// Create a new document.
Document document = new Document(PageSize.LETTER.rotate());
Font bold = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
Font normal = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);

try {
    // Get an instance of PdfWriter and create a Table.pdf file
    // as an output.
	PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:/Users/mlaml/Desktop/PPE/test.pdf"));

    document.open();

    // Create an instance of PdfPTable. After that we transform
    // the header and data array into a PdfPCell object. When
    // each table row is complete we have to call the
    // table.completeRow() method.
    //
    // For better presentation we also set the cell normal name,
    // size and weight. And we also define the background fill
    // for the cell.
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

    document.addTitle("RelancePDF");
document.add(table);
} catch (DocumentException | FileNotFoundException e) {
    e.printStackTrace();
} finally {
    document.close();
}*/