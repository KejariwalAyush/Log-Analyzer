# Log Analyzer

An analysis tool for log files that extract, parses, and stores information
to easily filter out activities based on different parameters and the final result with all details.

## How to Use

- Locate the `LogAnalyzer.jar` file
- Open Terminal/Command Prompt at that location
- Check if Java is installed or not: `java --version` with version above 7 since some dependencies requires java 7 minimum
- Now, Run `java -jar LogAnalyzer.jar`
- It will ask for the log file path in txt format
- Thats it! It will locate the file parse it and stores the output as csv file named as `analyzedLogs.csv`

## Dependencies

- `opencsv-5.7.0` : To save details of logs in csv format

## Run Code

- Unzip `LogAnalyzer.zip`
- Locate `opencsv-5.7.0.jar` and add it to the classpath of the project.
- Settings.json for VSCode

```
{
    "java.project.sourcePaths": ["src"],
    "java.project.outputPath": "bin",
    "java.project.referencedLibraries": [
        "lib/**/*.jar",
        "opencsv-5.7.0.jar"
    ]
}
```

- For VS Code: Use `Ctrl+Shift+P -> Java: Configure ClassPath`
- Run the app directly or App.java inside src folder

NOTE: if you are running directly through code use full path to file, relative one may not work.

## Log Sample

```
2018-09-18 04:49:39,842 ERROR (default task-21)
IP-Address=157.49.141.133#,!User-Agent=Mozilla/5.0 (Windows NT 10.0; WOW64;Trident/7.0; rv:11.0) like
Gecko#,!X-Request-From=UIX#,!Request-Type=GET#,!API=/v2/developers#,!User-Login=test@demo.com#,!User-Name=testUser#,!EnterpriseId=2#,!EnterpriseName=Enterprise-2#,!Auth-Status=#,!Status-Code=200#,!Response-Time=1240#,!Request-Body=
```

## Log Details In our Output

- TimeStamp
- IP-Address
- User-Agent
- Request-Type
- API
- User-Login
- User-Name
- EnterpriseId
- EnterpriseName
- Status-Code
- Response-Time
