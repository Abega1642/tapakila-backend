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
                            font-family: 'Arial', sans-serif;
                            background-color: #f7fafc;
                            margin: 0;
                            padding: 0;
                            color: #2d3748;
                        }
                        .container {
                            max-width: 600px;
                            margin: 20px auto;
                            background: #ffffff;
                            border-radius: 12px;
                            overflow: hidden;
                            box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1), 0 2px 4px -1px rgba(0, 0, 0, 0.06);
                        }
                        .header {
                            background: linear-gradient(135deg, #1e3a8a, #22c55e);
                            padding: 30px 20px;
                            text-align: center;
                            color: white;
                        }
                        .header h1 {
                            margin: 0;
                            font-size: 28px;
                            font-weight: bold;
                        }
                        .header p {
                            margin: 10px 0 0;
                            font-size: 18px;
                            font-weight: 500;
                        }
                        .content {
                            padding: 20px;
                            text-align: center;
                        }
                        .content p {
                            font-size: 16px;
                            line-height: 1.5;
                            color: #4a5568;
                        }
                        .activation-code {
                            font-size: 24px;
                            font-weight: bold;
                            background: #22c55e;
                            color: white;
                            padding: 15px 30px;
                            border-radius: 8px;
                            display: inline-block;
                            margin: 20px 0;
                            cursor: pointer;
                            transition: background 0.3s, transform 0.2s;
                        }
                        .activation-code:hover {
                            background: #1e3a8a;
                            transform: scale(1.05);
                        }
                        .footer {
                            padding: 20px;
                            background: #f7fafc;
                            text-align: center;
                            font-size: 14px;
                            color: #718096;
                        }
                        .footer p {
                            margin: 0;
                        }
                        .toast {
                            visibility: hidden;
                            min-width: 250px;
                            background-color: #333;
                            color: #fff;
                            text-align: center;
                            border-radius: 4px;
                            padding: 16px;
                            position: fixed;
                            z-index: 1;
                            bottom: 30px;
                            left: 50%;
                            transform: translateX(-50%);
                            font-size: 14px;
                            opacity: 0;
                            transition: opacity 0.5s, visibility 0.5s;
                        }
                        .toast.show {
                            visibility: visible;
                            opacity: 1;
                        }
                        @media (max-width: 600px) {
                            .container {
                                margin: 10px;
                            }
                            .header h1 {
                                font-size: 24px;
                            }
                            .header p {
                                font-size: 16px;
                            }
                            .activation-code {
                                font-size: 20px;
                                padding: 12px 24px;
                            }
                        }
                    </style>
                    <script>
                        function copyCode() {
                            const code = document.getElementById("activationCode").innerText;
                            navigator.clipboard.writeText(code).then(() => {
                                showToast("Activation code copied to clipboard!");
                            });
                        }

                        function showToast(message) {
                            const toast = document.getElementById("toast");
                            toast.innerText = message;
                            toast.classList.add("show");
                            setTimeout(() => {
                                toast.classList.remove("show");
                            }, 3000);
                        }
                    </script>
                </head>
                <body>
                    <div class="container">
                        <div class="header">
                            <h1>Welcome to Tapakila!</h1>
                            <p>Let's get your account activated</p>
                        </div>
                        <div class="content">
                            <p>Hi <strong>
                """
				+ accountActivation.getUser().getUsername() + 
				"""
							</strong>,</p>
							<p>We're excited to have you on board! To activate your account, simply click the code below to copy it:</p>
							<p id="activationCode" class="activation-code" onclick="copyCode()">
				""" 
				+ accountActivation.getActivationCode() + 
				"""
							</p>
							<p>Paste the code into the activation field in the app to complete your registration.</p>
							<p><strong>Note:</strong> This code will expire in 10 minutes, so don't wait too long!</p>
						</div>
						<div class="footer">
							<p>&copy; 2025 Tapakila 3BEW. All rights reserved.</p>
						</div>
					</div>
					 <div id="toast" class="toast"></div>
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
                            font-family: 'Arial', sans-serif;
                            background-color: #f7fafc;
                            margin: 0;
                            padding: 0;
                            color: #2d3748;
                        }
                        .container {
                            max-width: 600px;
                            margin: 20px auto;
                            background: #ffffff;
                            border-radius: 12px;
                            overflow: hidden;
                            box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1), 0 2px 4px -1px rgba(0, 0, 0, 0.06);
                        }
                        .header {
                            background: linear-gradient(135deg, #1e3a8a, #22c55e);
                            padding: 30px 20px;
                            text-align: center;
                            color: white;
                        }
                        .header h1 {
                            margin: 0;
                            font-size: 28px;
                            font-weight: bold;
                        }
                        .header p {
                            margin: 10px 0 0;
                            font-size: 18px;
                            font-weight: 500;
                        }
                        .content {
                            padding: 20px;
                            text-align: center;
                        }
                        .content p {
                            font-size: 16px;
                            line-height: 1.5;
                            color: #4a5568;
                        }
                        .reset-code {
                            font-size: 24px;
                            font-weight: bold;
                            background: #22c55e;
                            color: white;
                            padding: 15px 30px;
                            border-radius: 8px;
                            display: inline-block;
                            margin: 20px 0;
                            cursor: pointer;
                            transition: background 0.3s, transform 0.2s;
                        }
                        .reset-code:hover {
                            background: #1e3a8a;
                            transform: scale(1.05);
                        }
                        .footer {
                            padding: 20px;
                            background: #f7fafc;
                            text-align: center;
                            font-size: 14px;
                            color: #718096;
                        }
                        .footer p {
                            margin: 0;
                        }
                        .toast {
                            visibility: hidden;
                            min-width: 250px;
                            background-color: #333;
                            color: #fff;
                            text-align: center;
                            border-radius: 4px;
                            padding: 16px;
                            position: fixed;
                            z-index: 1;
                            bottom: 30px;
                            left: 50%;
                            transform: translateX(-50%);
                            font-size: 14px;
                            opacity: 0;
                            transition: opacity 0.5s, visibility 0.5s;
                        }
                        .toast.show {
                            visibility: visible;
                            opacity: 1;
                        }
                        @media (max-width: 600px) {
                            .container {
                                margin: 10px;
                            }
                            .header h1 {
                                font-size: 24px;
                            }
                            .header p {
                                font-size: 16px;
                            }
                            .reset-code {
                                font-size: 20px;
                                padding: 12px 24px;
                            }
                        }
                    </style>
                    <script>
                        function copyCode() {
                            const code = document.getElementById("resetCode").innerText;
                            navigator.clipboard.writeText(code).then(() => {
                                showToast("Reset code copied to clipboard!");
                            });
                        }

                        function showToast(message) {
                            const toast = document.getElementById("toast");
                            toast.innerText = message;
                            toast.classList.add("show");
                            setTimeout(() => {
                                toast.classList.remove("show");
                            }, 3000);
                        }
                    </script>
                </head>
                <body>
                    <div class="container">
                        <div class="header">
                            <h1>Reset Your Password</h1>
                            <p>Let's get you back into your account</p>
                        </div>
                        <div class="content">
                            <p>Hi <strong>
                """
						+ accountActivation.getUser().getUsername() +
				"""
							</strong>,</p>
							<p>We received a request to reset your Tapakila password. Click the code below to copy it:</p>
							<p id="resetCode" class="reset-code" onclick="copyCode()">
				""" 
					+ accountActivation.getActivationCode() +
				"""
							</p>
							 <p>Paste the code into the password reset field in the app to continue.</p>
							 <p><strong>Note:</strong> This code will expire in 10 minutes, so act fast!</p>
							 <p>If you didn't request a password reset, you can safely ignore this email.</p>
						</div>
						<div class="footer">
							<p>&copy; 2025 Tapakila 3BEW. All rights reserved.</p>
						</div>
					</div>
					<div id="toast" class="toast"></div>
					</body>
				</html>
				""";
    }

    public static String getEMailReminderMessage(User user) {
        throw new NotImplementedException("getEMailFromUserReminderMessage not implemented yet");
    }
}
