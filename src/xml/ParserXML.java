package xml;

import entity.User;
import entity.Users;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.DOMBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tanya on 16.10.2016.
 */
public class ParserXML {
    private static String FILE_NAME = "D://users.xml";

    public ParserXML() {
    }

    public void writeXmlUsingDOM(Users user) throws IOException, JAXBException {
        JAXBContext context = JAXBContext.newInstance(Users.class);
        Marshaller marshaller = context.createMarshaller();
        // устанавливаем флаг для читабельного вывода XML в JAXB
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        File file = new File(FILE_NAME);
        if (!file.exists())
            new File(FILE_NAME).createNewFile();
        marshaller.marshal(user, new File(FILE_NAME));
    }

    public Users readXml() throws ParserConfigurationException, IOException, SAXException {
        try {
            // создаем объект JAXBContext - точку входа для JAXB
            JAXBContext jaxbContext = JAXBContext.newInstance(Users.class);
            Unmarshaller un = jaxbContext.createUnmarshaller();
            if (new File(FILE_NAME).exists())
                return (Users) un.unmarshal(new File(FILE_NAME));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

}
