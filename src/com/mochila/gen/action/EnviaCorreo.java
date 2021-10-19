package com.mochila.gen.action;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.apache.log4j.Logger;

public class EnviaCorreo {
	protected static Logger log = Logger.getLogger(EnviaCorreo.class);

	/**
	 * @name enviar
	 * @return int cero '0' indica que el correo fue enviado satisfactoriamente,
	 *         si retorna 1 fallÃ³ el envio
	 * @param servidorSMTP
	 *            nombre o ip del servidor de correo
	 * @param puertoSMTP
	 *            puerto smtp del servidor de correo
	 * @param requiereTLS
	 *            valor '1' si requiere comunicaciÃ³n TLS, valor 0 si no
	 *            requiere
	 * @param requiereAtenticacion
	 *            valor '1' si requiere autenticacion para el envio de correo ,
	 *            valor 0 si no requiere
	 * @param usuarioAuth
	 *            si rquiere autenticacion nombre del usuario para autenticar
	 * @param claveAuth
	 *            si rquiere autenticacion clave del usuario para autenticar
	 * @param secCorreo
	 *            secuencia del correo para consultar sus adjuntos
	 * @param remitente
	 *            cuenta remitente del correo
	 * @param destinatarios
	 *            destinatarios separados por coma ','
	 * @param copiaDestinatarios
	 *            copia a destinatarios separados por coma ','
	 * @param asunto
	 *            asunto del correo
	 * @param cuerpoCorreo
	 *            cuerpo del correo
	 * @param ErrorMessage
	 *            Mensaje de error de retorno en caso de fallo del correo
	 * @desc mÃ©todo encargado de enviar un correo SMTP con sus adjuntos
	 */
	public static int enviar(String servidorSMTP, String puertoSMTP, int requiereTLS, int requiereAtenticacion,
			String usuarioAuth, String claveAuth, long secCorreo, String remitente, String destinatarios,
			String copiaDestinatarios, String asunto, String cuerpoCorreo, String ErrorMessage[]) {
		// Error status;
		int ErrorStatus = 0;

		try {
			
			// configuracion con el servidor de correo
			Properties propiedades = getConfiguracion(servidorSMTP, puertoSMTP, requiereTLS, requiereAtenticacion);
			Session session = Session.getInstance(propiedades);
			
			// Se compone la parte del texto del correo
			BodyPart texto = new MimeBodyPart();
			if (asunto.equals("Correo notificacion enviado por el sistema Iceberg"))
				texto.setContent(cuerpoCorreo, "text/plain; charset=utf-8");
			else
				texto.setContent(cuerpoCorreo, "text/html");
			
			// Una MultiParte para agrupar texto e imagen.
			Multipart multiParte = new MimeMultipart();
			multiParte.addBodyPart(texto);
			
						// contenido.
			MimeMessage message = new MimeMessage(session);

			
			// remitente
			message.setFrom(new InternetAddress(remitente));

			
			// destinatarios si son varios debe llegar separados por coma ','
			if (null != destinatarios) {
				InternetAddress[] TheAddresses = InternetAddress.parse(destinatarios);
				message.addRecipients(Message.RecipientType.TO, TheAddresses);
			}
			
			
			// con copia a destinatarios si son varios debe llegar separados por
			// coma ','
			if (null != copiaDestinatarios) {
				InternetAddress[] TheAddresses = InternetAddress.parse(copiaDestinatarios);
				message.addRecipients(Message.RecipientType.CC, TheAddresses);
			}
			
			// asunto mensaje
			message.setSubject(asunto);
			message.setContent(multiParte);
			message.setSentDate(new Date());

			
			// Adjuntos
			//consultarAdjuntos(secCorreo, multiParte);
			
			
			// Se envia el correo.
			Transport t = session.getTransport("smtp");

			if (requiereAtenticacion == 1)
				t.connect(usuarioAuth, claveAuth);

			t.sendMessage(message, message.getAllRecipients());
			t.close();
			
			log.info("correo enviado!!");

		} catch (MessagingException MsgException) {

			ErrorMessage[0] = ErrorMessage[0] + "\n" + MsgException.toString();
			Exception TheException = null;

			if ((TheException = MsgException.getNextException()) != null)
				ErrorMessage[0] = ErrorMessage[0] + "\n" + TheException.toString();

			ErrorStatus = 1;
			log.info("error: algo salio mal al enviar [MsgException] "+MsgException);
		} catch (Exception e) {

			ErrorMessage[0] = ErrorMessage[0] + "--Err--" + e.toString();
			ErrorStatus = 1;
			log.info("error: algo salio mal al enviar [Exception] "+e);
		}
		
		return ErrorStatus;
	}

	/**
	 * @name consultarAdjuntos
	 * @return void
	 * @param secCorreo
	 *            secuencia del correo al que se le consultaran sus adjuntos
	 * @param multiParte
	 *            contenedor al que se le agregarÃ¡n los adjuntos
	 * @desc MÃ©todo encargado de consultar los adjuntos que le pertenecen al
	 *       correo
	 */
	public static void consultarAdjuntos(long secCorreo, Multipart multiParte) throws Exception {

		Connection conn = DriverManager.getConnection("jdbc:default:connection");

		PreparedStatement stmt = conn.prepareStatement("SELECT adj.nombre,adj.extension,adj.contenido,"
				+ " NVL(mime.tipo_contenido,'application/octet-stream') mimetype" + " FROM Sit_Correo_Adjunto  adj"
				+ " LEFT OUTER JOIN Get_MimeType mime" + " ON adj.extension = mime.mimetype" + " WHERE Sec_Correo= ?");
		stmt.setLong(1, secCorreo);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {

			addAdjunto(multiParte, rs.getString("nombre"), rs.getString("extension"), rs.getString("mimetype"),
					rs.getBinaryStream("contenido"));
		}

		rs.close();
		stmt.close();
		conn.close();

	}

	/**
	 * @name addAdjunto
	 * @return void
	 * @param multiParte
	 *            contenedor del correo que permide adicionar adjuntos o
	 *            subpartes al correo
	 * @param nombre
	 *            nombre del archivo adjunto
	 * @param mimeType
	 *            tipo mimeType del archivo
	 * @param contenido
	 *            archivo adjunto en un InputStream
	 * @desc MÃ©todo encargado de adicionar al correo un adjunto
	 */
	public static void addAdjunto(Multipart multiParte, String nombre, String extension, String mimeType,
			InputStream contenido) throws Exception {

		MimeBodyPart mbp = new MimeBodyPart();
		mbp.setDataHandler(new DataHandler(new ByteArrayDataSource(contenido, mimeType)));
		mbp.setFileName(nombre + "." + extension);
		multiParte.addBodyPart(mbp);

	}

	/**
	 * @name getConfiguracion
	 * @return Properties
	 * @param servidorSMTP
	 *            nombre o ip del servidor de correo
	 * @param puertoSMTP
	 *            puerto del servidor de correo
	 * @param requiereTLS
	 *            1 si el servidor exige comuncicaciÃ³n TLS
	 * @param requiereAtenticacion
	 *            1 si el servidor de correo exige autenticaciÃ³n
	 * @desc MÃ©todo encargado de regresar el objeto Properties con toda la
	 *       configuracion necesaria para inicial la comunicacion con el
	 *       servidor de correo
	 */
	public static Properties getConfiguracion(String servidorSMTP, String puertoSMTP, int requiereTLS,
			int requiereAtenticacion) {

		Properties propiedades = new Properties();

		propiedades.put("mail.smtp.host", servidorSMTP);

		propiedades.setProperty("mail.smtp.port", puertoSMTP);

		if (requiereTLS == 1){
			propiedades.setProperty("mail.smtp.starttls.enable", "true");
		}else{
			propiedades.setProperty("mail.smtp.startssl.enable", "true");
		}

		if (requiereAtenticacion == 1)
			propiedades.setProperty("mail.smtp.auth", "true");

		return propiedades;

	}

}
