package company;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class CompFPServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	@SuppressWarnings("null")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String c_email = request.getParameter("c_email");
		RequestDispatcher dispatcher = null;
		int otpvalue = 0;
		HttpSession mySession = request.getSession();

		if (c_email != null || !c_email.equals("")) {
			// sending otp
			Random rand = new Random();
			otpvalue = rand.nextInt(1255650);

			String to = c_email;// change accordingly
			// Get the session object
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("demo.mail23409@gmail.com", "qhqokmbkomaucjvj");
				}
			});
			// compose message
			try {
				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress(c_email));// change accordingly
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
				message.setSubject("Verification code");
				message.setText("Please use the verification code below to sign in. '" + otpvalue
						+ "'. If you didn’t request this, you can ignore this email.\r\n" + "\r\n" + "Thank you."
								+ "Thank you.\r\n"
								+ "This is auto generated email, please do not reply to this email, for further communication contact college or find college contact details on above mentioned web site.\r\n"
						+ "Placement and Assistance System ");
				// send message
				Transport.send(message);
				System.out.println("message sent successfully");
			}

			catch (MessagingException e) {
				throw new RuntimeException(e);
			}
			dispatcher = request.getRequestDispatcher("comp_enter_otp.jsp");
			request.setAttribute("message", "OTP is sent to your email id");
			// request.setAttribute("connection", con);
			mySession.setAttribute("otp", otpvalue);
			mySession.setAttribute("c_email", c_email);
			dispatcher.forward(request, response);
			request.setAttribute("status", "success");
		}

	}

}
