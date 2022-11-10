import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * LogGetter
 * 
 * parser class to get data from str to LogData Class
 */
public class LogGetter {

    /**
     * parses string into log data
     * 
     * @param str
     * @return LogData with all details about log
     */
    public static LogData getLogDetails(String str) {
        // ArrayList<String> val = new ArrayList<>();
        LogData log = new LogData();
        log.setTimeStamp(str.split(",")[0]);
        log.setIPAddress(getValue(getPatternString(str, "IP-Address=(.*?)#")));
        log.setRequestType(getValue(getPatternString(str, "Request-Type=(.*?)#")));
        log.setUserAgent(getValue(getPatternString(str, "User-Agent=(.*?)#")));
        log.setAPI(getValue(getPatternString(str, "API=(.*?)#")));
        log.setEnterpriseId(getValue(getPatternString(str, "EnterpriseId=(.*?)#")));
        log.setEnterpriseName(getValue(getPatternString(str, "EnterpriseName=(.*?)#")));
        log.setResponseTime(getValue(getPatternString(str, "Response-Time=(.*?)#")));
        log.setStatusCode(getValue(getPatternString(str, "Status-Code=(.*?)#")));
        log.setUserLogin(getValue(getPatternString(str, "User-Login=(.*?)#")));
        log.setUserName(getValue(getPatternString(str, "User-Name=(.*?)#")));

        // System.out.println(log);
        return log;

    }

    /**
     * returns value i.e., string between = and #
     * 
     * @param s
     * @return
     */
    private static String getValue(String s) {
        if (s.length() > 1)
            return s.substring(s.indexOf('=') + 1, s.length() - 1).trim();
        else
            return s.trim();
    }

    private static String getPatternString(String s, String regex) {
        // 1. create Pattern
        Pattern p = Pattern.compile(regex);

        // 2. generate Matcher
        Matcher m = p.matcher(s);

        // 3. find value using groups(int)
        if (m.find()) {
            return m.group(0);
        }
        return "";

    }
}
