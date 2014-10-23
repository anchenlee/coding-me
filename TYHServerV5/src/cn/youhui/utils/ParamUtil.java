package cn.youhui.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;


public final class ParamUtil {

    private static DateFormat dateFormat = new SimpleDateFormat ("dd/MM/yyyy");

    public static String getServer(HttpServletRequest request) {
        
        StringBuffer server = new StringBuffer(128);
        String scheme = request.getScheme();
        int port = request.getServerPort();
        if (port < 0) {
            port = 80; // Work around java.net.URL bug
        }
        server.append(scheme);
        server.append ("://");
        server.append(request.getServerName());
        if ( (scheme.equals("http") && (port != 80))
            || (scheme.equals("https") && (port != 443)) ) {
            server.append(':');
            server.append(port);
        }
        return server.toString();
    }

    public static String getServer2(HttpServletRequest request) {

        StringBuffer server = new StringBuffer(128);
        server.append(request.getScheme());
        server.append ("://");
        server.append(request.getHeader("host"));
        return server.toString();
    }

    public static String getParameter(HttpServletRequest request, String param) {

        String ret = request.getParameter(param);
        if (ret == null) ret = "";
        return ret.trim();
    }
    
    
    public static String getParameter(ServletRequest request, String param) {

        String ret = request.getParameter(param);
        if (ret == null) ret = "";
        return ret.trim();
    }


    public static String getParameter(HttpServletRequest request, String param, boolean checkEmpty){

        String ret = request.getParameter(param);
        if (ret == null) ret = "";
//        ret = ret.trim();    //加密字符串的“ ”可能会被删除，导致数据不对
        if ( checkEmpty && (ret.length() == 0) ) {
        	throw new BadParameterException(param +" can not be null!");
        }
        return ret;
    }


    public static String getParameterSafe(HttpServletRequest request, String param, boolean checkEmpty){
        String ret = getParameter(request, param, checkEmpty);
        if ( (ret.indexOf('<') != -1) ||(ret.indexOf('>') != -1)) {
        	throw new BadParameterException("param contains '<' or '>'");
        }
        return ret;
    }

    public static int getParameterInt(HttpServletRequest request, String param) throws BadParameterException{

        String inputStr = getParameter(request, param, true);
        int ret;
        try {
            ret = Integer.parseInt(inputStr);
        } catch (NumberFormatException e) {
            throw new BadParameterException(e);
        }
        return ret;
    }

    public static int getParameterUnsignedInt(HttpServletRequest request, String param)throws BadParameterException{

        int retValue = getParameterInt(request, param);
        if (retValue < 0) {
            throw new BadParameterException("the parameter must > 0.");
        }
        return retValue;
    }

    public static int getParameterInt(HttpServletRequest request, String param, int defaultValue)throws BadParameterException{

        String inputStr = getParameter(request, param, false);
        if (inputStr.length() == 0) {
            return defaultValue;
        }
        int ret;
        try {
            ret = Integer.parseInt(inputStr);
        } catch (NumberFormatException e) {
        	throw new BadParameterException(e);
        }
        return ret;
    }

    public static int getParameterUnsignedInt(HttpServletRequest request, String param, int defaultValue) throws BadParameterException{

        int retValue = getParameterInt(request, param, defaultValue);
        if (retValue < 0) {
        	throw new BadParameterException("the parameter must > 0.");
        }
        return retValue;
    }

    public static long getParameterLong(HttpServletRequest request, String param) throws BadParameterException{

        String inputStr = getParameter(request, param, true);
        long ret;
        try {
            ret = Long.parseLong(inputStr);
        } catch (NumberFormatException e) {
        	throw new BadParameterException(e);
        }
        return ret;
    }

    public static long getParameterLong(HttpServletRequest request, String param, long defaultValue) throws BadParameterException{
        String inputStr = getParameter(request, param, false);
        if (inputStr.length() == 0) {
            return defaultValue;
        }
        long ret;
        try {
            ret = Long.parseLong(inputStr);
        } catch (NumberFormatException e) {
            throw new BadParameterException(e);
        }
        return ret;
    }

    /**
     * @param  param is the name of variable
     * @return true if the value of param is not empty
     */
    public static boolean getParameterBoolean(HttpServletRequest request, String param) {

        String inputStr = getParameter(request, param);
        if("true".equals(inputStr))
        	return true;
        else return false;
    }

    public static byte getParameterByte(HttpServletRequest request, String param) throws BadParameterException{
        String inputStr = getParameter(request, param, true);
        byte ret;
        try {
            ret = Byte.parseByte(inputStr);
        } catch (NumberFormatException e) {
        	 throw new BadParameterException(e);
        }
        return ret;
    }

    public static double getParameterDouble(HttpServletRequest request, String param) throws BadParameterException{

        String inputStr = getParameter(request, param, true);
        double ret;
        try {
            ret = Double.parseDouble(inputStr);
        } catch (NumberFormatException e) {
            throw new BadParameterException(e);
        }
        return ret;
    } 

    public static double getParameterDouble(HttpServletRequest request, String param, double defaultValue) throws BadParameterException{

        String inputStr = getParameter(request, param, false);
        if (inputStr.length() == 0) {
            return defaultValue;
        }
        double ret;
        try {
            ret = Double.parseDouble(inputStr);
        } catch (NumberFormatException e) {
            throw new BadParameterException(e);
        }
        return ret;
    }
    
    public static String getParameterUrl(HttpServletRequest request, String param) throws BadParameterException{

        String ret = getParameter(request, param);
        if ( ret.length() > 0 ) {
            if ( !ret.startsWith("http://") && !ret.startsWith("https://") && !ret.startsWith("ftp://") ) {
                throw new BadParameterException("the url is wrong.");
            }
        }
        return ret;
    }

    public static String getParameterPassword(HttpServletRequest request, String param, int minLength) throws BadParameterException{

        if (minLength < 1) minLength = 1;
        String ret = request.getParameter(param);
        if (ret == null) ret = "";
        ret = ret.replaceAll(" +", "");
        if ( ret.length() < minLength ) {
            throw new BadParameterException("the password is short.");
        }

        return ret;
    }

    public static Date getParameterDate(HttpServletRequest request, String param)
        throws BadParameterException{

        String inputStr = getParameter(request, param, true);
        Date ret;
        try {
            ret = dateFormat.parse(inputStr);
        } catch (java.text.ParseException e) {
            throw new BadParameterException(e);
        }
        return ret;
    }

    public static java.sql.Date getParameterDate(HttpServletRequest request, String paramDay, String paramMonth, String paramYear) throws BadParameterException{

        int day = getParameterInt(request, paramDay);
        int month = getParameterInt(request, paramMonth);
        int year = getParameterInt(request, paramYear);
        StringBuffer buffer = new StringBuffer();
        buffer.append(day).append("/").append(month).append("/").append(year);
        String inputStr = buffer.toString();

        java.util.Date ret;
        try {
            ret = dateFormat.parse(inputStr);
        } catch (java.text.ParseException e) {
            throw new BadParameterException(e);
        }
        return new java.sql.Date(ret.getTime());
    }

}