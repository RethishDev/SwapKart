package com.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailOtpService {

    private final SendGrid sendGrid;
    private final Random random = new Random();

    @Value("${spring.sendgrid.from-email}")
    private String fromEmail;

    public String sendOtp(String toEmail, String username) throws IOException {
        String otp = String.format("%06d", random.nextInt(1000000));
        log.info("OTP generated for {}: {}", username, otp);

        String subject = "SwapKart OTP Verification for Registration";

        String htmlContent = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "  <head>\n" +
                "    <meta charset=\"UTF-8\" />\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\" />\n" +
                "    <title>Static Template</title>\n" +
                "\n" +
                "    <link\n" +
                "      href=\"https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600&display=swap\"\n" +
                "      rel=\"stylesheet\"\n" +
                "    />\n" +
                "  </head>\n" +
                "  <body\n" +
                "    style=\"\n" +
                "      margin: 0;\n" +
                "      font-family: 'Poppins', sans-serif;\n" +
                "      background: #ffffff;\n" +
                "      font-size: 14px;\n" +
                "    \"\n" +
                "  >\n" +
                "    <div\n" +
                "      style=\"\n" +
                "        max-width: 680px;\n" +
                "        margin: 0 auto;\n" +
                "        padding: 45px 30px 60px;\n" +
                "        background: #f4f7ff;\n" +
                "        background-image: url(https://archisketch-resources.s3.ap-northeast-2.amazonaws.com/vrstyler/1661497957196_595865/email-template-background-banner);\n" +
                "        background-repeat: no-repeat;\n" +
                "        background-size: 800px 452px;\n" +
                "        background-position: top center;\n" +
                "        font-size: 14px;\n" +
                "        color: #434343;\n" +
                "      \"\n" +
                "    >\n" +
                "      <header>\n" +
                "        <table style=\"width: 100%; border-collapse: collapse;\">\n" +
                "          <tbody>\n" +
                "            <tr style=\"height: 0;\">\n" +
                "              <td style=\"text-align: left; vertical-align: middle;\">\n" +
                "                <img\n" +
                "                 alt=\"Swapkart Logo\"\n" +
                "                 src=\"https://raw.githubusercontent.com/RethishDev/swapkart-assets/main/swapkart-logo.png\"\n" +
                "                 style=\"\n" +
                "                   display: block;\n" +
                "                   width: 100px;\n" +
                "                   max-width: 120px;\n" +
                "                   height: auto;\n" +
                "                   margin: 10px 0 10px 10px;\n" +
                "                 \"\n" +
                "                />\n" +
                "              </td>\n" +
                "              <td style=\"text-align: right;\">\n" +
                "                <span\n" +
                "                  style=\"font-size: 16px; line-height: 30px; color: #ffffff;\"\n" +
                "                  >Since 2025</span\n" +
                "                >\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          </tbody>\n" +
                "        </table>\n" +
                "      </header>\n" +
                "\n" +
                "      <main>\n" +
                "        <div\n" +
                "          style=\"\n" +
                "            margin: 0;\n" +
                "            margin-top: 70px;\n" +
                "            padding: 92px 30px 115px;\n" +
                "            background: #ffffff;\n" +
                "            border-radius: 30px;\n" +
                "            text-align: center;\n" +
                "          \"\n" +
                "        >\n" +
                "          <div style=\"width: 100%; max-width: 489px; margin: 0 auto;\">\n" +
                "            <h1\n" +
                "              style=\"\n" +
                "                margin: 0;\n" +
                "                font-size: 24px;\n" +
                "                font-weight: 500;\n" +
                "                color: #1f1f1f;\n" +
                "              \"\n" +
                "            >\n" +
                "              Your OTP\n" +
                "            </h1>\n" +
                "            <p\n" +
                "              style=\"\n" +
                "                margin: 0;\n" +
                "                margin-top: 17px;\n" +
                "                font-size: 16px;\n" +
                "                font-weight: 500;\n" +
                "              \"\n" +
                "            >\n" +
                "              Hey " + username + ",\n" +
                "            </p>\n" +
                "            <p\n" +
                "              style=\"\n" +
                "                margin: 0;\n" +
                "                margin-top: 17px;\n" +
                "                font-weight: 500;\n" +
                "                letter-spacing: 0.56px;\n" +
                "              \"\n" +
                "            >\n" +
                "              Thank you for choosing SwapKart. Use the following OTP\n" +
                "              to complete the procedure to verify your email address. OTP is\n" +
                "              valid for\n" +
                "              <span style=\"font-weight: 600; color: #1f1f1f;\">5 minutes</span>.\n" +
                "              Do not share this code with others, including SwapKart\n" +
                "              employees.\n" +
                "            </p>\n" +
                "            <p\n" +
                "              style=\"\n" +
                "                margin: 0;\n" +
                "                margin-top: 60px;\n" +
                "                font-size: 40px;\n" +
                "                font-weight: 600;\n" +
                "                letter-spacing: 25px;\n" +
                "                color: #ba3d4f;\n" +
                "              \"\n" +
                "            >\n" +
                "              " + otp + "\n" +
                "            </p>\n" +
                "          </div>\n" +
                "        </div>\n" +
                "\n" +
                "        <p\n" +
                "          style=\"\n" +
                "            max-width: 400px;\n" +
                "            margin: 0 auto;\n" +
                "            margin-top: 90px;\n" +
                "            text-align: center;\n" +
                "            font-weight: 500;\n" +
                "            color: #8c8c8c;\n" +
                "          \"\n" +
                "        >\n" +
                "          Need help? Ask at\n" +
                "          <a\n" +
                "            href=\"mailto:swapkart.official2025@gmail.com\"\n" +
                "            style=\"color: #499fb6; text-decoration: none;\"\n" +
                "            >swapkart.official2025@gmail.com</a\n" +
                "          >\n" +
                "        </p>\n" +
                "      </main>\n" +
                "\n" +
                "      <footer\n" +
                "        style=\"\n" +
                "          width: 100%;\n" +
                "          max-width: 490px;\n" +
                "          margin: 20px auto 0;\n" +
                "          text-align: center;\n" +
                "          border-top: 1px solid #e6ebf1;\n" +
                "        \"\n" +
                "      >\n" +
                "        <p\n" +
                "          style=\"\n" +
                "            margin: 0;\n" +
                "            margin-top: 40px;\n" +
                "            font-size: 16px;\n" +
                "            font-weight: 600;\n" +
                "            color: #434343;\n" +
                "          \"\n" +
                "        >\n" +
                "          SwapKart Company\n" +
                "        </p>\n" +
                "        <p style=\"margin: 0; margin-top: 8px; color: #434343;\">\n" +
                "          Developed by Aiswarya Sunil\n" +
                "        </p>\n" +
                "        <p style=\"margin: 0; margin-top: 16px; color: #434343;\">\n" +
                "          Copyright © 2025 Company. All rights reserved.\n" +
                "        </p>\n" +
                "      </footer>\n" +
                "    </div>\n" +
                "  </body>\n" +
                "</html>\n";

        log.info("Email initiated....");
        // Building the SendGrid Mail Request Object
        Email from = new Email(fromEmail);
        Email to = new Email(toEmail);
        Content content = new Content("text/html", htmlContent); // Essential: flag it as text/html
        Mail mail = new Mail(from, subject, to, content);

        Request request = new Request();
        try {
            log.info("Sending Email....");
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            
            Response response = sendGrid.api(request);
            log.info("SendGrid API Response Status Code: {}", response.getStatusCode());
            
            if (response.getStatusCode() >= 400) {
                log.error("SendGrid failed to dispatch email. Body: {}", response.getBody());
                throw new IOException("Email delivery failure via SendGrid API");
            }
        } catch (IOException ex) {
            log.error("Exception thrown while connecting to SendGrid API", ex);
            throw ex;
        }

        return otp;
    }
}
