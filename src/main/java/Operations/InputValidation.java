package Operations;

import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidation {
    public boolean inputValidation(JTextField textField){
        String exp = textField.getText();
        if(exp.equals(""))
            return false;
        Pattern pattern = Pattern.compile("[A-z&&[^^x]]");
        Matcher matcher = pattern.matcher(exp);
        if(matcher.find())
            return false;
        else
            return true;
    }
}
