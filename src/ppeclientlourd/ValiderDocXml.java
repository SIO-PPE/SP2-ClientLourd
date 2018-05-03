package ppeclientlourd;

import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class ValiderDocXml {


	public static ArrayList<SAXParseException> chercherErreurs(String documentXML) throws Exception {
		// Instanciation d'une factory d'analyseurs SAX
		SAXParserFactory factory = SAXParserFactory.newInstance();
		// Instanciation d'un analyseur SAX validant les documents lus
		factory.setValidating(true);
		SAXParser parser = factory.newSAXParser();
		// Ensemble stockant les erreurs trouvees dans le fichier XML
		final ArrayList<SAXParseException> erreurs = new ArrayList();
		// Creation d'un gestionnaire SAX
		DefaultHandler gestionnaireSAX = new DefaultHandler() {
			// Redefinition des methodes de gestion d'erreur
			public void warning(SAXParseException ex) {
				erreurs.add(ex);
			}

			public void error(SAXParseException ex) {
				erreurs.add(ex);
			}

			public void fatalError(SAXParseException ex) {
				erreurs.add(ex);
			}
		};
		// Analyse du fichier avec le gestionnaire precedent
		try {
			parser.parse(documentXML, gestionnaireSAX);
		} catch (Exception ex) {
			// l'erreur a deja ete signalee par un appel a fatalError
		}
		return erreurs;
	}
	
	
}

