package dev.razafindratelo.tapakilaBackend.service.mailService;

import dev.razafindratelo.tapakilaBackend.entity.AccountActivation;
import dev.razafindratelo.tapakilaBackend.entity.User;
import dev.razafindratelo.tapakilaBackend.exception.NotImplementedException;

public class MailMessageHandler {

    public static String getEmailAccountActivation(AccountActivation accountActivation) {
        return
                """
                        <!DOCTYPE html>
                        <html lang="en">
                        <head>
                            <meta charset="UTF-8">
                            <meta name="viewport" content="width=device-width, initial-scale=1.0">
                            <title>Account Activation - Tapakila</title>
                            <style>
                                body {
                                    font-family: Arial, sans-serif;
                                    background: linear-gradient(135deg, #1e3a8a, #22c55e);
                                    color: #1e3a8a;
                                    text-align: center;
                                    padding: 20px;
                                }
                                .container {
                                    max-width: 600px;
                                    margin: auto;
                                    background: #ffffff;
                                    padding: 20px;
                                    border-radius: 10px;
                                    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
                                    border: 2px solid #1e3a8a;
                                }
                                .header {
                                    background: linear-gradient(135deg, #1e3a8a, #22c55e);
                                    padding: 20px;
                                    border-radius: 10px 10px 0 0;
                                    color: white;
                                }
                                .activation-code {
                                    font-size: 24px;
                                    font-weight: bold;
                                    background: #22c55e;
                                    color: white;
                                    padding: 10px 20px;
                                    border-radius: 5px;
                                    display: inline-block;
                                    letter-spacing: 2px;
                                    cursor: pointer;
                                    transition: background 0.3s, transform 0.2s;
                                }
                                .activation-code:hover {
                                    background: #1e3a8a;
                                    transform: scale(1.05);
                                }
                                .footer {
                                    margin-top: 20px;
                                    font-size: 14px;
                                    color: #6b7280;
                                }
                            </style>
                            <script>
                                function copyCode() {
                                    const code = document.getElementById("activationCode").innerText;
                                    navigator.clipboard.writeText(code).then(() => {
                                        alert("Activation code copied to clipboard!");
                                    });
                                }
                            </script>
                        </head>
                        <body>
                            <div class="container">
                                <div class="header">
                                    <h1 style="font-size: 28px; font-weight: bold;">Join Tapakila adventure</h1>
                                    <p style="color: #fff; font-weight: bold; font-size: 18px;">Activate Your Account Now!</p>
                                </div>
                                <p style="font-size: 16px; margin-top: 20px;">hello """ +
                                accountActivation.getUser().getUsername() +
                        """
                                </p>
                                <p style="font-size: 16px; margin-top: 20px;">Use the following activation code to verify your account:</p>
                                <p id="activationCode" class="activation-code" onclick="copyCode()">""" +

                                accountActivation.getActivationCode() +
                """
                                </p>
                                <p style="margin-top: 20px;">If you did not sign up for Tapakila, please ignore this email.</p>
                                <div class="footer">
                                    <p>&copy; 2025 Tapakila 3BEW. All rights reserved.</p>
                                </div>
                            </div>
                        </body>
                        </html>
                """;
    }

    public static String getEmailResetPassword(AccountActivation accountActivation) {
        return
                """
                        <!DOCTYPE html>
                        <html lang="en">
                        <head>
                            <meta charset="UTF-8">
                            <meta name="viewport" content="width=device-width, initial-scale=1.0">
                            <title>Password Reset - Tapakila</title>
                            <style>
                                body {
                                    font-family: Arial, sans-serif;
                                    background: linear-gradient(135deg, #1e3a8a, #22c55e);
                                    color: #1e3a8a;
                                    text-align: center;
                                    padding: 20px;
                                }
                                .container {
                                    max-width: 600px;
                                    margin: auto;
                                    background: #ffffff;
                                    padding: 20px;
                                    border-radius: 10px;
                                    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
                                    border: 2px solid #1e3a8a;
                                }
                                .header {
                                    background: linear-gradient(135deg, #1e3a8a, #22c55e);
                                    padding: 20px;
                                    border-radius: 10px 10px 0 0;
                                    color: white;
                                }
                                .reset-code {
                                    font-size: 24px;
                                    font-weight: bold;
                                    background: #22c55e;
                                    color: white;
                                    padding: 10px 20px;
                                    border-radius: 5px;
                                    display: inline-block;
                                    letter-spacing: 2px;
                                    cursor: pointer;
                                    transition: background 0.3s, transform 0.2s;
                                }
                                .reset-code:hover {
                                    background: #1e3a8a;
                                    transform: scale(1.05);
                                }
                                .footer {
                                    margin-top: 20px;
                                    font-size: 14px;
                                    color: #6b7280;
                                }
                            </style>
                            <script>
                                function copyCode() {
                                    const code = document.getElementById("resetCode").innerText;
                                    navigator.clipboard.writeText(code).then(() => {
                                        alert("Reset code copied to clipboard!");
                                    });
                                }
                            </script>
                        </head>
                        <body>
                            <div class="container">
                                <div class="header">
                                    <h1 style="font-size: 28px; font-weight: bold;">Reset Your Password</h1>
                                    <p style="color: #fff; font-weight: bold; font-size: 18px;">Enter the code below to reset your password</p>
                                </div>
                                <p style="font-size: 16px; margin-top: 20px;">Hello 
                                """ +
                                accountActivation.getUser().getUsername() +
                                """
                        "       </p>
                                <p style="font-size: 16px; margin-top: 20px;">
                                    You have send us request to change you reset your password. 
                                    Use the following reset code to proceed:</p>
                                <p id="resetCode" class="reset-code" onclick="copyCode()">
                                """ +
                                accountActivation.getActivationCode() +
                                """
                                </p>
                                <p style="margin-top: 20px;">If you did not request a password reset, please ignore this email.</p>
                                <div class="footer">
                                    <p>&copy; 2025 Tapakila 3BEW. All rights reserved.</p>
                                </div>
                            </div>
                        </body>
                        </html>
                """;
    }

    public static String getEMailReminderMessage(User user) {
        throw new NotImplementedException("getEMailFromUserReminderMessage not implemented yet");
    }
}
