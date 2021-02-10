package com.chazool.vehiclepasser.ui.service.impl;

import com.chazool.highwayvehiclepasser.model.driverservice.Driver;
import com.chazool.highwayvehiclepasser.model.driverservice.Vehicle;
import com.chazool.highwayvehiclepasser.model.emailservice.Email;
import com.chazool.highwayvehiclepasser.model.paymentservice.Payment;
import com.chazool.highwayvehiclepasser.model.paymentservice.PaymentMethod;
import com.chazool.highwayvehiclepasser.model.transactionservice.Location;
import com.chazool.highwayvehiclepasser.model.transactionservice.Terminal;
import com.chazool.vehiclepasser.ui.service.*;
import com.chazool.vehiclepasser.ui.thread.EmailSender;
import com.chazool.vehiclepasser.ui.thread.PaymentEmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DriverService driverService;
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private EmailSenderService emailSenderService;


    @Override
    public Payment enter(int cardNo, int terminalId) {
        PaymentMethod paymentMethod = getPaymentMethod(cardNo);

        Payment payment = new Payment();
        payment.setPaymentMethod(cardNo);
        payment.setEntranceTerminal(terminalId);
        payment.setDriver(paymentMethod.getDriver());


        payment = restTemplate.postForObject("http://localhost:9193/services/payments", payment, Payment.class);

        //sendEmail("Entering Highway - Safe Drive",);
        PaymentEmailSender paymentEmailSender = new PaymentEmailSender("Entering Highway - Safe Drive"
                , payment.getDriver()
                , payment.getEntranceTerminal()
                , this);
        paymentEmailSender.start();

        return payment;
    }

    @Override
    public Payment exit(int cardNo, int terminalId) {
        return null;
    }

    @Override
    public Payment isSendEmail(Payment payment) {
        return null;
    }

    private PaymentMethod getPaymentMethod(int cardNo) {
        return restTemplate.getForObject("http://localhost:9193/services/payment-method/" + cardNo, PaymentMethod.class);
    }


    public void sendEmail(String subject, int driverId, int terminalId) {

        Driver driver = driverService.findById(driverId);

        Vehicle vehicle = vehicleService.findById(driver.getActiveVehicle());

        Terminal terminal = locationService.findTerminalById(terminalId);

        Location location = locationService.findLocationById(terminal.getLocationId());

        Email email = new Email();
        email.setEmail(driver.getEmail());
        email.setSubject(subject);
        email.setMessage(emailBody(
                driver.getFName() + " " + driver.getLName()
                , vehicle.getVehicleNo()
                , location.getDescription()
                , terminal.getName() + " - " + terminal.getTerminalType()
        ));
        emailSenderService.send(email);
    }

    private String emailBody(String driver, String vehicle, String location, String terminal) {

        String html = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional //EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">" +
                "<head>" +
                "<!--[if gte mso 9]>" +
                "<xml>" +
                "  <o:OfficeDocumentSettings>" +
                "    <o:AllowPNG/>" +
                "    <o:PixelsPerInch>96</o:PixelsPerInch>" +
                "  </o:OfficeDocumentSettings>" +
                "</xml>" +
                "<![endif]-->" +
                "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                "  <meta name=\"x-apple-disable-message-reformatting\">" +
                "  <!--[if !mso]><!--><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><!--<![endif]-->" +
                "  <title></title>" +
                "  " +
                "    <style type=\"text/css\">" +
                "@media only screen and (min-width: 620px) {" +
                "  .u-row {" +
                "    width: 600px !important;" +
                "  }" +
                "  .u-row .u-col {" +
                "    vertical-align: top;" +
                "  }" +
                "" +
                "  .u-row .u-col-33p33 {" +
                "    width: 199.98px !important;" +
                "  }" +
                "" +
                "  .u-row .u-col-66p67 {" +
                "    width: 400.02px !important;" +
                "  }" +
                "" +
                "  .u-row .u-col-100 {" +
                "    width: 600px !important;" +
                "  }" +
                "" +
                "}" +
                "" +
                "@media (max-width: 620px) {" +
                "  .u-row-container {" +
                "    max-width: 100% !important;" +
                "    padding-left: 0px !important;" +
                "    padding-right: 0px !important;" +
                "  }" +
                "  .u-row .u-col {" +
                "    min-width: 320px !important;" +
                "    max-width: 100% !important;" +
                "    display: block !important;" +
                "  }" +
                "  .u-row {" +
                "    width: calc(100% - 40px) !important;" +
                "  }" +
                "  .u-col {" +
                "    width: 100% !important;" +
                "  }" +
                "  .u-col > div {" +
                "    margin: 0 auto;" +
                "  }" +
                "}" +
                "body {" +
                "  margin: 0;" +
                "  padding: 0;" +
                "}" +
                "" +
                "table," +
                "tr," +
                "td {" +
                "  vertical-align: top;" +
                "  border-collapse: collapse;" +
                "}" +
                "" +
                "p {" +
                "  margin: 0;" +
                "}" +
                "" +
                ".ie-container table," +
                ".mso-container table {" +
                "  table-layout: fixed;" +
                "}" +
                "" +
                "* {" +
                "  line-height: inherit;" +
                "}" +
                "" +
                "a[x-apple-data-detectors='true'] {" +
                "  color: inherit !important;" +
                "  text-decoration: none !important;" +
                "}" +
                "" +
                "</style>" +
                "  " +
                "  " +
                "" +
                "<!--[if !mso]><!--><link href=\"https://fonts.googleapis.com/css?family=Lato:400,700\" rel=\"stylesheet\" type=\"text/css\"><!--<![endif]-->" +
                "" +
                "</head>" +
                "" +
                "<body class=\"clean-body\" style=\"margin: 0;padding: 0;-webkit-text-size-adjust: 100%;background-color: #ffffff\">" +
                "  <!--[if IE]><div class=\"ie-container\"><![endif]-->" +
                "  <!--[if mso]><div class=\"mso-container\"><![endif]-->" +
                "  <table style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;min-width: 320px;Margin: 0 auto;background-color: #ffffff;width:100%\" cellpadding=\"0\" cellspacing=\"0\">" +
                "  <tbody>" +
                "  <tr style=\"vertical-align: top\">" +
                "    <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">" +
                "    <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td align=\"center\" style=\"background-color: #ffffff;\"><![endif]-->" +
                "    " +
                "" +
                "<div class=\"u-row-container\" style=\"padding: 0px 10px 3px;background-color: rgba(255,255,255,0)\">" +
                "  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\">" +
                "    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">" +
                "      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px 10px 3px;background-color: rgba(255,255,255,0);\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: transparent;\"><![endif]-->" +
                "      " +
                "<!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->" +
                "<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">" +
                "  <div style=\"width: 100% !important;\">" +
                "  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\"><!--<![endif]-->" +
                "  " +
                "<table style=\"font-family:'Lato',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">" +
                "  <tbody>" +
                "    <tr>" +
                "      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:0px;font-family:'Lato',sans-serif;\" align=\"left\">" +
                "        " +
                "  <table height=\"0px\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;border-top: 1px solid #CCC;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">" +
                "    <tbody>" +
                "      <tr style=\"vertical-align: top\">" +
                "        <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;font-size: 0px;line-height: 0px;mso-line-height-rule: exactly;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">" +
                "          <span> </span>" +
                "        </td>" +
                "      </tr>" +
                "    </tbody>" +
                "  </table>" +
                "" +
                "      </td>" +
                "    </tr>" +
                "  </tbody>" +
                "</table>" +
                "" +
                "  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->" +
                "  </div>" +
                "</div>" +
                "<!--[if (mso)|(IE)]></td><![endif]-->" +
                "      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->" +
                "    </div>" +
                "  </div>" +
                "</div>" +
                "" +
                "" +
                "" +
                "<div class=\"u-row-container\" style=\"padding: 0px 10px 10px;background-color: #ffffff\">" +
                "  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\">" +
                "    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">" +
                "      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px 10px 10px;background-color: #ffffff;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: transparent;\"><![endif]-->" +
                "      " +
                "<!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->" +
                "<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">" +
                "  <div style=\"width: 100% !important;\">" +
                "  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\"><!--<![endif]-->" +
                "  " +
                "<table style=\"font-family:'Lato',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">" +
                "  <tbody>" +
                "    <tr>" +
                "      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:20px 20px 5px;font-family:'Lato',sans-serif;\" align=\"left\">" +
                "        " +
                "  <div style=\"color: #303030; line-height: 120%; text-align: left; word-wrap: break-word;\">" +
                "    <p style=\"font-size: 14px; line-height: 120%; text-align: center;\"><span style=\"font-size: 20px; line-height: 24px;\">Safe Drive!</span></p>" +
                "  </div>" +
                "" +
                "      </td>" +
                "    </tr>" +
                "  </tbody>" +
                "</table>" +
                "" +
                "  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->" +
                "  </div>" +
                "</div>" +
                "<!--[if (mso)|(IE)]></td><![endif]-->" +
                "      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->" +
                "    </div>" +
                "  </div>" +
                "</div>" +
                "" +
                "" +
                "" +
                "<div class=\"u-row-container\" style=\"padding: 0px 10px;background-color: rgba(255,255,255,0)\">" +
                "  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\">" +
                "    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">" +
                "      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px 10px;background-color: rgba(255,255,255,0);\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: transparent;\"><![endif]-->" +
                "      " +
                "<!--[if (mso)|(IE)]><td align=\"center\" width=\"400\" style=\"width: 400px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->" +
                "<div class=\"u-col u-col-66p67\" style=\"max-width: 320px;min-width: 400px;display: table-cell;vertical-align: top;\">" +
                "  <div style=\"width: 100% !important;\">" +
                "  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\"><!--<![endif]-->" +
                "  " +
                "<table style=\"font-family:'Lato',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">" +
                "  <tbody>" +
                "    <tr>" +
                "      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:15px 20px;font-family:'Lato',sans-serif;\" align=\"left\">" +
                "        " +
                "  <div style=\"color: #303030; line-height: 120%; text-align: left; word-wrap: break-word;\">" +
                "    <p style=\"font-size: 14px; line-height: 120%;\"><strong>Driver Name </strong></p>" +
                "  </div>" +
                "" +
                "      </td>" +
                "    </tr>" +
                "  </tbody>" +
                "</table>" +
                "" +
                "<table style=\"font-family:'Lato',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">" +
                "  <tbody>" +
                "    <tr>" +
                "      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:15px 20px;font-family:'Lato',sans-serif;\" align=\"left\">" +
                "        " +
                "  <div style=\"color: #303030; line-height: 120%; text-align: left; word-wrap: break-word;\">" +
                "    <p style=\"font-size: 14px; line-height: 120%;\"><strong>Vehicle No</strong></p>" +
                "  </div>" +
                "" +
                "      </td>" +
                "    </tr>" +
                "  </tbody>" +
                "</table>" +
                "" +
                "  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->" +
                "  </div>" +
                "</div>" +
                "<!--[if (mso)|(IE)]></td><![endif]-->" +
                "<!--[if (mso)|(IE)]><td align=\"center\" width=\"200\" style=\"width: 200px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->" +
                "<div class=\"u-col u-col-33p33\" style=\"max-width: 320px;min-width: 200px;display: table-cell;vertical-align: top;\">" +
                "  <div style=\"width: 100% !important;\">" +
                "  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\"><!--<![endif]-->" +
                "  " +
                "<table style=\"font-family:'Lato',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">" +
                "  <tbody>" +
                "    <tr>" +
                "      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:15px 20px;font-family:'Lato',sans-serif;\" align=\"left\">" +
                "        " +
                "  <div style=\"color: #303030; line-height: 120%; text-align: left; word-wrap: break-word;\">" +
                "    <p style=\"font-size: 14px; line-height: 120%;\"> " + driver + "</p>" +
                "  </div>" +
                "" +
                "      </td>" +
                "    </tr>" +
                "  </tbody>" +
                "</table>" +
                "" +
                "<table style=\"font-family:'Lato',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">" +
                "  <tbody>" +
                "    <tr>" +
                "      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:15px 20px;font-family:'Lato',sans-serif;\" align=\"left\">" +
                "        " +
                "  <div style=\"color: #303030; line-height: 120%; text-align: left; word-wrap: break-word;\">" +
                "    <p style=\"font-size: 14px; line-height: 120%;\">" + vehicle + "</p>" +
                "  </div>" +
                "" +
                "      </td>" +
                "    </tr>" +
                "  </tbody>" +
                "</table>" +
                "" +
                "  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->" +
                "  </div>" +
                "</div>" +
                "<!--[if (mso)|(IE)]></td><![endif]-->" +
                "      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->" +
                "    </div>" +
                "  </div>" +
                "</div>" +
                "" +
                "" +
                "" +
                "<div class=\"u-row-container\" style=\"padding: 0px 10px 7px;background-color: rgba(255,255,255,0)\">" +
                "  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\">" +
                "    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">" +
                "      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px 10px 7px;background-color: rgba(255,255,255,0);\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: transparent;\"><![endif]-->" +
                "      " +
                "<!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->" +
                "<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">" +
                "  <div style=\"width: 100% !important;\">" +
                "  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\"><!--<![endif]-->" +
                "  " +
                "<table style=\"font-family:'Lato',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">" +
                "  <tbody>" +
                "    <tr>" +
                "      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:0px;font-family:'Lato',sans-serif;\" align=\"left\">" +
                "        " +
                "  <table height=\"0px\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;border-top: 1px dotted #CCC;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">" +
                "    <tbody>" +
                "      <tr style=\"vertical-align: top\">" +
                "        <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;font-size: 0px;line-height: 0px;mso-line-height-rule: exactly;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">" +
                "          <span> </span>" +
                "        </td>" +
                "      </tr>" +
                "    </tbody>" +
                "  </table>" +
                "" +
                "      </td>" +
                "    </tr>" +
                "  </tbody>" +
                "</table>" +
                "" +
                "  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->" +
                "  </div>" +
                "</div>" +
                "<!--[if (mso)|(IE)]></td><![endif]-->" +
                "      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->" +
                "    </div>" +
                "  </div>" +
                "</div>" +
                "" +
                "" +
                "" +
                "<div class=\"u-row-container\" style=\"padding: 0px 10px;background-color: rgba(255,255,255,0)\">" +
                "  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\">" +
                "    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">" +
                "      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px 10px;background-color: rgba(255,255,255,0);\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: transparent;\"><![endif]-->" +
                "      " +
                "<!--[if (mso)|(IE)]><td align=\"center\" width=\"400\" style=\"width: 400px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->" +
                "<div class=\"u-col u-col-66p67\" style=\"max-width: 320px;min-width: 400px;display: table-cell;vertical-align: top;\">" +
                "  <div style=\"width: 100% !important;\">" +
                "  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\"><!--<![endif]-->" +
                "  " +
                "<table style=\"font-family:'Lato',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">" +
                "  <tbody>" +
                "    <tr>" +
                "      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 20px;font-family:'Lato',sans-serif;\" align=\"left\">" +
                "        " +
                "  <div style=\"color: #000; line-height: 120%; text-align: left; word-wrap: break-word;\">" +
                "    <p style=\"font-size: 14px; line-height: 120%;\"><strong>Location</strong></p>" +
                "  </div>" +
                "" +
                "      </td>" +
                "    </tr>" +
                "  </tbody>" +
                "</table>" +
                "" +
                "  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->" +
                "  </div>" +
                "</div>" +
                "<!--[if (mso)|(IE)]></td><![endif]-->" +
                "<!--[if (mso)|(IE)]><td align=\"center\" width=\"200\" style=\"width: 200px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->" +
                "<div class=\"u-col u-col-33p33\" style=\"max-width: 320px;min-width: 200px;display: table-cell;vertical-align: top;\">" +
                "  <div style=\"width: 100% !important;\">" +
                "  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\"><!--<![endif]-->" +
                "  " +
                "<table style=\"font-family:'Lato',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">" +
                "  <tbody>" +
                "    <tr>" +
                "      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 20px;font-family:'Lato',sans-serif;\" align=\"left\">" +
                "        " +
                "  <div style=\"color: #000; line-height: 120%; text-align: left; word-wrap: break-word;\">" +
                "    <p style=\"font-size: 14px; line-height: 120%;\">" + location + "</p>" +
                "  </div>" +
                "" +
                "      </td>" +
                "    </tr>" +
                "  </tbody>" +
                "</table>" +
                "" +
                "  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->" +
                "  </div>" +
                "</div>" +
                "<!--[if (mso)|(IE)]></td><![endif]-->" +
                "      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->" +
                "    </div>" +
                "  </div>" +
                "</div>" +
                "" +
                "" +
                "" +
                "<div class=\"u-row-container\" style=\"padding: 0px 10px;background-color: rgba(255,255,255,0)\">" +
                "  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\">" +
                "    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">" +
                "      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px 10px;background-color: rgba(255,255,255,0);\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: transparent;\"><![endif]-->" +
                "      " +
                "<!--[if (mso)|(IE)]><td align=\"center\" width=\"400\" style=\"width: 400px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->" +
                "<div class=\"u-col u-col-66p67\" style=\"max-width: 320px;min-width: 400px;display: table-cell;vertical-align: top;\">" +
                "  <div style=\"width: 100% !important;\">" +
                "  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\"><!--<![endif]-->" +
                "  " +
                "<table style=\"font-family:'Lato',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">" +
                "  <tbody>" +
                "    <tr>" +
                "      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 20px;font-family:'Lato',sans-serif;\" align=\"left\">" +
                "        " +
                "  <div style=\"color: #000; line-height: 120%; text-align: left; word-wrap: break-word;\">" +
                "    <p style=\"font-size: 14px; line-height: 120%;\"><strong>Terminal</strong></p>" +
                "  </div>" +
                "" +
                "      </td>" +
                "    </tr>" +
                "  </tbody>" +
                "</table>" +
                "" +
                "  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->" +
                "  </div>" +
                "</div>" +
                "<!--[if (mso)|(IE)]></td><![endif]-->" +
                "<!--[if (mso)|(IE)]><td align=\"center\" width=\"200\" style=\"width: 200px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->" +
                "<div class=\"u-col u-col-33p33\" style=\"max-width: 320px;min-width: 200px;display: table-cell;vertical-align: top;\">" +
                "  <div style=\"width: 100% !important;\">" +
                "  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\"><!--<![endif]-->" +
                "  " +
                "<table style=\"font-family:'Lato',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">" +
                "  <tbody>" +
                "    <tr>" +
                "      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 20px;font-family:'Lato',sans-serif;\" align=\"left\">" +
                "        " +
                "  <div style=\"color: #000; line-height: 120%; text-align: left; word-wrap: break-word;\">" +
                "    <p style=\"font-size: 14px; line-height: 120%;\">" + terminal + "</p>" +
                "  </div>" +
                "" +
                "      </td>" +
                "    </tr>" +
                "  </tbody>" +
                "</table>" +
                "" +
                "  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->" +
                "  </div>" +
                "</div>" +
                "<!--[if (mso)|(IE)]></td><![endif]-->" +
                "      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->" +
                "    </div>" +
                "  </div>" +
                "</div>" +
                "" +
                "" +
                "" +
                "<div class=\"u-row-container\" style=\"padding: 0px 10px 5px;background-color: rgba(255,255,255,0)\">" +
                "  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\">" +
                "    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">" +
                "      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px 10px 5px;background-color: rgba(255,255,255,0);\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: transparent;\"><![endif]-->" +
                "      " +
                "<!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->" +
                "<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">" +
                "  <div style=\"width: 100% !important;\">" +
                "  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\"><!--<![endif]-->" +
                "  " +
                "<table style=\"font-family:'Lato',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">" +
                "  <tbody>" +
                "    <tr>" +
                "      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:0px;font-family:'Lato',sans-serif;\" align=\"left\">" +
                "        " +
                "  <table height=\"0px\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;border-top: 1px dotted #CCC;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">" +
                "    <tbody>" +
                "      <tr style=\"vertical-align: top\">" +
                "        <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;font-size: 0px;line-height: 0px;mso-line-height-rule: exactly;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">" +
                "          <span> </span>" +
                "        </td>" +
                "      </tr>" +
                "    </tbody>" +
                "  </table>" +
                "" +
                "      </td>" +
                "    </tr>" +
                "  </tbody>" +
                "</table>" +
                "" +
                "  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->" +
                "  </div>" +
                "</div>" +
                "<!--[if (mso)|(IE)]></td><![endif]-->" +
                "      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->" +
                "    </div>" +
                "  </div>" +
                "</div>" +
                "" +
                "" +
                "" +
                "<div class=\"u-row-container\" style=\"padding: 0px 0px 20px;background-color: #ffffff\">" +
                "  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\">" +
                "    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">" +
                "      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px 0px 20px;background-color: #ffffff;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: transparent;\"><![endif]-->" +
                "      " +
                "<!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->" +
                "<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">" +
                "  <div style=\"width: 100% !important;\">" +
                "  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\"><!--<![endif]-->" +
                "  " +
                "<table style=\"font-family:'Lato',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">" +
                "  <tbody>" +
                "    <tr>" +
                "      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:20px;font-family:'Lato',sans-serif;\" align=\"left\">" +
                "        " +
                "  <div style=\"color: #7d7d7d; line-height: 140%; text-align: center; word-wrap: break-word;\">" +
                "    <p style=\"font-size: 14px; line-height: 140%;\"><span style=\"font-size: 12px; line-height: 16.8px;\">Copyright © 2017  chazool. All rights reserved. </span></p>" +
                "  </div>" +
                "" +
                "      </td>" +
                "    </tr>" +
                "  </tbody>" +
                "</table>" +
                "" +
                "  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->" +
                "  </div>" +
                "</div>" +
                "<!--[if (mso)|(IE)]></td><![endif]-->" +
                "      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->" +
                "    </div>" +
                "  </div>" +
                "</div>" +
                "    <!--[if (mso)|(IE)]></td></tr></table><![endif]-->" +
                "    </td>" +
                "  </tr>" +
                "  </tbody>" +
                "  </table>" +
                "  <!--[if mso]></div><![endif]-->" +
                "  <!--[if IE]></div><![endif]-->" +
                "</body>" +
                "" +
                "</html>";

        return html;
    }


}
