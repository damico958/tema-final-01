package com.damico958.cloud_native.tema06;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(
        name = "Calculator",
        displayName = "Spring Calculator",
        description = "Calculator Servlet based on my Spring Calculator from Cloud Native's Assignment 01",
        urlPatterns = "/calculations"
)
public class CalculatorServlet extends HttpServlet {
    private static final char DOUBLE_QUOTE = '\u0022';

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        String bodyStyle = getStringWithDoubleQuoteAsHtml("style","background-color:DodgerBlue;color:white;text-align:center;");
        writer.println("<html><body " + bodyStyle + ">");
        writer.println("<h1> Welcome to our Calculator app!</h1>");
        writer.println("<h3> Let's test this servlet with some operations! </h3>");
        writer.println("<h3> Our valid operations are: add/sub/mul/div/pow </h3>");
        writer.println("<h3> Format of URI </h3>");
        writer.println("<h4> localhost:8080/calculator-app/calculations/op=insert-operation&x=insert-first-argument&y=insert-second-argument </h4>");

        Map<String, String[]> parametersMap = request.getParameterMap();
        String operation = parametersMap.get("op")[0];
        double firstArgument = Double.parseDouble(parametersMap.get("x")[0]);
        double secondArgument = Double.parseDouble(parametersMap.get("y")[0]);

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(CalculatorConfig.class);

        CalculatorService calculatorService = (CalculatorService) applicationContext.getBean("calculatorService");

        String result;

        switch(operation) {
            case "add":
                Operation addition = (Operation) applicationContext.getBean("addition");
                result = String.valueOf(calculatorService.execute(addition, firstArgument, secondArgument));
                break;
            case "sub":
                Operation subtraction = (Operation) applicationContext.getBean("subtraction");
                result = String.valueOf(calculatorService.execute(subtraction, firstArgument, secondArgument));
                break;
            case "mul":
                Operation multiplication = (Operation) applicationContext.getBean("multiplication");
                result = String.valueOf(calculatorService.execute(multiplication, firstArgument, secondArgument));
                break;
            case "div":
                Operation division = (Operation) applicationContext.getBean("division");
                result = String.valueOf(calculatorService.execute(division, firstArgument, secondArgument));
                break;
            case "pow":
                Operation exponentiation = (Operation) applicationContext.getBean("exponentiation");
                result = String.valueOf(calculatorService.execute(exponentiation, firstArgument, secondArgument));
                break;
            default:
                result = "<br> Fail! Something went wrong. Please, try again with a valid option!";
        }
        for(Operation operationInHistory : calculatorService.getCalculatorHistory()) {
            writer.println("<br><h3> " + operationInHistory + "</h3>");
        }
        writer.println("<h3> Result: " + result + "</h3>");

        printFormWithRadioButton(writer);

        writer.println("</body></html>");
    }

    private void printFormWithRadioButton(PrintWriter writer) {
        writer.println("<br> You can apply a new operation with our form below or by using our URI method again! Select the chosen operation and fill the argument fields!<br><br> <form>");
        writer.println("<input " + getStringWithDoubleQuoteAsHtml("type","radio") + " "
                + getStringWithDoubleQuoteAsHtml("id", "add") + " " +
                getStringWithDoubleQuoteAsHtml("name","op") + " " +
                getStringWithDoubleQuoteAsHtml("value", "add") + ">");
        writer.println("<label " + getStringWithDoubleQuoteAsHtml("for","add") + ">add</label>");

        writer.println("<input " + getStringWithDoubleQuoteAsHtml("type","radio") + " "
                + getStringWithDoubleQuoteAsHtml("id", "sub") + " " +
                getStringWithDoubleQuoteAsHtml("name","op") + " " +
                getStringWithDoubleQuoteAsHtml("value", "sub") + ">");
        writer.println("<label " + getStringWithDoubleQuoteAsHtml("for","sub") + ">sub</label>");

        writer.println("<input " + getStringWithDoubleQuoteAsHtml("type","radio") + " "
                + getStringWithDoubleQuoteAsHtml("id", "mul") + " " +
                getStringWithDoubleQuoteAsHtml("name","op") + " " +
                getStringWithDoubleQuoteAsHtml("value", "mul") + ">");
        writer.println("<label " + getStringWithDoubleQuoteAsHtml("for","mul") + ">mul</label>");

        writer.println("<input " + getStringWithDoubleQuoteAsHtml("type","radio") + " "
                + getStringWithDoubleQuoteAsHtml("id", "div") + " " +
                getStringWithDoubleQuoteAsHtml("name","op") + " " +
                getStringWithDoubleQuoteAsHtml("value", "div") + ">");
        writer.println("<label " + getStringWithDoubleQuoteAsHtml("for","div") + ">div</label>");

        writer.println("<input " + getStringWithDoubleQuoteAsHtml("type","radio") + " "
                + getStringWithDoubleQuoteAsHtml("id", "pow") + " " +
                getStringWithDoubleQuoteAsHtml("name","op") + " " +
                getStringWithDoubleQuoteAsHtml("value", "pow") + ">");
        writer.println("<label " + getStringWithDoubleQuoteAsHtml("for","pow") + ">pow</label><br>");

        writer.println("<label " + getStringWithDoubleQuoteAsHtml("for","x") + ">First Argument:</label><br>");
        writer.println("<input " + getStringWithDoubleQuoteAsHtml("type","text") + " "
                + getStringWithDoubleQuoteAsHtml("id", "x") + " " +
                getStringWithDoubleQuoteAsHtml("name","x") + "><br>");

        writer.println("<label " + getStringWithDoubleQuoteAsHtml("for","y") + ">Second Argument:</label><br>");
        writer.println("<input " + getStringWithDoubleQuoteAsHtml("type","text") + " "
                + getStringWithDoubleQuoteAsHtml("id", "y") + " " +
                getStringWithDoubleQuoteAsHtml("name","y") + "><br><br>");

        writer.println("<input " + getStringWithDoubleQuoteAsHtml("type", "submit") + " " + getStringWithDoubleQuoteAsHtml("value", "Submit") + ">");
        writer.println("</form>");
    }

    private String getStringWithDoubleQuoteAsHtml(String property, String value) {
        return property + "=" + DOUBLE_QUOTE + value + DOUBLE_QUOTE;
    }
}