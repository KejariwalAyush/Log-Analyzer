public class LogData {
    private String TimeStamp;
    private String IPAddress;
    private String UserAgent;
    private String RequestType;
    private String API;
    private String StatusCode;
    private String UserName;
    private String UserLogin;
    private String EnterpriseId;
    private String EnterpriseName;
    private String ResponseTime;

    public void setTimeStamp(String timeStamp) {
        TimeStamp = timeStamp;
    }

    public void setIPAddress(String ipAddress) {
        IPAddress = ipAddress;
    }

    public void setUserAgent(String userAgent) {
        UserAgent = userAgent;
    }

    public void setRequestType(String requestType) {
        RequestType = requestType;
    }

    public void setAPI(String api) {
        API = api;
    }

    public void setStatusCode(String statusCode) {
        StatusCode = statusCode;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void setUserLogin(String userLogin) {
        UserLogin = userLogin;
    }

    public void setEnterpriseId(String enterpriseId) {
        EnterpriseId = enterpriseId;
    }

    public void setEnterpriseName(String enterpriseName) {
        EnterpriseName = enterpriseName;
    }

    public void setResponseTime(String responseTime) {
        ResponseTime = responseTime;
    }

    @Override
    public String toString() {
        return "LogData [TimeStamp=" + TimeStamp + ", IPAddress=" + IPAddress + ", UserAgent=" + UserAgent
                + ", RequestType=" + RequestType + ", API=" + API + ", StatusCode=" + StatusCode + ", UserName="
                + UserName + ", UserLogin=" + UserLogin + ", EnterpriseId=" + EnterpriseId + ", EnterpriseName="
                + EnterpriseName + ", ResponseTime=" + ResponseTime + "]";
    }

    public static String[] header() {
        String[] headers = { "TimeStamp", "IPAddress", "UserAgent", "RequestType", "API", "StatusCode", "UserName",
                "UserLogin", "EnterpriseId", "EnterpriseName", "ResponseTime" };
        return headers;
    }

    public String[] getDataList() {
        String[] datas = { TimeStamp, IPAddress, UserAgent, RequestType, API, StatusCode, UserName,
                UserLogin, EnterpriseId, EnterpriseName, ResponseTime };
        return datas;
    }
}
