package com.example.EmployeeManagement.EmployeeManagement.CommonValidatons;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Common
{
    public boolean isPhoneValid(String phone) {
        if (phone.length() == 10) {
            Pattern p = Pattern.compile("[^0-9]");
            Matcher m = p.matcher(phone);
            if (m.find()) {
                return false;
            }
            else
            {
                return true;
            }
        }
        return true;
    }

    public boolean isEmailValidated(String email)
    {
        if(email.contains("@") && email.contains(".com"))
        {
            return true;
        }
        else
        {
            return true;
        }
    }

    public boolean isPinCodeValid(String pincode)
    {
        if (pincode.length() == 6) {
            Pattern p = Pattern.compile("[^0-9]");
            Matcher m = p.matcher(pincode);
            if (m.find()) {
                return false;
            }
            else
            {
                return true;
            }
        }
        return true;
    }
}
