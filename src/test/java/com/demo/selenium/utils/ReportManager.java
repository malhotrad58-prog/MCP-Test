package com.demo.selenium.utils;

import org.testng.ITestResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ReportManager {
    private static final List<TestExecutionResult> TEST_RESULTS = new ArrayList<>();
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static synchronized void recordResult(ITestResult result) {
        String status = switch (result.getStatus()) {
            case ITestResult.SUCCESS -> "PASS";
            case ITestResult.FAILURE -> "FAIL";
            case ITestResult.SKIP -> "SKIP";
            default -> "UNKNOWN";
        };

        String message = status.equals("PASS")
                ? "Test executed successfully."
                : (result.getThrowable() != null ? result.getThrowable().getMessage() : "No details available");

        TEST_RESULTS.add(new TestExecutionResult(
                result.getMethod().getMethodName(),
                status,
                message,
                LocalDateTime.now().format(FORMATTER)
        ));
    }

    public static synchronized void generateHtmlReport() {
        Path reportDir = Paths.get("output", "html-reports");
        Path reportPath = reportDir.resolve("Test-Suite-Report.html");

        try {
            Files.createDirectories(reportDir);
            Files.writeString(reportPath, buildHtmlReport());
            System.out.println("HTML report generated at: " + reportPath.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Failed to generate HTML report: " + e.getMessage());
        }
    }

    private static String buildHtmlReport() {
        long pass = TEST_RESULTS.stream().filter(r -> "PASS".equals(r.status())).count();
        long fail = TEST_RESULTS.stream().filter(r -> "FAIL".equals(r.status())).count();
        long skip = TEST_RESULTS.stream().filter(r -> "SKIP".equals(r.status())).count();
        long total = TEST_RESULTS.size();

        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html>")
                .append("<html>")
                .append("<head>")
                .append("<meta charset='UTF-8'>")
                .append("<title>Combined Test Suite Report</title>")
                .append("<style>")
                .append("body { font-family: Arial, sans-serif; margin: 24px; background: #f3f4f6; color: #111827; }")
                .append("h1 { color: #111827; }")
                .append(".summary { display: flex; gap: 20px; margin: 20px 0; flex-wrap: wrap; }")
                .append(".card { background: white; border: 1px solid #d1d5db; border-radius: 8px; padding: 16px 20px; min-width: 150px; }")
                .append(".label { font-size: 12px; color: #6b7280; }")
                .append(".value { font-size: 28px; font-weight: bold; margin-top: 6px; }")
                .append("table { border-collapse: collapse; width: 100%; background: white; margin-top: 20px; }")
                .append("th, td { border: 1px solid #d1d5db; padding: 10px; text-align: left; }")
                .append("th { background: #e5e7eb; }")
                .append(".PASS { color: green; font-weight: bold; }")
                .append(".FAIL { color: red; font-weight: bold; }")
                .append(".SKIP { color: orange; font-weight: bold; }")
                .append("</style>")
                .append("</head>")
                .append("<body>")
                .append("<h1>Combined Test Suite Execution Report</h1>")
                .append("<div class='summary'>")
                .append("<div class='card'><div class='label'>Total Tests</div><div class='value'>").append(total).append("</div></div>")
                .append("<div class='card'><div class='label'>Passed</div><div class='value' style='color:green'>").append(pass).append("</div></div>")
                .append("<div class='card'><div class='label'>Failed</div><div class='value' style='color:red'>").append(fail).append("</div></div>")
                .append("<div class='card'><div class='label'>Skipped</div><div class='value' style='color:orange'>").append(skip).append("</div></div>")
                .append("</div>")
                .append("<table>")
                .append("<tr><th>Test Name</th><th>Status</th><th>Message</th><th>Timestamp</th></tr>");

        for (TestExecutionResult entry : TEST_RESULTS) {
            html.append("<tr>")
                    .append("<td>").append(entry.testName()).append("</td>")
                    .append("<td class='")
                    .append(entry.status())
                    .append("'>")
                    .append(entry.status())
                    .append("</td>")
                    .append("<td>").append(entry.message()).append("</td>")
                    .append("<td>").append(entry.timestamp()).append("</td>")
                    .append("</tr>");
        }

        html.append("</table>")
                .append("</body>")
                .append("</html>");

        return html.toString();
    }

    public record TestExecutionResult(String testName, String status, String message, String timestamp) {
    }
}
