package link.seeyouat.take2.util;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class EnvironmentVariableHelper {

    private static EnvironmentVariableHelper environmentVariableHelper = new EnvironmentVariableHelper();
    ConfigurableEnvironment _env;

    private EnvironmentVariableHelper(){
        // 컨테이너 생성
        GenericXmlApplicationContext ctx
                = new GenericXmlApplicationContext();

        // 환경변수 관리 객체 생성
        _env = ctx.getEnvironment();
    }

    public static EnvironmentVariableHelper getInstance() {
        if(environmentVariableHelper == null) {
            environmentVariableHelper = new EnvironmentVariableHelper();
        }
        return environmentVariableHelper;
    }

    public String getProperty(String parameter) {
        return _env.getProperty(parameter);
    }

}
