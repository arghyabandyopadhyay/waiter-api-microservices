package com.businessgenie.otpservice.template;

public class EmailTemplate {
    public String template= """
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>OTP Confirmation</title>
                <style>
                    body {
                        font-family: Arial, sans-serif;
                        background-color: #f9f9f9;
                        color: #333;
                        margin: 0;
                        padding: 0;
                        display: flex;
                        align-items: center;
                        justify-content: center;
                        min-height: 100vh;
                    }
                    .container {
                        background-color: #ffffff;
                        padding: 20px;
                        max-width: 500px;
                        border-radius: 8px;
                        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                        text-align: center;
                    }
                    h3 {
                        color: #4A90E2;
                        margin-bottom: 10px;
                    }
                    .content {
                        font-size: 16px;
                        color: #555;
                        line-height: 1.6;
                        margin-bottom: 20px;
                    }
                    .otp {
                        font-size: 24px;
                        font-weight: bold;
                        color: #d9534f;
                        margin: 10px 0;
                    }
                    .footer {
                        font-size: 14px;
                        color: #888;
                        margin-top: 20px;
                    }
                </style>
            </head>
            <body>
                <div class="container">
                    <h3>Hi %s,</h3>
                    <p class="content">
                        Welcome to Waiterr! We are excited to have you on board. To complete your verification process, please enter the following One-Time Password (OTP) in the required field. This OTP is valid for the next 10 minutes.
                    </p>
                    <p>Your OTP Number is:</p>
                    <div class="otp">%s</div>
                    <p class="content">
                        Please remember to keep this OTP confidential and do not share it with anyone. If you did not request this OTP, please ignore this message or contact our support team for assistance.
                    </p>
                    <p class="footer">With Regards,<br>Waiterr Team</p>
                    <em>Note: This is a no-reply email.</em>
                </div>
            </body>
            </html>
                    """;
}

